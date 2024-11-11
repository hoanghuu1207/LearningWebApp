package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DAOInterface;
import model.UserModel;
import util.JDBCUtil;

public class UserDAO implements DAOInterface<UserModel> {
	public static UserDAO getInstance() {
		return new UserDAO();
	}

	@Override
	public int insert(UserModel t) {
		int row = 0;
		try {
			Connection con = JDBCUtil.getConnection();

			String query = "INSERT INTO users(firstname, lastname, email, password, roleID, tokenUser) "
					+ "VALUES (?, ?, ?, ?, ?, ?)";

			PreparedStatement pstm = con.prepareStatement(query);

			pstm.setString(1, t.getFirstName());
			pstm.setString(2, t.getLastName());
			pstm.setString(3, t.getEmail());
			pstm.setString(4, t.getPassword());
			pstm.setInt(5, t.getRoleID());
			pstm.setString(6, t.getTokenUser());

			row = pstm.executeUpdate();

			if (row != 0) {
				System.out.println("Thêm thành công: " + row);
			}

			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}

	@Override
	public int update(UserModel t) {
		return 0;
	}

	@Override
	public int delete(UserModel t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<UserModel> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserModel selectById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public UserModel getUserByEmail(String email) {
		UserModel userModel = null;
		try {
			Connection con = JDBCUtil.getConnection();

			String query = "SELECT * FROM users WHERE email = ?";

			PreparedStatement pstm = con.prepareStatement(query);

			pstm.setString(1, email);

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				String firstName = rs.getString("firstname");
				String lastName = rs.getString("lastname");
				String password = rs.getString("password");
				int roleID = rs.getInt("roleID");
				String avatar = rs.getString("avatar");
				String tokenUser = rs.getString("tokenUser");

				userModel = new UserModel();

				userModel.setFirstName(firstName);
				userModel.setLastName(lastName);
				userModel.setPassword(password);
				userModel.setRoleID(roleID);
				userModel.setAvatar(avatar);
				userModel.setTokenUser(tokenUser);
			}

			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userModel;
	}

	public UserModel getUserByTokenUser(String tokenUser) {
		UserModel userModel = null;

		try {
			Connection con = JDBCUtil.getConnection();

			String query = "SELECT * FROM users WHERE tokenUser = ?";

			PreparedStatement pstm = con.prepareStatement(query);

			pstm.setString(1, tokenUser);

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				String firstName = rs.getString("firstname");
				String lastName = rs.getString("lastname");
				String password = rs.getString("password");
				int roleID = rs.getInt("roleID");
				String avatar = rs.getString("avatar");

				userModel = new UserModel();

				userModel.setFirstName(firstName);
				userModel.setLastName(lastName);
				userModel.setPassword(password);
				userModel.setRoleID(roleID);
				userModel.setAvatar(avatar);
				userModel.setTokenUser(tokenUser);
			}

			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userModel;
	}

	public int updatePasswordWithTokenUser(UserModel t) {
		int row = 0;
		try {
			Connection con = JDBCUtil.getConnection();

			String query = "UPDATE users SET password = ? WHERE tokenUser = ?";

			PreparedStatement pstm = con.prepareStatement(query);

			pstm.setString(1, t.getPassword());
			pstm.setString(2, t.getTokenUser());

			row = pstm.executeUpdate();

			if (row != 0) {
				System.out.println("Thêm thành công: " + row);
			}

			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}
	public ArrayList<UserModel> getTeachersByClassId(int classId) {
		ArrayList<UserModel> teachers = new ArrayList<>();

		//ArrayList<UserModel> teachers = new ArrayList<>();
//        String sql = "SELECT * FROM users c "
//        		+ "JOIN students_classrooms sc ON c.userID = sc.studentID"
//        		+ "WHERE roleID = '2' AND class_id = ?";
		String sql = "SELECT * FROM users c "
				+ "JOIN classrooms sc ON c.userID = sc.teacherID "
				+ "WHERE c.roleID = 2 AND sc.classroomID = ?";
		try (Connection conn = JDBCUtil.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, classId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int userid = rs.getInt("userID");
				String firstName = rs.getString("firstname");
				String lastName = rs.getString("lastname");
				String password = rs.getString("password");
				int roleID = rs.getInt("roleID");
				String avatar = rs.getString("avatar");
				String tokenUser = rs.getString("tokenUser");
				UserModel userModel = new UserModel();

				userModel.setUserID(userid);
				userModel.setFirstName(firstName);
				userModel.setLastName(lastName);
				userModel.setPassword(password);
				userModel.setRoleID(roleID);
				userModel.setAvatar(avatar);
				userModel.setTokenUser(tokenUser);
				teachers.add(userModel);
			}
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return teachers;
	}

	public ArrayList<UserModel> getStudentsByClassId(int classId) {
		ArrayList<UserModel> students = new ArrayList<>();
		String sql = "SELECT * FROM users c "
				+ "JOIN students_classrooms sc ON c.userID = sc.studentID "
				+ "WHERE c.roleID = 3 AND sc.classroomID = ?";

		try (Connection conn = JDBCUtil.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, classId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				UserModel userModel= new UserModel();
				int userid = rs.getInt("userID");
				String firstName = rs.getString("firstname");
				String lastName = rs.getString("lastname");
				String password = rs.getString("password");
				int roleID = rs.getInt("roleID");
				String avatar = rs.getString("avatar");
				String tokenUser = rs.getString("tokenUser");

				userModel.setUserID(userid);
				userModel.setFirstName(firstName);
				userModel.setLastName(lastName);
				userModel.setPassword(password);
				userModel.setRoleID(roleID);
				userModel.setAvatar(avatar);
				userModel.setTokenUser(tokenUser);
				students.add(userModel);
			}
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return students;
	}
}
