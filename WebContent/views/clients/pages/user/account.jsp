<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Account Settings</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
  <style>
    * {
      margin: 0;
      padding: 0;
    }

    body {
      margin: 10px;
      font-family: Arial, sans-serif;
      background-color: #f5f5f5;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      color: #333;
      overflow: hidden;
    }

    .account-container {
      width: 100%;
      padding: 40px;
      text-align: center;
      font-size: 1.2em;
    }

    .profile-pic {
      width: 140px;
      height: 140px;
      border-radius: 50%;
      margin: 0 auto 20px;
      border: 3px solid #827c7c;
      position: relative;
    }

    .profile-pic img {
      width: 90%;
      height: 92%;
      object-fit: fill;
    }

    .upload-btn {
      position: absolute;
      bottom: 0;
      right: 0;
      height: 36px;
      width: 36px;
      background-color: #333;
      color: #fff;
      border: none;
      padding: 8px;
      border-radius: 50%;
      cursor: pointer;
      font-size: 1.5em;
      transition: background 0.3s;
    }

    .upload-btn:hover {
      background-color: #555;
    }

    h2 {
      font-size: 2.5em;
      color: #333;
      margin-bottom: 20px;
      font-weight: 600;
    }

    .account-info {
      margin: 10px calc(30%);
      text-align: left;
    }

    .account-info label {
      font-weight: bold;
      margin-bottom: 5px;
      display: block;
      color: #444;
    }

    .account-info input[type="text"],
    .account-info input[type="email"],
    .account-info input[type="password"],
    .account-info input[type="file"] {
      width: 100%;
      padding: 12px;
      margin: 5px 0 15px;
      border: 1px solid #ccc;
      border-radius: 8px;
      font-size: 1em;
    }

    .account-info input[type="password"]:focus,
    .account-info input[type="text"]:focus,
    .account-info input[type="email"]:focus {
      border-color: #333;
      outline: none;
    }

    .button-save {
      background-color: #333;
      color: #fff;
      padding: 12px 30px;
      border: none;
      border-radius: 8px;
      font-size: 1.2em;
      cursor: pointer;
      transition: background 0.3s;
    }

    .button-save:hover {
      background-color: #555;
    }

    .error-message {
      color: red;
      font-size: 1em;
      margin-top: 10px;
    }
  </style>
</head>
<body>
<div class="account-container">
  <form action="<%= request.getContextPath() %>/user/update" method="post" enctype="multipart/form-data">
    <!-- Profile Picture -->
    <div class="profile-pic">
      <img src="<%= request.getContextPath() %>/views/clients/assets/img/default-avatar.png" alt="Profile Picture">
      <label for="avatar-upload" class="upload-btn"><i class="fas fa-camera"></i></label>
      <input type="file" id="avatar-upload" name="avatar" accept="image/*" style="display:none;">
    </div>
    <div class="account-info">
      <label for="firstName">First Name</label>
      <input type="text" id="firstName" name="firstName" value="<%= request.getAttribute("firstName") %>" required>

      <label for="lastName">Last Name</label>
      <input type="text" id="lastName" name="lastName" value="<%= request.getAttribute("lastName") %>" required>

      <label for="email">Email</label>
      <input type="email" id="email" name="email" value="<%= request.getAttribute("email") %>" required>

      <label for="password">Password</label>
      <input type="password" id="password" name="password" required>

      <label for="confirmPassword">Confirm Password</label>
      <input type="password" id="confirmPassword" name="confirmPassword" required>
      <div class="error-message" id="passwordError" style="display:none;">Passwords do not match!</div>
    </div>

    <button type="submit" class="button-save" onclick="return validatePasswords()">Save</button>
  </form>
</div>

<script>
  function validatePasswords() {
    var password = document.getElementById("password").value;
    var confirmPassword = document.getElementById("confirmPassword").value;

    if (password !== confirmPassword) {
      document.getElementById("passwordError").style.display = "block";
      return false; // Prevent form submission
    } else {
      document.getElementById("passwordError").style.display = "none";
      return true; // Allow form submission
    }
  }
</script>

</body>
</html>
