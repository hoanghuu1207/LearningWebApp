<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title><sitemesh:write property="title"/></title>
	<link rel="stylesheet" href="/views/clients/assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="/views/clients/assets/css/style.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.css">
	<sitemesh:write property="head"/>

	<style>
		body, html {
			min-height: 100%;
			display: flex;
			flex-direction: column;
		}



		main {
			flex-grow: 1;
			padding-bottom: 10px;
			min-height: calc(100vh - 200px);
		}

	</style>
</head>

<body>
<!-- Include the header -->
<%@ include file="../partials/header.jsp" %>

<!-- Main content area -->
<main>
	<sitemesh:write property="body"/>
</main>

<!-- Include the footer -->
<%@ include file="../partials/footer.jsp" %>

<!-- JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="/views/clients/assets/js/vendor.bundle.js"></script>
<script src="/views/clients/assets/js/theme.bundle.js"></script>

<script>
	document.addEventListener("DOMContentLoaded", function() {
		const bellIcon = document.getElementById("bell-icon");
		const notificationMenu = document.getElementById("notification-menu");

		bellIcon.addEventListener("click", function(event) {
			event.stopPropagation();
			notificationMenu.classList.toggle("active");
		});

		document.addEventListener("click", function(event) {
			if (!bellIcon.contains(event.target)) {
				notificationMenu.classList.remove("active");
			}
		});
	});
</script>
</body>
</html>
