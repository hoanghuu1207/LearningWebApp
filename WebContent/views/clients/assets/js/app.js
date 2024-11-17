// Khởi tạo kết nối WebSocket tới địa chỉ server
const socket = new WebSocket("wss://192.168.158.200:8443/chat/"+ meetingId);

let localStream ;
let screenStream;
let peerConnections = {};
let currentParticipantId = null;


// Danh sách người tham gia
let participants = [];
// Xử lý khi kết nối được mở
socket.onopen = function() {
    console.log("WebSocket is connected to meeting " + meetingId);
};
socket.onmessage = function(event) {
    const data = JSON.parse(event.data);

    switch (data.type) {
        case "newParticipant":
            addParticipant(data.id, data.name);
            break;
        case "participantLeft":
            removeParticipant(data.id);
            break;
        case "message":
            displayMessage(data.sender, data.message);
            break;
        default:
            console.log("Message received: ", event.data);
    }
};

// Xử lý khi WebSocket đóng
socket.onclose = function(event) {
    console.log("WebSocket is closed.");
};

// Xử lý lỗi WebSocket
socket.onerror = function(error) {
    console.log("WebSocket error: ", error);
};
function sendMessage() {
    const messageInput = document.getElementById('messageInput');
    const message = messageInput.value.trim();

    if (message !== "") {
        // Gửi tin nhắn qua WebSocket
        socket.send( message);
        messageInput.value = "";
    }
}
// Hàm hiển thị tin nhắn trong khung chat
function displayMessage(sender, message) {
    const chatWindow = document.getElementById('chatWindow');
    const messageElement = document.createElement('div');
    messageElement.classList.add('message');
    messageElement.innerHTML = `<strong>${sender}:</strong> ${message}`;

    chatWindow.appendChild(messageElement);
    chatWindow.scrollTop = chatWindow.scrollHeight;  // Cuộn xuống dưới cùng để xem tin nhắn mới nhất
}
// Chọn người tham gia để hiển thị video
function selectParticipant(id) {
    const selectedPeerConnection = peerConnections[id];
    if (selectedPeerConnection && selectedPeerConnection.remoteStream) {
        const mainVideo = document.getElementById('mainVideo');
        mainVideo.srcObject = selectedPeerConnection.remoteStream;
    } else {
        console.error("No video stream available for id:", id);
    }
}

// Hàm thêm người tham gia vào danh sách
function addParticipant(id, name) {
    if (!participants.some(p => p.id === id)) {
        participants.push({ id, name });

        const participantsList = document.getElementById('participants');
        const participantItem = document.createElement('li');

        participantItem.innerHTML = `${name}
            <button onclick="selectParticipant('${id}')">Xem Video</button> `;
        participantsList.appendChild(participantItem);

        if (!localStream) {
            startMediaStream().then(() => {
                createPeerConnection(id);
            }).catch(error => {
                console.error("Error initializing media stream: ", error);
            });
        } else {
            createPeerConnection(id);
        }
    }
}

// Hàm xóa người tham gia khỏi danh sách
function removeParticipant(id) {
    if (peerConnections[id]) {
        peerConnections[id].close();
        delete peerConnections[id];
    }
    participants = participants.filter(p => p.id !== id);
    const participantsList = document.getElementById('participants');
    participantsList.innerHTML = ''; // Xóa danh sách hiện tại
    // Thêm lại tất cả những người còn trong danh sách
    participants.forEach(p => console.log(p.id, p.name));
    participants.forEach(p => {
        const participantItem = document.createElement('li');
        participantItem.innerHTML = `${p.name}
            <button onclick="selectParticipant('${p.id}')">Xem Video</button> `;
        participantsList.appendChild(participantItem);
    }  );
}
// Khởi tạo Peer Connection cho mỗi người tham gia
function createPeerConnection(id) {
    const pc = new RTCPeerConnection();
    peerConnections[id] = pc;

    // Add local stream to new peer connection
    localStream.getTracks().forEach(track => pc.addTrack(track, localStream));

    // Set up event listener for when remote track arrives
    pc.ontrack = event => {
        if (!pc.remoteStream) {
            pc.remoteStream = new MediaStream();
            const mainVideo = document.getElementById('mainVideo');
            mainVideo.srcObject = pc.remoteStream;
        }
        event.streams[0].getTracks().forEach(track => pc.remoteStream.addTrack(track));
    };
}


const mainVideo = document.getElementById('mainVideo');
let audioContext, analyser, microphone, javascriptNode;

// Bắt đầu chia sẻ màn hình
function startScreenShare() {
    if (navigator.mediaDevices && navigator.mediaDevices.getDisplayMedia) {
        navigator.mediaDevices.getDisplayMedia({ video: true })
            .then(stream => {
                screenStream = stream;
                const screenVideoTrack = stream.getVideoTracks()[0];

                // Thêm track chia sẻ màn hình vào peer connection (thay thế track video)
                for (let id in peerConnections) {
                    const sender = peerConnections[id].getSenders().find(s => s.track.kind === 'video');
                    if (sender) {
                        sender.replaceTrack(screenVideoTrack);
                    }
                }
                // Hiển thị stream chia sẻ màn hình lên video chính
                mainVideo.srcObject = new MediaStream([screenVideoTrack]);
                // Khi chia sẻ màn hình kết thúc
                screenVideoTrack.onended = () => {
                    stopScreenShare();
                };
            })
            .catch(err => {
                console.error("Error sharing screen: ", err);
            });
    } else {
        console.log("getDisplayMedia is not supported in this browser.");
    }
}

