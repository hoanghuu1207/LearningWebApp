<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
	<title>Homepage</title>
	<style>

		*{
			margin: 0;
			padding: 0;
		}
		body {
			font-family: Arial, sans-serif;
			color: #333;
			overflow-y: scroll;
		}
		.display-3 {
			font-size: 2.5rem;
		}

		.carousel-item{
			height: 75vh;
			width: 100%;
			background-size: contain;
			background-position: center;
			transition: ease-in-out !important;
		}

		.carousel-caption h2{
			color: white;
			text-shadow: 2px 2px 4px #5a5757;
		}
		.hidden {
			opacity: 0;
			transform: translateY(100px);
			transition: opacity 0.8s ease-out, transform 0.8s ease-out;
		}

		.item-slide {
			opacity: 1 !important;
			transform: translateY(0) !important;
		}


		#team .card {
			transition: transform 0.3s ease, box-shadow 0.3s ease;
			border: none;
			border-radius: 20px;
			overflow: hidden;
		}

		#team .card:hover {
			transform: translateY(-10px);
			box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2);
		}

		#team .card .card-body a {
			color: #555;
			font-size: 1.2rem;
			transition: color 0.3s;
		}

		#team .card .card-body a:hover {
			color: #007bff;
		}

		#team .avatar-img {
			width: 150px;
			height: 150px;
			border: 3px solid #fff;
			transition: border-color 0.3s;
		}

		#team .card:hover .avatar-img {
			border-color: #007bff;
		}

		#team .card .avatar-img {
			width: 120px;
			height: 120px;
			border-radius: 50%;
			object-fit: cover;
			object-position: center;
		}

		</style>

</head>


<body>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">

<%--slider--%>
	<section class="mt-0 ">
		<div class="container-fluid p-0">
			<div id="carouselExample" class="carousel slide bg-dark" data-bs-ride="carousel" data-bs-interval="5000">
				<div class="carousel-inner">
					<div class="carousel-item active" style="background-image: url('/views/clients/assets/img/i3.jpg')">
						<div class="d-flex h-100 align-items-center justify-content-center text-center text-white bg-black bg-opacity-50">
							<div class="carousel-caption">
								<h2 class="display-3 fw-bold opacity-75">Giáo Dục Cho Tương Lai</h2>
							</div>
						</div>
					</div>
					<div class="carousel-item" style="background-image: url('/views/clients/assets/img/i2.jpg')">
						<div class="d-flex h-100 align-items-center justify-content-center text-center text-white bg-black bg-opacity-50">
							<div class="carousel-caption">
								<h2 class="display-3 fw-bold opacity-75">Học Online Mọi Lúc</h2>
								<h2 class="display-3 fw-bold opacity-75">Mọi Nơi</h2>

							</div>
						</div>
					</div>
				</div>
				<button class="carousel-control-prev" type="button"
					data-bs-target="#carouselExample" data-bs-slide="prev">
					<span class="carousel-control-prev-icon"></span>
				</button>
				<button class="carousel-control-next" type="button"
					data-bs-target="#carouselExample" data-bs-slide="next">
					<span class="carousel-control-next-icon"></span>
				</button>
			</div>
		</div>
		<div class="container my-5 hidden">
			<div class="row g-4 align-items-center">
				<div class="col-lg-6" data-aos="fade-right">
					<p class="lead">Giảng dạy linh hoạt</p>
					<p class="lead">Học tập hiệu quả</p>
				</div>
				<div class="col-lg-6" data-aos="fade-left">
					<img src="/views/clients/assets/img/i1.jpg" class="img-fluid rounded">
				</div>
			</div>
		</div>

		<div class="container my-5 hidden">
			<div class="row g-4 align-items-center">
				<div class="col-lg-6 order-lg-2" data-aos="fade-left">
					<p class="lead">Giáo dục tiên tiến</p>
					<p class="lead">Giải pháp tương lai</p>
				</div>
				<div class="col-lg-6 order-lg-1" data-aos="fade-right">
					<img src="/views/clients/assets/img/i11.jpg" class="img-fluid rounded">
				</div>
			</div>
		</div>
	</section>

