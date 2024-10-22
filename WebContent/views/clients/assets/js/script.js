function createMeeting() {
    let newMeetingCode = Math.random().toString(36).substring(2, 8);
    alert("Tạo cuộc họp mới với mã: " + newMeetingCode);
    window.location.href = `prepare_meeting.html?code=${newMeetingCode}`;
}

function joinMeeting() {
    let meetingCode = document.getElementById("meeting-id").value;
    const joinMeeting = document.getElementById("joinMeeting");
    if (meetingCode) {
        window.location.href = `meeting.html?code=${meetingCode}`;
        joinMeeting.disabled = false;
    
    } else {
        joinMeeting.disabled = true;

    }
}

function checkMeetingId() {
    const meetingId = document.getElementById("meeting-id").value;
    const joinButton = document.getElementById("joinMeeting");

    if (meetingId.trim() !== "") {
        joinButton.disabled = false;
    } else {
        joinButton.disabled = true;
    }
}


function enterCode(){
    const meetingCodeInput = document.getElementById('meetingCode');
    const joinButton = document.getElementById('joinButton');

    if (meetingCodeInput.value.trim() !== "" && meetingCodeInput.value !== "Nhập mã cuộc họp") {
        joinButton.disabled = false; 
        joinButton.classList.add('active'); // Thêm class active để có màu xanh
    } else {
        joinButton.disabled = true; 
        joinButton.classList.remove('active'); // Loại bỏ class active
    }
}

function login(){
    window.location.href = "index.html";
}

function openMeeting(){
    window.location.replace("meeting.html");
}

document.getElementById("btn-openModal").addEventListener("click", function () {
    document.getElementById("newMeeting_Modal").style.display = "block";
});

document.getElementById("btn-closeModal").addEventListener("click", function () {
    document.getElementById("newMeeting_Modal").style.display = "none";
});









