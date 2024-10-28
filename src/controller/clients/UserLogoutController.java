package controller.clients;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/user/logout" })
public class UserLogoutController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie[] cookies = req.getCookies();

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("tokenUser")) {
					cookie.setValue("");
					cookie.setPath("/");
					cookie.setMaxAge(0);
					resp.addCookie(cookie);

					String referer = req.getHeader("Referer");

					System.out.println("Dang xuat thanh cong");
					if (referer != null) {
						resp.sendRedirect(referer);
					} else {
						resp.sendRedirect("/user/home");
					}

					return;
				}
			}
		}
	}
}
