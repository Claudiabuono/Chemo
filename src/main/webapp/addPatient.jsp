<%--
  Created by IntelliJ IDEA.
  User: anton
  Date: 09/01/2023
  Time: 20:32
--%>
<%@ page contentType="text/html;charset=UTF-8"
         import="userManagement.application.UserBean" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Chemo | Aggiunta paziente</title>
    <script src="./static/scripts/patient.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<body>
<%
    HttpSession sessione=request.getSession(false);
    if (sessione == null) {
        //redirect alla pagina di error 401 Unauthorized
        response.sendRedirect("./error401.jsp");
    } else {
        UserBean user = (UserBean) sessione.getAttribute("currentSessionUser");
        if (user == null || user.getType() != 1) {
            //è presente una sessione senza utente o con utente non autorizzato
            if (user == null) {
                response.sendRedirect("./error401.jsp");
            } else {
                response.sendRedirect("./error403.jsp");
            }
        } else {
%>
<header>
    <jsp:include page="./static/templates/userHeaderLogged.html"/>
</header>
<div id="page-content">
    <div id="alert-box"></div>
    <div id="add-patient-box" class="box">
        <div id="box-name-row" class="row">
            <h1 class="title">Aggiunta paziente</h1>
            <jsp:include page="./static/templates/loggedUserButtons.html"/>
        </div>
        <div id="add-patient-form" class="form">
            <div class="title-section">
                <h2 class="title">Anagrafica</h2>
            </div>
            <div class="input-fields-row">
                <div class="field left">
                    <label for="name">Nome</label>
                    <input required id="name" class="input-field" type="text" name="name">
                    <p id="name-validity" class="validity-paragraph status-unavailable"></p>
                </div>
                <div class="field right">
                    <label for="surname">Cognome</label>
                    <input required id="surname" class="input-field" type="text" name="surname">
                    <p id="surname-validity" class="validity-paragraph status-unavailable"></p>
                </div>
            </div>
            <div class="input-fields-row">
                <div class="field left">
                    <label for="birthdate">Data di nascita</label>
                    <input required id="birthdate" class="input-field" type="date" name="birthdate">
                    <p id="birthdate-validity" class="validity-paragraph status-unavailable"></p>
                </div>
                <div class="field right">
                    <label for="city">Città di nascita</label>
                    <input required id="city" class="input-field" type="text" name="city">
                    <p id="city-validity" class="validity-paragraph status-unavailable"></p>
                </div>
            </div>
            <div class="input-fields-row">
                <div class="field left">
                    <label for="tax-code">Codice fiscale</label>
                    <input required id="tax-code" class="input-field" type="text" name="taxCode">
                    <p id="tax-code-validity" class="validity-paragraph status-unavailable"></p>
                </div>
            </div>
            <div class="title-section">
                <h2 class="title">Contatti</h2>
            </div>
            <div class="input-fields-row">
                <div class="field left">
                    <label for="phone-number">Numero di telefono</label>
                    <input required id="phone-number" class="input-field" type="tel" name="phoneNumber">
                    <p id="phone-number-validity" class="validity-paragraph status-unavailable"></p>
                </div>
            </div>
            <div class="title-section">
                <h2 class="title">Note</h2>
            </div>
            <label for="notes">Allergie ed intolleranze</label>
            <input required id="notes" class="input-field" type="text" name="notes">
            <p id="notes-validity" class="validity-paragraph status-unavailable"></p>
            <input type="button" class="button-primary-m submit-button" value="Aggiungi paziente" onclick="addPatient()">
        </div>
    </div>
</div>
<%
        }
    }
%>
</body>
</html>
