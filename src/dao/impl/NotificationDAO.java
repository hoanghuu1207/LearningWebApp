package dao.impl;

import dao.DAOInterface;
import model.*;
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
        String sql = "SELECT " +
                "   n.notificationID, " +
                "   n.status, " +
                "   n.type, " +
                "   n.relatedID, " +
                "   n.informedUser, " +
                "   CASE " +
                "      WHEN n.type = 'add_to_class' THEN c.title " +
                "      WHEN n.type = 'post' THEN class.title " +
                "      ELSE NULL " +
                "   END AS title, " +
                "   CASE " +
                "      WHEN n.type = 'add_to_class' THEN c.classroomID " +
                "      WHEN n.type = 'post' THEN cm.classroomID " +
                "      ELSE NULL " +
                "   END AS classroomID, " +
                "   CASE " +
                "      WHEN n.type = 'add_to_class' THEN us.firstname " +
                "      WHEN n.type = 'post' THEN u.firstname " +
                "      ELSE NULL " +
                "   END AS firstname, " +
                "   CASE " +
                "      WHEN n.type = 'add_to_class' THEN us.lastname " +
                "      WHEN n.type = 'post' THEN u.lastname " +
                "      ELSE NULL " +
                "   END AS lastname, " +
                "   COALESCE(sc.createdAt, cm.createdAt) AS createdAt " +
                "FROM" +
                "   notification AS n " +
                "LEFT JOIN " +
                "   students_classrooms AS sc " +
                "   ON n.type = 'add_to_class' AND n.relatedID = sc.id " +
                "LEFT JOIN " +
                "   classrooms AS c " +
                "   ON c.classroomID = sc.classroomID " +
                "LEFT JOIN " +
                "   users AS us " +
                "   ON us.userID = c.teacherID " +
                "LEFT JOIN " +
                "   classroom_messages AS cm " +
                "   ON n.type = 'post' AND n.relatedID = cm.messageID " +
                "LEFT JOIN" +
                "   classrooms AS class " +
                "   ON class.classroomID = cm.classroomID " +
                "LEFT JOIN " +
                "   users AS u " +
                "   ON cm.userID = u.userID " +
                "WHERE " +
                "   n.informedUser = ? " +
                "   AND (u.userID IS NULL OR u.userID != ?) " +
                "ORDER BY " +
                "   createdAt DESC;";

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
                }else if(type.equals("add_to_class")){
                    url = "/class/detail?classID=" + classroomID;
                    content = firstName + " " + lastName + " đã thêm bạn vào " + title;
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
    public SubNotificationModel getSubNotificationWithStudent_ClassroomId(int id){
        SubNotificationModel subNotificationModel = null;
        String sql = "SELECT * FROM classrooms AS c " +
                "JOIN students_classrooms AS sc ON c.classroomID = sc.classroomID " +
                "JOIN users AS u ON u.userID = c.teacherID " +
                "WHERE sc.id = ?;";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                subNotificationModel= new SubNotificationModel();

                String title = rs.getString("title");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                int classroomID = rs.getInt("classroomID");

                subNotificationModel.setUrl("/class/detail?classID=" + classroomID);
                subNotificationModel.setContent(firstname + " " + lastname + " đã thêm bạn vào " + title);
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subNotificationModel;
    }
}
