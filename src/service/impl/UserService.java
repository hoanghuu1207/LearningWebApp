package service.impl;

import org.mindrot.jbcrypt.BCrypt;

import dao.impl.ForgotPasswordDAO;
import dao.impl.UserDAO;
import model.UserModel;
import service.I_UserService;

public class UserService implements I_UserService {
	private UserDAO userDao = new UserDAO();
	private ForgotPasswordDAO forgotPasswordDao = new ForgotPasswordDAO();

	@Override
	public boolean registerUser(UserModel user) {
		if (userDao.getUserByEmail(user.getEmail()) == null) {
			userDao.insert(user);

			return true;
		}

		System.out.println("Email da ton tai");
		return false;
	}

	@Override
	public UserModel loginUser(UserModel user) {
		UserModel userModel = userDao.getUserByEmail(user.getEmail());
		if (userModel == null) {
			System.out.println("Email khong ton tai");

			return userModel;
		}

		if (!BCrypt.checkpw(user.getPassword(), userModel.getPassword())) {
			System.out.println("Sai mat khau");

			return null;
		}

		return userModel;
	}

	@Override
	public UserModel emailForgotPassword(UserModel user) {
		UserModel userModel = userDao.getUserByEmail(user.getEmail());
		if (userModel == null) {
			System.out.println("Email khong ton tai");
		}
		return userModel;
	}

	@Override
	public UserModel otpPasswordUser(String email, String otp) {
		UserModel userModel = null;

		if (forgotPasswordDao.getForgotPasswordByEmailOtp(email, otp) == null) {
			System.out.println("OTP khong hop le");

			return null;
		}

		userModel = userDao.getUserByEmail(email);

		System.out.println("OTP hop le");

		return userModel;
	}

	@Override
	public boolean resetPasswordUser(UserModel user) {
		if (userDao.getUserByTokenUser(user.getTokenUser()) != null) {
			userDao.updatePasswordWithTokenUser(user);

			System.out.println("Thay doi mat khau thanh cong");

			return true;
		}

		System.out.println("Thay doi mat khau that bai");
		return false;
	}

}
