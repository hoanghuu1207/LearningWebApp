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
                        <li class="list-group-item" data-bs-toggle="modal" data-bs-target="#assignmentModal-${assignment.assignmentID}">
                            <strong>${assignment.title} </strong><br>
                            <small>Hạn nộp: <fmt:formatDate value="${assignment.endTime}" pattern="dd/MM/yyyy HH:mm" /></small>
                        </li>
                        <div class="modal fade" id="assignmentModal-${assignment.assignmentID}" tabindex="-1" aria-labelledby="assignmentModalLabel-${assignment.assignmentID}" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="assignmentModalLabel-${assignment.assignmentID}">Bài tập</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="form-group">
                                            <label for="datetime">Thời gian hết hạn</label>
                                            <input type="datetime-local" class="form-control" id="datetime" name="datetime" required value="${assignment.endTime}" readonly>
                                        </div>
                                        <div class="form-group">
                                            <label for="title">Tiêu đề</label>
                                            <input type="text" class="form-control" id="title" name="title" required placeholder="Nhập tiêu đề cho bài tập" value="${assignment.title}" readonly>
                                        </div>
                                        <div class="form-group">
                                            <label for="description">Mô tả</label>
                                            <input type="text" class="form-control" id="description" name="description" required placeholder="Nhập mô tả cho bài tập" value="${assignment.description}" readonly>
                                        </div>
                                        <c:if test="${assignment.titleFile != null && assignment.filePath != null}">
                                            <div class="upload-section">
                                               <label for="file">Tài liệu tham khảo</label>
                                               <div class="mb-3">
                                                    <a class="btn" href="/downloadFile?filePath=${assignment.filePath}" download><i class="fa fa-download"></i> ${assignment.titleFile}</a>
                                               </div>
                                            </div>
                                        </c:if>
                                        <form action="/class_assignments" method="POST" id="form-search">
                                            <label for="file" class="h4">Bài làm của bạn</label>
                                             <div class="mb-3">
                                               <input type="file" name="file" class="form-control" required>
                                             </div>
                                        </form>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-primary" onclick="submitForm()">Nộp bài</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </ul>
            </div>

            <div id="submitted" class="assignment-table mt-3" style="display: none;">
                <h3>Bài Tập Đã Nộp</h3>
                <ul class="list-group">
                    <c:forEach var="entry" items="${submitted}">
                        <li class="list-group-item" data-bs-toggle="modal" data-bs-target="#assignmentModal-${entry.key.assignmentID}">
                            <strong>${entry.key.title} </strong><br>
                            <small>Hạn nộp: <fmt:formatDate value="${entry.key.endTime}" pattern="dd/MM/yyyy HH:mm" /></small>
                        </li>
                        <div class="modal fade" id="assignmentModal-${entry.key.assignmentID}" tabindex="-1" aria-labelledby="assignmentModalLabel-${entry.key.assignmentID}" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="assignmentModalLabel-${entry.key.assignmentID}">Bài tập</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="form-group">
                                            <label for="datetime">Thời gian hết hạn</label>
                                            <input type="datetime-local" class="form-control" id="datetime" name="datetime" required value="${entry.key.endTime}" readonly>
                                        </div>
                                        <div class="form-group">
                                            <label for="title">Tiêu đề</label>
                                            <input type="text" class="form-control" id="title" name="title" required placeholder="Nhập tiêu đề cho bài tập" value="${entry.key.title}" readonly>
                                        </div>
                                        <div class="form-group">
                                            <label for="description">Mô tả</label>
                                            <input type="text" class="form-control" id="description" name="description" required placeholder="Nhập mô tả cho bài tập" value="${entry.key.description}" readonly>
                                        </div>
                                        <c:if test="${entry.key.titleFile != null && entry.key.filePath != null}">
                                            <div class="upload-section">
                                               <label for="file">Tài liệu tham khảo</label>
                                               <div class="mb-3">
                                                    <a class="btn" href="/downloadFile?filePath=${entry.key.filePath}" download><i class="fa fa-download"></i> ${entry.key.titleFile}</a>
                                               </div>
                                            </div>
                                        </c:if>
                                        <c:if test="${entry.value.titleFile != null && entry.value.filePath != null}">
                                            <div class="upload-section">
                                               <label for="file">Bài làm của bạn</label>
                                               <div class="mb-3">
                                                    <a class="btn" href="/downloadFile?filePath=${entry.value.filePath}" download><i class="fa fa-download"></i> ${entry.value.titleFile}</a>
                                               </div>
                                            </div>
                                        </c:if>
                                    </div>
                                    <div class="modal-footer">
                                        <%--<button type="button" class="btn btn-primary" onclick="submitForm()">Nộp lại</button>--%>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </ul>
            </div>

            <div id="overdue" class="assignment-table mt-3" style="display: none;">
                <h3>Bài Tập Quá Hạn</h3>
                <ul class="list-group">
                    <c:forEach var="assignment" items="${overdue}">
                        <li class="list-group-item" data-bs-toggle="modal" data-bs-target="#assignmentModal-${assignment.assignmentID}">
                            <strong>${assignment.title} </strong><br>
                            <small>Hạn nộp: <fmt:formatDate value="${assignment.endTime}" pattern="dd/MM/yyyy HH:mm" /></small>
                        </li>
                        <div class="modal fade" id="assignmentModal-${assignment.assignmentID}" tabindex="-1" aria-labelledby="assignmentModalLabel-${assignment.assignmentID}" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="assignmentModalLabel-${assignment.assignmentID}">Bài tập</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="form-group">
                                            <label for="datetime">Thời gian hết hạn</label>
                                            <input type="datetime-local" class="form-control" id="datetime" name="datetime" required value="${assignment.endTime}" readonly>
                                        </div>
                                        <div class="form-group">
                                            <label for="title">Tiêu đề</label>
                                            <input type="text" class="form-control" id="title" name="title" required placeholder="Nhập tiêu đề cho bài tập" value="${assignment.title}" readonly>
                                        </div>
                                        <div class="form-group">
                                            <label for="description">Mô tả</label>
                                            <input type="text" class="form-control" id="description" name="description" required placeholder="Nhập mô tả cho bài tập" value="${assignment.description}" readonly>
                                        </div>
                                        <c:if test="${assignment.titleFile != null && assignment.filePath != null}">
                                            <div class="upload-section">
                                               <label for="file">Tài liệu tham khảo</label>
                                               <div class="mb-3">
                                                    <a class="btn" href="/downloadFile?filePath=${assignment.filePath}" download><i class="fa fa-download"></i> ${assignment.titleFile}</a>
                                               </div>
                                            </div>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>

</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<%
    int classID = (Integer) session.getAttribute("classID");
%>

<script>
    const classID = "<%= classID %>";
</script>

<script src="/views/clients/assets/js/ClassAssignmentWebSocket.js"></script>
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

</script>
</body>
</html>
