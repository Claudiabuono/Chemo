function validateMedicineData(medicine){
    let validity = true;
    //validazione del formato
    if (!medicineNameValidity(medicine.name)) {
        document.getElementById("name-validity").innerHTML = "Formato errato";
        validity = false;
    } else {
        document.getElementById("name-validity").innerHTML = "";
    }
    if (!ingredientsValidity(medicine.ingredients)) {
        document.getElementById("ingredients-validity").innerHTML = "Formato errato";
        validity = false;
    } else {
        document.getElementById("ingredients-validity").innerHTML = "";
    }
    return validity;
}

function validatePackageData(medicinePackage, id){
    let validity = true;
    //validazione del formato
    if (!capacityValidity(medicinePackage.capacity)) {
        document.getElementById("package-" + id + "-capacity-validity").innerHTML = "Formato errato";
        validity = false;
    } else {
        document.getElementById("package-" + id + "-capacity-validity").innerHTML = "";
    }
    if (!dateValidity(medicinePackage.expiryDate)) {
        document.getElementById("package-" + id + "-expiry-date-validity").innerHTML = "Formato errato";
        validity = false;
    } else {
        document.getElementById("package-" + id + "-expiry-date-validity").innerHTML = "";
    }
    return validity;
}


function addPackageForm() {
    document.getElementById("new-package-button").className = "hidden";
    document.getElementById("new-package-form").className = "box";
}

function editMedicineButton(id) {
    editToSaveButton("save-medicine-button", "medicine-data-buttons", "edit-medicine-data-button", "submitUpdatedMedicine('" + id + " ')");
    document.getElementById("name").className = "input-field";
    document.getElementById("ingredients").className = "input-field";
}

function editPackageButton(id) {
    editToSaveButton("save-package-button", "package-data-buttons", "edit-package-data-button", "submitUpdatedPackage('" + id + " ')");
    document.getElementById(id + "-package-capacity").className = "input-field";
    document.getElementById(id + "-package-expiry-date").className = "input-field";
    document.getElementById(id + "-package-status").className = "input-field";
}