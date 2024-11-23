<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bài tập</title>
</head>
<body>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<style>
    .container-class {
        display: flex;
        width: 100%;
        height: 100%;
    }

    .sidebar {
        width: 250px;
        height: 100vh;
        overflow-y: auto;
        background-color: #f8f9fa;
    }

    .main-content-class {
        flex-grow: 1;
        padding: 20px;
    }
    .table-container {
        max-height: 300px;
        overflow-y: auto;
    }
    .table th {
        background-color: #535456;
        color: white;
    }
    .fixed-bottom {
        position: relative;
        bottom: -10px;
        height: auto;
        width: 100%;
        background: rgba(221, 221, 221, 0.8);
        padding: 20px;
    }
    .btn-primary {
        background-color: #4b77dd;
        border: none;
    }
    .btn-primary:hover {
        background-color: #e9ecef;
        color: black;
    }
</style>
<div class="container-class">
    <div class="sidebar">
        <div class="d-flex flex-column">
            <div class="p-3 mb-2 border-bottom">
                <h5 id="class_name">${classroom.title}</h5>
            </div>
            <div class="list-group">
               <a href="${user.roleID == 2 ? '/teacher' : ''}/class/detail?classID=${classroom.classroomID}"
                   class="list-group-item list-group-item-action">Trang chủ</a>
               <a href="${user.roleID == 2 ? '/teacher' : ''}/class_assignments?classroomID=${classroom.classroomID}"
                  class="list-group-item list-group-item-action">Bài tập</a>
               <a href="/materials?classroomID=${classroom.classroomID}"
                  class="list-group-item list-group-item-action">Tài liệu</a>
               <a href="${user.roleID == 2 ? '/teacher' : ''}/class_members?classId=${classroom.classroomID}"
                  class="list-group-item list-group-item-action">Danh sách</a>
               <a href="/meetings?classroomID=${classroom.classroomID}"
                  class="list-group-item list-group-item-action">Cuộc họp</a>
               <!--<a href="?page=schedule" class="list-group-item list-group-item-action">Lịch</a>-->
               <a href="/prepareMeeting?classroomID=${classroom.classroomID}" target="_parent" class="list-group-item list-group-item-action">
                    Tạo cuộc họp
                  <img src="/views/clients/assets/fonts/myself-icons/ic_video_camera.png" class="icon-btn" alt="">
               </a>
            </div>
        </div>
    </div>

    <div class="main-content-class">
        <div class="container">
            <h2 class="mb-4">Bài tập</h2>
            <div class="table-container">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Tiêu đề bài tập</th>
                        <th>Thời gian hết hạn</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="assignment" items="${assignments}">
                        <tr>
                            <td>${assignment.title}</td>
                            <td><fmt:formatDate value="${assignment.endTime}" pattern="HH:mm dd/MM/yyyy"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="fixed-bottom">
            <form action="/teacher/class_assignments" method="POST" enctype="multipart/form-data">
                <h2 class="mb-4">Tạo bài tập</h2>
                <div class="form-group">
                    <label for="datetime">Thời gian hết hạn</label>
                    <input type="datetime-local" class="form-control" id="datetime" name="datetime" required>
                </div>
                <div class="form-group">
                    <label for="title">Tiêu đề</label>
                    <input type="text" class="form-control" id="title" name="title" required placeholder="Nhập tiêu đề cho bài tập">
                </div>
                <div class="form-group">
                    <label for="description">Mô tả</label>
                    <input type="text" class="form-control" id="description" name="description" required placeholder="Nhập mô tả cho bài tập">
                </div>
                <div class="upload-section">
                   <label for="file">Tài liệu tham khảo</label>
                     <div class="mb-3">
                        <input type="hidden" name="classID" value="${classroom.classroomID}">
                       <input type="file" name="file" class="form-control" required>
                     </div>
                 </div>
                <button type="submit" class="btn btn-primary">Thêm Bài Tập</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>
