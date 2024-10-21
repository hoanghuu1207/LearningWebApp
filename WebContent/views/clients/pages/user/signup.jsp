<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Sign up</title>
<link rel="stylesheet"
	href="/views/clients/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="/views/clients/assets/css/style.css">
</head>
<body class=" bg-light">

	<section
		class="mt-0 overflow-hidden  vh-100 d-flex justify-content-center align-items-center p-4">

		<div class="col col-md-8 col-lg-6 col-xxl-5">
			<div class="shadow-xl p-4 p-lg-5 bg-white">
				<a href="#" class="btn btn-facebook d-block mb-2"><i
					class="ri-facebook-circle-fill align-bottom"></i> Login with
					Facebook</a> <a href="#" class="btn btn-twitter d-block mb-2"><i
					class="ri-twitter-fill align-bottom"></i> Login with Twitter</a> <span
					class="text-muted text-center d-block fw-bolder my-4">OR</span>
				<form method="POST" action="/user/register">
					<div class="form-group">
						<label class="form-label" for="register-fname">First name</label>
						<input type="text" class="form-control" id="register-fname"
							name="firstName" placeholder="Enter your first name" required>
					</div>
					<div class="form-group">
						<label class="form-label" for="register-lname">Last name</label> <input
							type="text" class="form-control" id="register-lname"
							name="lastName" placeholder="Enter your last name" required>
					</div>
					<div class="form-group">
						<label class="form-label" for="register-email">Email
							address</label> <input type="email" class="form-control"
							id="register-email" name="email" placeholder="name@email.com"
							required>
					</div>
					<div class="form-group">
						<label class="form-label" for="register-password">Password</label>
						<input type="password" class="form-control" id="register-password"
							name="password" placeholder="Enter your password" required>
					</div>
					<div class="form-group">
						<label class="form-label" for="register-roles">Roles</label><br>
						<input type="radio" id="teacher" name="role" value="teacher" checked>
						<label for="teacher">Teacher</label><br> 
						<input type="radio" id="student" name="role" value="student">
						<label for="student">Student</label><br>
					</div>
					<button type="submit" class="btn btn-dark d-block w-100 my-4">Sign
						Up</button>
				</form>
				<p class="d-block text-center text-muted">
					Already registered? <a class="text-muted" href="/user/login">Login
						here.</a>
				</p>
			</div>

		</div>

	</section>

	<footer>
		<p>&copy; PBL4</p>
	</footer>
	<!-- <script src="./assets/js/vendor.bundle.js"></script>

    <script src="./assets/js/theme.bundle.js"></script> -->
</body>

</html>