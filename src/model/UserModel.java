package model;

import java.util.Random;

public class UserModel {
	private int userID;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String avatar;
	private int roleID;
	private String tokenUser;

	public UserModel(String firstName, String lastName, String email, String password, String avatar, int roleID) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.avatar = avatar;
		this.roleID = roleID;

		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";

		Random rand = new Random();

		String tokenUser = "";
		for (int i = 0; i < 70; i++) {
			tokenUser += characters.charAt(rand.nextInt(characters.length()));
		}

		this.tokenUser = tokenUser;
	}

	public UserModel() {
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	public String getTokenUser() {
		return tokenUser;
	}

	public void setTokenUser(String tokenUser) {
		this.tokenUser = tokenUser;
	}
}
