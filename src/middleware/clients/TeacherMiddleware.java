package middleware.clients;

import dao.impl.UserDAO;
import model.UserModel;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = { "/teacher/*"})
public class TeacherMiddleware implements Filter {
    private UserDAO userDAO = new UserDAO();

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

        if (tokenUser.equals("")) {
            httpResponse.sendRedirect("/user/login");
            return;
        }

        System.out.println("Filter teacher");

        UserModel user = userDAO.getUserByTokenUser(tokenUser);

        if(user == null){
            httpResponse.sendRedirect("/user/login");
            return;
        }

        if(user.getRoleID() != 2){
            httpResponse.sendRedirect("/home");
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}
