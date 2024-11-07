package middleware.admin;

import java.io.IOException;

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

import dao.impl.UserDAO;
import model.UserModel;

@WebFilter(urlPatterns = { "/admin/*" })
public class AuthMiddleware implements Filter {
    private UserDAO userDao = new UserDAO();

    // them token, pass vao db; kiem tra neu duong dan ben usermiddleware la /admin/* thi return de authmiddleware ben day xu ly
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
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub

    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

}
