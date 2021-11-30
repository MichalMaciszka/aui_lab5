import {
    getApiUrl,
    getParameterByName,
    clearElementChildren,
    createButtonCell,
    createTextCell,
    createLinkCell,
    setTextNode
} from "../utils.js"

window.addEventListener('load', () => {
    fetchComapny();
    fetchEmployees();
    new_employee_link();
});

function fetchComapny(){
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function(){
        if (this.readyState === 4 && this.status === 200) {
            let json = JSON.parse(this.responseText);
            setTextNode("name", json["name"]);
            setTextNode("budget", json["budget"]);
        }
    };

    xhttp.open("GET", getApiUrl() + "/api/companies/" + getParameterByName("name"), true);
    xhttp.send();
}

function deleteEmployee(employee_id){
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function(){
        if (this.readyState === 4 && this.status === 202) {
            fetchEmployees();
        }
    }

    xhttp.open("DELETE", getApiUrl() + "/api/companies/" + getParameterByName("name") + "/employees/" + employee_id, true);
    xhttp.send();
}

function createTableRow(employee){
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(employee["firstname"] + " " + employee["lastname"]));
    tr.appendChild(createLinkCell("view", "../employee_view/employee.html?company=" + getParameterByName("name") + "&employee=" + employee["id"]));
    tr.appendChild(createLinkCell("edit", "edit_employee.html?company=" + getParameterByName("name") + "&employee=" + employee["id"]));
    tr.appendChild(createButtonCell("delete", () => deleteEmployee(employee["id"])));
    return tr;
}

function displayEmployees(employees){
    let tableBody = document.getElementById("tableBody");
    clearElementChildren(tableBody);
    employees.employees.forEach(element => {
        tableBody.appendChild(createTableRow(element))
    });
}

function fetchEmployees(){
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function(){
        if (this.readyState === 4 && this.status === 200) {
            displayEmployees(JSON.parse(this.responseText));
        }
    };
    xhttp.open("GET", getApiUrl() + "/api/companies/" + getParameterByName("name") + "/employees", true);
    xhttp.send();
}

function new_employee_link(){
    let d = document.getElementById("link_container");
    let link = document.createElement('a')
    link.href = "new_employee.html?company=" + getParameterByName("name");
    link.innerText = "Add new employee to this company"
    d.appendChild(link);
}