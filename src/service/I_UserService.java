package service;

import model.UserModel;

public interface I_UserService {
	boolean registerUser(UserModel user);

	UserModel loginUser(UserModel user);

	UserModel emailForgotPassword(UserModel user);

	UserModel otpPasswordUser(String email, String otp);

	boolean resetPasswordUser(UserModel user);
}
