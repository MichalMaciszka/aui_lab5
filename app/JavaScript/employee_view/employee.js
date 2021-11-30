import {
    getApiUrl,
    getParameterByName,
    setTextNode
} from "../utils.js"

window.addEventListener("load", () =>{
    fetchEmployee();
});

function fetchEmployee() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if(this.readyState === 4 && this.status === 200){
            let json = JSON.parse(this.responseText);
            setTextNode("firstname", json["firstname"]);
            setTextNode("lastname", json["lastname"]);
            setTextNode("salary", json["salary"]);
        }
    };

    xhttp.open("GET", getApiUrl() + "/api/companies/" + getParameterByName("company") + "/employees/" + getParameterByName("employee"));
    xhttp.send();
}