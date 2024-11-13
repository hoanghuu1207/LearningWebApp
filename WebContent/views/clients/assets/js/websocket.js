console.log("oke");
const socket = new WebSocket("ws://localhost:8080/notificationEndpoint");

socket.onmessage = function(event) {
    alert(event.data);  // Hoặc cập nhật giao diện tùy ý
};
