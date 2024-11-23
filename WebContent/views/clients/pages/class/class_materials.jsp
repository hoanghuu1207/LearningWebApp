<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Class Detail</title>
    <link rel="stylesheet" href="../../assets/fonts/themify-icons-font/themify-icons/themify-icons.css">
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
</style>

<div class = "container-class">
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
       <div class="container mt-5">
         <h1>Tài liệu học tập</h1> </br>

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
           <h4>Tải lên</h4>
           <form action="materials" method="post" enctype="multipart/form-data">
             <input type="hidden" name="classroomID" value="${classroom.classroomID}" />
             <div class="mb-3">
               <input type="file" name="file" class="form-control" required>
             </div>
             <button type="submit" class="btn btn-success">Tải lên</button>
           </form>
         </div>
       </div>
    </div>

</div>
</body>
</html>
