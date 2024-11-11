package dao.impl;

import dao.DAOInterface;
import model.AssignmentsModel;
import model.MaterialsModel;
import model.MeetingsModel;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MaterialDAO implements DAOInterface<MaterialsModel> {
    @Override
    public int insert(MaterialsModel materialsModel) {
        return 0;
    }

    @Override
    public int update(MaterialsModel materialsModel) {
        return 0;
    }

    @Override
    public int delete(MaterialsModel materialsModel) {
        return 0;
    }

    @Override
    public ArrayList<MaterialsModel> selectAll() {
        return null;
    }

    @Override
    public MaterialsModel selectById(int id) {
        return null;
    }
    public ArrayList<MaterialsModel> getFilesByClassroomID(int classroomID) {
        ArrayList<MaterialsModel> materials = new ArrayList<>();
        String sql = "SELECT * FROM materials WHERE classroomID = ?";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, classroomID);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                MaterialsModel material = new MaterialsModel();
                material.setMaterialID(rs.getInt("materialID"));
                material.setTitle(rs.getString("title"));
                material.setClassroomID(rs.getInt("classroomID"));
                material.setFilePath(rs.getString("filePath"));

                materials.add(material);
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return materials;
    }
    public void uploadFile(int classroomID, String fileName, String filePath) {
        String query = "INSERT INTO materials (classroomID, title, filePath) VALUES (?, ?, ?)";

        try (Connection conn = JDBCUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, classroomID);
            ps.setString(2, fileName);
            ps.setString(3, filePath);
            ps.executeUpdate();
            JDBCUtil.closeConnection(conn);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
          MeetingDAO dao = new MeetingDAO();
         ArrayList<MeetingsModel> messages = dao.getPastMeetings(1);
        for ( MeetingsModel message : messages) {
        System.out.println("AssignmentID: " + message.getMeetingID());
        System.out.println("Content: " + message.getTitle());
        System.out.println("StartTime: " + message.getStartTime());
        System.out.println("EndTime: " + message.getEndTime());
        System.out.println("ClassroomID: " + message.getClassroomID());
        System.out.println("Duration: " + message.getDuration());
        System.out.println("isCanceled: " + message.getIsCancelled());
    }
}
}
