// Khởi tạo kết nối WebSocket tới địa chỉ server
const socket = new WebSocket("wss://192.168.50.180:8443/meet/"+ meetingId);

let localStream ;
let screenStream;
let peerConnections = {};
const candidateQueues = {}; // Queue for ICE candidates
const configuration = {
    iceServers: [
        { urls: "stun:stun.l.google.com:19302" } // Máy chủ STUN miễn phí
    ]
};
// Danh sách người tham gia
let participants = [];
// Xử lý khi kết nối được mở
socket.onopen = function() {
    console.log("WebSocket is connected to meeting " + meetingId);
    socket.send(JSON.stringify({ type: "register", userId: userId }));
};
socket.onmessage = async function (event) {
    const data = JSON.parse(event.data);

    switch (data.type) {
        case "newParticipant":
            if (data.id != userId) {
                addParticipant(data.id, data.name);
                console.log(data.id, data.name);
            }
            break;
        case "participantLeft":
            removeParticipant(data.id);
            break;
        case "message":
            displayMessage(data.sender, data.message);
            console.log(data.sender, data.message);
            break;
        case "offer":
            console.log("part2");
            await handleOffer(data.offer, data.id);
            break;
        case "answer":
            await handleAnswer(data.answer, data.id);
            break;
        case "candidate":
            await handleNewICECandidate(data.candidate, data.id);
            break;
        default:
            console.log("Message received: ", event.data);
    }
};

async function handleOffer(offer, senderId) {
    const pc = peerConnections[senderId] || await createPeerConnection(senderId);
    //const pc = createPeerConnection(senderId);
    try {
        await pc.setRemoteDescription(new RTCSessionDescription(offer));
        const answer = await pc.createAnswer();
        await pc.setLocalDescription(answer);

        // Gửi answer tới peer
        // socket.send(JSON.stringify({
        //     type: 'answer',
        //     to: from,
        //     data: answer
        // }));
        socket.send(JSON.stringify({ type: "answer", answer: pc.localDescription, id: senderId }));
        // Xử lý các ICE Candidate đã xếp hàng
        if (candidateQueues[senderId]) {
            for (const candidate of candidateQueues[senderId]) {
                await pc.addIceCandidate(new RTCIceCandidate(candidate));
            }
            delete candidateQueues[senderId];
        }
    } catch (error) {
        console.error(`Error handling offer from ${senderId}:`, error);
    }


}

async function handleAnswer(answer, senderId) {
    const pc = peerConnections[senderId];
    if (pc) {
        try {
            await pc.setRemoteDescription(new RTCSessionDescription(answer));
        } catch (error) {
            console.error(`Error handling answer from ${senderId}:`, error);
        }
    }
}

async function handleNewICECandidate(candidate, senderId) {
    const pc = peerConnections[senderId];
    if (pc) {
        if (pc.remoteDescription && pc.remoteDescription.type) {
            try {
                await pc.addIceCandidate(new RTCIceCandidate(candidate));
            } catch (error) {
                console.error(`Error adding ICE candidate from ${senderId}:`, error);
            }
        } else {
            // Nếu remoteDescription chưa sẵn sàng, xếp hàng ICE Candidate
            if (!candidateQueues[senderId]) candidateQueues[senderId] = [];
            candidateQueues[senderId].push(candidate);
        }
    } else {
        console.warn(`No PeerConnection found for ${senderId}`);
    }
}


// Xử lý khi WebSocket đóng
socket.onclose = function(event) {
    console.log("WebSocket is closed.");
};

// Xử lý lỗi WebSocket
socket.onerror = function(error) {
    console.log("WebSocket error: ", error);
};

