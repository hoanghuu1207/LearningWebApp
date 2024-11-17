package dao.impl;

import dao.DAOInterface;
import model.SubNotificationModel;
import model.UserModel;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class NotificationDAO implements DAOInterface<NotificationDAO> {
    @Override
    public int insert(NotificationDAO notificationDAO) {
        return 0;
    }

    @Override
    public int update(NotificationDAO notificationDAO) {
        return 0;
    }

    @Override
    public int delete(NotificationDAO notificationDAO) {
        return 0;
    }

    @Override
    public ArrayList<NotificationDAO> selectAll() {
        return null;
    }

    @Override
    public NotificationDAO selectById(int id) {
        return null;
    }

    public ArrayList<SubNotificationModel> selectNotificationWithUserid(int userID){
        ArrayList<SubNotificationModel> notificationModels = new ArrayList<>();
        String sql = "SELECT * " +
                "FROM notification AS n " +
                "JOIN classroom_messages AS cm ON n.relatedID = cm.messageID " +
                "JOIN classrooms AS c ON cm.classroomID = c.classroomID " +
                "JOIN users AS u ON u.userID = cm.userID " +
                "WHERE informedUser = ? AND u.userID != ? " +
                "ORDER BY cm.createdAt DESC;";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userID);
            stmt.setInt(2, userID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                SubNotificationModel subNotificationModel = new SubNotificationModel();

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
}