// Dừng chia sẻ màn hình
function stopScreenShare() {
    if (screenStream) {
        screenStream.getTracks().forEach(track => track.stop()); // Dừng track chia sẻ màn hình
    }
    // Trở lại stream của camera sau khi dừng chia sẻ màn hình
    const videoTrack = localStream.getVideoTracks()[0];
    for (let id in peerConnections) {
        const sender = peerConnections[id].getSenders().find(s => s.track.kind === 'video');
        if (sender) {
            sender.replaceTrack(videoTrack);
        }
    }
    // Hiển thị lại video từ camera lên video chính
    mainVideo.srcObject = localStream;
}

// Bắt đầu luồng media (video và audio)
async function startMediaStream() {
    console.log(isPermittedvideo, isPermittedaudio);
    await navigator.mediaDevices.getUserMedia({video: isPermittedvideo, audio: isPermittedaudio})
        .then(stream => {
            localStream = stream;

            // Hiển thị camera lên video chính
            mainVideo.srcObject = localStream;
            // Thêm cả audio và video vào peer connection
            // for (let id in peerConnections) {
            //     const pc = peerConnections[id];
            //     stream.getTracks().forEach(track => pc.addTrack(track, stream));
            // }
            Object.values(peerConnections).forEach(pc => {
                localStream.getTracks().forEach(track => pc.addTrack(track, localStream));
            });
            // Bat/Tat cam/mic tu prepare_meeting
            if (isPermittedaudio && audioEnabled) {
                const audioTrack = localStream.getAudioTracks()[0];
                audioTrack.enabled = true;
                const micIcon = document.getElementById("ic_mute");
                micIcon.classList.replace("icon-muted", "icon-active");
                micIcon.src = "/views/clients/assets/fonts/myself-icons/ic_mic.png";
            } else {
                const audioTrack = localStream.getAudioTracks()[0];
                audioTrack.enabled = false;
            }
            if (isPermittedvideo && videoEnabled) {
                const videoTrack = localStream.getVideoTracks()[0];
                videoTrack.enabled = true;
                const camIcon = document.getElementById("ic_mute_cam");
                // Cập nhật nút Camera
                camIcon.classList.replace("icon-muted", "icon-active");
                camIcon.src = "/views/clients/assets/fonts/myself-icons/ic_video_camera.png"; // Icon bật cam
            } else {
                const videoTrack = localStream.getVideoTracks()[0];
                videoTrack.enabled = false;
            }
        })
        .catch(error => {
            console.error('Error accessing media devices.', error);
        });
}

// Bật/tắt micro
function toggleMute() {
    if (localStream && localStream.getAudioTracks().length > 0) {
        const audioTrack = localStream.getAudioTracks()[0];
        audioTrack.enabled = !audioTrack.enabled; // Đảo trạng thái của track
        // Cập nhật nút Micro
        const micIcon = document.getElementById("ic_mute");
        if (audioTrack.enabled) {
            micIcon.classList.replace("icon-muted", "icon-active");
            micIcon.src = "/views/clients/assets/fonts/myself-icons/ic_mic.png";
        }else {
            micIcon.classList.replace("icon-active", "icon-muted");
            micIcon.src = "/views/clients/assets/fonts/myself-icons/ic_mute.png"; // Icon tắt mic
        }
    } else {
        console.error('Audio track is not available.');
    }
}

// Bật/tắt camera
function toggleVideo() {
    if (localStream && localStream.getVideoTracks().length > 0) {
        const videoTrack = localStream.getVideoTracks()[0];
        videoTrack.enabled = !videoTrack.enabled; // Đảo trạng thái của track
        const camIcon = document.getElementById("ic_mute_cam");
        // Cập nhật nút Camera
        if (videoTrack.enabled) {
            camIcon.classList.replace("icon-muted", "icon-active");
            camIcon.src = "/views/clients/assets/fonts/myself-icons/ic_video_camera.png"; // Icon bật cam
        } else {
            camIcon.classList.replace("icon-active", "icon-muted");
            camIcon.src = "/views/clients/assets/fonts/myself-icons/ic_mute_camera.png"; // Icon tắt cam
        }
    } else {
        console.error('Video track is not available.');
    }
}

// Khởi động stream khi trang được tải
window.onload = startMediaStream;




function toggleParticipants() {
    const chatSection = document.getElementById('chatSection');
    const participantsList = document.getElementById('participantsList');
    const mainScreen = document.getElementById('mainScreen');
    if (!chatSection.classList.contains('hidden')) {
        chatSection.classList.add('hidden');
    }
    participantsList.classList.toggle('hidden');

    // Adjust the main screen layout
    if (participantsList.classList.contains('hidden')) {
        mainScreen.style.width = '100%';
    } else {
        mainScreen.style.width = '70%'; // Adjust width to show participants on the right
    }
}
function toggleChat() {
    const participantsList = document.getElementById('participantsList');
    const chatSection = document.getElementById('chatSection');
    const mainScreen = document.getElementById('mainScreen');
    if (!participantsList.classList.contains('hidden')) {
        participantsList.classList.add('hidden');
    }
    chatSection.classList.toggle('hidden');

    // Adjust the main screen layout
    if (chatSection.classList.contains('hidden')) {
        mainScreen.style.width = '100%';
    } else {
        mainScreen.style.width = '70%'; // Adjust width to show chat on the right
    }
}
function cancelMeeting() {
    if (confirm("Bạn muốn rời khỏi cuộc hop ?")) {
        socket.close(); // Close the WebSocket connection
        window.location.href = "/views/clients/pages/class/aftermeeting.jsp"; // Redirect to the desired page after cancel
    }
}