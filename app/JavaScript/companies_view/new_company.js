import {
    getApiUrl
} from "../utils.js";


window.addEventListener('load', () => {
    const f = document.getElementById("form");

    f.addEventListener('submit', event => addComapnyAction(event));
});

function addComapnyAction(event){
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 201){
            window.location.reload(true);
        }
    };

    xhttp.open("POST", getApiUrl() + "/api/companies", true);
    const request = {
        'name': document.getElementById('name').value,
        'budget': parseFloat(document.getElementById('budget').value)
    };
    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send(JSON.stringify(request));
}