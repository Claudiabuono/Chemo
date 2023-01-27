function addPatient() {
    //recupero dei parametri dalla pagina
    var name = document.getElementById("name").value;
    var surname = document.getElementById("surname").value;
    var birthDate = document.getElementById("birthdate").value;
    var city = document.getElementById("city").value;
    var taxCode = document.getElementById("tax-code").value;
    var phoneNumber = document.getElementById("phone-number").value;
    var notes = document.getElementById("notes").value;

    var validity = true;
    //validazione del formato

    if (validity) {
        //i campi hanno tutti il formato corretto
        var request = new XMLHttpRequest();
        request.open('POST', "PatientServlet", true);
        request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
        request.setRequestHeader('Authorization', 'Basic ');
        request.setRequestHeader('Accept', 'application/json');
        var body = "action=createPatientProfile&name=" + name +"&surname=" + surname + "&birthDate=" +
            birthDate + "&city=" + city + "&taxCode=" + taxCode +
            "&phoneNumber=" + phoneNumber + "&notes=" + notes;
        request.send(body);

        request.onreadystatechange = function () {
            if (request.readyState === 4 && request.status == 200) {
                //redirectToPage("patientDetails.jsp");
                //alert(request.responseText);
                if (request.getResponseHeader('OPERATION_RESULT')){
                    //window.location = "patientDetails.jsp";
                    const patientID = request.getResponseHeader('PATIENT_ID');
                    //recupero id dalla risposta
                    redirectToPatientDetails(patientID);
                } else {
                    //errore creazione paziente
                }
            }
        };
    }
}

function redirectToPatientDetails(id) {
    //crea una richiesta alla servlet paziente per reindirizzare
    window.location.replace("PatientServlet?action=testRedirect&id=" + id);
}
