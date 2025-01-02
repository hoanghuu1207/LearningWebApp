<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Share Screen & Chat</title>
</head>
<body>
    <h1 id="my-peer">Share Screen & Chat </h1>
    <video id="localVideo" autoplay muted style="border: 1px solid;"></video>
    <video id="remoteVideo" autoplay style="border: 1px solid;"></video>
    <input type='text' id='remoteId' placeholder='Remote ID'>
    <button id="btnShare">Share</button>
    <%--<textarea id="chat" rows="10" cols="30" readonly></textarea>
    <br>
    <input type="text" id="message" placeholder="Type your message" />
    <button onclick="sendMessage()">Send</button>--%>
    <script src="/views/clients/assets/js/peerjs.min.js"></script>
    <script src="/views/clients/assets/js/peer.js"></script>
</body>
</html>
