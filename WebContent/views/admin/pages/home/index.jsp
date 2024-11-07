<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en">
  <head>
  	<title>Homepage Admin</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<link href='https://fonts.googleapis.com/css?family=Roboto:400,100,300,700' rel='stylesheet' type='text/css'>

	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">

	<link rel="stylesheet" href="/views/admin/assets/css/style.css">

	</head>
	<body>
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


	</body>
</html>

