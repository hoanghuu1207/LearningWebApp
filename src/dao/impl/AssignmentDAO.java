package dao.impl;

import dao.DAOInterface;
import model.AssignmentsModel;
import model.SubmissionsModel;
import util.JDBCUtil;

import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AssignmentDAO implements DAOInterface<AssignmentsModel> {
    @Override
    public int insert(AssignmentsModel assignmentsModel) {
        return 0;
    }

    @Override
    public int update(AssignmentsModel assignmentsModel) {
        return 0;
    }

    @Override
    public int delete(AssignmentsModel assignmentsModel) {
        return 0;
    }

    @Override
    public ArrayList<AssignmentsModel> selectAll() {
        return null;
    }

    @Override
    public AssignmentsModel selectById(int id) {
        return null;
    }
    public ArrayList<AssignmentsModel> getNotSubmittedAssignmentsOnTime(int userID, int classroomID) {
        ArrayList<AssignmentsModel> assignments = new ArrayList<>();
        String sql = "SELECT a.assignmentID, a.title AS titleAssignment, a.description, a.startTime, a.endTime, a.classroomID, m.materialID, m.title AS titleFile, m.filePath\n" +
                "FROM assignments a\n" +
                "LEFT JOIN materials AS m ON a.materialID = m.materialID\n" +
                "WHERE a.classroomID = ?\n" +
                "AND a.assignmentID NOT IN (SELECT assignmentID FROM submissions \n" +
                "WHERE studentID = ?) AND a.endTime >= NOW()";

        try (Connection conn = JDBCUtil.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, classroomID);
            statement.setInt(2, userID);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                AssignmentsModel assignment = new AssignmentsModel();
                assignment.setAssignmentID(rs.getInt("assignmentID"));
                assignment.setTitle(rs.getString("titleAssignment"));
                assignment.setClassroomID(rs.getInt("classroomID"));
                assignment.setDescription(rs.getString("description"));
                assignment.setStartTime(rs.getTimestamp("startTime"));
                assignment.setEndTime(rs.getTimestamp("endTime"));
                assignment.setMaterialID(rs.getInt("materialID"));
                assignment.setTitleFile(rs.getString("titleFile"));

                String filePath = rs.getString("filePath");
                if (filePath != null) {
                    String encodedFilePath = URLEncoder.encode(filePath, "UTF-8");
                    assignment.setFilePath(encodedFilePath);
                }

                assignments.add(assignment);
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return assignments;
    }

    public Map<AssignmentsModel, SubmissionsModel> getSubmittedAssignments(int userID, int classroomID) {
        Map<AssignmentsModel, SubmissionsModel> map = new HashMap<>();
        String sql = "SELECT a.assignmentID, a.title AS titleAssignment, a.classroomID, a.description, a.startTime, a.endTime, ma.title AS titleFileAssignment, ma.filePath AS filePathAssignment, s.submissionID, s.submissionDate, m.title AS titleFileSubmission, m.filePath AS filePathSubmission\n" +
                "FROM assignments a\n" +
                "LEFT JOIN materials ma ON a.materialID = ma.materialID\n" +
                "INNER JOIN submissions s ON a.assignmentID = s.assignmentID\n" +
                "INNER JOIN materials AS m ON s.materialID = m.materialID\n" +
                "WHERE a.classroomID = ? AND s.studentID = ?";

        try (Connection conn = JDBCUtil.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, classroomID);
            statement.setInt(2, userID);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                AssignmentsModel assignment = new AssignmentsModel();
                assignment.setAssignmentID(rs.getInt("assignmentID"));
                assignment.setTitle(rs.getString("titleAssignment"));
                assignment.setClassroomID(rs.getInt("classroomID"));
                assignment.setDescription(rs.getString("description"));
                assignment.setStartTime(rs.getTimestamp("startTime"));
                assignment.setEndTime(rs.getTimestamp("endTime"));
                assignment.setTitleFile(rs.getString("titleFileAssignment"));

                String filePath = rs.getString("filePathAssignment");
                if (filePath != null) {
                    String encodedFilePath = URLEncoder.encode(filePath, "UTF-8");
                    assignment.setFilePath(encodedFilePath);
                }

                SubmissionsModel submissionsModel = new SubmissionsModel();
                submissionsModel.setSubmissionID(rs.getInt("submissionID"));
                submissionsModel.setSubmissionDate(rs.getTimestamp("submissionDate"));
                submissionsModel.setTitleFile(rs.getString("titleFileSubmission"));

                String filePath1 = rs.getString("filePathSubmission");
                if (filePath1 != null) {
                    String encodedFilePath = URLEncoder.encode(filePath1, "UTF-8");
                    submissionsModel.setFilePath(encodedFilePath);
                }

                map.put(assignment, submissionsModel);
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public ArrayList<AssignmentsModel> getOverdueAssignments(int userID, int classroomID) {
        ArrayList<AssignmentsModel> assignments = new ArrayList<>();
        String sql = "SELECT a.assignmentID, a.title AS titleAssignment, a.description, a.startTime, a.endTime, a.classroomID, m.materialID, m.title AS titleFile, m.filePath\n" +
                "FROM assignments a\n" +
                "LEFT JOIN materials AS m ON a.materialID = m.materialID\n" +
                "WHERE a.classroomID = ? \n" +
                "AND a.assignmentID NOT IN (SELECT assignmentID FROM submissions WHERE studentID = ?)\n" +
                "AND a.endTime < NOW()";

        System.out.println("Hehe: " + userID + " " + classroomID);

        try (Connection conn = JDBCUtil.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, classroomID);
            statement.setInt(2, userID);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                AssignmentsModel assignment = new AssignmentsModel();
                assignment.setAssignmentID(rs.getInt("assignmentID"));
                assignment.setTitle(rs.getString("titleAssignment"));
                assignment.setClassroomID(rs.getInt("classroomID"));
                assignment.setDescription(rs.getString("description"));
                assignment.setStartTime(rs.getTimestamp("startTime"));
                assignment.setEndTime(rs.getTimestamp("endTime"));
                assignment.setMaterialID(rs.getInt("materialID"));
                assignment.setTitleFile(rs.getString("titleFile"));

                String filePath = rs.getString("filePath");
                if (filePath != null) {
                    String encodedFilePath = URLEncoder.encode(filePath, "UTF-8");
                    assignment.setFilePath(encodedFilePath);
                }

                assignments.add(assignment);
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return assignments;
    }

    public ArrayList<AssignmentsModel> teacherGetAssignments(int classroomID) {
        ArrayList<AssignmentsModel> assignments = new ArrayList<>();
        String sql = "SELECT * FROM assignments AS a " +
                "WHERE a.classroomID = ?";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, classroomID);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                AssignmentsModel assignment = new AssignmentsModel();
                assignment.setAssignmentID(rs.getInt("assignmentID"));
                assignment.setTitle(rs.getString("title"));
                assignment.setClassroomID(rs.getInt("classroomID"));
                assignment.setDescription(rs.getString("description"));
                assignment.setStartTime(rs.getTimestamp("startTime"));
                assignment.setEndTime(rs.getTimestamp("endTime"));
                assignments.add(assignment);
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return assignments;
    }
    public int getIdAfterInsertAssignment(AssignmentsModel assignmentsModel) {
        int assignmentID = 0;
        String query = "INSERT INTO assignments (title, description, endTime, classroomID, materialID) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, assignmentsModel.getTitle());
            ps.setString(2, assignmentsModel.getDescription());
            ps.setTimestamp(3, assignmentsModel.getEndTime());
            ps.setInt(4, assignmentsModel.getClassroomID());
            ps.setInt(5, assignmentsModel.getMaterialID());
            int row = ps.executeUpdate();

            if (row > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        System.out.println("Thêm thành công: " + row + ", ID: " + generatedId);

                        assignmentID = generatedId;
                    }
                }
            }

            JDBCUtil.closeConnection(conn);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return assignmentID;
    }
    public static void main(String[] args) {
        AssignmentDAO dao = new AssignmentDAO();
        ArrayList<AssignmentsModel> messages = dao.getOverdueAssignments(3,1);
        for (AssignmentsModel message : messages) {
            System.out.println("AssignmentID: " + message.getAssignmentID());
            System.out.println("Content: " + message.getDescription());
            System.out.println("StartTime: " + message.getStartTime());
            System.out.println("EndTime: " + message.getEndTime());
        }
    }
}
