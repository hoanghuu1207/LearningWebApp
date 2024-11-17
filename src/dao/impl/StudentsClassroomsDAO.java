package dao.impl;

import dao.DAOInterface;
import model.ClassMessageModel;
import model.StudentsClassroomsModel;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

public class StudentsClassroomsDAO implements DAOInterface<StudentsClassroomsModel> {
    @Override
    public int insert(StudentsClassroomsModel studentsClassroomsModel) {
        return 0;
    }

    @Override
    public int update(StudentsClassroomsModel studentsClassroomsModel) {
        return 0;
    }

    @Override
    public int delete(StudentsClassroomsModel studentsClassroomsModel) {
        return 0;
    }

    @Override
    public ArrayList<StudentsClassroomsModel> selectAll() {
        return null;
    }

    @Override
    public StudentsClassroomsModel selectById(int id) {
        return null;
    }

    public ArrayList<StudentsClassroomsModel> selectByClassroomID(int classroomID){
        ArrayList<StudentsClassroomsModel> studentsClassroomsModels = new ArrayList<>();
        String sql = " SELECT *" +
                " FROM students_classrooms AS sc" +
                " WHERE sc.classroomID = ?";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, classroomID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                StudentsClassroomsModel studentsClassroomsModel= new StudentsClassroomsModel();
                int studentId = rs.getInt("studentID");
                int classroomId = rs.getInt("classroomID");

                studentsClassroomsModel.setStudentID(studentId);
                studentsClassroomsModel.setClassroomID(classroomId);
                studentsClassroomsModels.add(studentsClassroomsModel);
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentsClassroomsModels;
    }
    public StudentsClassroomsModel selectTeacherByClassroomID(int classroomID){
        StudentsClassroomsModel studentsClassroomsModel = null;
        String sql = " SELECT cl.classroomID, cl.teacherID" +
                " FROM classrooms AS cl" +
                " WHERE cl.classroomID = ?;";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, classroomID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                studentsClassroomsModel= new StudentsClassroomsModel();

                int studentId = rs.getInt("teacherID");
                int classroomId = rs.getInt("classroomID");

                studentsClassroomsModel.setStudentID(studentId);
                studentsClassroomsModel.setClassroomID(classroomId);
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentsClassroomsModel;
    }
}
