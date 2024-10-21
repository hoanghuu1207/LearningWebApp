<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Forgotten Password</title>
    <link rel="stylesheet" href="/views/clients/assets/css/bootstrap.min.css">
</head>

<body class="bg-light">

    <section class="mt-0 overflow-hidden vh-100 d-flex justify-content-center align-items-center p-4">
        <!-- Page Content Goes Here -->

        <!-- Login Form-->
        <div class="col col-md-8 col-lg-6 col-xxl-5">
            <!-- Logo-->
            <a class="navbar-brand fw-bold fs-3 flex-shrink-0 order-0 align-self-center justify-content-center d-flex mx-0 px-0"
                href="./index.html">
                <!-- Logo icon could go here -->
            </a>
            <!-- / Logo-->
            <div class="shadow-xl p-4 p-lg-5 bg-white">
                <h1 class="text-center fs-2 mb-5 fw-bold">GET OTP</h1>
                <p class="text-muted">Please enter your OTP in your email</p>
                <form action="/user/password/otp" method="POST">
                    <div class="form-group">
                        <label for="otp-password" class="form-label">Email address</label>
                        <input type="email" class="form-control" name="email" placeholder="name@email.com" required readonly value=${email}>
                    </div>
                    <div class="form-group">
                        <label for="otp-password" class="form-label">OTP code</label>
                        <input type="number" class="form-control" name="otp" placeholder="OTP code" required min="000000" max="999999">
                    </div>
                    <button type="submit" class="btn btn-dark d-block w-100 my-4">Send OTP code</button>
                </form>
            </div>

        </div>
        <!-- / Login Form-->

        <!-- /Page Content -->
    </section>
    <!-- / Main Section-->

    <!-- Bootstrap JS -->

</body>

</html>