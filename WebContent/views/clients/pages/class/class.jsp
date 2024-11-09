<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Danh Sách Lớp Học</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="views/clients/assets/fonts/themify-icons-font/themify-icons/themify-icons.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/themify-icons/themify-icons.css">
</head>
<body>
    <link rel="stylesheet" href="views/clients/assets/css/class.css?v=1.1">

	<div class="container-fluid m-5 p-0">
		<div class="d-flex flex-wrap">
			<!-- Lặp qua danh sách classroom -->
			<c:forEach var="classroom" items="${classrooms}">
				<div class="fixed-box">
					<div class="container-md class-box p-3 border rounded shadow-lg text-center" class_id="${classroom.classroomID}" >
						<!--onclick="openClass('${classroom.classroomID}')"-->
						<div class="container class-avatar bg-secondary text-white p-3 rounded justify-content-center align-content-center">
							<strong>${classroom.title.substring(0, 2)}</strong>
						</div>
						<div class="container class-box-bottom d-flex align-items-center mt-2 p-0 justify-content-between">
							<div class="class_name_box small fs-6 m-1 flex-grow-1 text-center pt-2 p-1">
									${classroom.title}
							</div>
							<span class="dropdown">
        <a class="nav-link" aria-expanded="false" href="#">
            <img src="/views/clients/assets/img/more_icon.jpg" alt="More" height="20px" width="20px">
        </a>
        <div class="dropdown-menu">
            <a class="dropdown-item" href="/class/delete">Xóa</a>
            <a class="dropdown-item" href="#">Lấy code</a>
        </div>
    </span>
						</div>

					</div>
				</div>
			</c:forEach>
		</div>
	</div>

	<!-- JavaScript -->
	<!--
	<script>
        document.addEventListener("DOMContentLoaded", function () {
            const dropdowns = document.querySelectorAll('.dropdown ul');

            dropdowns.forEach((dropdown) => {
                dropdown.addEventListener('click', function (event) {
                    event.stopPropagation();
                    this.classList.toggle('active');
                });
            });

            document.addEventListener('click', function (e) {
                dropdowns.forEach((dropdown) => {
                    if (!dropdown.contains(e.target)) {
                        dropdown.classList.remove('active');
                    }
                });
            });
        });
    </script>
    -->
    <script>
        //localStorage.setItem('classroomID', classroomID);
        //localStorage.setItem('className', className);
        const classBoxes = document.querySelectorAll('[class_id]');
        classBoxes.forEach(classBox => {
            classBox.addEventListener('click', (event) => {
                if(event.target.matches('img')) return;
                window.location.href = 'class/detail?classID=' + classBox.getAttribute("class_id");
            });
        });

    </script>

</body>
</html>
