import {
     getApiUrl,
     getParameterByName
} from "../utils.js"

window.addEventListener('load', () => {
    const f = document.getElementById("form");
    f.addEventListener('submit', event => addEmployeeAction(event));
});

function addEmployeeAction(event){
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if(this.readyState === 4 && this.status === 201){
            window.location.reload(true);
        }
    };

    xhttp.open("POST", getApiUrl() + "/api/companies/" + getParameterByName("company") + "/employees", true);
    const request = {
        'firstname': document.getElementById('firstname').value,
        'lastname': document.getElementById('lastname').value,
        'salary': parseFloat(document.getElementById('salary').value)
    };
    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send(JSON.stringify(request));
}