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