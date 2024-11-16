const socket = new WebSocket("ws://localhost:8080/notificationEndpoint");

socket.onmessage = function(event) {
    alert(JSON.parse(event.data.message));  // Hoặc cập nhật giao diện tùy ý
};
