<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Classroom Messages</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
  <style>
    body {
      height: 100vh;
      overflow-x: hidden;
      margin: 0;
      padding: 10px;
      box-sizing: border-box;
      background: linear-gradient(to right, rgba(231, 220, 86, 0.92) 0%, rgba(255, 255, 255, 0.99) 100%);
    }
    .welcome-image {
      text-align: center;
      margin: 20px 0;
      max-width: 100%;
      height: auto;
    }
    .message-box { border: 1px solid rgba(221, 221, 221, 0.51); border-radius: 8px; padding: 10px; margin-bottom: 10px; background-color: rgba(255, 255, 255, 0.47); }
    .reply-box { margin-left: 40px; display: none; } /* Hidden by default */
  </style>
</head>
<body>
<div class="container">
  <div class="welcome-image">
    <img src="/views/clients/assets/img/welcome-to-our-class2.jpg" alt="Welcome to the Classroom" class="img-fluid">
  </div>

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
  <div class="mb-3">
    <button class="btn btn-primary">Bắt đầu một bài đăng</button>
  </div>
</div>

<%--<script>--%>
<%--  function toggleReplies(id) {--%>
<%--    const replies = document.querySelectorAll(`.reply-${id}`);--%>
<%--    replies.forEach(reply => {--%>
<%--      reply.style.display = (reply.style.display === "none" || reply.style.display === "") ? "block" : "none";--%>
<%--    });--%>
<%--  }--%>
<%--</script>--%>
</body>
</html>
