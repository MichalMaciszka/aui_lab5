import {
    getApiUrl, getParameterByName
} from "../utils.js"

window.addEventListener('load', () => {
    let f = document.getElementById("form");
    f.addEventListener('submit', event => updateEmployeeAction(event));
    fetchEmployee();
});

function fetchEmployee() {
    let id = getParameterByName("employee");

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function(){
        if(this.readyState === 4 && this.status === 200){
            let response = JSON.parse(this.responseText);
            for(const [key, val] of Object.entries(response)){
                let input = document.getElementById(key);
                if(input){
                    input.value = val;
                }
            }
        }
    }

    xhttp.open("GET", getApiUrl() + "/api/companies/" + getParameterByName("company") + "/employees/" + id);
    xhttp.send();
}

function updateEmployeeAction(event){
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if(this.readyState === 4 && this.status === 202){
            window.location.reload(true);
            // fetchEmployee();
        }
    };

    xhttp.open("PUT", getApiUrl() + "/api/companies/" + getParameterByName("company") + "/employees/" + getParameterByName("employee"));
    const request = {
        'firstname': document.getElementById("firstname").value,
        'lastname': document.getElementById('lastname').value,
        'salary': parseFloat(document.getElementById("salary").value)
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send(JSON.stringify(request));
}