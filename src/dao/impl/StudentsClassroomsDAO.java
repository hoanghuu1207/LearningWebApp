package dao.impl;

import dao.DAOInterface;
import model.*;
import util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class StudentsClassroomsDAO implements DAOInterface<StudentsClassroomsModel> {
    @Override
    public int insert(StudentsClassroomsModel studentsClassroomsModel) {
        int row = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String query = "INSERT INTO students_classrooms(studentID, classroomID, createdAt) "
                    + "VALUES (?, ?, ?)";

            PreparedStatement pstm = con.prepareStatement(query);

            pstm.setInt(1, studentsClassroomsModel.getStudentID());
            pstm.setInt(2, studentsClassroomsModel.getClassroomID());

            java.util.Date date = new Date();
            pstm.setTimestamp(3, new Timestamp(date.getTime()));

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
    public int getIdAfterInsert(StudentsClassroomsModel studentsClassroomsModel) {
        int id = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String query = "INSERT INTO students_classrooms(studentID, classroomID, createdAt) "
                    + "VALUES (?, ?, ?)";

            PreparedStatement pstm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            pstm.setInt(1, studentsClassroomsModel.getStudentID());
            pstm.setInt(2, studentsClassroomsModel.getClassroomID());

            java.util.Date date = new Date();
            pstm.setTimestamp(3, new Timestamp(date.getTime()));

            int row = pstm.executeUpdate();

            if (row > 0) {
                try (ResultSet generatedKeys = pstm.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        System.out.println("Thêm thành công: " + row + ", ID: " + generatedId);

                        id = generatedId;
                    }
                }
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}
