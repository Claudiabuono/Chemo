function editUserCredentials(id) {
    editToSaveButton("save-user-credentials-button", "user-credentials-button", "edit-user-credentials-button", "submitUpdatedCredentials('" + id + " ')");
    document.getElementById("username").className = "input-field";
    document.getElementById("password").className = "input-field";
}