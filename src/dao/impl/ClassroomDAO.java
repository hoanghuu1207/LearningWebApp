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
		// TODO Auto-generated method stub
		return 0;
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
