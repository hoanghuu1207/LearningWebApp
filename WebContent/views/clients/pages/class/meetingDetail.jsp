<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Meeting Details</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container my-5">
    <h2>Chi tiết cuộc họp</h2>

    <c:choose>
        <c:when test="${type == 1}">
            <p>Tiêu đề: ${meeting.title}</p></br>
            <c:forEach var="teacher" items="${teachers}">
            <h4>Được lên lịch bởi: ${teacher.firstName} ${teacher.lastName}</h4>
            </c:forEach>
            <p>Đã lên lịch vào lúc: <fmt:formatDate value="${schedule.timeCreate}" pattern="dd/MM/yyyy HH:mm" /></p>
            <p>Thời gian bắt đầu cuộc họp: <fmt:formatDate value="${meeting.startTime}" pattern="dd/MM/yyyy HH:mm" /></p>
        </c:when>

        <c:when test="${type == 2}">
            <p>Tiêu đề: ${meeting.title}</p></br>
            <p>Thời gian bắt đầu cuộc họp: <fmt:formatDate value="${meeting.startTime}" pattern="dd/MM/yyyy HH:mm" /></p>
            <p>Thời gian kết thúc cuộc họp: <fmt:formatDate value="${meeting.endTime}" pattern="dd/MM/yyyy HH:mm" /></p>
            <p>Thời lượng cuộc họp: ${meeting.duration}</p>
        </c:when>

        <c:when test="${type == 3}">
            <p>Tiêu đề: ${meeting.title}</p></br>
            <c:forEach var="teacher" items="${teachers}">
            <h4>Được lên lịch bởi: ${teacher.firstName} ${teacher.lastName}</h4>
            </c:forEach>
            <p>Đã lên lịch vào lúc: <fmt:formatDate value="${schedule.timeCreate}" pattern="dd/MM/yyyy HH:mm" /></p>
            <p>Cuộc họp đã được hủy vào lúc: <fmt:formatDate value="${meeting.endTime}" pattern="dd/MM/yyyy HH:mm" /></p>
            <p class="text-danger">Cuộc họp đã hủy!</p>
        </c:when>
    </c:choose>

</div>
</body>
</html>
