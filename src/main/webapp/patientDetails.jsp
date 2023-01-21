<%--
  Created by IntelliJ IDEA.
  User: anton
  Date: 12/01/2023
  Time: 16:43
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Chemo | Pagina paziente</title>
    <script src="./static/scripts/patientDetails.js"></script>
</head>
<body>
<header>
    <jsp:include page="./static/templates/userHeaderLogged.html"/>
</header>
<div id="page-content">
    <div id="patient-data-box" class="box">
        <div id="box-name-row" class="row">
            <h1 class="title">Scheda paziente</h1>
            <jsp:include page="./static/templates/loggedUserButtons.html"/>
        </div>
        <div id="patient-data-form" class="form">
            <div class="title-section">
                <h2 class="title">Anagrafica</h2>
                <div id="patient-data-buttons">
                    <input type="button" id="delete-patient-button" class="button-tertiary-m rounded edit-button" value="Elimina paziente">
                    <%--
                    <input type="button" id="save-patient-button" class="button-primary-m rounded edit-button" value="Salva">
                    <input type="button" id="edit-patient-data-button" class="button-secondary-s rounded edit-button" value="Modifica">
                    --%>
                </div>
            </div>
            <div class="input-fields-row">
                <div class="field left">
                    <label for="name">Nome</label>
                    <input required id="name" class="input-field inactive" type="text" name="name" value="Mario">
                </div>
                <div class="field right">
                    <label for="surname">Cognome</label>
                    <input required id="surname" class="input-field inactive" type="text" name="surname" value="Rossi">
                </div>
            </div>
            <div class="input-fields-row">
                <div class="field left">
                    <label for="birthdate">Data di nascita</label>
                    <input required id="birthdate" class="input-field inactive" type="date" name="birthdate" value="1968-05-01">
                </div>
                <div class="field right">
                    <label for="city">Città di nascita</label>
                    <input required id="city" class="input-field inactive" type="text" name="city" value="Salerno">
                </div>
            </div>
            <div class="input-fields-row">
                <div class="field left">
                    <label for="tax-code">Codice fiscale</label>
                    <input required id="tax-code" class="input-field inactive" type="text" name="taxCode" value="RSSMRA68E01H703R">
                </div>
            </div>
            <div class="title-section">
                <h2 class="title">Contatti</h2>
            </div>
            <div class="input-fields-row">
                <div class="field left">
                    <label for="phone-number">Numero di telefono</label>
                    <input required id="phone-number" class="input-field inactive" type="tel" name="phoneNumber" value="0818412110">
                </div>
            </div>
            <div class="title-section">
                <h2 class="title">Note</h2>
            </div>
            <label for="notes">Allergie ed intolleranze</label>
            <input required id="notes" class="input-field inactive" type="text" name="notes" value="Intolleranza al lattosio">
            <div class="title-section">
                <h2 class="title">Stato</h2>
                <div id="patient-status-button">
                    <input type="button" id="edit-patient-status-button" class="button-secondary-s rounded edit-button" value="Modifica" onclick="editStatusButton('id')">
                </div>
            </div>
            <div class="input-fields-row">
                <div id="status-icon" class="icon status-avaliable">
                    <svg xmlns="http://www.w3.org/2000/svg" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
                        <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
                        <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
                    </svg>
                </div>
                <select id="status" class="input-field inactive" name="status">
                    <option value="disponibile" selected>Disponibile</option>
                    <option value="nonDisponibile">Non disponibile</option>
                </select>
            </div>
            <%-- Aggiungere controllo in java per nascondere questa sezione --%>
            <%-- Se il paziente non ha una terapia far vedere questa parte --%>
            <input type="button" id="new-therapy-button" class="button-primary-m submit-button" value="Aggiungi terapia" onclick="addTherapyForm()">
            <div id="new-therapy-form" class="box hidden">
                <div class="title-section">
                    <h2 class="title">Aggiunta terapia</h2>
                </div>
                <label for="new-condition">Patologia</label>
                <input required id="new-condition" class="input-field" type="text" name="condition">
                <label for="new-sessions-number">Numero di sedute</label>
                <input required id="new-sessions-number" class="input-field" type="text" name="sessionNumber">
                <div id="new-medicines">
                    <div id="new-medicine-item-1" class="input-fields-row">
                        <div class="field left">
                            <label for="new-medicine-name-item-1">1° Medicinale</label>
                            <select id="new-medicine-name-item-1" class="input-field" name="medicineName1">
                                <option value="id-medicinale-1" selected>Nome medicinale 1</option>
                                <option value="id-medicinale-1">Nome medicinale 2</option>
                            </select>
                        </div>
                        <div class="field right">
                            <label for="new-medicine-dose-item-1">Dose (in ml)</label>
                            <input required id="new-medicine-dose-item-1" class="input-field" type="text" name="medicineDose1">
                        </div>
                    </div>
                </div>
                <input type="button" id="add-medicine-new" class="button-secondary-s rounded edit-button" value="Aggiungi medicinale" onclick="addMedicineField('new', 2)">
                <input type="button" class="button-primary-m submit-button" value="Salva terapia">
            </div>
            <%-- Aggiungere controllo in java per nascondere questa sezione --%>
            <%-- Se il paziente ha una terapia far vedere questa parte --%>
            <div id="therapy-section" class="hidden">
                <div class="title-section">
                    <h2 class="title">Terapia</h2>
                    <div id="therapy-buttons">
                        <input type="button" id="edit-therapy-button" class="button-secondary-s rounded edit-button" value="Modifica" onclick="editTherapyButtons('id', 1)">
                    </div>
                </div>
                <label for="condition">Patologia</label>
                <input required id="condition" class="input-field inactive" type="text" name="condition" value="Tumore al pancreas">
                <label for="sessions-number">Numero di sedute</label>
                <input required id="sessions-number" class="input-field inactive" type="text" name="sessionNumber" value="6">
                <div id="saved-medicines">
                    <div id="medicine-item-1" class="input-fields-row">
                        <div class="field left">
                            <label for="medicine-name-item-1">1° Medicinale</label>
                            <input required id="medicine-name-item-1" class="input-field inactive" type="text" name="medicineName1" value="5-fluorouracile">
                        </div>
                        <div class="field right">
                            <label for="medicine-dose-item-1">Dose</label>
                            <input required id="medicine-dose-item-1" class="input-field inactive" type="text" name="medicineDose1" value="100 ml">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
