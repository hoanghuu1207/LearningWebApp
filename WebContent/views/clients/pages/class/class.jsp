<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Danh Sách Lớp Học</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="../../assets/fonts/themify-icons-font/themify-icons/themify-icons.css">
<link rel="stylesheet" href="../../assets/css/style.css">
<link rel="stylesheet" href="../../assets/css/class.css">
<style>
.subnav {
	list-style-type: none;
	display: none;
	position: absolute;
	top: 100%;
	left: 0;
	background-color: #f4f4f4;
	min-width: 100px;
	padding: 0;
	z-index: 9;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
	text-align: left;
}

.subnav li {
	display: block;
}

.subnav a {
	color: black;
	padding: 0 12px;
	line-height: 38px;
	text-decoration: none;
}

.subnav>li:hover>a {
	display: block;
	color: white;
	background-color: gray;
}

.dropdown {
	position: relative;
	cursor: pointer;
}

.dropdown.active .subnav {
	display: block;
}

.fixed-box {
	cursor: pointer;
}
</style>
</head>
<body>
	<div class="container-fluid m-5 p-0">
		<div class="d-flex flex-wrap">
			<!-- Lặp qua danh sách classroom -->
			<c:forEach var="classroom" items="${classrooms}">
				<div class="fixed-box">
					<div
						class="container-md class-box p-3 border rounded shadow-lg text-center"
						onclick="openClass('${classroom.classroomID}')">
						<div
							class="container class-avatar bg-secondary text-white p-3 rounded justify-content-center align-content-center">
							<strong>GV</strong>
						</div>
						<div
							class="container class-box-bottom d-flex align-items-center mt-2">
							<div class="class_name_box small m-1">${classroom.title}</div>
							<i class="ti-more-alt small dropdown">
								<ul class="subnav">
									<li><a href="#">Xóa</a></li>
									<li><a href="#">Ẩn</a></li>
									<li><a href="#">...</a></li>
								</ul>
							</i>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>

	<!-- JavaScript -->
	<script>
        document.addEventListener("DOMContentLoaded", function () {
            const dropdowns = document.querySelectorAll('.dropdown');

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

        function openClass(classroomID) {
            //localStorage.setItem('classroomID', classroomID);
            //localStorage.setItem('className', className);
        	window.location.href = 'class/detail?classID=' + classroomID;
        }
    </script>
</body>
</html>
