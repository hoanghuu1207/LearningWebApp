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
      align-items: center;
      color: #333;
      overflow: hidden;
      height:100vh;
    }

    .account-img{
      position: relative;
      width: 600px;
      height: 100%;
      background-size: cover;
      background-position: center;
      overflow: hidden;
    }

    .overlay {
      position: absolute;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      background-color: rgba(143, 143, 143, 0.5);
      z-index: 1;
    }

    .account-container {
      width: 100%;
      text-align: center;
      font-size: 1.2em;
      margin: 20px;
    }

    .profile-pic {
      border-radius: 50%;
      margin: 100px;
      border: 3px solid #dcd9d9;
      position: relative;
      z-index: 2;
    }

    .profile-pic img {
      width: 100%;
      height: 100%;
      object-fit: cover;
      border-radius: 50%;
    }

    .upload-btn {
      position: absolute;
      bottom: 0;
      right: 0;
      height: 30px;
      width: 30px;
      background-color: #333;
      color: #fff;
      border: none;
      padding: 8px;
      border-radius: 50%;
      cursor: pointer;
      font-size: 1.5em;
      display: flex;
      justify-content: center;
      align-items: center;
      transition: background 0.3s;
    }

    .fa-camera{
      object-fit: cover;
      border-radius: 50%;
      font-size: 1em;
    }

    .upload-btn:hover {
      background-color: #555;
    }

    .profile-inf {
      position: absolute;
      top: 38%;
      left: 50%;
      transform: translateX(-50%);
      z-index: 2;
      font-size: 1.2em;
      color: white;
      text-align: center;
    }

    h2 {
      font-size: 2.5em;
      color: #333;
      margin-bottom: 20px;
      font-weight: 600;
    }

    .account-info {
      margin: 3% 20%;
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

<div class="account-img" style="background-image: url('/views/clients/assets/img/account_background.jpg');">
  <div class="overlay">
  </div>
  <div class="profile-pic">
    <img src="<%= request.getContextPath() %>/views/clients/assets/img/default-avatar2.png" alt="Profile Picture">
    <label for="avatar-upload" class="upload-btn"><i class="fas fa-camera"></i></label>
    <input type="file" id="avatar-upload" name="avatar" accept="image/*" style="display:none;">

  </div>
  <div class="profile-inf">
    <p class="text-white center">mail@gmail.com</p>
  </div>

</div>



<div class="account-container">
  <form action="<%= request.getContextPath() %>/user/update" method="post" enctype="multipart/form-data">

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
