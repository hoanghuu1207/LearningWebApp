package middleware.clients;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.impl.NotificationDAO;
import dao.impl.UserDAO;
import model.SubNotificationModel;
import model.UserModel;

@WebFilter(urlPatterns = { "/*" })
public class UserMiddleware implements Filter {
	private UserDAO userDao = new UserDAO();
	private NotificationDAO notificationDAO = new NotificationDAO();

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		Cookie[] cookies = httpRequest.getCookies();

		String tokenUser = "";

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("tokenUser".equals(cookie.getName())) {
					tokenUser = cookie.getValue();
					break;
				}
			}
		}

		if (!tokenUser.equals("")) {

			UserModel user = userDao.getUserByTokenUser(tokenUser);
			System.out.println(user == null);
			System.out.println("id: " + user.getUserID());

			if (user != null) {
				httpRequest.setAttribute("user", user);

				ArrayList<SubNotificationModel> notificationModels = notificationDAO.selectNotificationWithUserid(user.getUserID());

				boolean status = false;

				String tmp = "";

				if(user.getRoleID() == 2){
					tmp = "/teacher";
				}

				for(int i = 0 ; i < notificationModels.size() ; i++){
					if(notificationModels.get(i).getStatus() == 0){
						status = true;
					}

					notificationModels.get(i).setUrl(tmp + notificationModels.get(i).getUrl());
				}

				httpRequest.setAttribute("notifications", notificationModels);
				httpRequest.setAttribute("status", status);
			}
		}

		System.out.println("Filter User");

		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
