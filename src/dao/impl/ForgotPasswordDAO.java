package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import dao.DAOInterface;
import model.ForgotPasswordModel;
import util.JDBCUtil;

public class ForgotPasswordDAO implements DAOInterface<ForgotPasswordModel> {

	@Override
	public int insert(ForgotPasswordModel t) {
		int row = 0;
		try {
			Connection con = JDBCUtil.getConnection();

			String query = "INSERT INTO forgot_password (email, otp, expireAt) " + "VALUES (?, ?, ?)";

			PreparedStatement pstm = con.prepareStatement(query);

			pstm.setString(1, t.getEmail());
			pstm.setString(2, t.getOtp());

			LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh")).plusSeconds(120);
			Timestamp timestamp = Timestamp.valueOf(now);

			pstm.setTimestamp(3, timestamp);

			row = pstm.executeUpdate();

			if (row != 0) {
				System.out.println("Th�m th�nh c�ng: " + row);
			}

			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}

	@Override
	public int update(ForgotPasswordModel t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(ForgotPasswordModel t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<ForgotPasswordModel> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ForgotPasswordModel selectById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ForgotPasswordModel getForgotPasswordByEmailOtp(String email, String otp) {
		ForgotPasswordModel forgotPasswordModel = null;
		try {
			Connection con = JDBCUtil.getConnection();

			String query = "SELECT * FROM forgot_password WHERE email = ? AND otp = ?";

			PreparedStatement pstm = con.prepareStatement(query);

			pstm.setString(1, email);
			pstm.setString(2, otp);

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				forgotPasswordModel = new ForgotPasswordModel();

				forgotPasswordModel.setEmail(email);
				forgotPasswordModel.setOtp(otp);
			}

			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return forgotPasswordModel;
	}
}
