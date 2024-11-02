<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Class Detail</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../assets/fonts/themify-icons-font/themify-icons/themify-icons.css">
    <link rel="stylesheet" href="../../assets/css/style.css">
</head>

<body>
    <div class="container-fluid m-5 p-0">
        <div class="row">
            <!-- Sidebar -->
            <div class="col-md-3 col-lg-2 sidebar">
                <div class="d-flex flex-column">
                    <div class="p-3 mb-2 border-bottom">
                        <h5 id="class_name">${classroom.title}</h5>
                    </div>
                    <div class="list-group">
                        <a href="post.html" target="main-content-class" class="list-group-item list-group-item-action">Trang chủ</a>
                        <a href="assignment_student.html" target="main-content-class" class="list-group-item list-group-item-action">Bài tập</a>
                        <a href="file.html" target="main-content-class" class="list-group-item list-group-item-action">Tài liệu</a>
                        <a href="#" class="list-group-item list-group-item-action">Danh sách</a>
                        <a href="#" class="list-group-item list-group-item-action">Điểm số</a>
                        <a href="#" class="list-group-item list-group-item-action">Lịch</a>
                        <a href="/views/clients/pages/class/prepare_meeting.jsp" target="_parent" class="list-group-item list-group-item-action">
                            Tạo cuộc họp
                            <img src="/views/clients/assets/fonts/myself-icons/ic_video_camera.png" class="icon-btn" alt="">
                        </a>
                    </div>
                </div>
            </div>

            <!-- Main Content -->
            <div class="col-md-9 col-lg-10">
                <iframe name="main-content-class" src="post.html"></iframe>
            </div>
        </div>
    </div>

    <script src="../../assets/js/script.js"></script>
</body>

</html>
