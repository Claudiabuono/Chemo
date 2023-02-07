/* FUNZIONI PER IL CONTROLLO DEGLI INPUT GENERICI */
function numberValidity(input) {
    var number = /^[0-9]+$/;
    return input.match(number);
}

function nameValidity(input) {
    var name = /^[A-Za-z][A-Za-z'-]+([ A-Za-z][A-Za-z'-]+)*$/;
    return input.match(name);
}

function idValidity(input) {
    var id = /^[a-f\d]{24}$/i;
    return input.match(id);
}



/* FUNZIONI PER IL CONTROLLO DEGLI INPUT DEI PAZIENTI */

function namesValidity(input) {
    if (input.length > 32)
        return false;
    return nameValidity(input);
}

function cityValidity(input) {
    if (input.length > 32)
        return false;
    return nameValidity(input);
}

function dateValidity(input) {
    const now = new Date();
    if (new Date(input).getTime() > now.getTime())
    var date = /^(19|20)[0-9]{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$/;
    return input.match(date);
}

function taxCodeValidity(input) {
    var code = /^[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]$/i;
    return input.match(code);
}

function phoneNumberValidity(input) {
    var phoneNumber = /^[+]?[(]?[0-9]{2,3}[)]?[-\s.]?[0-9]{3}[-\s.]?[0-9]{4,7}$/;
    return input.match(phoneNumber);
}

function notesValidity(input) {
    if (input.length > 255)
        return false;
    var notes = /^[A-Za-z0-9][A-Za-z0-9'.,\n-]+([ A-Za-z0-9][A-Za-z0-9'.,\n-]+)*$/;
    return input.match(notes);
}


/* FUNZIONI PER IL CONTROLLO DEGLI INPUT DELLE TERAPIE */

function conditionValidity(input) {
    if (input.length > 255)
        return false;
    var condition = /^[A-Za-z0-9][A-Za-z0-9'-]+([ A-Za-z0-9][A-Za-z0-9'-]+)*$/;
    return input.match(condition);
}

function doseValidity(input) {
    return numberValidity(input);
}

function sessionsValidity(input) {
    return numberValidity(input);
}

function durationValidity(input) {
    return numberValidity(input);
}

function frequencyValidity(input) {
    return numberValidity(input);
}


/* FUNZIONI PER IL CONTROLLO DEGLI INPUT DEI MEDICINALI */

function medicineNameValidity(input) {
    if (input.length > 32)
        return false;
    var medicineName = /^[A-Za-z0-9][A-Za-z0-9'\-]+([ A-Za-z0-9][A-Za-z0-9'-]+)*$/;
    return input.match(medicineName);
}

function ingredientsValidity(input) {
    if (input.length > 100)
        return false;
    var ingredients = /^[A-Za-z0-9][A-Za-z0-9'.,\n-]+([ A-Za-z0-9][A-Za-z0-9'.,\n-Z]+)*$/;
    return input.match(ingredients);
}

function quantityValidity(input) {
    return numberValidity(input);
}

function capacityValidity(input) {
    return numberValidity(input);
}