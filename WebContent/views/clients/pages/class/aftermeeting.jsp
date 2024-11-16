<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Thank You for Joining the Meeting</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
      background: linear-gradient(120deg, #84fab0, #8fd3f4);
      color: #fff;
      font-family: 'Arial', sans-serif;
    }
    .container {
      height: 100vh;
      text-align: center;
    }
    .content-wrapper {
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      height: 80%; /* Chiều cao 100% của container */
    }
    .thank-you-text {
      font-size: 2.5rem;
      font-weight: bold;
    }
    .options {
      margin-top: 2rem;
    }
    .btn-custom {
      background-color: #6c63ff;
      color: white;
      padding: 0.8rem 2rem;
      font-size: 1.2rem;
      border-radius: 30px;
      transition: transform 0.2s, background-color 0.3s;
    }
    .btn-custom:hover {
      transform: scale(1.1);
      background-color: #5149de;
    }
  </style>
</head>
<body>
<div class="container">
  <!-- Thêm lớp content-wrapper để căn giữa nội dung -->
  <div class="content-wrapper">
    <div class="thank-you-text">
      Cảm ơn bạn đã tham gia cuộc họp! 🎉
    </div>
    <h5 class="mt-3">
      Chúng tôi hy vọng cuộc họp đã mang lại giá trị cho bạn!
    </h5>
    <div class="options">
      <a href="class_detail.jsp" class="btn btn-custom ms-3">Quay lại Lớp học</a>
    </div>
  </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
