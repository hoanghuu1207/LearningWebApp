const inputSearch = document.querySelector('.input-search');
const tBody = document.querySelector('tbody');
inputSearch.addEventListener('change', () => {
    const value = inputSearch.value;
    const link = `/admin/search/` + value;
        const option = {
          method: "GET"
        }

        fetch(link, option)
          .then(res => res.json())
          .then(data => {
            if(data.code == 200){
                tBody.innerHTML = '';
                JSON.parse(data.users).forEach(user => {
                    const tr = `<tr class="alert" role="alert">
                                  <td class="d-flex align-items-center">
                                    <div class="img" style="background-image: url('${user.avatar}');"></div>
                                    <div class="pl-3 email">
                                        <span>${user.email}</span>
                                    </div>
                                  </td>
                                  <td>${user.firstName} ${user.lastName}</td>
                                  <td class="status"><span class=${user.roleID == 2 ? "active" : "waiting"} role_id='${user.roleID}' user_id='${user.userID}'>${user.roleID == 2 ? "Teacher" : "Student"}</span></td>
                                  <!-- <td>
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                    <span aria-hidden="true"><i class="fa fa-close"></i></span>
                                </button>
                                </td> -->
                                </tr>`;

                    tBody.innerHTML += tr;
                });
                attachRoleEvents();
            }
          })
});