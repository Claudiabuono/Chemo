function addTherapyForm() {
    document.getElementById("new-therapy-button").className = "hidden";
    document.getElementById("new-therapy-form").className = "box";
}

function addMedicineField(id, number) {
    const newmedicine = document.createElement("div");
    newmedicine.setAttribute("id", id + "-medicine-item-" + number);
    newmedicine.setAttribute("class", "input-fields-row");

    const firstfield = document.createElement("div");
    firstfield.setAttribute("class", "field left");
    const label1 = document.createElement("label");
    label1.setAttribute("for", id + "-medicine-name-item-" + number);
    label1.innerHTML = number + "° Medicinale";
    const select1 = document.createElement("select");
    select1.setAttribute("id", id + "-medicine-name-item-" + number);
    select1.setAttribute("class", "input-field");
    select1.setAttribute("name", "medicineName" + number);
    firstfield.appendChild(label1);
    firstfield.appendChild(select1);

    const secondfield = document.createElement("div");
    secondfield.setAttribute("class", "field right");
    const label2 = document.createElement("label");
    label2.setAttribute("for", id + "-medicine-dose-item-" + number);
    label2.innerHTML = "Dose (in ml)";
    const input2 = document.createElement("input");
    input2.setAttribute("id", id + "-medicine-dose-item-" + number);
    input2.setAttribute("class", "input-field");
    input2.setAttribute("type", "text");
    input2.setAttribute("name", "medicineDose" + number);
    secondfield.appendChild(label2);
    secondfield.appendChild(input2);

    newmedicine.appendChild(firstfield);
    newmedicine.appendChild(secondfield);
    document.getElementById(id + "-medicines").appendChild(newmedicine);
    number += 1;
    document.getElementById("add-medicine-" + id).setAttribute("onclick", "addMedicineField('" + id + "'," + number + ")");
}

function editToSaveButton(newid, parentid, oldbuttonid, funcname) {
    const newbutton = document.createElement("input");
    newbutton.setAttribute("type", "button");
    newbutton.setAttribute("id", newid);
    newbutton.setAttribute("class", "button-primary-m edit-button");
    newbutton.setAttribute("value", "Salva");
    newbutton.setAttribute("onclick", funcname);
    const parent = document.getElementById(parentid);
    const oldbutton = document.getElementById(oldbuttonid);
    parent.replaceChild(newbutton, oldbutton);
}

function addDeleteButton(text, newid,parentid, nextbuttonid, funcname) {
    const newbutton = document.createElement("input");
    newbutton.setAttribute("type", "button");
    newbutton.setAttribute("id", newid);
    newbutton.setAttribute("class", "button-tertiary-m edit-button");
    newbutton.setAttribute("value", text);
    newbutton.setAttribute("onclick", funcname);
    const parent = document.getElementById(parentid);
    const nextbutton = document.getElementById(nextbuttonid);
    parent.insertBefore(newbutton, nextbutton);
}

function editStatusButton(id) {
    console.log("Cambio stato 1");
    editToSaveButton("save-patient-status-button", "patient-status-button", "edit-patient-status-button", "submitUpdatedStatus('" + id + " ')");
    document.getElementById("status").className = "input-field";
}

function editTherapyButtons(id, medicines) {
    editToSaveButton("save-therapy-button", "therapy-buttons", "edit-therapy-button", "submitUpdatedTherapy('" + id + " ')");
    addDeleteButton("Elimina","delete-therapy-button","therapy-buttons", "save-therapy-button", "deleteTherapy('" + id + "')");
    document.getElementById("condition").className = "input-field";
    document.getElementById("sessions-number").className = "input-field";
    var i = 0;
    console.log(medicines);
    for (let i = 1; i <= medicines; i++) {
        console.log(i);
        document.getElementById("medicine-name-item-" + i ).className = "input-field";
        document.getElementById("medicine-dose-item-" + i ).className = "input-field";
    }

    const nextmedicine = medicines + 1;
    const addmedicine = document.createElement("input");
    addmedicine.setAttribute("type", "button");
    addmedicine.setAttribute("id", "add-medicine-saved");
    addmedicine.setAttribute("class", "button-secondary-s edit-button");
    addmedicine.setAttribute("value", "Aggiungi medicinale");
    addmedicine.setAttribute("onclick", "addMedicineField('saved'," + nextmedicine + ")");
    document.getElementById("therapy-section").appendChild(addmedicine);
}

