package dao.impl;

import dao.DAOInterface;
import model.ClassMessageModel;
import model.NotificationModel;
import model.SubNotificationModel;
import model.UserModel;
import util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class NotificationDAO implements DAOInterface<NotificationModel> {
    @Override
    public int insert(NotificationModel notificationModel) {
        int row = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String query = "INSERT INTO notification(status, type, relatedID, informedUser) "
                    + "VALUES (?, ?, ?, ?)";

            PreparedStatement pstm = con.prepareStatement(query);

            pstm.setInt(1, notificationModel.getStatus());
            pstm.setString(2, notificationModel.getType());
            pstm.setInt(3, notificationModel.getRelatedID());
            pstm.setInt(4, notificationModel.getInformedID());

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
    public NotificationModel insertAndGetNotification(NotificationModel notificationModel) {
        NotificationModel insertedNotification = null;
        try {
            Connection con = JDBCUtil.getConnection();

            String query = "INSERT INTO notification(status, type, relatedID, informedUser) "
                    + "VALUES (?, ?, ?, ?)";

            PreparedStatement pstm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            pstm.setInt(1, notificationModel.getStatus());
            pstm.setString(2, notificationModel.getType());
            pstm.setInt(3, notificationModel.getRelatedID());
            pstm.setInt(4, notificationModel.getInformedID());

            int row = pstm.executeUpdate();

            if (row > 0) {
                try (ResultSet generatedKeys = pstm.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        System.out.println("Thêm thành công: " + row + ", ID: " + generatedId);

                        insertedNotification = new NotificationModel();
                        insertedNotification.setNotificationID(generatedId);
                        insertedNotification.setStatus(notificationModel.getStatus());
                        insertedNotification.setType(notificationModel.getType());
                        insertedNotification.setRelatedID(notificationModel.getRelatedID());
                        insertedNotification.setInformedID(notificationModel.getInformedID());
                    }
                }
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insertedNotification;
    }
    @Override
    public int update(NotificationModel notificationModel) {
        return 0;
    }

    @Override
    public int delete(NotificationModel notificationModel) {
        return 0;
    }

    @Override
    public ArrayList<NotificationModel> selectAll() {
        return null;
    }

    @Override
    public NotificationModel selectById(int id) {
        return null;
    }

    public ArrayList<SubNotificationModel> selectNotificationWithUserid(int userID){
        ArrayList<SubNotificationModel> notificationModels = new ArrayList<>();
        String sql = "SELECT * " +
                "FROM notification AS n " +
                "JOIN classroom_messages AS cm ON n.relatedID = cm.messageID " +
                "JOIN classrooms AS c ON cm.classroomID = c.classroomID " +
                "JOIN users AS u ON u.userID = cm.userID " +
                "WHERE n.informedUser = ? AND u.userID != ? " +
                "ORDER BY cm.createdAt DESC;";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userID);
            stmt.setInt(2, userID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                SubNotificationModel subNotificationModel = new SubNotificationModel();

                int notificationID = rs.getInt("notificationID");
                String type = rs.getString("type");
                int status = rs.getInt("status");
                int classroomID = rs.getInt("classroomID");
                String title = rs.getString("title");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");

                String url = "";
                String content = "";

                if(type.equals("post")){
                    url = "/class/detail?classID=" + classroomID;
                    content = firstName + " " + lastName + " thêm bài viết mới trong " + title;
                }
                // assignment, schedule

                subNotificationModel.setNotificationID(notificationID);
                subNotificationModel.setUrl(url);
                subNotificationModel.setContent(content);
                subNotificationModel.setStatus(status);
                notificationModels.add(subNotificationModel);
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return notificationModels;
    }
    public int updateStatusToSeen(int userID) {
        int row = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String query = "UPDATE notification SET status = ? WHERE informedUser = ?;";

            PreparedStatement pstm = con.prepareStatement(query);

            pstm.setInt(1, 1);
            pstm.setInt(2, userID);

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
}