// Khởi tạo Peer Connection cho mỗi người tham gia
async function createPeerConnection(id) {
    const pc = new RTCPeerConnection(configuration); // Sử dụng cấu hình ICE servers
    peerConnections[id] = pc;
    // localStream.getTracks().forEach(track => pc.addTrack(track, localStream));
    if (localStream) {
        localStream.getTracks().forEach(track => pc.addTrack(track, localStream));
    }
    console.log("Khoi tao Peer cho " , id);
    // Set up event listener for when remote track arrives
    pc.ontrack = event => {
        if (!pc.remoteStream) {
            pc.remoteStream = new MediaStream();
            const mainVideo = document.getElementById('mainVideo');
            mainVideo.srcObject = pc.remoteStream;
        }
        event.streams[0].getTracks().forEach(track =>
            pc.remoteStream.addTrack(track)
        );
    };
    pc.onicecandidate = event => {
        if (event.candidate) {
            socket.send(
                JSON.stringify({
                    type: "candidate",
                    candidate: event.candidate,
                    id: id,
                })
            );
        }
    };

    return pc;
}
function sendMessage() {
    const messageInput = document.getElementById('messageInput');
    const message = messageInput.value.trim();

    if (message !== "") {
        // Gửi tin nhắn qua WebSocket
        //socket.send( message);
        socket.send(JSON.stringify({ type: "message", message : message, classroomID }));
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
    // const pc = peerConnections[id];
    // if (pc && pc.remoteStream) {
    //     const mainVideo = document.getElementById("mainVideo");
    //     const screenTrack = pc.remoteStream.getVideoTracks().find(track => track.label.includes("screen"));
    //     const cameraTrack = pc.remoteStream.getVideoTracks().find(track => track.label.includes("camera"));
    //
    //     if (screenTrack) {
    //         console.log("screen");
    //         mainVideo.srcObject = new MediaStream([screenTrack]);
    //     } else if (cameraTrack) {
    //         console.log("camera");
    //         mainVideo.srcObject = new MediaStream([cameraTrack]);
    //     } else {
    //         console.log("ddefault");
    //         mainVideo.srcObject = null; // Clear the video
    //     }
    // } else {
    //     console.error("No video stream available for id:", id);
    // }
}

// Hàm thêm người tham gia vào danh sách
function addParticipant(id, name) {
    if (!participants.some(p => p.id === id)) {
        participants.push({ id, name });

        const participantsList = document.getElementById("participants");
        const participantItem = document.createElement("li");

        participantItem.innerHTML = `${name}
            <button onclick="selectParticipant('${id}')">Xem Video</button> `;
        participantsList.appendChild(participantItem);

        if (!localStream) {
            startMediaStream().then(() => {
                createPeerConnection(id).then(() => createOffer(id));
                console.log("on1");
                //createOffer(id); // Gửi SDP Offer
                console.log("part1");
            }).catch(error => {
                console.error("Error initializing media stream: ", error);
            });
        } else {
            createPeerConnection(id).then(() => createOffer(id));
            console.log("on2");
            //createOffer(id); // Gửi SDP Offer
            console.log("part2");
        }
    }
}

async function createOffer(id) {
    const pc = peerConnections[id];
    if (pc) {
        const offer = await pc.createOffer();
        await pc.setLocalDescription(offer);

        socket.send(
            JSON.stringify({
                type: "offer",
                offer: pc.localDescription,
                id: id,
            })
        );
        console.log("offer1");
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



const mainVideo = document.getElementById('mainVideo');
let audioContext, analyser, microphone, javascriptNode;

// Bắt đầu chia sẻ màn hình
async function startScreenShare() {
    if (navigator.mediaDevices && navigator.mediaDevices.getDisplayMedia) {
        await navigator.mediaDevices.getDisplayMedia({ video: true, audio: true })
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
async function stopScreenShare() {
    if (screenStream) {
        screenStream.getTracks().forEach(track => track.stop()); // Dừng track chia sẻ màn hình
    }
    // Trở lại stream của camera sau khi dừng chia sẻ màn hình
    const videoTrack = localStream.getVideoTracks()[0];
    for (let id in peerConnections) {
        const sender = peerConnections[id].getSenders().find(s => s.track.kind === 'video');
        if (sender) {
            await sender.replaceTrack(videoTrack);
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
        window.location.href = "/afterMeeting?classID="+ classroomID; // Redirect to the desired page after cancel
    }
}