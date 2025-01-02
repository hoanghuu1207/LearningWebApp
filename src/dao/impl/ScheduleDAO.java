package dao.impl;

import dao.DAOInterface;
import model.MeetingsModel;
import model.ScheduleModel;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ScheduleDAO implements DAOInterface<ScheduleModel> {
    @Override
    public int insert(ScheduleModel schedule) {
        int rowsInserted = 0;
        String sql = "INSERT INTO schedule (meetingID, timeCreate, timeAccess, title) " +
                "VALUES (?, CURRENT_TIMESTAMP(), ?, ?)";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, schedule.getMeetingID());
            ps.setTimestamp(2, schedule.getTimeAccess());
            ps.setString(3, schedule.getTitle());

            rowsInserted = ps.executeUpdate();
            JDBCUtil.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsInserted;
    }

    @Override
    public int update(ScheduleModel scheduleModel) {
        return 0;
    }

    @Override
    public int delete(ScheduleModel scheduleModel) {
        return 0;
    }

    @Override
    public ArrayList<ScheduleModel> selectAll() {
        return null;
    }

    @Override
    public ScheduleModel selectById(int id) {
        return null;
    }
    public ScheduleModel selectByMeetingID(int meetingID) {
        ScheduleModel meetingsModel = null;
        String sql = "SELECT * FROM schedule WHERE meetingID = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, meetingID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                meetingsModel = new ScheduleModel();
                meetingsModel.setScheduleID(rs.getInt("scheduleID"));
                meetingsModel.setMeetingID(rs.getInt("meetingID"));
                meetingsModel.setTitle(rs.getString("title"));
                meetingsModel.setTimeCreate(rs.getTimestamp("timeCreate"));
                meetingsModel.setTimeAccess(rs.getTimestamp("timeAccess"));
            }
            JDBCUtil.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return meetingsModel;
    }

    public ArrayList<ScheduleModel> getUpcomingSchedules(int classroomID){
        ArrayList<ScheduleModel> scheduleModels = new ArrayList<>();
        String sql = "SELECT s.* FROM schedule s " +
                "JOIN meetings m ON s.meetingID = m.meetingID " +
                "WHERE m.classroomID = ? AND m.isCancelled = 0 " +
                "AND s.timeAccess > NOW() " +
                "ORDER BY s.timeAccess";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, classroomID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ScheduleModel scheduleModel = new ScheduleModel();
                scheduleModel.setScheduleID(rs.getInt("scheduleID"));
                scheduleModel.setMeetingID(rs.getInt("meetingID"));
                scheduleModel.setTitle(rs.getString("title"));
                scheduleModel.setTimeCreate(rs.getTimestamp("timeCreate"));
                scheduleModel.setTimeAccess(rs.getTimestamp("timeAccess"));
                scheduleModels.add(scheduleModel);
            }
            JDBCUtil.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scheduleModels;
    }
    public void createSchedule(ScheduleModel schedule) {
        String sql = "INSERT INTO schedule (meetingID, timeCreate, timeAccess, title) " +
                "VALUES (?, CURRENT_TIMESTAMP(), ?, ?)";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, schedule.getMeetingID());
            ps.setTimestamp(2, schedule.getTimeAccess());
            ps.setString(3, schedule.getTitle());
            ps.executeUpdate();
            JDBCUtil.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
