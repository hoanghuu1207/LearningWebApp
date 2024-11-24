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
    public NotificationModel insertAndGetNotificationOfRemoveStudentFromClass(NotificationModel notificationModel) {
        NotificationModel insertedNotification = null;
        try {
            Connection con = JDBCUtil.getConnection();

            String query = "INSERT INTO notification(status, type, relatedID, informedUser, createdAt) "
                    + "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement pstm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            pstm.setInt(1, notificationModel.getStatus());
            pstm.setString(2, notificationModel.getType());
            pstm.setInt(3, notificationModel.getRelatedID());
            pstm.setInt(4, notificationModel.getInformedID());

            java.util.Date date = new Date();
            pstm.setTimestamp(5, new Timestamp(date.getTime()));

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
                        insertedNotification.setCreatedAt(notificationModel.getCreatedAt());
                    }
                }
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insertedNotification;
    }
    public NotificationModel insertAndGetNotificationOfAddAssignmentToClass(NotificationModel notificationModel) {
        NotificationModel insertedNotification = null;
        try {
            Connection con = JDBCUtil.getConnection();

            String query = "INSERT INTO notification(status, type, relatedID, informedUser, createdAt) "
                    + "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement pstm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            pstm.setInt(1, notificationModel.getStatus());
            pstm.setString(2, notificationModel.getType());
            pstm.setInt(3, notificationModel.getRelatedID());
            pstm.setInt(4, notificationModel.getInformedID());

            java.util.Date date = new Date();
            pstm.setTimestamp(5, new Timestamp(date.getTime()));

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
                        insertedNotification.setCreatedAt(notificationModel.getCreatedAt());
                    }
                }
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insertedNotification;
    }
    public SubNotificationModel subNotificationAddAssignment(int classroomID){
        SubNotificationModel subNotificationModel = null;
        String sql = "SELECT * FROM classrooms AS c " +
                "JOIN users AS u ON c.teacherID = u.userID " +
                "WHERE c.classroomID = ?;";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, classroomID);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String title = rs.getString("title");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");

                subNotificationModel = new SubNotificationModel();
                subNotificationModel.setUrl("/class_assignments?classroomID=" + classroomID);
                subNotificationModel.setContent(firstname + " " + lastname + " đã thêm bài tập vào " + title);
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subNotificationModel;
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
        String sql = "SELECT \n" +
                "   n.notificationID,\n" +
                "   n.status,\n" +
                "   n.type,\n" +
                "   n.relatedID,\n" +
                "   n.informedUser,\n" +
                "   CASE \n" +
                "      WHEN n.type = 'add_to_class' THEN c.title\n" +
                "      WHEN n.type = 'post' THEN class.title\n" +
                "      WHEN n.type = 'remove_from_class' THEN class1.title\n" +
                "      WHEN n.type = 'assignment' THEN c2.title\n" +
                "      ELSE NULL\n" +
                "   END AS title,\n" +
                "   CASE \n" +
                "      WHEN n.type = 'add_to_class' THEN c.classroomID\n" +
                "      WHEN n.type = 'post' THEN cm.classroomID\n" +
                "      WHEN n.type = 'remove_from_class' THEN class1.classroomID\n" +
                "      WHEN n.type = 'assignment' THEN c2.classroomID\n" +
                "      ELSE NULL\n" +
                "   END AS classroomID,\n" +
                "   CASE \n" +
                "      WHEN n.type = 'add_to_class' THEN us.firstname\n" +
                "      WHEN n.type = 'post' THEN u.firstname\n" +
                "      WHEN n.type = 'remove_from_class' THEN u1.firstname\n" +
                "      WHEN n.type = 'assignment' THEN u2.firstname\n" +
                "      ELSE NULL\n" +
                "   END AS firstname,\n" +
                "   CASE \n" +
                "      WHEN n.type = 'add_to_class' THEN us.lastname\n" +
                "      WHEN n.type = 'post' THEN u.lastname\n" +
                "      WHEN n.type = 'remove_from_class' THEN u1.lastname\n" +
                "      WHEN n.type = 'assignment' THEN u2.lastname\n" +
                "      ELSE NULL\n" +
                "   END AS lastname,\n" +
                "   COALESCE(sc.createdAt, cm.createdAt, n.createdAt) AS createdAt\n" +
                "FROM\n" +
                "   notification AS n\n" +
                "LEFT JOIN\n" +
                "\tstudents_classrooms AS sc\n" +
                "\tON n.type = 'add_to_class' AND n.relatedID = sc.id\n" +
                "LEFT JOIN \n" +
                "   classrooms AS c\n" +
                "   ON c.classroomID = sc.classroomID\n" +
                "LEFT JOIN\n" +
                "\tusers AS us\n" +
                "\tON us.userID = c.teacherID\n" +
                "LEFT JOIN \n" +
                "\tclassroom_messages AS cm\n" +
                "   ON n.type = 'post' AND n.relatedID = cm.messageID\n" +
                "LEFT JOIN\n" +
                "\tclassrooms AS class\n" +
                "\tON class.classroomID = cm.classroomID\n" +
                "LEFT JOIN \n" +
                "   users AS u\n" +
                "   ON cm.userID = u.userID\n" +
                "LEFT JOIN\n" +
                "\tclassrooms AS class1\n" +
                "\tON n.type = 'remove_from_class' AND n.relatedID = class1.classroomID\n" +
                "LEFT JOIN\n" +
                "\tusers AS u1\n" +
                "\tON u1.userID = class1.teacherID\n" +
                "LEFT JOIN \n" +
                "\tassignments AS a\n" +
                "   ON n.type = 'assignment' AND n.relatedID = a.assignmentID\n" +
                "LEFT JOIN\n" +
                "\tclassrooms AS c2\n" +
                "\tON c2.classroomID = a.classroomID\n" +
                "LEFT JOIN\n" +
                "\tusers AS u2\n" +
                "\tON c2.teacherID = u2.userID\n" +
                "WHERE \n" +
                "   n.informedUser = ?\n" +
                "   AND (u.userID IS NULL OR u.userID != ?)\n" +
                "ORDER BY \n" +
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

                if(notificationID == 0 || type == null || status == 0 || classroomID == 0 || title == null || firstName == null || lastName == null) continue;

                String url = "";
                String content = "";

                if(type.equals("post")){
                    url = "/class/detail?classID=" + classroomID;
                    content = firstName + " " + lastName + " thêm bài viết mới trong " + title;
                }else if(type.equals("add_to_class")){
                    url = "/class/detail?classID=" + classroomID;
                    content = firstName + " " + lastName + " đã thêm bạn vào " + title;
                }else if(type.equals("remove_from_class")){
                    url = "#";
                    content = firstName + " " + lastName + " đã xóa bạn khỏi " + title;
                }else if(type.equals("assignment")){
                    url = "/class_assignments?classroomID=" + classroomID;
                    content = firstName + " " + lastName + " đã thêm bài tập vào " + title;
                }
                //, schedule

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
