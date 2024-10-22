package controller.clients;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserModel;
import service.I_UserService;
import service.impl.UserService;

@WebServlet(urlPatterns = { "/user/password/otp" })
public class UserOtpPasswordController extends HttpServlet {
	private I_UserService userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setAttribute("email", req.getParameter("email"));

		RequestDispatcher dispactcher = req.getRequestDispatcher("/views/clients/pages/user/otp_password.jsp");
		dispactcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String otp = req.getParameter("otp");

		UserModel user = userService.otpPasswordUser(email, otp);

		String referer = req.getHeader("Referer");

		if (user == null) {
//			resp.sendRedirect("failed.jsp");
			if (referer != null) {
				resp.sendRedirect(referer);
			} else {
				resp.sendRedirect("/user/password/forgot");
			}
			return;
		}

		Cookie cookie = new Cookie("tokenUser", user.getTokenUser());

		cookie.setPath("/");
		cookie.setMaxAge(120); // set coookie trong 12h

		resp.addCookie(cookie);

		resp.sendRedirect("/user/password/reset");
	}
}
