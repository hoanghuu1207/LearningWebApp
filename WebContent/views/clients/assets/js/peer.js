//<script>
//        const signalingServer = new WebSocket("wss://localhost:8443/signaling");
//        const peer = new Peer();
//
//        signalingServer.onmessage = event => {
//            const data = JSON.parse(event.data);
//            if (data.type === 'offer') peer.signal(data.offer);
//            if (data.type === 'answer') peer.signal(data.answer);
//        };
//
//        // Screen Sharing
//        navigator.mediaDevices.getUserMedia({ video: true }).then(stream => {
//            const video = document.getElementById('localVideo').srcObject = stream;
//            video.play();
//            peer.on('call', call => call.answer(stream));
//        });
//
//        peer.on('call', call => {
//            call.on('stream', remoteStream => {
//                document.getElementById('remoteVideo').srcObject = remoteStream;
//            });
//        });
//
//        // Chat Messaging
//        const chatBox = document.getElementById('chat');
//        const messageInput = document.getElementById('message');
//        let conn;
//        peer.on('connection', connection => {
//            conn = connection;
//            conn.on('data', data => {
//                chatBox.value += `\nRemote: ${data}`;
//            });
//        });
//
//        function sendMessage() {
//            const message = messageInput.value;
//            if (conn) {
//                conn.send(message);
//                chatBox.value += `\nYou: ${message}`;
//                messageInput.value = '';
//            }
//        }
//    </script>

function openStream(){
    const config = {audio: false, video: true};
    return navigator.mediaDevices.getDisplayMedia(config);
}

function playStream(idVideoTag, stream){
    const video = document.getElementById(idVideoTag);
    video.srcObject = stream;
    video.play();
}

//openStream()
//    .then(stream => playStream('localVideo', stream));

const peer = new Peer();

peer.on('open', id => document.getElementById("my-peer").append(id));

document.getElementById("btnShare").addEventListener('click', () => {
    const id = document.getElementById("remoteId").value;
    console.log(id);
    openStream()
        .then(stream => {
            playStream('localVideo', stream);
            const call = peer.call(id, stream);
            call.on('stream', remoteStream => playStream('remoteVideo', remoteStream));
        });
});

peer.on('call', call => {
    playStream('localVideo', call);
//    openStream()
//        .then(stream => {
//            call.answer(stream);
//            playStream('localVideo', stream);
//            call.on('stream', remoteStream => playStream('remoteVideo', remoteStream));
//        });
});