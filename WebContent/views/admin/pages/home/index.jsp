<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en">
  <head>
  	<title>Homepage Admin</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

	<link href='https://fonts.googleapis.com/css?family=Roboto:400,100,300,700' rel='stylesheet' type='text/css'>

	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">

	<link rel="stylesheet" href="/views/admin/assets/css/style.css">

	<style>
		header-dark{
			position: relative;
		}

		.header-dark {
          background:url('https://cdn.pixabay.com/photo/2015/10/12/14/59/milky-way-984050_960_720.jpg') #444;
          background-size:cover;
        }

        @media (min-width:768px) {
          .header-dark {
          }
        }

        .header-dark .navbar {
          background:transparent;
          padding-top:0;
          padding-bottom:0;
          color:#fff;
          border-radius:0;
          box-shadow:none;
          border:none;
        }

        @media (min-width:768px) {
          .header-dark .navbar {
            padding-top:.75rem;
            padding-bottom:.75rem;
          }
        }

        .header-dark .navbar .navbar-brand {
          font-weight:bold;
          color:inherit;
        }

        .header-dark .navbar .navbar-brand:hover {
          color:#f0f0f0;
        }

        .header-dark .navbar .navbar-collapse span {
          margin-top:5px;
        }

        .header-dark .navbar .navbar-collapse span .login {
          color:#d9d9d9;
          margin-right:.5rem;
          text-decoration:none;
        }

        .header-dark .navbar .navbar-collapse span .login:hover {
          color:#fff;
        }

        .header-dark .navbar .navbar-toggler {
          border-color:#747474;
        }

        .header-dark .navbar .navbar-toggler:hover, .header-dark .navbar-toggler:focus {
          background:none;
        }

        .header-dark .navbar .navbar-toggler {
          color:#eee;
        }

        .header-dark .navbar .navbar-collapse, .header-dark .navbar .form-inline {
          border-color:#636363;
        }

        @media (min-width: 992px) {
          .header-dark .navbar.navbar .navbar-nav .nav-link {
            padding-left:1.2rem;
            padding-right:1.2rem;
          }
        }

        .header-dark .navbar.navbar-dark .navbar-nav .nav-link {
          color:#d9d9d9;
        }

        .header-dark .navbar.navbar-dark .navbar-nav .nav-link:focus, .header-dark .navbar.navbar-dark .navbar-nav .nav-link:hover {
          color:#fcfeff !important;
          background-color:transparent;
        }

        .dropdown-menu {
        	background-color: #292929;
        }

        .header-dark .navbar .navbar-nav > li > .dropdown-menu {
          margin-top:-5px;
          box-shadow:0 4px 8px rgba(0,0,0,.1);
          background-color:white;
          border-radius:2px;
        }

        .header-dark .navbar .dropdown-menu .dropdown-item:focus, .header-dark .navbar .dropdown-menu .dropdown-item {
          line-height:2;
          font-size:14px;
          color:white;
        }

        .header-dark .navbar .dropdown-menu .dropdown-item:focus, .header-dark .navbar .dropdown-menu .dropdown-item:hover {
          background:white;
          color: black;
        }

        .header-dark .navbar .action-button, .header-dark .navbar .action-button:active {
          background:#208f8f;
          border-radius:20px;
          font-size:inherit;
          color:#fff;
          box-shadow:none;
          border:none;
          text-shadow:none;
          padding:.5rem .8rem;
          transition:background-color 0.25s;
        }

        .header-dark .navbar .action-button:hover {
          background:#269d9d;
        }

        .header-dark .navbar .form-inline label {
          color:#ccc;
        }

        .header-dark .navbar .form-inline .search-field {
          display:inline-block;
          width:80%;
          background:none;
          border:none;
          border-bottom:1px solid transparent;
          border-radius:0;
          color:#ccc;
          box-shadow:none;
          color:inherit;
          transition:border-bottom-color 0.3s;
        }

        .header-dark .navbar .form-inline .search-field:focus {
          border-bottom:1px solid #ccc;
        }

        .header-dark .hero {
          margin-top:60px;
        }

        @media (min-width:768px) {
          .header-dark .hero {
            margin-top:20px;
          }
        }

        .header-dark .hero h1 {
          color:#fff;
          font-family:'Bitter', serif;
          font-size:40px;
          margin-top:20px;
          margin-bottom:20px;
        }

        @media (min-width:768px) {
          .header-dark .hero h1 {
            margin-bottom:50px;
            line-height:1.5;
          }
        }

        .header-dark .hero .embed-responsive iframe {
          background-color:#666;
        }

        #notice{
            cursor: pointer;
        }

        #notice:hover{
            color: black;
        }
	</style>

	</head>
	<body>
        <div class="header-dark">
            <nav
                class="navbar navbar-dark navbar-expand-md navigation-clean-search"
                style="height: 70px;">
                <div class="container d-flex">
                    <a class="navbar-brand" href="/admin/home"><img
                        src="/views/clients/assets/img/logo.png" alt="Trang chủ"
                        height="80px" width="80px"></a>
                    <div class="pr-auto" id="navcol-1">
                        <ul class="nav navbar-nav">
                            <li class="nav-item" role="presentation"><a class="nav-link"
                                href="/admin/logout">Log out</a></li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>

		<section class="ftco-section">
			<div class="container">
			<div class="search-box">
				<button class="btn-search"><i class="fas fa-search"></i></button>
				<input type="text" class="input-search" placeholder="Type to Search...">
			</div>

			<div class="row">
				<div class="col-md-12">
					<div class="table-wrap">
						<table class="table table-responsive-xl">
						  <thead>
						    <tr>
                              <th>Email</th>
						      <th>Username</th>
						      <th>Role</th>
						    </tr>
						  </thead>
						  <tbody>
						    <c:forEach var="user" items="${users}">
                                <tr class="alert" role="alert">
                                  <td class="d-flex align-items-center">
                                    <div class="img" style="background-image: url('${user.avatar}');"></div>
                                    <div class="pl-3 email">
                                        <span>${user.email}</span>
                                    </div>
                                  </td>
                                  <td>${user.firstName} ${user.lastName}</td>
                                  <td class="status"><span class=${user.roleID == 2 ? "active" : "waiting"} role_id="${user.roleID}" user_id="${user.userID}">${user.roleID == 2 ? "Teacher" : "Student"}</span></td>
                                  <!-- <td>
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                    <span aria-hidden="true"><i class="fa fa-close"></i></span>
                                </button>
                                </td> -->
                                </tr>
						    </c:forEach>
						  </tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- <script src="js/jquery.min.js"></script> -->
  <!-- <script src="js/popper.js"></script> -->
  <!-- <script src="js/bootstrap.min.js"></script> -->
	<!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script> -->
  <!-- <script src="js/main.js"></script> -->

	<script src="/views/admin/assets/js/search.js"></script>
	<script src="/views/admin/assets/js/home.js"></script>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

	</body>
</html>

