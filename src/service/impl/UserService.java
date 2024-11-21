package service.impl;

import org.mindrot.jbcrypt.BCrypt;

import dao.impl.ForgotPasswordDAO;
import dao.impl.UserDAO;
import model.UserModel;
import service.I_UserService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	@Override
	public ArrayList<UserModel> getTeachersByClassId(int classId) {
		return userDao.getTeachersByClassId(classId);
	}

	@Override
	public ArrayList<UserModel> getAllUsers() {
		return userDao.selectAll();
	}

	@Override
	public boolean updateUserToTeacher(int userId) {
		return userDao.updateUserToTeacher(userId) != 0;
	}

	@Override
	public boolean updateUserToStudent(int userId) {
		return userDao.updateUserToStudent(userId) != 0;
	}

	@Override
	public ArrayList<UserModel> searchUsers(String inputText) {
		ArrayList<UserModel> userList = userDao.selectAll();
		ArrayList<UserModel> searchUserList = new ArrayList<>();

		String searchText = removeAccent(inputText).toLowerCase();
		Pattern pattern = Pattern.compile(searchText, Pattern.CASE_INSENSITIVE);

		for(UserModel user: userList){
			ArrayList<String> tmp = new ArrayList<>();
			String role = user.getRoleID() == 2 ? "Teacher" : "Student";
			tmp.add(role);
			tmp.add(user.getFirstName());
			tmp.add(user.getLastName());
			tmp.add(user.getEmail());

			for(String s: tmp){
				Matcher matcher = pattern.matcher(s);
				if(matcher.find()){
					searchUserList.add(user);
					break;
				}
			}
		}

		return searchUserList;
	}

	public String removeAccent(String text) {
		return text
			.replaceAll("[àáạảãâầấậẩẫăằắặẳẵ]", "a")
			.replaceAll("[èéẹẻẽêềếệểễ]", "e")
			.replaceAll("[ìíịỉĩ]", "i")
			.replaceAll("[òóọỏõôồốộổỗơờớợởỡ]", "o")
			.replaceAll("[ùúụủũưừứựửữ]", "u")
			.replaceAll("[ỳýỵỷỹ]", "y")
			.replaceAll("[đ]", "d");
	}
	@Override
	public ArrayList<UserModel> getStudentsByClassId(int classId) {
		return userDao.getStudentsByClassId(classId);
	}

	@Override
	public String getUserNameById(int userId) {
		return userDao.getUserNameById(userId);
	}
}
