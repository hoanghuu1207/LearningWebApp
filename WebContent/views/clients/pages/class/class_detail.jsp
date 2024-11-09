<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Class Detail</title>
    <!--<link rel="stylesheet" href="../../assets/fonts/themify-icons-font/themify-icons/themify-icons.css">-->
    <!--<link rel="stylesheet" href="/views/clients/assets/css/style.css">-->
    <style>
        .container-class{
            display: flex;
            width: 100%;
            height:100%;
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
            overflow-y: auto;
            height: 100vh;
        }
    </style>
</head>

<body>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<!-- Sidebar -->
<div class = "container-class">
    <div class="sidebar">
        <div class="d-flex flex-column">
            <div class="p-3 mb-2 border-bottom">
                <h5 id="class_name">${classroom.title}</h5>
            </div>
            <div class="list-group">
                <a href="?page=post" class="list-group-item list-group-item-action">Trang chủ</a>
                <a href="?page=assignment_student" class="list-group-item list-group-item-action">Bài tập</a>
                <a href="?page=file" class="list-group-item list-group-item-action">Tài liệu</a>
                <a href="?page=students" class="list-group-item list-group-item-action">Danh sách</a>
                <a href="?page=scores" class="list-group-item list-group-item-action">Điểm số</a>
                <!--<a href="?page=schedule" class="list-group-item list-group-item-action">Lịch</a>-->
                <a href="/views/clients/pages/class/prepare_meeting.jsp" class="list-group-item list-group-item-action">
                    Tạo cuộc họp
                    <img src="/views/clients/assets/fonts/myself-icons/ic_video_camera.png" class="icon-btn" alt="">
                </a>
            </div>
        </div>
    </div>

    <!-- Main Content -->
    <div class="main-content-class">
        <c:choose>
            <c:when test="${param.page == 'post'}">
                <jsp:include page="post.jsp"/>
            </c:when>
            <c:when test="${param.page == 'assignment_student'}">
                <jsp:include page="post.jsp"/>
            </c:when>
            <c:when test="${param.page == 'file'}">
                <jsp:include page="post.jsp"/>
            </c:when>
            <c:when test="${param.page == 'students'}">
                <jsp:include page="post.jsp"/>
            </c:when>
            <c:when test="${param.page == 'scores'}">
                <jsp:include page="post.jsp"/>
            </c:when>
            <c:when test="${param.page == 'schedule'}">
                <jsp:include page="post.jsp"/>
            </c:when>
<%--            <c:otherwise>--%>
<%--                <jsp:include page="post.jsp"/>--%>
<%--            </c:otherwise>--%>
        </c:choose>
    </div>
</div>
</body>
</html>
