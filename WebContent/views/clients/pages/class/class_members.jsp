<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Class Detail</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>

<body>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="/views/clients/assets/css/class_member.css">
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

<div class="container-class">
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
        <div class="container member-list-container">
            <div class="mb-3 d-flex justify-content-end position-relative">
                <c:if test="${user.roleID == 2}">
                    <button class="btn btn-add mr-5 border-dark" data-bs-toggle="modal" data-bs-target="#addMemberModal">
                        <i class="fas fa-plus"></i> Thêm thành viên
                    </button>
                    <div class="modal fade" id="addMemberModal" tabindex="-1" aria-labelledby="addMemberModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="addMemberModalLabel">Thêm thành viên mới</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body" class="dropdown">
                                    <div class="input-box" data-toggle="dropdown" href="#">
                                        <input id="dropdown-search" type="text" class="form-control" onkeyup="dropdownSearch()">
                                        <i class="fa fa-search"></i>
                                    </div>
                                    <form action="/teacher/class_members" method="POST" id="form-search">
                                        <div class="dropdown-menu" id="search-box">
                                            <c:if test="${studentsSearch != null}">
                                                <c:forEach var="studentSearch" items="${studentsSearch}">
                                                    <div class="list border-bottom dropdown-item">
                                                        <input class="checkbox" type="checkbox" name="checkbox" value="${studentSearch.userID}">
                                                        <div class="d-flex flex-column ml-3">
                                                            <span id="name-search">${studentSearch.firstName} ${studentSearch.lastName}</span>
                                                            <small id="email-search">#<u>${studentSearch.email}</u></small>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                            </c:if>
                                        </div>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" onclick="uncheckCheckox()">Hủy</button>
                                    <button type="button" class="btn btn-primary" onclick="submitForm()">Thêm</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
                <div class="search-wrapper position-relative">
                    <input type="text" id="searchMember" class="form-control" placeholder="Tìm kiếm" onkeyup="performSearch()">
                    <i class="fas fa-search position-absolute search-icon"></i>
                </div>
            </div>

            <div class="mb-4">
                <div class="d-flex align-items-center">
                    <c:forEach var="teacher" items="${teachers}">
                        <img src="${teacher.avatar}" alt="${teacher.firstName}" class="rounded-circle bg-dark" style="width: 48px; height: 48px; margin-right: 15px; object-fit: contain;">
                        <span class="fs-5">${teacher.firstName} ${teacher.lastName}</span>
                    </c:forEach>
                </div>
            </div>

            <div class="member-group">
                <table class="table table-striped member-table">
                    <thead>
                    <tr>
                        <th>STT</th>
                        <th>Học viên</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="student" items="${students}" varStatus="status">
                        <tr studentId="${student.userID}">
                            <td>${status.index + 1}</td>
                            <td>
                                <img src="${student.avatar}" alt="${student.firstName}" data-bs-toggle="tooltip" title="Email: ${student.email}">
                                    ${student.firstName} ${student.lastName}
                            </td>
                            <td>
                                <c:if test="${user.roleID == 2}">
                                    <i id="delete-student" class="fas fa-trash" data-bs-toggle="modal" data-bs-target="#deleteModal" style="cursor: pointer; color: rgb(90,87,87);"></i>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                    <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="deleteModalLabel">Xác Nhận Xóa</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    Bạn có chắc chắn muốn xóa?
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                                    <button type="button" class="btn btn-danger" onclick="confirmDelete()" data-bs-dismiss="modal">Xóa</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </table>
            </div>
        </div>
    </div>
</div>

<script>
    function handleEnter(event) {
        if (event.key === "Enter") {
            performSearch();
        }
    }

    function performSearch() {
        const searchValue = document.getElementById('searchMember').value.toLowerCase().trim();
        const rows = document.querySelectorAll('.member-table tbody tr');

        rows.forEach(row => {
            const name = row.querySelector('td:nth-child(2)').textContent.toLowerCase();
            const email = row.querySelector('td:nth-child(2)').querySelector('[title]').getAttribute('title');
            row.style.display = name.includes(searchValue) || email.includes(searchValue) ? '' : 'none';
        });
    }

</script>

<%
    String classID = (String) session.getAttribute("classID");
%>

<script>
    const classID = "<%= classID %>";
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="/views/clients/assets/js/ClassMemberSearch.js"></script>
<script src="/views/clients/assets/js/DeleteClassMemberWebSocket.js"></script>
</body>

</html>
