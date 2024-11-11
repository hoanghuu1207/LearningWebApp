
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bài Tập</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f4f4f4;
        }

        .container {
            background: white;
            border-radius: 8px;
            box-shadow: 0 2px 15px rgba(0, 0, 0, 0.1);
            padding: 30px;
            margin-top: 50px;
        }

        h2 {
            color: #343a40;
        }

        .assignment-table {
            margin-top: 30px;
        }

        .overdue {
            color: red;
        }

        .nav-item:hover {
            border-bottom: 1px solid gray;
        }

        .nav-link {
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .nav-link:hover {
            background-color: rgba(233, 236, 239, 0.5);
        }

        .nav-link.active {
            background-color: #d3d3d3;
            color: #343a40;
        }

        .assignment-card {
            border: 1px solid #e0e0e0;
            border-radius: 5px;
            padding: 10px;
            margin: 5px 0;
            background-color: #fafafa;
        }

        .list-group-item {
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="container my-5">
    <ul class="nav nav-pills mb-3">
        <li class="nav-item">
            <a class="nav-link active" id="not-submitted-tab">Bài Tập Chưa Nộp</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" id="submitted-tab">Bài Tập Đã Nộp</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" id="overdue-tab">Bài Tập Quá Hạn</a>
        </li>
    </ul>

    <div id="not-submitted" class="assignment-table mt-3">
        <h3>Bài Tập Chưa Nộp</h3>
        <ul class="list-group">
            <c:forEach var="assignment" items="${notSubmitted}">
                <li class="list-group-item" onclick="location.href='assignmentDetail.jsp?id=${assignment.assignmentID}'">
                    <strong>${assignment.title} </strong> (Hạn nộp: <fmt:formatDate value="${assignment.endTime}" pattern="dd/MM/yyyy HH:mm" />)
                </li>
            </c:forEach>
        </ul>
    </div>

    <div id="submitted" class="assignment-table mt-3" style="display: none;">
        <h3>Bài Tập Đã Nộp</h3>
        <ul class="list-group">
            <c:forEach var="assignment" items="${submitted}">
                <li class="list-group-item" onclick="location.href='assignmentDetail.jsp?id=${assignment.assignmentID}'">
                    <strong> ${assignment.title} </strong> (Hạn nộp: <fmt:formatDate value="${assignment.endTime}" pattern="dd/MM/yyyy HH:mm" />)
                </li>
            </c:forEach>
        </ul>
    </div>

    <div id="overdue" class="assignment-table mt-3" style="display: none;">
        <h3>Bài Tập Quá Hạn</h3>
        <ul class="list-group">
            <c:forEach var="assignment" items="${overdue}">
                <li class="list-group-item" onclick="location.href='assignmentDetail.jsp?id=${assignment.assignmentID}'">
                    <strong> ${assignment.title} </strong> (Hạn nộp: <fmt:formatDate value="${assignment.endTime}" pattern="dd/MM/yyyy HH:mm" />)
                </li>
            </c:forEach>
        </ul>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    // Chuyển đổi giữa các tab
    function showTab(tabId) {
        document.getElementById('not-submitted').style.display = 'none';
        document.getElementById('submitted').style.display = 'none';
        document.getElementById('overdue').style.display = 'none';
        document.getElementById(tabId).style.display = 'block';

        document.querySelectorAll('.nav-link').forEach(link => {
            link.classList.remove('active');
        });
        document.getElementById(tabId + '-tab').classList.add('active');
    }

    document.getElementById('not-submitted-tab').addEventListener('click', () => showTab('not-submitted'));
    document.getElementById('submitted-tab').addEventListener('click', () => showTab('submitted'));
    document.getElementById('overdue-tab').addEventListener('click', () => showTab('overdue'));

    // Khi click vào bài tập chưa nộp
    document.querySelectorAll('#not-submitted .list-group-item').forEach(item => {
        item.addEventListener('click', function () {
            $('#submissionModal').modal('show');
            document.getElementById('selected-assignment').value = this.textContent;
            document.getElementById('submission-form').dataset.deadline = this.dataset.deadline;
        });
    });

    // Xử lý sự kiện nộp bài
    document.getElementById('submission-form').addEventListener('submit', function (event) {
        event.preventDefault();
        const assignment = document.getElementById('selected-assignment').value;
        const content = document.getElementById('content').value;
        const deadline = this.dataset.deadline;
        const submissionStatus = new Date(deadline) >= new Date() ? 'Đúng Hạn' : 'Quá Hạn';

        let newAssignment;
        if (submissionStatus === 'Đúng Hạn') {
            newAssignment = `<div class="assignment-card">
                                    <strong>${assignment}</strong> - ${content}<br>
                                    Hạn nộp: ${deadline.replace('T', ' ')} <span class="text-success">(${submissionStatus})</span>
                                    <button class="btn btn-link resubmit-btn">Nộp Lại</button>
                                </div>`;
            document.getElementById('submitted-assignments').innerHTML += newAssignment;
        } else {
            newAssignment = `<div class="assignment-card overdue">
                                    <strong>${assignment}</strong> - ${content}<br>
                                    Hạn nộp: ${deadline.replace('T', ' ')} <span class="text-danger">(${submissionStatus})</span>
                                    <button class="btn btn-link resubmit-btn">Nộp Lại</button>
                                </div>`;
            document.getElementById('overdue-assignments').innerHTML += newAssignment;
        }

        this.reset();
        $('#submissionModal').modal('hide'); // Ẩn modal sau khi nộp bài
        showTab('submitted'); // Hiển thị tab đã nộp

        // Đăng ký sự kiện cho nút nộp lại
        registerResubmitEvents();
    });

    // Đăng ký sự kiện cho nút nộp lại
    function registerResubmitEvents() {
        document.querySelectorAll('.resubmit-btn').forEach(btn => {
            btn.addEventListener('click', function () {
                const assignmentCard = btn.closest('.assignment-card');
                const assignmentText = assignmentCard.querySelector('strong').textContent;
                const contentText = assignmentCard.childNodes[1].textContent.split('-')[1].trim();

                document.getElementById('resubmission-assignment').value = assignmentText;
                document.getElementById('resubmission-content').value = contentText;
                $('#resubmissionModal').modal('show');
            });
        });
    }

    // Xử lý sự kiện nộp lại bài
    document.getElementById('resubmission-form').addEventListener('submit', function (event) {
        event.preventDefault();
        const assignment = document.getElementById('resubmission-assignment').value;
        const content = document.getElementById('resubmission-content').value;

        const newResubmission = `<div class="assignment-card">
                                        <strong>${assignment}</strong> - ${content}<br>
                                        <span class="text-info">(Bài nộp lại)</span>
                                    </div>`;
        document.getElementById('submitted-assignments').innerHTML += newResubmission;

        this.reset();
        $('#resubmissionModal').modal('hide'); // Ẩn modal sau khi nộp lại
    });

</script>
</body>
</html>
