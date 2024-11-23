<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Class Detail</title>
</head>

<body>
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
    }

    .assignment-table {
        margin-top: 30px;
    }

    .overdue {
        color: red;
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
        <div class="container my-5">
            <ul class="nav nav-pills mb-3">
                <li class="nav-item">
                    <a class="nav-link active" id="not-submitted-tab">Bài Tập Chưa Nộp</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="submitted-tab">Bài Tập Đã Nộp</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="overdue-tab">Bài Tập Quá Hạn</a>
                </li>
            </ul>

            <div id="not-submitted" class="assignment-table mt-3">
                <h3>Bài Tập Chưa Nộp</h3>
                <ul class="list-group">
                    <c:forEach var="assignment" items="${notSubmitted}">
                        <li class="list-group-item" onclick="location.href='assignmentDetail.jsp?id=${assignment.assignmentID}'">
                            <strong>${assignment.title} </strong> (Hạn nộp: <fmt:formatDate value="${assignment.endTime}" pattern="dd/MM/yyyy HH:mm" />)
                        </li>
                    </c:forEach>
                </ul>
            </div>

            <div id="submitted" class="assignment-table mt-3" style="display: none;">
                <h3>Bài Tập Đã Nộp</h3>
                <ul class="list-group">
                    <c:forEach var="assignment" items="${submitted}">
                        <li class="list-group-item" onclick="location.href='assignmentDetail.jsp?id=${assignment.assignmentID}'">
                            <strong> ${assignment.title} </strong> (Hạn nộp: <fmt:formatDate value="${assignment.endTime}" pattern="dd/MM/yyyy HH:mm" />)
                        </li>
                    </c:forEach>
                </ul>
            </div>

            <div id="overdue" class="assignment-table mt-3" style="display: none;">
                <h3>Bài Tập Quá Hạn</h3>
                <ul class="list-group">
                    <c:forEach var="assignment" items="${overdue}">
                        <li class="list-group-item" onclick="location.href='assignmentDetail.jsp?id=${assignment.assignmentID}'">
                            <strong> ${assignment.title} </strong> (Hạn nộp: <fmt:formatDate value="${assignment.endTime}" pattern="dd/MM/yyyy HH:mm" />)
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>

</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    // Chuyển đổi giữa các tab
    function showTab(tabId) {
        document.getElementById('not-submitted').style.display = 'none';
        document.getElementById('submitted').style.display = 'none';
        document.getElementById('overdue').style.display = 'none';
        document.getElementById(tabId).style.display = 'block';

        document.querySelectorAll('.nav-link').forEach(link => {
            link.classList.remove('active');
        });
        document.getElementById(tabId + '-tab').classList.add('active');
    }

    document.getElementById('not-submitted-tab').addEventListener('click', () => showTab('not-submitted'));
    document.getElementById('submitted-tab').addEventListener('click', () => showTab('submitted'));
    document.getElementById('overdue-tab').addEventListener('click', () => showTab('overdue'));

    // Khi click vào bài tập chưa nộp
    document.querySelectorAll('#not-submitted .list-group-item').forEach(item => {
        item.addEventListener('click', function () {
            $('#submissionModal').modal('show');
            document.getElementById('selected-assignment').value = this.textContent;
            document.getElementById('submission-form').dataset.deadline = this.dataset.deadline;
        });
    });

    // Xử lý sự kiện nộp bài
    document.getElementById('submission-form').addEventListener('submit', function (event) {
        event.preventDefault();
        const assignment = document.getElementById('selected-assignment').value;
        const content = document.getElementById('content').value;
        const deadline = this.dataset.deadline;
        const submissionStatus = new Date(deadline) >= new Date() ? 'Đúng Hạn' : 'Quá Hạn';

        let newAssignment;
        if (submissionStatus === 'Đúng Hạn') {
            newAssignment = `<div class="assignment-card">
                                    <strong>${assignment}</strong> - ${content}<br>
                                    Hạn nộp: ${deadline.replace('T', ' ')} <span class="text-success">(${submissionStatus})</span>
                                    <button class="btn btn-link resubmit-btn">Nộp Lại</button>
                                </div>`;
            document.getElementById('submitted-assignments').innerHTML += newAssignment;
        } else {
            newAssignment = `<div class="assignment-card overdue">
                                    <strong>${assignment}</strong> - ${content}<br>
                                    Hạn nộp: ${deadline.replace('T', ' ')} <span class="text-danger">(${submissionStatus})</span>
                                    <button class="btn btn-link resubmit-btn">Nộp Lại</button>
                                </div>`;
            document.getElementById('overdue-assignments').innerHTML += newAssignment;
        }

        this.reset();
        $('#submissionModal').modal('hide'); // Ẩn modal sau khi nộp bài
        showTab('submitted'); // Hiển thị tab đã nộp

        // Đăng ký sự kiện cho nút nộp lại
        registerResubmitEvents();
    });

    // Đăng ký sự kiện cho nút nộp lại
    function registerResubmitEvents() {
        document.querySelectorAll('.resubmit-btn').forEach(btn => {
            btn.addEventListener('click', function () {
                const assignmentCard = btn.closest('.assignment-card');
                const assignmentText = assignmentCard.querySelector('strong').textContent;
                const contentText = assignmentCard.childNodesdescription[1].textContent.split('-')[1].trim();

                document.getElementById('resubmission-assignment').value = assignmentText;
                document.getElementById('resubmission-content').value = contentText;
                $('#resubmissionModal').modal('show');
            });
        });
    }

    // Xử lý sự kiện nộp lại bài
    document.getElementById('resubmission-form').addEventListener('submit', function (event) {
        event.preventDefault();
        const assignment = document.getElementById('resubmission-assignment').value;
        const content = document.getElementById('resubmission-content').value;

        const newResubmission = `<div class="assignment-card">
                                        <strong>${assignment}</strong> - ${content}<br>
                                        <span class="text-info">(Bài nộp lại)</span>
                                    </div>`;
        document.getElementById('submitted-assignments').innerHTML += newResubmission;

        this.reset();
        $('#resubmissionModal').modal('hide'); // Ẩn modal sau khi nộp lại
    });

</script>
</body>
</html>
