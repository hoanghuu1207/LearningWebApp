package dao.impl;

import dao.DAOInterface;
import model.AssignmentsModel;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
        String sql = "SELECT * FROM assignments a " +
                "WHERE a.classroomID = ? " +
                "AND a.assignmentID NOT IN (SELECT assignmentID FROM submissions " +
                "WHERE studentID = ?) " + "AND a.endTime >= NOW()";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, classroomID);
            statement.setInt(2, userID);

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

    public ArrayList<AssignmentsModel> getSubmittedAssignments(int userID, int classroomID) {
        ArrayList<AssignmentsModel> assignments = new ArrayList<>();
        String sql = "SELECT a.* FROM assignments a " +
                "INNER JOIN submissions s ON a.assignmentID = s.assignmentID " +
                "WHERE a.classroomID = ? " +
                "AND s.studentID = ?";

        try (Connection conn = JDBCUtil.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, classroomID);
            statement.setInt(2, userID);

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

    public ArrayList<AssignmentsModel> getOverdueAssignments(int userID, int classroomID) {
        ArrayList<AssignmentsModel> assignments = new ArrayList<>();
        String sql = "SELECT * FROM assignments a " +
                "WHERE a.classroomID = ? " +
                "AND a.assignmentID NOT IN (SELECT assignmentID FROM submissions WHERE studentID = ?) " +
                "AND a.endTime < NOW()";

        try (Connection conn = JDBCUtil.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, classroomID);
            statement.setInt(2, userID);

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
