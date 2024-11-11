package service;

import model.UserModel;

import java.util.ArrayList;

public interface I_UserService {
	boolean registerUser(UserModel user);

	UserModel loginUser(UserModel user);

	UserModel emailForgotPassword(UserModel user);

	UserModel otpPasswordUser(String email, String otp);

	boolean resetPasswordUser(UserModel user);

	ArrayList<UserModel> getTeachersByClassId(int classId);

	ArrayList<UserModel> getStudentsByClassId(int classId);
}
