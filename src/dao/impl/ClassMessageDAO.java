package dao.impl;

import dao.DAOInterface;
import model.ClassMessageModel;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ClassMessageDAO implements DAOInterface<ClassMessageModel> {
    @Override
    public int insert(ClassMessageModel classMessageModel) {
        return 0;
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
        String sql = "SELECT m1.messageID, m1.content, m1.createdAt, m1.userID, m1.parentMessageID, " +
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
