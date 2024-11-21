const socketClassMember = new WebSocket("wss://192.168.158.200:8443/classMember/" + classID);

let studentIdDelete;

const listStudentDelete = document.querySelectorAll(".member-group #delete-student");

listStudentDelete.forEach(studentDelete => {
    studentDelete.addEventListener("click", () => {
        studentIdDelete = studentDelete.closest('tr').getAttribute("studentId");
        console.log(studentIdDelete);
    });
});

function confirmDelete(){
    const obj = { classID: classID, studentID: studentIdDelete };
    console.log(obj);
    const myJSON = JSON.stringify(obj);
    socketClassMember.send(myJSON);
}

socketClassMember.onmessage = function(event){
    const data = JSON.parse(event.data);

    const row = document.querySelector(`tr[studentId="${data.studentID}"]`);
    row.remove();
}