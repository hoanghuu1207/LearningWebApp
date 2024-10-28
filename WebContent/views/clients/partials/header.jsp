<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Bitter:400,700">
<link rel="stylesheet" href="/views/clients/assets/css/header.css">

<div class="header-dark">
	<nav
		class="navbar navbar-dark navbar-expand-md navigation-clean-search"
		style="height: 70px;">
		<div class="container">
			<a class="navbar-brand" href="/home"><img
				src="/views/clients/assets/img/logo.png" alt="Trang chủ"
				height="80px" width="80px"></a>
			<button class="navbar-toggler" data-toggle="collapse"
				data-target="#navcol-1">
				<span class="sr-only">Toggle navigation</span><span
					class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navcol-1">
				<c:if test="${user != null}">
					<ul class="nav navbar-nav">
						<li class="nav-item" role="presentation"><a class="nav-link"
							href="/class">Lớp học</a></li>
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
						<span class="navbar-text"><a href="/user/login" class="login">Log
										In</a></span><a class="btn btn-light action-button" role="button" href="/user/register">Sign Up</a>
					</c:when>
					<c:otherwise>
						<span class="dropdown"> <a class="nav-link"
							data-toggle="dropdown" aria-expanded="false" href="#"><img
								src="/views/clients/assets/img/user_icon.png" alt="User"
								height="35px" width="35px"> </a>
							<div class="dropdown-menu" role="menu">
								<a class="dropdown-item" role="presentation" href="/account">Tài
									khoản</a> <a class="dropdown-item" role="presentation"
									href="/user/logout">Đăng xuất</a>
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