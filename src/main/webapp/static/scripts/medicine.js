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