<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="/views/clients/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="/views/clients/assets/css/style.css">
    <link rel="stylesheet" href="/vsiews/clients/assets/css/login.css">
</head>

<body class="mt-0 overflow-hidden vh-100 d-flex justify-content-center align-items-center p-4">
    <div class="col col-md-8 col-lg-6 col-xxl-5">
        <a class="navbar-brand fw-bold fs-3 flex-shrink-0 order-0 align-self-center justify-content-center d-flex mx-0 px-0"
            href="/src/index.html">
            <!-- {{> logo/logo-icon }} -->
        </a>

        <div class="shadow-xl p-4 p-lg-5 bg-white">
            <h1 class="text-center fw-bold mb-5 fs-2">Login</h1>
            <a href="#" class="btn btn-facebook d-block mb-2"><i class="ri-facebook-circle-fill align-bottom"></i> Login
                with Facebook</a>
            <a href="#" class="btn btn-twitter d-block mb-2"><i class="ri-twitter-fill align-bottom"></i> Login with
                Twitter</a>
            <span class="text-muted text-center d-block fw-bolder my-4">OR</span>
            <form action="/user/login" method="POST"> <!-- Gọi hàm login khi submit form -->
                <div class="form-group">
                    <label class="form-label" for="login-email">Email address</label>
                    <input type="email" class="form-control" id="login-email" name="email" placeholder="name@email.com" required>
                </div>
                <div class="form-group">
                    <label for="login-password" class="form-label d-flex justify-content-between align-items-center">
                        Password
                        <a href="./password/forgot" class="text-muted small">Forgot your password?</a>
                    </label>
                    <input type="password" class="form-control" id="login-password" name="password" placeholder="Enter your password"
                        required>
                </div>
                <button type="submit" class="btn btn-dark d-block w-100 my-4">Login</button>
            </form>
            <a class="text-muted" href="./register">Sign up</a>
        </div>
    </div>

    <footer>
        <p>&copy; PBL4</p>
    </footer>
</body>

</html>