<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
	<title>Homepage</title>
	<style>
		body {
			font-family: Arial, sans-serif;
			background-color: #f4f4f4;
			color: #333;
			overflow-y: scroll;
		}

		.swiper-slide {
			background-size: cover;
			background-position: center;
		}

		.title-small {
			font-size: 1.25rem;
		}

		.display-3 {
			font-size: 2.5rem;
		}

		.btn-psuedo {
			background-color: rgba(255, 255, 255, 0.1);
			border: 1px solid white;
			padding: 0.75rem 1.5rem;
		}

		.text-outline-dark {
			text-shadow: 1px 1px 2px black;
		}

		.nav-item {
			display: block;
		}

		.nav-item:hover {
			background-color: rgb(215, 214, 214);
			border-radius: 5px;
		}
		</style>
</head>


<body>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<%--slider--%>
	<section class="mt-0 ">
		<div class="container-fluid p-0">
			<div id="carouselExample" class="carousel slide"
				data-bs-ride="carousel" data-bs-interval="5000">
				<div class="carousel-inner">
					<div class="carousel-item active"
						style="background-image: url('/views/clients/assets/img/i3.jpg'); height: 75vh;">
							<div class="carousel-caption">
								<p class="title-small opacity-75">Nơi Học Tập Hiện Đại</p>
								<h2 class="display-3 fw-bold">Giáo Dục Cho Tương Lai</h2>
							</div>

					</div>
					<div class="carousel-item"
						style="background-image: url('/views/clients/assets/img/i2.jpg'); height: 75vh;">
							<div class="carousel-caption">
								<p class="title-small opacity-75">Học Tập Không Giới Hạn</p>
								<h2 class="display-3 fw-bold">Học Online Mọi Lúc, Mọi Nơi</h2>
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
		<div class="container my-5">
			<div class="row g-4 align-items-center">
				<div class="col-lg-6" data-aos="fade-right">
					<h3 class="display-3 fw-bold mb-3">
						Học Tập Cùng Chúng Tôi</span>
					</h3>
					<p class="lead">Giảng dạy linh hoạt.</p>
					<p class="lead">Học tập hiệu quả.</p>
				</div>
				<div class="col-lg-6" data-aos="fade-left">
					<img src="/views/clients/assets/img/i1.jpg" class="img-fluid rounded"
						alt="Giáo dục tiên tiến">
				</div>
			</div>
		</div>

		<div class="container my-5">
			<div class="row g-4 align-items-center">
				<div class="col-lg-6 order-lg-2" data-aos="fade-left">
					<h3 class="display-3 fw-bold mb-3">
						Học Tập Cùng Chúng Tôi</span>
					</h3>
					<p class="lead">Giảng dạy linh hoạt.</p>
					<p class="lead">Học tập hiệu quả.</p>
				</div>
				<div class="col-lg-6 order-lg-1" data-aos="fade-right">
					<img src="/views/clients/assets/img/i1.jpg" class="img-fluid rounded"
						alt="Giáo dục tiên tiến">
				</div>
			</div>
		</div>
	</section>
</body>
</html>
