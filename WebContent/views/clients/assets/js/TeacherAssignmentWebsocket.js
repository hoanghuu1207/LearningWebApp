const socketTeacherAssignment = new WebSocket("wss://192.168.1.9:8443/teacher_assignment/" + userID);

function toggleAssignment(assignmentID){
    console.log(assignmentID);
    const obj = { assignmentID: assignmentID, userID: userID };
    console.log(obj);
    const myJSON = JSON.stringify(obj);
    socketTeacherAssignment.send(myJSON);
}

socketTeacherAssignment.onmessage = function(event) {
    const data = JSON.parse(event.data);

    data.submissions = JSON.parse(data.submissions);

    console.log(data.submissions);

    const modal = document.getElementById("modal-submision");
    let innerHTML = "";
    data.submissions.forEach(submision => {
        innerHTML += `<tr>
                        <td>${submision.sendername}</td>
                      <td>${submision.submissionDate}</td>
                      <td><a class="btn" href="/downloadFile?filePath=${submision.filePath}" download><i class="fa fa-download"></i> ${submision.titleFile}</a></td>
                      </tr>`;
    });
    modal.innerHTML = innerHTML;
}