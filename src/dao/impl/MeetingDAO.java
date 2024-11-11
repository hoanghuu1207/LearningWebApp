package dao.impl;

import dao.DAOInterface;
import model.ClassroomsModel;
import model.MaterialsModel;
import model.MeetingsModel;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public static void main()
    {

    }
}
