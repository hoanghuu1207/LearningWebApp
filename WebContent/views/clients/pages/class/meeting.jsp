<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Video Meeting with Chat</title>
    <link rel="stylesheet" href="/views/clients/assets/css/style_meeting.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"/>

</head>
<body>
<div class="container" id = "container">
    <div class="main-content">
        <!-- Màn hình lớn để hiển thị video hoặc chia sẻ màn hình -->
        <div id="mainScreen" class="expanded">
            <video id="mainVideo" autoplay="autoplay" ></video>
        </div>


    </div>
    <div class="controls">
        <img src="/views/clients/assets/fonts/myself-icons/ic_mute.png" alt="Mute Mic" id="ic_mute"
             class="icon-btn icon-muted" onclick="toggleMute()">
        <img src="/views/clients/assets/fonts/myself-icons/ic_mute_camera.png" alt="Mute Camera" id="ic_mute_cam"
             class="icon-btn icon-muted" onclick="toggleVideo()">
        <img src="/views/clients/assets/fonts/myself-icons/share_screen_icon.png" alt="Share Screen" id="shareScreenButton"
             class="icon-btn icon-normal" onclick="startScreenShare()">
        <img src="/views/clients/assets/fonts/myself-icons/participants.png" alt="Share Screen" id="participantsButton"
             class="icon-btn icon-normal" onclick="toggleParticipants()">
        <img src="/views/clients/assets/fonts/myself-icons/chat.png" alt="Share Screen" id="chatButton"
             class="icon-btn icon-normal" onclick="toggleChat()">
        <img src="/views/clients/assets/fonts/myself-icons/cancel_meeting.png" alt="Share Screen" id="cancelButton"
             class="icon-btn icon-muted" onclick="cancelMeeting()">
    </div>

</div>

<div class = "secondary-container"  id = 'secondary-container'>
    <div class="sidebar hidden" id="participantsList">
        <h3>Participants</h3>
        <ul id="participants">
        </ul>

    </div>
    <!-- Khung trò chuyện -->
    <div  id="chatSection" class="hidden">
        <div id="chatWindow">
        </div>
        <div class="input-area">
            <input type="text" id="messageInput" placeholder="Nhập tin nhắn..." onkeydown="if(event.key === 'Enter') sendMessage()"/>
            <button onclick="sendMessage()"><i class="fas fa-paper-plane"></i></button>
        </div>
    </div>
</div>

<script>
    const isPermittedvideo =  <%= request.getAttribute("isPermittedvideo") %> ;
    const isPermittedaudio = <%= request.getAttribute("isPermittedaudio") %>;
    const videoEnabled =  <%= request.getAttribute("videoEnabled") %> ;
    const audioEnabled = <%= request.getAttribute("audioEnabled") %> ;
    const meetingId = '<%= request.getAttribute("meetingId") %>';
    const classroomID = '<%= request.getAttribute("classroomID") %>';
    const userId = '<%= request.getAttribute("userId") %>';
</script>
<script src="/views/clients/assets/js/app2.js"></script>

</body>
</html>