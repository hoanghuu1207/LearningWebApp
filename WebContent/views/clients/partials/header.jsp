<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<ul id="nav">
		<li><a href="/home">Trang chủ</a></li>
		<c:if  test="${user != null}">
			<li><a href="/account">Tài khoản</a></li>
			<li><a href="/class">Lớp học</a></li>
			<li><a href="/calendar">Lịch hẹn</a></li>
		</c:if>
	</ul>

<c:choose>
    <c:when test="${user != null}">
		<div class="icon">
			<i class="ti-bell" id="bell-icon"></i>
			<ul class="notification-menu" id="notification-menu">
				<li><a href="#">Thông báo 1</a></li>
				<li><a href="#">Thông báo 2</a></li>
				<li><a href="#">Thông báo 3</a></li>
			</ul>
		</div>
    </c:when>
	<c:otherwise>
		<div class="d-flex justify-content-end">
			<ul>
				<li><a class="nav-link text-body"
					href="login_and_signup/login.html">Đăng nhập</a></li>
				<li><a class="nav-link text-body"
					href="login_and_signup/signup.html">Đăng ký</a></li>
			</ul>
		</div>
	</c:otherwise>
</c:choose>
	