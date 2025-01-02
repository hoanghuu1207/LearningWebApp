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

@WebServlet(urlPatterns = { "/user/login" })
public class UserLoginController extends HttpServlet {
	private I_UserService userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispactcher = req.getRequestDispatcher("/views/clients/pages/user/login.jsp");
		dispactcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		UserModel user = new UserModel();
		user.setEmail(email);
		user.setPassword(password);

		UserModel userModel = userService.loginUser(user);

		String referer = req.getHeader("Referer");

		if (userModel == null) {
//			resp.sendRedirect("register-failed.jsp");
			System.out.println("Dang nhap that bai");
			if (referer != null) {
				resp.sendRedirect(referer);
			} else {
				resp.sendRedirect("/user/login");
			}

			return;
		} else {
//			resp.sendRedirect("register-failed.jsp");
			System.out.println("Dang nhap thanh cong");

			Cookie cookie = new Cookie("tokenUser", userModel.getTokenUser());
			Cookie role = new Cookie("role", userModel.getRoleID() + "");

			cookie.setPath("/");
			cookie.setMaxAge(1 * 60 * 60); // set coookie trong 1h
			role.setPath("/");
			role.setMaxAge(1 * 60 * 60);

			resp.addCookie(cookie);
			resp.addCookie(role);

			resp.sendRedirect("/home");
		}
	}
}
