package model;

import java.time.LocalDateTime;

public class ForgotPasswordModel {
	private int id;
	private String email;
	private String otp;
	private LocalDateTime expireAt;

	public ForgotPasswordModel(String email, String otp, LocalDateTime expireAt) {
		super();
		this.email = email;
		this.otp = otp;
		this.expireAt = expireAt;
	}

	public ForgotPasswordModel() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public LocalDateTime getExpireAt() {
		return expireAt;
	}

	public void setExpireAt(LocalDateTime expireAt) {
		this.expireAt = expireAt;
	}
}
