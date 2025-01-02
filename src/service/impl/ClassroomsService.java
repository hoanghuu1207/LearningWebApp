package service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dao.impl.ClassroomDAO;
import dao.impl.UserDAO;
import model.ClassroomsModel;
import model.StudentsClassroomsModel;
import service.I_ClassroomsService;
import util.JDBCUtil;

public class ClassroomsService implements I_ClassroomsService{
	private ClassroomDAO classroomDao = new ClassroomDAO();
	@Override
	public ArrayList<ClassroomsModel> getClassroomsByStudentId(int studentId) {	
		return classroomDao.getClassroomsByStudentId(studentId);
	}
	@Override
	public ClassroomsModel selectById(int id) {
		ClassroomsModel classroomsModel = null;
		String sql = "SELECT * " +
				"FROM classrooms AS c " +
				"WHERE c.classroomID = ?;";

		try (Connection conn = JDBCUtil.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				classroomsModel = new ClassroomsModel();

				int teacherID = rs.getInt("teacherID");
				String title = rs.getString("title");
				int classroomId = rs.getInt("classroomID");

				classroomsModel.setTeacherID(teacherID);
				classroomsModel.setClassroomID(classroomId);
				classroomsModel.setTitle(title);
			}
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return classroomsModel;
	}

	@Override
	public ArrayList<ClassroomsModel> getClassroomsByTeacherId(int teacherId) {
		return classroomDao.getClassroomsByTeacherId(teacherId);
	}

	@Override
	public int insertClassroom(ClassroomsModel classroomsModel) {
		return classroomDao.insert(classroomsModel);
	}

	public ClassroomsModel selectByIdAndStudentID(int classroomID, int userID) {
		return classroomDao.selectByIdAndStudent(classroomID, userID);
	}

	public ClassroomsModel selectByIdAndTeacherID(int classroomID, int userID) {
		return classroomDao.selectByIdAndTeacher(classroomID, userID);
	}
}
