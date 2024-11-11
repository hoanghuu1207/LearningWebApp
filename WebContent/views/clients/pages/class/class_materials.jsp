<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Class Materials</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
  <style>
    body {
      height: 100vh;
      overflow-x: hidden;
      margin: 0;
      padding: 10px;
      box-sizing: border-box;
      background: linear-gradient(to right, rgba(185, 180, 180, 0.61) 0%, rgba(255, 255, 255, 0.99) 100%);
    }
    .materials-container {
      max-height: 300px;
      overflow-y: auto;
      margin-bottom: 20px;
      border: 1px solid #483939;
      padding: 10px;
    }
    .upload-section {
      position: fixed;
      bottom: 5px;
      left: 50px;
      right: 50px;
      padding: 5px;
      background-color: #98d4d1;
      box-shadow: 0 -2px 5px rgba(0, 0, 0, 0.1);
    }
  </style>
</head>
<body>
<div class="container mt-5">
  <h1>Tài liệu học tập của lớp ${classroom.title}</h1> </br>

  <!-- Materials List -->
  <div class="materials-container">
    <table class="table table-striped">
      <thead>
      <tr>
        <th>Tên file</th>
        <th></th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="material" items="${materials}">
        <tr>
          <td>${material.title}</td>
          <td>
            <a href="/downloadFile?filePath=${material.filePath}"
               class="btn btn-primary btn-sm" download>Tải xuống</a>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>

  <div class="upload-section">
    <h4>Tải lên Tài liệu</h4>
    <form action="materials" method="post" enctype="multipart/form-data">
      <input type="hidden" name="classroomID" value="${classroom.classroomID}" />
      <div class="mb-3">
        <input type="file" name="file" class="form-control" required>
      </div>
      <button type="submit" class="btn btn-success">Tải lên</button>
    </form>
  </div>
</div>
</body>
</html>
