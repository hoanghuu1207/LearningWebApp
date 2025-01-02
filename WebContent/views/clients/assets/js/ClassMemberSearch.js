const listDropdownItem = document.querySelectorAll("#search-box .dropdown-item");
listDropdownItem.forEach(dropdownItem => {
    dropdownItem.addEventListener("click", (e) => {
        if(event.target.matches('.checkbox')){
            return;
        }
        const checkboxItem = dropdownItem.querySelector(".checkbox");
        checkboxItem.checked = !checkboxItem.checked;
        e.stopPropagation();
    });
});

function dropdownSearch() {
    const searchValue = document.getElementById('dropdown-search').value.toLowerCase().trim();
    const rows = document.querySelectorAll('#search-box .dropdown-item');

    rows.forEach(row => {
        const name = row.querySelector('#name-search').textContent.toLowerCase();
        const email = row.querySelector('#email-search').textContent.toLowerCase();
        row.style.display = name.includes(searchValue) || email.includes(searchValue) ? '' : 'none';
    });
}

function uncheckCheckox(){
    const listCheckboxItem = document.querySelectorAll("#search-box .dropdown-item .checkbox");
    listCheckboxItem.forEach(checkboxItem => {
        checkboxItem.checked = false;
    });
}

function submitForm(){
    const form = document.getElementById("form-search");
    form.submit();
}