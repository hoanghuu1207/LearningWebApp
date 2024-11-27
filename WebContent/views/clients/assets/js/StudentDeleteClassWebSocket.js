const socketStudentDeleteClass = new WebSocket("wss://172.20.10.5:8443/student_remove_class/" + userID);

let classDelete;

const classBoxes = document.querySelectorAll('[class_id]');
classBoxes.forEach(classBox => {
    classBox.addEventListener('click', (event) => {
        if(event.target.matches("#student-delete-class")){
            classDelete = classBox.getAttribute("class_id");

            StudentDelteClass(classDelete);

            return;
        }
        if(event.target.matches('#get-code') || event.target.matches('img')){
            navigator.clipboard.writeText(classBox.getAttribute("class_id"))
                        .then(() => alert("Đã sao chép mã lớp"))
                        .catch(err => alert("Lỗi khi sao chép: " + err));
            return;
        }
        window.location.href = 'class/detail?classID=' + classBox.getAttribute("class_id");
    });
});

function StudentDelteClass(classID){
    const obj = { classID: classID, studentID: userID };
    console.log(obj);
    const myJSON = JSON.stringify(obj);
    socketStudentDeleteClass.send(myJSON);
}

socketStudentDeleteClass.onmessage = function(event){
    const data = JSON.parse(event.data);

    const box = document.querySelector(`.container-md[class_id="${data.classID}"]`).closest(".fixed-box");
    box.remove();
}