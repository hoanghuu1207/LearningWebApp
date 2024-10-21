package controller.clients;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import model.UserModel;
import service.I_UserService;
import service.impl.UserService;

@WebServlet(urlPatterns = { "/user/register" })
public class UserRegisterController extends HttpServlet {
	private I_UserService userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispactcher = req.getRequestDispatcher("/views/clients/pages/user/signup.jsp");
		dispactcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String role = req.getParameter("role");

		int roleID = 3;

		if (role.equals("teacher")) {
			roleID = 2;
		} else if (role.equals("student"))
			roleID = 3;

		// Tạo Salt và băm mật khẩu
		String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));

		UserModel user = new UserModel(firstName, lastName, email, hashedPassword, "", roleID);

		String referer = req.getHeader("Referer");

		if (userService.registerUser(user)) {
//			resp.sendRedirect("register-success.jsp");
			System.out.println("Dang ky thanh cong");
			resp.sendRedirect("/user/login");
		} else {
//			resp.sendRedirect("register-failed.jsp");
			System.out.println("Dang ky that bai");
			if (referer != null) {
				resp.sendRedirect(referer);
			} else {
				resp.sendRedirect("/user/register");
			}
		}
	}
}
