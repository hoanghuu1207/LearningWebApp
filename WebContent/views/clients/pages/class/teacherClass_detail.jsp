<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Class Detail</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="../../assets/fonts/themify-icons-font/themify-icons/themify-icons.css">
  <link rel="stylesheet" href="../../assets/css/style.css">
  <style>
    .container-class{
      display: flex;
      width: 100%;
      height:100%;
    }
    .sidebar {
      width: 250px;
      height: 100vh;
      overflow-y: auto;
      background-color: rgba(248, 249, 250, 0.67);
    }

    .main-content-class {
      flex-grow: 1;
      padding: 20px;
      overflow-y: auto;
      height: 100vh;
    }
  </style>
</head>

<body>
<!-- Sidebar -->
<div class = "container-class">
  <div class="sidebar">
    <div class="d-flex flex-column">
      <div class="p-3 mb-2 border-bottom">
        <h5 id="class_name">${classroom.title}</h5>
      </div>
      <div class="list-group">
        <a href="/teacher/class_post?classroomID=${classroom.classroomID}"
           class="list-group-item list-group-item-action">Trang chủ</a>
<%--        target="main-content-class"--%>
        <a href="/teacher/class_assignments?classroomID=${classroom.classroomID}"
           class="list-group-item list-group-item-action">Bài tập</a>
        <a href="/teacher/materials?classroomID=${classroom.classroomID}"
           class="list-group-item list-group-item-action">Tài liệu</a>
        <a href="/teacher/class_members?classId=${classroom.classroomID}"
           class="list-group-item list-group-item-action">Danh sách</a>
        <a href="/teacher/meetings?classroomID=${classroom.classroomID}"
           class="list-group-item list-group-item-action">Cuộc họp</a>
        <a href="/teacher/schedules?classroomID=${classroom.classroomID}"
           class="list-group-item list-group-item-action">Lên lịch hẹn</a>
        <a href="/views/clients/pages/class/prepare_meeting.jsp" target="_parent" class="list-group-item list-group-item-action">
          Tạo cuộc họp
          <img src="/views/clients/assets/fonts/myself-icons/ic_video_camera.png" class="icon-btn" alt="">
        </a>
      </div>
    </div>
  </div>

  <!-- Main Content -->
  <div class="main-content-class">

  </div>
  <%--    <div class="col-md-9 col-lg-10">--%>
  <%--        <iframe name="main-content-class" src="/class_post?classroomID=${classroom.classroomID}"></iframe>--%>
  <%--    </div>--%>

</div>
<!-- JavaScript -->
<%--<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>--%>
<%--<script>--%>
<%--    $(document).ready(function() {--%>
<%--        // Lắng nghe sự kiện click vào các link trong sidebar--%>
<%--        $('.list-group a').click(function(event) {--%>
<%--            event.preventDefault(); // Ngăn không cho trang tải lại--%>

<%--            // Lấy URL của Servlet từ thuộc tính href của link được nhấn--%>
<%--            const url = $(this).attr('href');--%>

<%--            // Gửi yêu cầu AJAX đến Servlet và tải nội dung vào main-content-class--%>
<%--            $.ajax({--%>
<%--                url: url,--%>
<%--                method: 'GET',--%>
<%--                success: function(data) {--%>
<%--                    // Thay thế nội dung của main-content-class bằng nội dung từ Servlet--%>
<%--                    $('.main-content-class').html(data);--%>
<%--                },--%>
<%--                error: function() {--%>
<%--                    alert('Lỗi khi tải nội dung. Vui lòng thử lại sau.');--%>
<%--                }--%>
<%--            });--%>
<%--        });--%>
<%--    });--%>
<%--</script>--%>
</body>
</html>
