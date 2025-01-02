//const socketAssignmentClass = new WebSocket("wss://192.168.1.18:8443/class_assignment/" + userID);
//
//setInterval(function() {
//    const obj = { classID: classID, userID: userID };
//    const myJSON = JSON.stringify(obj);
//    socketAssignmentClass.send(myJSON);
//}, 10000);
//
//socketAssignmentClass.onmessage = function(event){
//    const data = JSON.parse(event.data);
//
//    data.notSubmitteds = JSON.parse(data.notSubmitteds);
//    data.submittedes = JSON.parse(data.submittedes);
//    data.overdues = JSON.parse(data.overdues);
//
//    let modalNotSubmitShow = document.querySelector("#not-submitted .modal.show");
//    let modalSubmitShow = document.querySelector("#submitted .modal.show");
//    let modalOverdueShow = document.querySelector("#overdue .modal.show");
//
//    let idNotSubmit;
//    if(modalNotSubmitShow){
//        idNotSubmit = modalNotSubmitShow.getAttribute("id").split("-")[1];
//    }
//
//     let idSubmit;
//     if(modalSubmitShow){
//        idSubmit = modalSubmitShow.getAttribute("id").split("-")[1];
//     }
//
//     let idOverdue;
//     if(modalOverdueShow){
//        idOverdue = modalOverdueShow.getAttribute("id").split("-")[1];
//     }
//
//    console.log(modalNotSubmitShow)
//    console.log(modalSubmitShow)
//    console.log(modalOverdueShow)
//
//    if(modalNotSubmitShow) modalNotSubmitShow = modalNotSubmitShow.outerHTML;
//    if(modalSubmitShow) modalSubmitShow = modalSubmitShow.outerHTML;
//    if(modalOverdueShow) modalOverdueShow = modalOverdueShow.outerHTML;
//
//    const listGroupNotsubmitted = document.querySelector("#not-submitted .list-group");
//    listGroupNotsubmitted.innerHTML = "";
//    data.notSubmitteds.forEach(assignment => {
//        const date = new Date(assignment.endTime);
//
//        const formattedDate = date.toLocaleString("en-GB", {
//            day: "2-digit",
//            month: "2-digit",
//            year: "numeric",
//            hour: "2-digit",
//            minute: "2-digit",
//            hour12: false,
//        });
//
//        assignment.endTime = formattedDate;
//
//        let content;
//
//        if(assignment.assignmentID == idNotSubmit){
//            content = modalNotSubmitShow;
//        }else{
//            content = `<div class="modal fade" id="assignmentModal-${assignment.assignmentID}" tabindex="-1" aria-labelledby="assignmentModalLabel-${assignment.assignmentID}" aria-hidden="true">
//                            <div class="modal-dialog">
//                                <div class="modal-content">
//                                    <div class="modal-header">
//                                        <h5 class="modal-title" id="assignmentModalLabel-${assignment.assignmentID}">Bài tập</h5>
//                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
//                                    </div>
//                                    <div class="modal-body">
//                                        <div class="form-group">
//                                            <label for="datetime">Thời gian hết hạn</label>
//                                            <input type="datetime-local" class="form-control" id="datetime" name="datetime" required value="${assignment.endTime}" readonly>
//                                        </div>
//                                        <div class="form-group">
//                                            <label for="title">Tiêu đề</label>
//                                            <input type="text" class="form-control" id="title" name="title" required placeholder="Nhập tiêu đề cho bài tập" value="${assignment.title}" readonly>
//                                        </div>
//                                        <div class="form-group">
//                                            <label for="description">Mô tả</label>
//                                            <input type="text" class="form-control" id="description" name="description" required placeholder="Nhập mô tả cho bài tập" value="${assignment.description}" readonly>
//                                        </div>
//                                        ${assignment.titleFile && assignment.filePath ? `
//                                            <div class="upload-section">
//                                               <label for="file">Tài liệu tham khảo</label>
//                                               <div class="mb-3">
//                                                    <a class="btn" href="/downloadFile?filePath=${assignment.filePath}" download><i class="fa fa-download"></i> ${assignment.titleFile}</a>
//                                               </div>
//                                            </div>
//                                        ` : ''}
//                                        <form action="/class_assignments" method="POST" id="form-search">
//                                            <label for="file" class="h4">Bài làm của bạn</label>
//                                             <div class="mb-3">
//                                               <input type="file" name="file" class="form-control" required>
//                                               <input type="submit" class="btn btn-primary" value="Nộp bài"></button>
//                                             </div>
//                                        </form>
//                                    </div>
//                                    <div class="modal-footer">
//                                    </div>
//                                </div>
//                            </div>
//                        </div>`;
//        }
//
//        console.log(content)
//
//        let innerHTML = `<li class="list-group-item" data-bs-toggle="modal" data-bs-target="#assignmentModal-${assignment.assignmentID}">
//                             <strong>${assignment.title} </strong><br>
//                             <small>Hạn nộp: ${assignment.endTime}</small>
//                         </li>` + content;
//
//        listGroupNotsubmitted.innerHTML += innerHTML;
//    });
//
//    const listGroupSubmitted = document.querySelector("#submitted .list-group");
//    listGroupSubmitted.innerHTML = "";
//
//    data.submittedes.forEach(assignment => {
//        const date1 = new Date(assignment.key.endTime);
//        const date2 = new Date(assignment.value.submissionDate);
//
//        const formattedDate1 = date1.toLocaleString("en-GB", {
//            day: "2-digit",
//            month: "2-digit",
//            year: "numeric",
//            hour: "2-digit",
//            minute: "2-digit",
//            hour12: false,
//        });
//
//        const formattedDate2 = date2.toLocaleString("en-GB", {
//            day: "2-digit",
//            month: "2-digit",
//            year: "numeric",
//            hour: "2-digit",
//            minute: "2-digit",
//            hour12: false,
//        });
//
//        assignment.key.endTime = formattedDate1;
//        assignment.value.submissionDate = formattedDate2;
//
//        let content;
//
//        console.log("a: " + assignment.key.assignmentID + " b: " + idSubmit)
//
//        if(assignment.key.assignmentID == idSubmit){
//            content = modalSubmitShow;
//        }else{
//            content = `<div class="modal fade" id="assignmentModal-${assignment.key.assignmentID}" tabindex="-1" aria-labelledby="assignmentModalLabel-${assignment.key.assignmentID}" aria-hidden="true">
//                            <div class="modal-dialog">
//                                <div class="modal-content">
//                                    <div class="modal-header">
//                                        <h5 class="modal-title" id="assignmentModalLabel-${assignment.key.assignmentID}">Bài tập</h5>
//                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
//                                    </div>
//                                    <div class="modal-body">
//                                        <div class="form-group">
//                                            <label for="datetime">Thời gian hết hạn</label>
//                                            <input type="datetime-local" class="form-control" id="datetime" name="datetime" required value="${assignment.key.endTime}" readonly>
//                                        </div>
//                                        <div class="form-group">
//                                            <label for="title">Tiêu đề</label>
//                                            <input type="text" class="form-control" id="title" name="title" required placeholder="Nhập tiêu đề cho bài tập" value="${assignment.key.title}" readonly>
//                                        </div>
//                                        <div class="form-group">
//                                            <label for="description">Mô tả</label>
//                                            <input type="text" class="form-control" id="description" name="description" required placeholder="Nhập mô tả cho bài tập" value="${assignment.key.description}" readonly>
//                                        </div>
//                                        ${assignment.key.titleFile && assignment.key.filePath ? `
//                                            <div class="upload-section">
//                                               <label for="file">Tài liệu tham khảo</label>
//                                               <div class="mb-3">
//                                                    <a class="btn" href="/downloadFile?filePath=${assignment.key.filePath}" download><i class="fa fa-download"></i> ${assignment.key.titleFile}</a>
//                                               </div>
//                                            </div>
//                                        ` : ''}
//                                        ${assignment.value.titleFile && assignment.value.filePath ? `
//                                            <div class="upload-section">
//                                               <label for="file">Bài làm của bạn</label>
//                                               <div class="mb-3">
//                                                    <a class="btn" href="/downloadFile?filePath=${assignment.value.filePath}" download><i class="fa fa-download"></i> ${assignment.value.titleFile}</a>
//                                               </div>
//                                            </div>
//                                        ` : ''}
//                                    </div>
//                                    <div class="modal-footer">
//                                    </div>
//                                </div>
//                            </div>
//                        </div>`;
//        }
//
//        console.log(content)
//
//        let innerHTML = `<li class="list-group-item" data-bs-toggle="modal" data-bs-target="#assignmentModal-${assignment.key.assignmentID}">
//                             <strong>${assignment.key.title} </strong><br>
//                             <small>Hạn nộp: ${assignment.key.endTime}</small>
//                         </li>` + content;
//
//        listGroupSubmitted.innerHTML += innerHTML;
//    });
//
//    const listGroupOverdue = document.querySelector("#overdue .list-group");
//    listGroupOverdue.innerHTML = "";
//
//    data.overdues.forEach(assignment => {
//        const date = new Date(assignment.endTime);
//
//        const formattedDate = date.toLocaleString("en-GB", {
//            day: "2-digit",
//            month: "2-digit",
//            year: "numeric",
//            hour: "2-digit",
//            minute: "2-digit",
//            hour12: false,
//        });
//
//        assignment.endTime = formattedDate;
//
//        let content;
//
//        if(assignment.assignmentID == idOverdue){
//            content = modalOverdueShow;
//        }else{
//            content = `<div class="modal fade" id="assignmentModal-${assignment.assignmentID}" tabindex="-1" aria-labelledby="assignmentModalLabel-${assignment.assignmentID}" aria-hidden="true">
//                            <div class="modal-dialog">
//                                <div class="modal-content">
//                                    <div class="modal-header">
//                                        <h5 class="modal-title" id="assignmentModalLabel-${assignment.assignmentID}">Bài tập</h5>
//                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
//                                    </div>
//                                    <div class="modal-body">
//                                        <div class="form-group">
//                                            <label for="datetime">Thời gian hết hạn</label>
//                                            <input type="datetime-local" class="form-control" id="datetime" name="datetime" required value="${assignment.endTime}" readonly>
//                                        </div>
//                                        <div class="form-group">
//                                            <label for="title">Tiêu đề</label>
//                                            <input type="text" class="form-control" id="title" name="title" required placeholder="Nhập tiêu đề cho bài tập" value="${assignment.title}" readonly>
//                                        </div>
//                                        <div class="form-group">
//                                            <label for="description">Mô tả</label>
//                                            <input type="text" class="form-control" id="description" name="description" required placeholder="Nhập mô tả cho bài tập" value="${assignment.description}" readonly>
//                                        </div>
//                                        ${assignment.titleFile && assignment.filePath ? `
//                                            <div class="upload-section">
//                                               <label for="file">Tài liệu tham khảo</label>
//                                               <div class="mb-3">
//                                                    <a class="btn" href="/downloadFile?filePath=${assignment.filePath}" download><i class="fa fa-download"></i> ${assignment.titleFile}</a>
//                                               </div>
//                                            </div>
//                                        ` : ''}
//                                    </div>
//                                </div>
//                            </div>
//                        </div>`;
//        }
//
//         console.log(content)
//
//        let innerHTML = `<li class="list-group-item" data-bs-toggle="modal" data-bs-target="#assignmentModal-${assignment.assignmentID}">
//                             <strong>${assignment.title} </strong><br>
//                             <small>Hạn nộp: ${assignment.endTime}</small>
//                         </li>` + content;
//
//        listGroupOverdue.innerHTML += innerHTML;
//    });
//
//    console.log(data.notSubmitteds);
//    console.log(data.submittedes);
//    console.log(data.overdues);
//}