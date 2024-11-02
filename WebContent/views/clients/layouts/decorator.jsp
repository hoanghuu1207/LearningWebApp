<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title><sitemesh:write property="title"/></title>
	<link rel="stylesheet"
		href="/views/clients/assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="/views/clients/assets/css/style.css">
	<!-- <link rel="stylesheet"
		href="/views/clients/assets/fonts/themify-icons-font/themify-icons/themify-icons.css"> -->
	
	<link rel="stylesheet"
		href="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.css">
	<sitemesh:write property="head"/>

</head>
<body>
	<%@ include file="../partials/header.jsp"%>
	<!-- <sitemesh:write property="header"/> -->

	<sitemesh:write property="body"/>


	<!-- <sitemesh:write property="footer"/> -->
	<!-- <%@ include file="../partials/footer.jsp"%> -->

	<script>
		document.addEventListener("DOMContentLoaded", function() {
			const bellIcon = document.getElementById("bell-icon");
			const notificationMenu = document
					.getElementById("notification-menu");

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
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

	<script src="/views/clients/assets/js/vendor.bundle.js"></script>

	<script src="/views/clients/assets/js/theme.bundle.js"></script>
</body>
</html>