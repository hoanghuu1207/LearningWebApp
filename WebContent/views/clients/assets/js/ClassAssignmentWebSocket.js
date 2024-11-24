const socketAssignmentClass = new WebSocket("wss://192.168.1.27:8443/class_assignment");

setInterval(function() {
    const obj = { classID: classID, userID: userID };
    const myJSON = JSON.stringify(obj);
    socketAssignmentClass.send(myJSON);
}, 10000);

socketAssignmentClass.onmessage = function(event){
    const data = JSON.parse(event.data);

    data.notSubmitteds = JSON.parse(data.notSubmitteds);
    data.submittedes = JSON.parse(data.submittedes);
    data.overdues = JSON.parse(data.overdues);

    console.log(data.submittedes[0].key);
    console.log(data.submittedes[0].value);
}