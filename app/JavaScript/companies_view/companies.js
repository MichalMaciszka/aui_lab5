import {
    getApiUrl, clearElementChildren, createButtonCell, createLinkCell, createTextCell
} from "../utils.js";

window.addEventListener('load', () => {
    fetchAndDisplayCompanies();
})



function fetchAndDisplayCompanies(){
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if(this.readyState === 4 && this.status === 200){
            displayCompanies(JSON.parse(this.responseText));
        }
    }
    xhttp.open("GET", getApiUrl() + "/api/companies/", true);
    xhttp.send();
}

function displayCompanies(companies){
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    companies.companies.forEach(company => {
        tableBody.appendChild(createTableRow(company));
    })
}

function createTableRow(company){
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(company["name"]));
    tr.appendChild(createLinkCell('view', '../company_view/company.html?name='+company["name"]));
    tr.appendChild(createLinkCell('edit', "edit_company.html?name="+company["name"]));
    tr.appendChild(createButtonCell('delete', () => deleteCompany(company)));
    return tr;
}

function deleteCompany(company){
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202){
            fetchAndDisplayCompanies();
        }
    };
    xhttp.open("DELETE", getApiUrl() + '/api/companies/' + company["name"], true);
    xhttp.send();
}

function editCompany(company){
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 202){
            fetchAndDisplayCompanies();
        }
    };


}