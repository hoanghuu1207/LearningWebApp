package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DAOInterface;
import model.ClassroomsModel;
import model.UserModel;
import util.JDBCUtil;

public class ClassroomDAO implements DAOInterface<ClassroomsModel> {

	public ArrayList<ClassroomsModel> getClassroomsByStudentId(int studentId)
	{
		ArrayList<ClassroomsModel> classrooms = new ArrayList<>();
		String sql = "SELECT c.classroomID, c.title, c.teacherID " +
                 "FROM classrooms c " +
                 "JOIN students_classrooms sc ON c.classroomID = sc.classroomID " +
                 "WHERE sc.studentID = ?";
		try (Connection conn = JDBCUtil.getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql)) {

	            ps.setInt(1, studentId);
	            ResultSet rs = ps.executeQuery();

	            while (rs.next()) {
	                ClassroomsModel classroom = new ClassroomsModel();
	                classroom.setClassroomID(rs.getInt("classroomID"));
	                classroom.setTitle(rs.getString("title"));
	                classroom.setTeacherID(rs.getInt("teacherID"));
	                classrooms.add(classroom);
	            }
			JDBCUtil.closeConnection(conn);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }	        
	        return classrooms;		
	}
	public ArrayList<ClassroomsModel> getClassroomsByTeacherId(int teacherId) {
		ArrayList<ClassroomsModel> classrooms = new ArrayList<>();
		String sql = "SELECT * FROM classrooms WHERE teacherID = ?";

		try (Connection conn = JDBCUtil.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, teacherId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ClassroomsModel classroom = new ClassroomsModel();
				classroom.setClassroomID(rs.getInt("classroomID"));
				classroom.setTitle(rs.getString("title"));
				classroom.setTeacherID(rs.getInt("teacherID"));
				classrooms.add(classroom);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return classrooms;
	}
	@Override
	public int insert(ClassroomsModel t) {
		int row = 0;
		try {
			Connection con = JDBCUtil.getConnection();

			String query = "INSERT INTO classrooms(title, teacherID) "
					+ "VALUES (?, ?)";

			PreparedStatement pstm = con.prepareStatement(query);

			pstm.setString(1, t.getTitle());
			pstm.setInt(2, t.getTeacherID());

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
	public int update(ClassroomsModel t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(ClassroomsModel t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<ClassroomsModel> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClassroomsModel selectById(int id) {
		ClassroomsModel classroom = null;
		String sql = "SELECT * FROM classrooms WHERE classroomID = ?";
		try (Connection conn = JDBCUtil.getConnection();
	            PreparedStatement ps = conn.prepareStatement(sql)) {
	            ps.setInt(1, id);
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	                classroom = new ClassroomsModel();
	                classroom.setClassroomID(rs.getInt("classroomID"));
	                classroom.setTitle(rs.getString("title"));
	                classroom.setTeacherID(rs.getInt("teacherID"));
	            }
	            JDBCUtil.closeConnection(conn);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }	        
	        return classroom;	
	}
	public ClassroomsModel selectByIdAndStudent(int classroomID, int userID) {
		ClassroomsModel classroom = null;
		String sql = "SELECT * FROM ((students_classrooms AS sc " +
				"JOIN classrooms AS cl ON cl.classroomID = sc.classroomID) " +
				"JOIN users AS us ON us.userID = sc.studentID) " +
				"WHERE sc.studentID = ? AND cl.classroomID = ?;";
		try (Connection conn = JDBCUtil.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, userID);
			ps.setInt(2, classroomID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				classroom = new ClassroomsModel();
				classroom.setClassroomID(rs.getInt("classroomID"));
				classroom.setTitle(rs.getString("title"));
				classroom.setTeacherID(rs.getInt("teacherID"));
			}
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return classroom;
	}
	public ClassroomsModel selectByIdAndTeacher(int classroomID, int userID) {
		ClassroomsModel classroom = null;
		String sql = "SELECT * FROM classrooms AS cl " +
				"WHERE cl.classroomID = ? AND cl.teacherID = ?;";
		try (Connection conn = JDBCUtil.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, classroomID);
			ps.setInt(2, userID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				classroom = new ClassroomsModel();
				classroom.setClassroomID(rs.getInt("classroomID"));
				classroom.setTitle(rs.getString("title"));
				classroom.setTeacherID(rs.getInt("teacherID"));
			}
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return classroom;
	}
	
	public static void main(String[] args) {
        ClassroomDAO dao = new ClassroomDAO();
        ArrayList<ClassroomsModel> classrooms = dao.getClassroomsByStudentId(3); // studentID = 1 là ví dụ
        for (ClassroomsModel classroom : classrooms) {
            System.out.println("Classroom ID: " + classroom.getClassroomID());
            System.out.println("Title: " + classroom.getTitle());
            System.out.println("Teacher ID: " + classroom.getTeacherID());
        }
    }
}
