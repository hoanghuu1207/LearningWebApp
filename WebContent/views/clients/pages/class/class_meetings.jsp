<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cuộc Họp</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f4f4f4;
        }

        .container {
            background: rgba(224, 219, 219, 0.38);
            border-radius: 8px;
            box-shadow: 0 2px 15px rgba(0, 0, 0, 0.1);
            padding: 30px;
            margin-top: 50px;
        }

        h2 {
            color: #343a40;
        }

        .assignment-table {
            margin-top: 30px;
        }

        .overdue {
            color: red;
        }

        .nav-item:hover {
            border-bottom: 1px solid gray;
        }

        .nav-link {
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .nav-link:hover {
            background-color: rgba(233, 236, 239, 0.5);
        }

        .nav-link.active {
            background-color: #d3d3d3;
            color: #343a40;
        }

        .assignment-card {
            border: 1px solid #e0e0e0;
            border-radius: 5px;
            padding: 10px;
            margin: 5px 0;
            background-color: #fafafa;
        }

        .list-group-item {
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="container my-5">
    <ul class="nav nav-pills mb-3">
        <li class="nav-item">
            <a class="nav-link active" id="upcoming-tab">Cuộc Họp Sắp Tới</a>
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
                <li class="list-group-item" onclick="location.href='/meetingDetail?id=${meeting.meetingID}&classroomID=${classroomID}&type=1'">
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
                <li class="list-group-item" onclick="location.href='/meetingDetail?id=${meeting.meetingID}&classroomID=${classroomID}&type=2'">
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
                <li class="list-group-item" onclick="location.href='/meetingDetail?id=${meeting.meetingID}&classroomID=${classroomID}&type=3'">
                    <strong>${meeting.title}</strong> <br>
                    <span class="text-danger"> - Đã Hủy</span>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>

<script>
    function showTab(tabId) {
        document.getElementById('upcoming').style.display = 'none';
        document.getElementById('past').style.display = 'none';
        document.getElementById('canceled').style.display = 'none';
        document.getElementById(tabId).style.display = 'block';

        document.querySelectorAll('.nav-link').forEach(link => link.classList.remove('active'));
        document.getElementById(tabId + '-tab').classList.add('active');
    }

    document.getElementById('upcoming-tab').addEventListener('click', () => showTab('upcoming'));
    document.getElementById('past-tab').addEventListener('click', () => showTab('past'));
    document.getElementById('canceled-tab').addEventListener('click', () => showTab('canceled'));
</script>
</body>
</html>
