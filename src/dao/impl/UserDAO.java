package dao.impl;

import java.sql.*;
import java.util.ArrayList;

import dao.DAOInterface;
import model.SubNotificationModel;
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
		ArrayList<UserModel> userList = new ArrayList<>();
		try {
			Connection con = JDBCUtil.getConnection();

			String query = "SELECT * FROM users WHERE roleID = ? OR roleID = ?";

			PreparedStatement pstm = con.prepareStatement(query);

			pstm.setInt(1, 2);
			pstm.setInt(2, 3);

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("userID");
				String firstName = rs.getString("firstname");
				String lastName = rs.getString("lastname");
				String email = rs.getString("email");
				String password = rs.getString("password");
				int roleID = rs.getInt("roleID");
				String avatar = rs.getString("avatar");
				String tokenUser = rs.getString("tokenUser");

				UserModel userModel = new UserModel();

				userModel.setUserID(id);
				userModel.setFirstName(firstName);
				userModel.setLastName(lastName);
				userModel.setEmail(email);
				userModel.setPassword(password);
				userModel.setRoleID(roleID);
				userModel.setAvatar(avatar);
				userModel.setTokenUser(tokenUser);

				userList.add(userModel);
			}

			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

	@Override
	public UserModel selectById(int id) {
		UserModel userModel = null;
		try {
			Connection con = JDBCUtil.getConnection();

			String query = "SELECT * FROM users WHERE userID = ?";

			PreparedStatement pstm = con.prepareStatement(query);

			pstm.setInt(1, id);

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				int userID = rs.getInt("userID");
				String firstName = rs.getString("firstname");
				String lastName = rs.getString("lastname");
				String email = rs.getString("email");
				String password = rs.getString("password");
				int roleID = rs.getInt("roleID");
				String avatar = rs.getString("avatar");
				String tokenUser = rs.getString("tokenUser");

				userModel = new UserModel();

				userModel.setUserID(userID);
				userModel.setFirstName(firstName);
				userModel.setLastName(lastName);
				userModel.setEmail(email);
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
				int id = rs.getInt("userID");
				String firstName = rs.getString("firstname");
				String lastName = rs.getString("lastname");
				String password = rs.getString("password");
				int roleID = rs.getInt("roleID");
				String avatar = rs.getString("avatar");

				userModel = new UserModel();

				userModel.setUserID(id);
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

	public int updateUserToTeacher(int userId){
		int row = 0;
		try {
			Connection con = JDBCUtil.getConnection();

			String query = "UPDATE users SET roleID = ? WHERE userID = ?";

			PreparedStatement pstm = con.prepareStatement(query);

			pstm.setInt(1, 2);
			pstm.setInt(2, userId);

			row = pstm.executeUpdate();

			if (row != 0) {
				System.out.println("Cập nhật thành công: " + row);
			}

			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}

	public int updateUserToStudent(int userId){
		int row = 0;
		try {
			Connection con = JDBCUtil.getConnection();

			String query = "UPDATE users SET roleID = ? WHERE userID = ?";

			PreparedStatement pstm = con.prepareStatement(query);

			pstm.setInt(1, 3);
			pstm.setInt(2, userId);

			row = pstm.executeUpdate();

			if (row != 0) {
				System.out.println("Cập nhật thành công: " + row);
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
				String email = rs.getString("email");
				String password = rs.getString("password");
				int roleID = rs.getInt("roleID");
				String avatar = rs.getString("avatar");
				String tokenUser = rs.getString("tokenUser");
				UserModel userModel = new UserModel();

				userModel.setUserID(userid);
				userModel.setFirstName(firstName);
				userModel.setLastName(lastName);
				userModel.setEmail(email);
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
				UserModel userModel = new UserModel();
				int userid = rs.getInt("userID");
				String firstName = rs.getString("firstname");
				String lastName = rs.getString("lastname");
				String email = rs.getString("email");
				String password = rs.getString("password");
				int roleID = rs.getInt("roleID");
				String avatar = rs.getString("avatar");
				String tokenUser = rs.getString("tokenUser");

				userModel.setUserID(userid);
				userModel.setFirstName(firstName);
				userModel.setLastName(lastName);
				userModel.setEmail(email);
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
	public ArrayList<UserModel> getStudentsOutOfClassAndExceptTeacher(int classID) {
		ArrayList<UserModel> students = new ArrayList<>();
		String sql = "SELECT * FROM " +
				"users AS u " +
				"LEFT JOIN students_classrooms sc " +
				"ON u.userID = sc.studentID AND sc.classroomID = ? " +
				"WHERE sc.classroomID IS NULL AND u.roleID = 3;";

		try (Connection conn = JDBCUtil.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, classID);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				UserModel userModel = new UserModel();
				int userid = rs.getInt("userID");
				String firstName = rs.getString("firstname");
				String lastName = rs.getString("lastname");
				String email = rs.getString("email");
				String password = rs.getString("password");
				int roleID = rs.getInt("roleID");
				String avatar = rs.getString("avatar");
				String tokenUser = rs.getString("tokenUser");

				userModel.setUserID(userid);
				userModel.setFirstName(firstName);
				userModel.setLastName(lastName);
				userModel.setEmail(email);
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
	public SubNotificationModel getSubNotificationWithClassroom(int classroomID){
		SubNotificationModel subNotificationModel = null;

		try {
			Connection con = JDBCUtil.getConnection();

			String query = "SELECT * FROM classrooms AS c " +
					"JOIN users AS u ON c.teacherID = u.userID " +
					"WHERE c.classroomID = ?";

			PreparedStatement pstm = con.prepareStatement(query);

			pstm.setInt(1, classroomID);

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				String url = "#";
				String title = rs.getString("title");
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");

				subNotificationModel = new SubNotificationModel();

				subNotificationModel.setUrl(url);
				subNotificationModel.setContent(firstname + " " + lastname + " đã xóa bạn khỏi " + title);
			}

			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return subNotificationModel;
	}
	public String getUserNameById(int userId) {
		String userName = "Unknown User";
		String sql = "SELECT * FROM users WHERE userID = ?";

		try (Connection conn = JDBCUtil.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				userName = rs.getString("firstname") + " " + rs.getString("lastname");
			}
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userName;
	}
}
