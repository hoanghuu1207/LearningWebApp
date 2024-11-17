const socketNotification = new WebSocket("wss://192.168.158.200:8443/notification/" + userID);

socketNotification.onmessage = function(event) {
    const data = JSON.parse(event.data);

    if(roleID == "2"){
        data.url = "/teacher" + data.url;
    }

    const notificationContent = document.getElementById("notification-content");
    const notificationCount = document.querySelector(".notification-container .notification-count");

    const innerHTML = `<a class="dropdown-item" role="presentation" href="${data.url}" style="font-size:12px;" data-bs-toggle="tooltip" title="${data.content}">${data.content}</a>`;

    notificationContent.innerHTML = innerHTML + notificationContent.innerHTML;
    notificationCount.style.display = 'block';

    const buttonVolume = document.getElementById("volume-button");
    buttonVolume.addEventListener('click', () => {
        const audio = new Audio('/views/clients/assets/img/livechat-129007.mp3');
        audio.play().catch(error => console.error(error));
    });

    buttonVolume.click();

};