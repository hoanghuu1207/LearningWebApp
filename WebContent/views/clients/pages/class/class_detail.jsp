<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Class Detail</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>

<body>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="/views/clients/assets/css/popup.css">
<link rel="stylesheet" href="/views/clients/assets/css/class_detail.css">
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
        background-color: #f8f9fa;
    }

    .main-content-class {
        flex-grow: 1;
        padding: 20px;
    }
    #postContainer {
        height: 100vh;          /* Chiều cao của container */
        overflow-y: auto;       /* Cho phép cuộn dọc */
    }

    .message-box { border: 1px solid rgba(221, 221, 221, 0.51); border-radius: 8px; padding: 10px; margin-bottom: 10px; background-color: rgba(255, 255, 255, 0.47); }
    .reply-box { margin-left: 40px; display: none; }
</style>
<!-- Sidebar -->
<div class = "container-class">
    <div class="sidebar">
        <div class="d-flex flex-column">
            <div class="p-3 mb-2 border-bottom">
                <h5 id="class_name">${classroom.title}</h5>
            </div>
            <div class="list-group">
                <a href="${user.roleID == 2 ? '/teacher' : ''}/class/detail?classID=${classroom.classroomID}"
                   class="list-group-item list-group-item-action">Trang chủ</a>
                <a href="${user.roleID == 2 ? '/teacher' : ''}/class_assignments?classroomID=${classroom.classroomID}"
                   class="list-group-item list-group-item-action">Bài tập</a>
                <a href="/materials?classroomID=${classroom.classroomID}"
                   class="list-group-item list-group-item-action">Tài liệu</a>
                <a href="${user.roleID == 2 ? '/teacher' : ''}/class_members?classId=${classroom.classroomID}"
                   class="list-group-item list-group-item-action">Danh sách</a>
                <a href="/meetings?classroomID=${classroom.classroomID}"
                   class="list-group-item list-group-item-action">Cuộc họp</a>
                <c:if test="${user.roleID == 2}">
                    <a href="/teacher/schedules?classroomID=${classroom.classroomID}"
                        class="list-group-item list-group-item-action">Lên lịch hẹn</a>
                </c:if>
                <a href="/prepareMeeting?classroomID=${classroom.classroomID}" target="_parent" class="list-group-item list-group-item-action">
                    Tạo cuộc họp
                    <img src="/views/clients/assets/fonts/myself-icons/ic_video_camera.png" class="icon-btn" alt="">
                </a>
            </div>
        </div>
    </div>

    <!-- Main Content -->
    <div class="main-content-class">
        <div id="popupBackground" onclick="closePopup()">
          <div id="popupForm" onclick="event.stopPropagation()">
            <h3>Thêm bài đăng</h3>
            <form action='/class_post' method='POST'>
              <div class="inputbox">
                <input type="text" required="required" name="post"/>
                <span>Nội dung</span>
              </div>
              <button class="btn btn-dark">Submit</button>
            </form>
            <span class="close-btn" onclick="closePopup()">×</span>
          </div>
        </div>
        <div class="container" id="postContainer">
          <h2>Bài đăng</h2><br>

          <c:forEach var="message" items="${messages}">
            <c:choose>
              <c:when test="${message.parentMessageID == 0}">
                <div class="message-box">
                  <div class="message-header">
                    <strong>${message.senderName}</strong> - <fmt:formatDate value="${message.createdAt}" pattern="dd/MM/yyyy HH:mm" />
                  </div>
                  <div class="message-body">
                    <p>${message.content}</p>
                    <button class="btn btn-link btn-sm" onclick=
                            "const replies = document.querySelectorAll('.reply-${message.messageID}');
                            const button = this;
                            replies.forEach(reply => {
                      reply.style.display = (reply.style.display === 'none' || reply.style.display === '') ? 'block' : 'none';
                      });
                      button.textContent = (button.textContent === 'Hiện trả lời') ? 'Ẩn trả lời' : 'Hiện trả lời';
                      ">Hiện trả lời</button>
                  </div>
                </div>
              </c:when>

              <c:otherwise>
                <div class="message-box reply-box reply-${message.parentMessageID}">
                  <div class="message-header">
                    <strong>${message.senderName}</strong> - <fmt:formatDate value="${message.createdAt}" pattern="dd/MM/yyyy HH:mm" />
                  </div>
                  <div class="message-body">
                    <p>${message.content}</p>
                  </div>
                </div>
              </c:otherwise>
            </c:choose>
          </c:forEach>
          <div id="start-post" class="mb-3">
            <button class="btn btn-primary" onclick="showPopup()">Bắt đầu một bài đăng</button>
          </div>
        </div>
    </div>
</div>

<script>
    function showPopup() {
        document.getElementById('popupBackground').style.display = 'block';
    }

    function closePopup() {
        document.getElementById('popupBackground').style.display = 'none';
    }
</script>

<script>
    window.onload = function() {
        const container = document.getElementById("postContainer");
        container.scrollTop = container.scrollHeight;
    };
</script>

<%
    String classID = (String) session.getAttribute("classID");
%>

<script>
    const classID = "<%= classID %>";
</script>

<script src="/views/clients/assets/js/PostArticleWebsocket.js?v=1.2"></script>

</body>
</html>