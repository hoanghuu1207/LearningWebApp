<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Class Detail</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
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
            overflow-y: auto;
            height: 100vh;
        }

        .member-list-container {
            padding: 20px;
            background-color: #f4f4f4;
        }

        .member-table th,
        .member-table td {
            padding: 10px;
            vertical-align: middle;
        }

        .member-table img {
            width: 40px;
            height: 40px;
            border-radius: 50%;
            margin-right: 10px;
        }

        .action-buttons i {
            font-size: 18px;
            margin-right: 10px;
            cursor: pointer;
            color: #6c757d;
        }

        .action-buttons i:hover {
            color: #495057;
        }

        .search-wrapper {
            width: 40%;
            position: relative;
            display: inline-block;
        }

        .form-control {
            width: 100%;
            padding-right: 40px;
        }

        .search-icon {
            position: absolute;
            right: 10px;
            top: 50%;
            transform: translateY(-50%);
            font-size: 18px;
            color: #888;
            cursor: pointer;
        }

        .btn-add{
            background-color: #5c62d1;
            color: whitesmoke;
        }

    </style>
</head>

<body>

<div class="container-class">
    <div class="sidebar">
        <div class="d-flex flex-column">
            <div class="p-3 mb-2 border-bottom">
                <h5 id="class_name">${classroom.title}</h5>
            </div>
            <div class="list-group">
                <a href="/class_post?classroomID=${classroom.classroomID}"
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
                <a href="/views/clients/pages/class/prepare_meeting.jsp" target="_parent" class="list-group-item list-group-item-action">
                    Tạo cuộc họp
                    <img src="/views/clients/assets/fonts/myself-icons/ic_video_camera.png" class="icon-btn" alt="">
                </a>
            </div>
        </div>
    </div>

    <div class="main-content-class">
        <div class="container member-list-container">


            <div class="mb-3 d-flex justify-content-end position-relative">

<%--                Role admin or teacher--%>
                <!-- Nút Thêm thành viên -->
                <button class="btn btn-add mr-5 border-dark" data-bs-toggle="modal" data-bs-target="#addMemberModal">
                    <i class="fas fa-plus"></i> Thêm thành viên
                </button>
                <!-- Modal Thêm Thành Viên -->
                <div class="modal fade" id="addMemberModal" tabindex="-1" aria-labelledby="addMemberModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="addMemberModalLabel">Thêm thành viên mới</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <form id="addMemberForm">
                                    <div class="mb-3">
                                        <label for="firstName" class="form-label">Họ tên</label>
                                        <input type="text" class="form-control" id="firstName" placeholder="Nhập tên">
                                    </div>
                                    <div class="mb-3">
                                        <label for="email" class="form-label">Email</label>
                                        <input type="email" class="form-control" id="email" placeholder="Nhập email">
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
<%--                                Gui yeu cau qua mail--%>
                                <button type="button" class="btn btn-primary" onclick="addMember()">Gửi yêu cầu</button>

                            </div>
                        </div>
                    </div>
                </div>

                <!-- Thanh tìm kiếm -->
                <div class="search-wrapper position-relative">
                    <input type="text" id="searchMember" class="form-control" placeholder="Tìm kiếm" onkeypress="handleEnter(event)">
                    <i class="fas fa-search position-absolute search-icon" onclick="performSearch()"></i>
                </div>
            </div>


            <!-- Giáo viên -->
            <div class="mb-4">
                <div class="d-flex align-items-center">
                    <c:forEach var="teacher" items="${teachers}">
                        <img src="/views/clients/assets/img/i1.jpg" alt="${teacher.firstName}" class="rounded-circle bg-dark" style="width: 48px; height: 48px; margin-right: 15px; object-fit: contain;">
                        <span class="fs-5">${teacher.firstName} ${teacher.lastName}</span>
                    </c:forEach>
                </div>
            </div>

            <!-- Học viên -->
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
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>
                                <img src="/views/clients/assets/img/user_icon.png" alt="${student.firstName}">
                                    ${student.firstName} ${student.lastName}
                            </td>
<%--                            Role Admin or Teacher--%>
                            <td>
                                <i class="fas fa-trash" data-bs-toggle="modal" data-bs-target="#deleteModal" style="cursor: pointer; color: rgb(90,87,87);"></i>
                                <!-- Modal Xác Nhận Xóa -->
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
                                                <button type="button" class="btn btn-danger" onclick="confirmDelete()">Xóa</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
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
        const searchValue = document.getElementById('searchMember').value.toLowerCase();
        const rows = document.querySelectorAll('.member-table tbody tr');

        rows.forEach(row => {
            const name = row.querySelector('td:nth-child(2)').textContent.toLowerCase();
            row.style.display = name.includes(searchValue) ? '' : 'none';
        });
    }

    function addMember() {
        // Lấy dữ liệu từ form
        const firstName = document.getElementById('firstName').value;
        const email = document.getElementById('email').value;
        const role = document.getElementById('role').value;

        // Kiểm tra dữ liệu
        if (!firstName || !email || !role) {
            alert("Vui lòng điền đầy đủ thông tin!");
            return;
        }

        // Xử lý thêm thành viên (gửi dữ liệu tới server, cập nhật danh sách, v.v.)
        alert(`Thêm thành viên: ${firstName}, Email: ${email}, Vai trò: ${role}`);

        // Đóng modal
        const modal = document.getElementById('addMemberModal');
        const bootstrapModal = bootstrap.Modal.getInstance(modal);
        bootstrapModal.hide();

        // Xóa form để reset
        document.getElementById('addMemberForm').reset();
    }

</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
