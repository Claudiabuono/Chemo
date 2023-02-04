function addAppointments() {
    var checkedPatients = getCheckedBoxes("patient-checkbox");
    if (checkedPatients == null) {
        // errore: deve essere selezionato almeno un paziente
    } else {
        var request = new XMLHttpRequest();
        request.open('POST', "PlannerServlet", true);
        request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
        request.setRequestHeader('Authorization', 'Basic ');
        request.setRequestHeader('Accept', 'application/json');
        var body = "action=addAppointments&patientsNumber=" + checkedPatients.length;
        for (let i=0; i < checkedPatients.length; i++) {
            // vengono aggiunti i pazienti al body
            body += "&patient" + i + "=" + checkedPatients[i].value;
        }
        request.send(body);
        request.onreadystatechange = function () {
            if (request.readyState === 4 && request.status === 200) {
                if (request.getResponseHeader('OPERATION_RESULT')) {
                    // redirect al calendario
                    redirectToPlanner();
                } else {
                    // errore creazione agenda
                    window.location.replace("addAppointments.jsp");
                }
            }
        };
    }
}

function redirectToPlanner() {
    // genera un redirect alla servlet paziente creando una richiesta get
    window.location.replace("PlannerServlet");
}

function getCheckedBoxes(checkboxName) {
    const checkboxes = document.getElementsByName(checkboxName);
    var checkboxesChecked = [];
    // ciclo per scorrere tutti i checkbox
    for (let i=0; i < checkboxes.length; i++) {
        // vengono aggiunti al nuovo array quelli selezionati
        if (checkboxes[i].checked) {
            checkboxesChecked.push(checkboxes[i]);
        }
    }
    // restituisce array di valori selezionati o null se non ci sono valori
    return checkboxesChecked.length > 0 ? checkboxesChecked : null;
}
