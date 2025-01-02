package controller.clients;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import model.UserModel;
import service.I_UserService;
import service.impl.UserService;

@WebServlet(urlPatterns = { "/user/password/reset" })
public class UserResetPasswordController extends HttpServlet {
	private I_UserService userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispactcher = req.getRequestDispatcher("/views/clients/pages/user/reset_password.jsp");
		dispactcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		String password = req.getParameter("password");
		String tokenUser = null;

		Cookie cookie = null;

		Cookie[] cookies = req.getCookies();

		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				if (cookie.getName().equals("tokenUser"))
					tokenUser = cookie.getValue();
			}
		}

		if (tokenUser != null) {
			UserModel user = new UserModel();
			user.setTokenUser(tokenUser);

			String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));

			user.setPassword(hashedPassword);

			if (userService.resetPasswordUser(user) == false) {
				System.out.println("Thay doi mat khau that bai");
			} else {
				resp.sendRedirect("/home");
			}
		}
	}
}
