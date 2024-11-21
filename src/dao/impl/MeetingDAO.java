package dao.impl;

import dao.DAOInterface;
import model.ClassroomsModel;
import model.MaterialsModel;
import model.MeetingsModel;
import util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;

public class MeetingDAO implements DAOInterface<MeetingsModel> {
    @Override
    public int insert(MeetingsModel meetingsModel) {
        return 0;
    }

    @Override
    public int update(MeetingsModel meetingsModel) {
        return 0;
    }

    @Override
    public int delete(MeetingsModel meetingsModel) {
        return 0;
    }

    @Override
    public ArrayList<MeetingsModel> selectAll() {
        return null;
    }

    @Override
    public MeetingsModel selectById(int id) {
        MeetingsModel meetingsModel = null;
        String sql = "SELECT * FROM meetings WHERE meetingID = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                meetingsModel = new MeetingsModel();
                meetingsModel.setMeetingID(rs.getInt("meetingID"));
                meetingsModel.setTitle(rs.getString("title"));
                meetingsModel.setStartTime(rs.getTimestamp("startTime"));
                meetingsModel.setEndTime(rs.getTimestamp("endTime"));
                meetingsModel.setClassroomID(rs.getInt("classroomID"));
                meetingsModel.setDuration(rs.getTime("duration"));
                meetingsModel.setIsCancelled(rs.getInt("isCancelled"));
            }
            JDBCUtil.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return meetingsModel;
    }
    public ArrayList<MeetingsModel> getUpcomingMeetings(int id){
        ArrayList<MeetingsModel> meetings = new ArrayList<>();
        String sql = "SELECT * FROM meetings WHERE startTime > NOW() AND isCancelled = 0" +
                " AND classroomID = ?";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                MeetingsModel meeting = new MeetingsModel();
                meeting.setMeetingID(resultSet.getInt("meetingID"));
                meeting.setTitle(resultSet.getString("title"));
                meeting.setStartTime(resultSet.getTimestamp("startTime"));
                meeting.setEndTime(resultSet.getTimestamp("endTime"));
                meeting.setClassroomID(resultSet.getInt("classroomID"));
                meeting.setDuration(resultSet.getTime("duration"));
                meeting.setIsCancelled(resultSet.getInt("isCancelled"));
                meetings.add(meeting);
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return meetings;
    }
    public ArrayList<MeetingsModel> getPastMeetings(int id){
        ArrayList<MeetingsModel> meetings = new ArrayList<>();
        String sql = "SELECT * FROM meetings WHERE endTime < NOW() AND isCancelled = 0" +
                " AND classroomID = ?";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                MeetingsModel meeting = new MeetingsModel();
                meeting.setMeetingID(resultSet.getInt("meetingID"));
                meeting.setTitle(resultSet.getString("title"));
                meeting.setStartTime(resultSet.getTimestamp("startTime"));
                meeting.setEndTime(resultSet.getTimestamp("endTime"));
                meeting.setClassroomID(resultSet.getInt("classroomID"));
                meeting.setDuration(resultSet.getTime("duration"));
                meeting.setIsCancelled(resultSet.getInt("isCancelled"));
                meetings.add(meeting);
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return meetings;
    }
    public ArrayList<MeetingsModel> getCanceledMeetings(int id){
        ArrayList<MeetingsModel> meetings = new ArrayList<>();
        String sql = "SELECT * FROM meetings WHERE isCancelled = 1 AND classroomID = ?";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                MeetingsModel meeting = new MeetingsModel();
                meeting.setMeetingID(resultSet.getInt("meetingID"));
                meeting.setTitle(resultSet.getString("title"));
                meeting.setStartTime(resultSet.getTimestamp("startTime"));
                meeting.setEndTime(resultSet.getTimestamp("endTime"));
                meeting.setClassroomID(resultSet.getInt("classroomID"));
                meeting.setDuration(resultSet.getTime("duration"));
                meeting.setIsCancelled(resultSet.getInt("isCancelled"));
                meetings.add(meeting);
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return meetings;
    }
    public ArrayList<MeetingsModel> getOngoingMeetings(int id) {
        ArrayList<MeetingsModel> meetings = new ArrayList<>();
        String sql = "SELECT * FROM meetings WHERE startTime <= NOW() AND endTime IS NULL AND isCancelled = 0" +
                " AND classroomID = ?";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                MeetingsModel meeting = new MeetingsModel();
                meeting.setMeetingID(resultSet.getInt("meetingID"));
                meeting.setTitle(resultSet.getString("title"));
                meeting.setStartTime(resultSet.getTimestamp("startTime"));
                meeting.setEndTime(resultSet.getTimestamp("endTime"));
                meeting.setClassroomID(resultSet.getInt("classroomID"));
                meeting.setDuration(resultSet.getTime("duration"));
                meeting.setIsCancelled(resultSet.getInt("isCancelled"));
                meetings.add(meeting);
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return meetings;
    }

    public int createNewMeeting(String title, int classroomID) {
        int meetingID = -1;
        String sql = "INSERT INTO meetings (title, startTime, endTime, classroomID, duration, isCancelled) " +
                "VALUES (?, current_timestamp(), NULL, ?, '00:00:00', '0')";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, title);
            ps.setInt(2, classroomID);
            ps.executeUpdate(); // Execute the insertion
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                meetingID = rs.getInt(1); // Lấy giá trị meetingID
            }
            JDBCUtil.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return meetingID;
    }
    public int createNewSchedule_Meeting(String title, Timestamp startTime, int classroomID ) {
        int meetingID = -1;
        String sql = "INSERT INTO meetings (title, startTime, endTime, classroomID, duration, isCancelled) " +
                "VALUES (?, ?, NULL, ?, '00:00:00', '0')";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, title);
            ps.setTimestamp(2, startTime);
            ps.setInt(3, classroomID);
            ps.executeUpdate(); // Execute the insertion
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                meetingID = rs.getInt(1); // Lấy giá trị meetingID
            }
            JDBCUtil.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return meetingID;
    }

    public void ModifierEndMeeting(int meetingID) {
        String sql = "UPDATE meetings " +
                "SET endTime = current_timestamp(), " +
                "duration = TIMEDIFF(current_timestamp(), startTime) " +
                "WHERE meetingID = ?";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, meetingID);
            ps.executeUpdate();
            JDBCUtil.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void cancelMeeting(int meetingID) {
        String sql = "UPDATE meetings " +
                "SET endTime = CURRENT_TIMESTAMP(), isCancelled = 1 " +
                "WHERE meetingID = ?";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, meetingID);
            ps.executeUpdate();
            JDBCUtil.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        MeetingDAO meetingDAO = new MeetingDAO();
//        int meetingID = 14;
//        meetingDAO.cancelMeeting(meetingID);
//        if (success) {
//            System.out.println("Cuộc họp đã bị hủy thành công!");
//        } else {
//            System.out.println("Hủy cuộc họp thất bại! Vui lòng kiểm tra lại meetingID.");
//        }
    }

}
