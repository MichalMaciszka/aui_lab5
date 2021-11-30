import {
    getApiUrl, getParameterByName 
} from "../utils.js";

window.addEventListener('load', () => {
    let f = document.getElementById('form');

    f.addEventListener('submit', event => updateCompanyAction(event));

    fetchCompany();
});

function fetchCompany(){
    let name = getParameterByName("name");

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let response = JSON.parse(this.responseText);
            for(const [key, val] of Object.entries(response)){
                let input = document.getElementById(key);
                if(input){
                    input.value = val;
                }
            }
        }
    }

    xhttp.open("GET", getApiUrl() + "/api/companies/" + name, true);
    xhttp.send();
}

function updateCompanyAction(event){
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            window.location.reload(true);
            //fetchCompany();
        }
    };
    xhttp.open("PUT", getApiUrl() + "/api/companies/" + getParameterByName("name"), true);
    const request = {
        'name': getParameterByName("name"),
        'budget': parseFloat(document.getElementById("budget").value)
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send(JSON.stringify(request));
}