<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Class Members</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../assets/css/style.css">
    <style>
        .member-list-container {
            padding: 20px;
            background-color: #f4f4f4;
        }
        .member-group {
            margin-bottom: 30px;
        }
        .member-group-title {
            font-size: 1.2rem;
            font-weight: bold;
            margin-bottom: 15px;
        }
        .member-item {
            display: flex;
            align-items: center;
            padding: 10px 0;
            border-bottom: 1px solid #ddd;
        }
        .member-item img {
            width: 40px;
            height: 40px;
            border-radius: 50%;
            margin-right: 15px;
        }
        .member-info {
            flex-grow: 1;
        }
        .member-role {
            font-weight: bold;
        }
    </style>
</head>

<body>
<div class="container member-list-container">
    <h3>Danh sách thành viên trong lớp</h3>

    <!-- (Giáo viên) -->
    <div class="member-group">
        <div class="member-group-title">Giáo viên</div>
        <c:forEach var="teacher" items="${teachers}">
            <div class="member-item">
                <img src="/views/clients/assets/img/user_icon.png" alt="${teacher.lastName}">
                <div class="member-info">
                    <div>${teacher.firstName} ${teacher.lastName}</div>
                    <div class="member-role">Chủ sở hữu</div>
                </div>
            </div>
        </c:forEach>
    </div>

    <!-- Members (Học viên) -->
    <div class="member-group">
        <div class="member-group-title">Học viên</div>
        <c:forEach var="student" items="${students}">
            <div class="member-item">
                <img src="/views/clients/assets/img/user_icon.png" alt="${student.lastName}">
                <div class="member-info">
                    <div>${student.firstName} ${student.lastName}</div>
                    <div class="member-role">Thành viên</div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>

</html>
