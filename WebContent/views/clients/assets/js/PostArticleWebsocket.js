const socket = new WebSocket("ws://localhost:8080/postArticle/" + classID);

socket.onmessage = function(event) {
    const data = JSON.parse(event.data);

    const messageBox = document.createElement("div");
    if(data.parentMessageID == 0){
        messageBox.className = "message-box";

        messageBox.innerHTML = `<div class="message-header">
                                 <strong>${data.senderName}</strong> - ${data.createdAt}
                               </div>
                               <div class="message-body">
                                 <p>${data.content}</p>
                                 <button class="btn btn-link btn-sm" onclick=
                                         "const replies = document.querySelectorAll('.reply-${data.messageID}');
                                         const button = this;
                                         replies.forEach(reply => {
                                   reply.style.display = (reply.style.display === 'none' || reply.style.display === '') ? 'block' : 'none';
                                   });
                                   button.textContent = (button.textContent === 'Hiện trả lời') ? 'Ẩn trả lời' : 'Hiện trả lời';
                                   ">Hiện trả lời</button>
                               </div>`;
    }else{
        messageBox.className = "message-box reply-box reply-${data.parentMessageID}";
        messageBox.innerHTML = `<div class="message-header">
                                 <strong>${data.senderName}</strong> - ${data.createdAt}
                               </div>
                               <div class="message-body">
                                 <p>${data.content}</p>
                               </div>`;
    }

    const container = document.getElementById("postContainer");
    const startPostButton = document.getElementById("start-post");

    container.insertBefore(messageBox, startPostButton);

    container.scrollTop = container.scrollHeight;
};