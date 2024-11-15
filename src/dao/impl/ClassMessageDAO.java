package dao.impl;

import dao.DAOInterface;
import model.ClassMessageModel;
import model.UserModel;
import util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class ClassMessageDAO implements DAOInterface<ClassMessageModel> {
    private UserDAO userDAO = new UserDAO();
    @Override
    public int insert(ClassMessageModel classMessageModel) {
        int row = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String query = "INSERT INTO classroom_messages(classroomID, userID, content, parentMessageID, createdAt) "
                    + "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement pstm = con.prepareStatement(query);

            pstm.setInt(1, classMessageModel.getClassroomID());
            pstm.setInt(2, classMessageModel.getUserID());
            pstm.setString(3, classMessageModel.getContent());
            pstm.setInt(4, classMessageModel.getParentMessageID());
            Date date = new Date();
            pstm.setTimestamp(5, new Timestamp(date.getTime()));

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

    @Override
    public int update(ClassMessageModel classMessageModel) {
        return 0;
    }

    @Override
    public int delete(ClassMessageModel classMessageModel) {
        return 0;
    }

    @Override
    public ArrayList<ClassMessageModel> selectAll() {
        return null;
    }

    @Override
    public ClassMessageModel selectById(int id) {
        return null;
    }
    public ArrayList<ClassMessageModel> selectAllByClassId(int classid) {
        ArrayList<ClassMessageModel> messages = new ArrayList<>();
        String sql = "SELECT m1.messageID, m1.content, m1.createdAt, m1.userID, m1.parentMessageID, m1.classroomID, " +
                "u.firstname, u.lastname " +
                "FROM classroom_messages m1 " +
                "JOIN users u ON m1.userID = u.userID " +
                "WHERE m1.classroomID = ? " +
                "ORDER BY COALESCE(m1.parentMessageID, m1.messageID), m1.createdAt ASC";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, classid);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ClassMessageModel message = new ClassMessageModel(
                        rs.getInt("messageID"),
                        rs.getString("content"),
                        rs.getTimestamp("createdAt"),
                        rs.getInt("userID"),
                        rs.getInt("parentMessageID"),
                        rs.getInt("classroomID"),
                        rs.getString("firstname") + " " + rs.getString("lastname")
                );
                messages.add(message);
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return messages;
    }
    public ClassMessageModel insertAndGetMessage(ClassMessageModel classMessageModel) {
        ClassMessageModel insertedMessage = null;
        try {
            Connection con = JDBCUtil.getConnection();

            String query = "INSERT INTO classroom_messages(classroomID, userID, content, parentMessageID, createdAt) "
                    + "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement pstm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            pstm.setInt(1, classMessageModel.getClassroomID());
            pstm.setInt(2, classMessageModel.getUserID());
            pstm.setString(3, classMessageModel.getContent());
            pstm.setInt(4, classMessageModel.getParentMessageID());
            Date date = new Date();
            pstm.setTimestamp(5, new Timestamp(date.getTime()));

            int row = pstm.executeUpdate();

            if (row > 0) {
                try (ResultSet generatedKeys = pstm.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        System.out.println("Thêm thành công: " + row + ", ID: " + generatedId);

                        insertedMessage = new ClassMessageModel();
                        insertedMessage.setMessageID(generatedId);
                        insertedMessage.setClassroomID(classMessageModel.getClassroomID());
                        insertedMessage.setUserID(classMessageModel.getUserID());
                        insertedMessage.setContent(classMessageModel.getContent());
                        insertedMessage.setParentMessageID(classMessageModel.getParentMessageID());
                        insertedMessage.setCreatedAt(new Timestamp(date.getTime()));

                        UserModel user = userDAO.selectById(classMessageModel.getUserID());
                        insertedMessage.setSenderName(user.getFirstName() + " " + user.getLastName());
                    }
                }
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insertedMessage;
    }
    public static void main(String[] args) {
        ClassMessageDAO dao = new ClassMessageDAO();
        ArrayList<ClassMessageModel> messages = dao.selectAllByClassId(1);
        for (ClassMessageModel message : messages) {
            System.out.println("MesageID: " + message.getMessageID());
            System.out.println("Name: " + message.getSenderName());
            System.out.println("Content: " + message.getContent());
            System.out.println("Content: " + (message.getParentMessageID() == 0));
            System.out.println("Time: " + message.getCreatedAt());
        }
    }
}
