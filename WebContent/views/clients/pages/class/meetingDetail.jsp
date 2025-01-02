<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Meeting Details</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
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
        .main-content-class .nav-link {
            margin-right: 20px;
            background-color: transparent !important;
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
                <a href="/class/detail?classID=${classroom.classroomID}"
                   class="list-group-item list-group-item-action">Trang chủ</a>
                <a href="/class_assignments?classroomID=${classroom.classroomID}"
                   class="list-group-item list-group-item-action">Bài tập</a>
                <a href="/materials?classroomID=${classroom.classroomID}"
                   class="list-group-item list-group-item-action">Tài liệu</a>
                <a href="/class_members?classId=${classroom.classroomID}"
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
        <div class="container my-5">
            <h1 class="mb-4">Chi Tiết Cuộc Họp</h1>
            <c:choose>
                <c:when test="${type == 1}">
                    <div class="card">
                        <div class="card-header bg-primary text-white">
                            <h3>${meeting.title}</h3>
                        </div>
                        <div class="card-body">
                            <c:forEach var="teacher" items="${teachers}">
                                <h4>Được lên lịch bởi: ${teacher.firstName} ${teacher.lastName}</h4>
                            </c:forEach>
                            <p>Đã lên lịch vào lúc: <fmt:formatDate value="${schedule.timeCreate}" pattern="dd/MM/yyyy HH:mm" /></p>
                            <p>Thời gian bắt đầu cuộc họp: <fmt:formatDate value="${meeting.startTime}" pattern="dd/MM/yyyy HH:mm" /></p>
                        </div>
                    </div>
                </c:when>

                <c:when test="${type == 2}">
                    <div class="card">
                        <div class="card-header bg-primary text-white">
                            <h3>${meeting.title}</h3>
                        </div>
                        <div class="card-body">
                            <p><strong>Thời gian bắt đầu:</strong>
                                <fmt:formatDate value="${meeting.startTime}" pattern="dd/MM/yyyy HH:mm" /></p>
                        </div>
                        <form action="/prepareMeeting" method="post" class="mt-4">
                            <input type="hidden" name="meetingID" value="${meeting.meetingID}" />
                            <input type="hidden" name="classroomID" value="${meeting.classroomID}" />
                            <button type="submit" class="btn btn-success">Tham gia Cuộc Họp</button>
                        </form>
                    </div>
                </c:when>

                <c:when test="${type == 3}">
                    <div class="card">
                        <div class="card-header bg-primary text-white">
                            <h3>${meeting.title}</h3>
                        </div>
                        <div class="card-body">
                            <p>Thời gian bắt đầu cuộc họp: <fmt:formatDate value="${meeting.startTime}" pattern="dd/MM/yyyy HH:mm" /></p>
                            <p>Thời gian kết thúc cuộc họp: <fmt:formatDate value="${meeting.endTime}" pattern="dd/MM/yyyy HH:mm" /></p>
                            <p>Thời lượng cuộc họp: ${meeting.duration}</p>
                        </div>
                    </div>
                </c:when>

                <c:when test="${type == 4}">
                    <div class="card">
                        <div class="card-header bg-primary text-white">
                            <h3>${meeting.title}</h3>
                        </div>
                        <div class="card-body">
                            <c:forEach var="teacher" items="${teachers}">
                                <h4>Được lên lịch bởi: ${teacher.firstName} ${teacher.lastName}</h4>
                            </c:forEach>
                            <p>Đã lên lịch vào lúc: <fmt:formatDate value="${schedule.timeCreate}" pattern="dd/MM/yyyy HH:mm" /></p>
                            <p>Cuộc họp đã được hủy vào lúc: <fmt:formatDate value="${meeting.endTime}" pattern="dd/MM/yyyy HH:mm" /></p>
                            <p class="text-danger">Cuộc họp đã hủy!</p>
                        </div>
                    </div>

                </c:when>
            </c:choose>
        </div>
    </div>
</div>
</body>
</html>
