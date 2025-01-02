<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Class Detail</title>
    <link rel="stylesheet" href="../../assets/fonts/themify-icons-font/themify-icons/themify-icons.css">
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

        .main-content-class .nav-link.active,
        .main-content-class .nav-link:hover {
            border-end-end-radius: unset !important;
            border-end-start-radius: unset !important;
            border-bottom: 2px solid #156cc1;
            transform: scale(1.05);
            color: black !important;
            background-color: rgba(88, 171, 251, 0.5) !important;
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
                    Tạo cuộc họp <img src="/views/clients/assets/fonts/myself-icons/ic_video_camera.png" class="icon-btn" alt="">
                </a>
            </div>
        </div>
    </div>

    <div class="main-content-class">
       <div class="container my-5">
           <ul class="nav nav-pills mb-3">
               <li class="nav-item">
                   <a class="nav-link active" id="upcoming-tab">Cuộc Họp Sắp Tới</a>
               </li>
               <li class="nav-item">
                   <a class="nav-link" id="ongoing-tab">Cuộc Họp Đang Diễn Ra</a>
               </li>
               <li class="nav-item">
                   <a class="nav-link" id="past-tab">Cuộc Họp Đã Diễn Ra</a>
               </li>
               <li class="nav-item">
                   <a class="nav-link" id="canceled-tab">Cuộc Họp Đã Hủy</a>
               </li>
           </ul>

           <!-- Upcoming Meetings -->
           <div id="upcoming" class="meeting-table mt-3">
               <h3>Cuộc Họp Sắp Tới</h3>
               <ul class="list-group">
                   <c:forEach var="meeting" items="${upcomingMeetings}">
                       <li class="list-group-item" onclick="location.href='/meetingDetail?id=${meeting.meetingID}&classroomID=${meeting.classroomID}&type=1'">
                           <strong>${meeting.title}</strong> <br>
                           <span> Thời gian bắt đầu: <fmt:formatDate value="${meeting.startTime}" pattern="dd/MM/yyyy HH:mm" /></span>
                       </li>
                   </c:forEach>
               </ul>
           </div>
           <!-- Ongoing Meetings -->
           <div id="ongoing" class="meeting-table mt-3" style="display: none;">
               <h3>Cuộc Họp Đang Diễn Ra</h3>
               <ul class="list-group">
                   <c:forEach var="meeting" items="${ongoingMeetings}">
                       <li class="list-group-item" onclick="location.href='/meetingDetail?id=${meeting.meetingID}&classroomID=${meeting.classroomID}&type=2'">
                           <strong>${meeting.title}</strong> <br>
                           <span> Thời gian bắt đầu: <fmt:formatDate value="${meeting.startTime}" pattern="dd/MM/yyyy HH:mm" /></span>
                       </li>
                   </c:forEach>
               </ul>
           </div>

           <!-- Past Meetings -->
           <div id="past" class="meeting-table mt-3" style="display: none;">
               <h3>Cuộc Họp Đã Diễn Ra</h3>
               <ul class="list-group">
                   <c:forEach var="meeting" items="${pastMeetings}">
                       <li class="list-group-item" onclick="location.href='/meetingDetail?id=${meeting.meetingID}&classroomID=${meeting.classroomID}&type=3'">
                           <strong>${meeting.title}</strong> <br>
                           <span> Thời gian diễn ra: <fmt:formatDate value="${meeting.startTime}" pattern="dd/MM/yyyy HH:mm" />
                                - Thời gian kết thúc: <fmt:formatDate value="${meeting.endTime}" pattern="dd/MM/yyyy HH:mm"/>
                               - Thời lượng: <fmt:formatDate value="${meeting.duration}" pattern="HH:mm:ss"/> </span>
                       </li>
                   </c:forEach>
               </ul>
           </div>

           <!-- Canceled Meetings -->
           <div id="canceled" class="meeting-table" style="display: none;">
               <h3>Cuộc Họp Đã Hủy</h3>
               <ul class="list-group">
                   <c:forEach var="meeting" items="${canceledMeetings}">
                       <li class="list-group-item" onclick="location.href='/meetingDetail?id=${meeting.meetingID}&classroomID=${meeting.classroomID}&type=4'">
                           <strong>${meeting.title}</strong> <br>
                           <span class="text-danger"> - Đã Hủy</span>
                       </li>
                   </c:forEach>
               </ul>
           </div>
       </div>
    </div>

</div>
<script>
    function showTab(tabId) {
        document.getElementById('upcoming').style.display = 'none';
        document.getElementById('past').style.display = 'none';
        document.getElementById('ongoing').style.display = 'none';
        document.getElementById('canceled').style.display = 'none';
        document.getElementById(tabId).style.display = 'block';

        document.querySelectorAll('.nav-link').forEach(link => link.classList.remove('active'));
        document.getElementById(tabId + '-tab').classList.add('active');
    }

    document.getElementById('upcoming-tab').addEventListener('click', () => showTab('upcoming'));
    document.getElementById('past-tab').addEventListener('click', () => showTab('past'));
    document.getElementById('canceled-tab').addEventListener('click', () => showTab('canceled'));
    document.getElementById('ongoing-tab').addEventListener('click', () => showTab('ongoing'));

</script>
</body>
</html>