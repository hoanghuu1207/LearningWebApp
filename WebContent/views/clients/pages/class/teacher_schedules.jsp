<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Lịch Hẹn</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
        }
        .container {
            background: white;
            border-radius: 8px;
            box-shadow: 0 2px 15px rgba(0, 0, 0, 0.1);
            padding: 30px;
            margin-top: 50px;
        }
        h2 {
            color: #343a40;
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
</head>
<body>

<div class="container my-5">
    <h2 class="mb-4">Cuộc Hẹn Sắp Tới</h2>
    <div class="table-container">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Thời gian lên lịch</th>
                <th>Thời gian bắt đầu cuộc họp</th>
                <th>Tiêu đề Lịch hẹn</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="schedule" items="${upcomingSchedules}">
                <tr>
                    <td><fmt:formatDate value="${schedule.timeCreate}" pattern="dd/MM/yyyy HH:mm"/></td>
                    <td><fmt:formatDate value="${schedule.timeAccess}" pattern="dd/MM/yyyy HH:mm"/></td>
                    <td>${schedule.title}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<div class="fixed-bottom">
    <form action="teacher/schedules" method="post">
        <h2 class="mt-5 mb-4">Tạo lịch hẹn</h2>
        <div class="form-group">
            <label for="datetime">Thời Gian</label>
            <input type="datetime-local" class="form-control" id="datetime" name="datetime" required>
        </div>
        <div class="form-group">
            <label for="title">Tiêu đề Lịch hẹn</label>
            <input type="text" class="form-control" id="title" name="title" required placeholder="Nhập tiêu đề cho lịch hẹn">
        </div>
        <div class="form-group">
            <label for="content">Tiêu đề Cuộc họp</label>
            <input type="text" class="form-control" id="content" name="content" required placeholder="Nhập tiêu đề cho cuộc họp">
        </div>
        <input type="hidden" name="classroomID" value="${param.classroomID}">
        <button type="submit" class="btn btn-primary">Thêm Cuộc Hẹn</button>
    </form>
</div>

</body>
</html>
