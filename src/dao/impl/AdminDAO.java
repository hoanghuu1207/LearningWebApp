package dao.impl;

import dao.DAOInterface;
import model.AdminModel;
import model.UserModel;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminDAO  implements DAOInterface<AdminModel> {
    @Override
    public int insert(AdminModel adminModel) {
        return 0;
    }

    @Override
    public int update(AdminModel adminModel) {
        return 0;
    }

    @Override
    public int delete(AdminModel adminModel) {
        return 0;
    }

    @Override
    public ArrayList<AdminModel> selectAll() {
        return null;
    }

    @Override
    public AdminModel selectById(int id) {
        return null;
    }

    public AdminModel getUserByUsername(String username) {
        AdminModel adminModel = null;
        try {
            Connection con = JDBCUtil.getConnection();

            String query = "SELECT * FROM admin WHERE username = ?";

            PreparedStatement pstm = con.prepareStatement(query);

            pstm.setString(1, username);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                String username1 = rs.getString("username");
                String password = rs.getString("password");
                String tokenAdmin = rs.getString("token");

                adminModel = new AdminModel();

                adminModel.setUsername(username1);
                adminModel.setPassword(password);
                adminModel.setToken(tokenAdmin);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminModel;
    }

    public AdminModel getAdminByTokenUser(String token) {
        AdminModel adminModel = null;

        try {
            Connection con = JDBCUtil.getConnection();

            String query = "SELECT * FROM admin WHERE token = ?";

            PreparedStatement pstm = con.prepareStatement(query);

            pstm.setString(1, token);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String tokenAdmin = rs.getString("token");

                adminModel = new AdminModel();

                adminModel.setUsername(username);
                adminModel.setPassword(password);
                adminModel.setToken(tokenAdmin);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminModel;
    }
}