<!-- Team Section -->
<section id="team" class="py-5 bg-light hidden">
	<div class="container d-flex flex-column align-items-center">
		<div class="text-center mx-auto mb-5" style="max-width: 550px;">
			<h2 class="mb-3">Nhóm tác giả</h2>
			<p class="text-muted">
				Mỗi người chúng tôi là một cá thể riêng biệt, với những đam mê và thế mạnh khác nhau. Nhưng chính điều
				đó đã kết nối chúng tôi lại, tạo nên một tập thể gắn kết và cùng nhau hoàn thiện dự án này.
			</p>
		</div>

		<div class="row justify-content-center">
			<div class="col-12 col-sm-6 col-md-4 col-lg-4 mb-4">
				<div class="card text-center shadow-sm h-100 position-relative">
					<div class="card-body">
						<div class="mb-3 position-relative" style="margin: 0 auto;">
							<img src="/views/clients/assets/img/h1.jpg" alt="Võ Thị Quỳnh Nga" class="avatar-img">
						</div>
						<h4 class="card-title mb-1">Võ Thị Quỳnh Nga</h4>
						<p class="card-text text-muted mb-3">Frontend Developer</p>
						<div class="d-flex justify-content-center gap-3">
							<a href="#" class="text-dark"><i class="bi bi-facebook"></i></a>
							<a href="#" class="text-dark"><i class="bi bi-twitter"></i></a>
							<a href="#" class="text-dark"><i class="bi bi-instagram"></i></a>
						</div>
					</div>
				</div>
			</div>
			<div class="col-12 col-sm-6 col-md-4 col-lg-4 mb-4">
				<div class="card text-center shadow-sm h-100 position-relative">
					<div class="card-body">
						<div class="mb-3 position-relative" style="margin: 0 auto;">
							<img src="/views/clients/assets/img/h2.jpg"  alt="Huỳnh Hữu Hoàng" class="avatar-img">
						</div>
						<h4 class="card-title mb-1">Huỳnh Hữu Hoàng</h4>
						<p class="card-text text-muted mb-3">Backend Developer</p>
						<div class="d-flex justify-content-center gap-3">
							<a href="#" class="text-dark"><i class="bi bi-facebook"></i></a>
							<a href="#" class="text-dark"><i class="bi bi-twitter"></i></a>
							<a href="#" class="text-dark"><i class="bi bi-instagram"></i></a>
						</div>
					</div>
				</div>
			</div>
			<div class="col-12 col-sm-6 col-md-4 col-lg-4 mb-4">
				<div class="card text-center shadow-sm h-100 position-relative">
					<div class="card-body">
						<div class="mb-3 position-relative" style="margin: 0 auto;">
							<img src="/views/clients/assets/img/h3.jpg"  alt="Lê Xuân Hòa" class="avatar-img">
						</div>
						<h4 class="card-title mb-1">Lê Xuân Hòa</h4>
						<br>
						<p class="card-text text-muted mb-3">Backend Developer</p>
						<div class="d-flex justify-content-center gap-3">
							<a href="#" class="text-dark"><i class="bi bi-facebook"></i></a>
							<a href="#" class="text-dark"><i class="bi bi-twitter"></i></a>
							<a href="#" class="text-dark"><i class="bi bi-instagram"></i></a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>

<script>
	document.addEventListener("DOMContentLoaded", function () {
		const elements = document.querySelectorAll(".hidden");

		const observer = new IntersectionObserver((entries, observer) => {
			entries.forEach(entry => {
				const element = entry.target;

				if (entry.isIntersecting) {
					element.classList.add("item-slide");
				} else {
					element.classList.remove("item-slide");
				}
			});
		}, { threshold: 0.1 });

		elements.forEach(element => observer.observe(element));
	});
</script>





</body>
</html>
