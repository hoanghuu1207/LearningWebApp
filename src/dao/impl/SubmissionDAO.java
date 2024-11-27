package dao.impl;

import dao.DAOInterface;
import model.AssignmentsModel;
import model.SubmissionUI;
import model.SubmissionsModel;
import util.JDBCUtil;

import java.net.URLEncoder;
import java.sql.*;
import java.util.ArrayList;

public class SubmissionDAO implements DAOInterface<SubmissionsModel> {
    @Override
    public int insert(SubmissionsModel submissionsModel) {
        int row = 0;
        String query = "INSERT INTO submissions (assignmentID, studentID, materialID) VALUES (?, ?, ?)";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, submissionsModel.getAssignmentID());
            ps.setInt(2, submissionsModel.getStudentID());
            ps.setInt(3, submissionsModel.getMaterialID());
            row = ps.executeUpdate();

            if (row > 0) {
                System.out.println("Thêm thành công: " + row);
            }

            JDBCUtil.closeConnection(conn);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return row;
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
    public ArrayList<SubmissionUI> getSubmissionAssignment(int assignmentID) {
        ArrayList<SubmissionUI> submissionUIS = new ArrayList<>();
        String sql = "SELECT * \n" +
                "FROM submissions AS s\n" +
                "JOIN users AS u ON s.studentID = u.userID\n" +
                "JOIN materials AS m ON s.materialID = m.materialID\n" +
                "WHERE s.assignmentID = ?";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, assignmentID);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                SubmissionUI submissionUI = new SubmissionUI();
                submissionUI.setSendername(rs.getString("firstname") + " " + rs.getString("lastname"));
                submissionUI.setSubmissionDate(rs.getTimestamp("submissionDate"));
                submissionUI.setMaterialID(rs.getInt("materialID"));
                submissionUI.setTitleFile(rs.getString("title"));

                String filePath = rs.getString("filePath");
                if (filePath != null) {
                    String encodedFilePath = URLEncoder.encode(filePath, "UTF-8");
                    submissionUI.setFilePath(encodedFilePath);
                }

                submissionUIS.add(submissionUI);
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return submissionUIS;
    }
}
