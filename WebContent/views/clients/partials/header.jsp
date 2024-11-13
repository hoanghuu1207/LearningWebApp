<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>
	<link rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
	<link rel="stylesheet"
		href="https://fonts.googleapis.com/css?family=Bitter:400,700">
	<link rel="stylesheet" href="/views/clients/assets/css/header.css?v=1.2">

	<style>
		.header-dark .navbar-nav .nav-item:hover{
			background: none;
		}
		.header-dark .navbar-nav .nav-link:hover{
			color:white;
		}
	</style>
</head>


<body>
	<div class="header-dark">
		<nav class="navbar navbar-dark navbar-expand-md navigation-clean-search">
			<div class="container d-flex align-items-center">
				<a class="navbar-brand d-flex align-items-center" href="/home">
					<img src="/views/clients/assets/img/logo.png" alt="Trang chủ"  height="80" width="80" class = "m-0">
				</a>
				<button class="navbar-toggler ml-auto" type="button" data-toggle="collapse" data-target="#navcol-1">
					<span class="sr-only">Toggle navigation</span>
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navcol-1">
					<c:if test="${user != null}">
						<ul class="nav navbar-nav">
							<li class="nav-item" role="presentation">
								<a class="nav-link" href=${user.roleID == 3 ? "/class" : "/teacher/classes"}>Lớp học
									<div class="slider"></div>
								</a>
							</li>
                            <li class="nav-item" role="presentation">
								<a class="nav-link" href="/assignments">Bài tập
									<div class="slider"></div>
								</a>
							</li>
						</ul>
                    </c:if>
					<form class="form-inline mr-auto" target="_self">
						<c:if test="${user != null}">
							<div class="form-group">
								<label for="search-field"><i class="fa fa-search"></i></label><input
									class="form-control search-field" type="search" name="search"
									id="search-field">
							</div>
						</c:if>
					</form>

					<c:choose>
						<c:when test="${user == null}">
							<span class="navbar-text">
								  <a class="btn action-button login" role="button" href="/user/login">Log In
									<div class="slider"></div>
								  </a>
									<a class="btn action-button signup" role="button" href="/user/register">Sign Up
									<div class="slider"></div>
									</a>

							</span>

						</c:when>
						<c:otherwise>
						    <span><i class="fa-regular fa-bell me-5" id="notice" style="transform: scale(1.6);"></i></span>
							<span class="dropdown"> <a class="nav-link"
								data-toggle="dropdown" aria-expanded="false" href="#"><img
									src="/views/clients/assets/img/user_icon.png" alt="User"
									height="35px" width="35px"></a>
								<div class="dropdown-menu" role="menu">
									<a class="dropdown-item" role="presentation" href="/user/account">Tài khoản</a>
									<a class="dropdown-item" role="presentation" href="/user/logout">Đăng xuất</a>
								</div>
							</span>
						</c:otherwise>
					</c:choose>

				</div>
			</div>
		</nav>
	</div>


<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/js/bootstrap.bundle.min.js"></script>
	<script>
		document.addEventListener("scroll", function() {
			const header = document.querySelector(".header-dark");
			if (window.scrollY > 50) {
				header.classList.add("transparent");
			} else {
				header.classList.remove("transparent");
			}
		});
	</script>

</body>
</html>