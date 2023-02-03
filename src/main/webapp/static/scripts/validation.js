/* FUNZIONI PER IL CONTROLLO DEGLI INPUT GENERICI */
function numberValidity(input) {
    var number = /^[0-9]+$/;
    return input.match(number);
}

function nameValidity(input) {
    var name = /^[A-Za-z][A-Za-z\'\-]+([\ A-Za-z][A-Za-z\'\-]+)*$/;
    return input.match(name);
}


/* FUNZIONI PER IL CONTROLLO DEGLI INPUT DEI PAZIENTI */

function surnameValidity(input) {
    return nameValidity(input);
}

function cityValidity(input) {
    return nameValidity(input);
}

function dateValidity(input) {
    var date = /^(19|20)[0-9]{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$/;
    return input.match(date);
}

function taxCodeValidity(input) {
    var code = /^[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]$/i;
    return input.match(code);
}

function phoneNumberValidity(input) {
    var phoneNumber = /^[\+]?[(]?[0-9]{2,3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,7}$/;
    return input.match(phoneNumber);
}

function notesValidity(input) {
    var notes = /^[A-Za-z0-9][A-Za-z0-9\'\-\.\,\n]+([\ A-Za-z0-9][A-Za-z0-9\'\-\.\,\n]+)*$/;
    return input.match(notes);
}


/* FUNZIONI PER IL CONTROLLO DEGLI INPUT DELLE TERAPIE */

function conditionValidity(input) {
    var condition = /^[A-Za-z0-9][A-Za-z0-9\'\-]+([\ A-Za-z0-9][A-Za-z0-9\'\-]+)*$/;
    return input.match(condition);
}

function idValidity(input) {
    var id = /^$/;          //Aggiungere regex id medicinale
    return input.match(id);
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
    var medicineName = /^[A-Za-z0-9][A-Za-z0-9\'\-]+([\ A-Za-z0-9][A-Za-z0-9\'\-]+)*$/;
    return input.match(medicineName);
}

function ingredientsValidity(input) {
    var ingredients = /^[A-Za-z0-9][A-Za-z0-9\'\-\.\,\n]+([\ A-Za-z0-9][A-Za-z0-9\'\-\.\,\n]+)*$/;
    return input.match(ingredients);
}

function quantityValidity(input) {
    return numberValidity(input);
}
