package controller.clients;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.impl.ForgotPasswordDAO;
import helper.EmailSender;
import helper.Generate;
import model.ForgotPasswordModel;
import model.UserModel;
import service.I_UserService;
import service.impl.UserService;

@WebServlet(urlPatterns = { "/user/password/forgot" })
public class UserForgotPasswordController extends HttpServlet {
	private I_UserService userService = new UserService();
	private EmailSender emailSender = new EmailSender();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispactcher = req.getRequestDispatcher("/views/clients/pages/user/forgotten_password.jsp");
		dispactcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");

		UserModel user = new UserModel();
		user.setEmail(email);

		UserModel userModel = userService.emailForgotPassword(user);

		String referer = req.getHeader("Referer");

		if (userModel == null) {
//			resp.sendRedirect("register-failed.jsp");
			if (referer != null) {
				resp.sendRedirect(referer);
			} else {
				resp.sendRedirect("/user/password/forgot");
			}
			return;
		}

		// Tạo mã OTP
		String otp = new Generate().generateRandomOtp(6);

		ForgotPasswordModel forgotPasswordModel = new ForgotPasswordModel();
		forgotPasswordModel.setEmail(email);
		forgotPasswordModel.setOtp(otp);

		new ForgotPasswordDAO().insert(forgotPasswordModel);

		emailSender.sendOtpEmail(email, otp);

		resp.sendRedirect("/user/password/otp?email=" + email);
	}
}
