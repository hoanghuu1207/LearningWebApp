package dao.impl;

import dao.DAOInterface;
import model.AssignmentsModel;
import model.SubmissionsModel;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SubmissionDAO implements DAOInterface<SubmissionsModel> {
    @Override
    public int insert(SubmissionsModel submissionsModel) {
        return 0;
    }

    @Override
    public int update(SubmissionsModel submissionsModel) {
        return 0;
    }

    @Override
    public int delete(SubmissionsModel submissionsModel) {
        return 0;
    }

    @Override
    public ArrayList<SubmissionsModel> selectAll() {
        return null;
    }

    @Override
    public SubmissionsModel selectById(int id) {
        return null;
    }

    public SubmissionsModel selectByAssignmentAndStudent(int assignmentID, int studentID){
        SubmissionsModel submissionsModel = null;

        String sql = "SELECT *\n" +
                "FROM submissions AS s\n" +
                "JOIN materials AS m ON s.materialID = m.materialID\n" +
                "WHERE s.assignmentID = ? AND s.studentID = ?";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, assignmentID);
            statement.setInt(2, studentID);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                submissionsModel = new SubmissionsModel();
                submissionsModel.setSubmissionID(rs.getInt("submissionID"));
                submissionsModel.setAssignmentID(rs.getInt("assignmentID"));
                submissionsModel.setStudentID(rs.getInt("studentID"));
                submissionsModel.setSubmissionDate(rs.getTimestamp("submissionDate"));
                submissionsModel.setMaterialID(rs.getInt("materialID"));
                submissionsModel.setTitleFile(rs.getString("title"));
                submissionsModel.setFilePath(rs.getString("filePath"));
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return submissionsModel;
    }
}
