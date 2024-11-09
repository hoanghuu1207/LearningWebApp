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

        <div class="shadow-xl p-4 p-lg-5 bg-white">
            <h1 class="text-center fw-bold mb-5 fs-2">ADMIN</h1>
            <form action="/admin/login" method="POST">
                <div class="form-group">
                    <label class="form-label" for="login-email">Username</label>
                    <input type="text" class="form-control" id="login-email" name="username" placeholder="Username" required>
                </div>
                <div class="form-group">
                    <label for="login-password" class="form-label d-flex justify-content-between align-items-center">
                        Password
                    </label>
                    <input type="password" class="form-control" id="login-password" name="password" placeholder="Enter your password"
                        required>
                </div>
                <button type="submit" class="btn btn-dark d-block w-100 my-4">Login</button>
            </form>
        </div>
    </div>
</body>

</html>