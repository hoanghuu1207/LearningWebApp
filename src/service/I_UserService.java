package service;

import model.UserModel;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface I_UserService {
	boolean registerUser(UserModel user);

	UserModel loginUser(UserModel user);

	UserModel emailForgotPassword(UserModel user);

	UserModel otpPasswordUser(String email, String otp);

	boolean resetPasswordUser(UserModel user);

	ArrayList<UserModel> getAllUsers();

	boolean updateUserToTeacher(int userId);

	boolean updateUserToStudent(int userId);

	ArrayList<UserModel> searchUsers(String inputText);

	ArrayList<UserModel> getTeachersByClassId(int classId);

	ArrayList<UserModel> getStudentsByClassId(int classId);

	ArrayList<UserModel> getStudentsOutofClassAndExceptTeacher(int classId);

	public String getUserNameById(int userId);
}
