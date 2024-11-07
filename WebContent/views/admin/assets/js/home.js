function attachRoleEvents() {
    const roles = document.querySelectorAll('.status [role_id]');
    roles.forEach(role => {
        role.addEventListener('click', () => {
            const role_id = role.getAttribute("role_id");
            const user_id = role.getAttribute("user_id");

            const link = `/admin/home/` + user_id + `/` + role_id;

            const option = {
              method: "POST"
            }

            fetch(link, option)
              .then(res => res.json())
              .then(data => {
                if(data.code == 200){
                    role.classList.remove(data.remove_class);
                    role.classList.add(data.add_class);
                    role.setAttribute("role_id", data.role_id);
                    role.textContent = data.role;
                }
              })
        });
    });
}

attachRoleEvents();