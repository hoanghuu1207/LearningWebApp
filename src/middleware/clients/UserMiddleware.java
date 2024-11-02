package middleware.clients;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.impl.UserDAO;
import model.UserModel;

@WebFilter(urlPatterns = { "/*" })
public class UserMiddleware implements Filter {
	private UserDAO userDao = new UserDAO();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

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

			if (user != null) {
				System.out.println("oke");
				httpRequest.setAttribute("user", user);
			}
		}

		System.out.println("Filter User");

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

}
