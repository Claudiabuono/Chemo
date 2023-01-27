<%--
  Created by IntelliJ IDEA.
  User: anton
  Date: 24/01/2023
  Time: 17:38
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Chemo | Dati personali</title>
    <script src="./static/scripts/user.js"></script>
</head>
<body>
<header>
    <jsp:include page="./static/templates/userHeaderLogged.html"/>
</header>
<div id="page-content">
    <div id="user-data-box" class="box">
        <div id="box-name-row" class="row">
            <h1 class="title">Dati personali</h1>
            <jsp:include page="./static/templates/loggedUserButtons.html"/>
        </div>
        <div id="user-data-form" class="form">
            <div class="title-section">
                <h2 class="title">Anagrafica</h2>
            </div>
            <div class="input-fields-row">
                <div class="field left">
                    <label for="name">Nome</label>
                    <input required id="name" class="input-field inactive" type="text" name="name" value="Lucia">
                </div>
                <div class="field right">
                    <label for="surname">Cognome</label>
                    <input required id="surname" class="input-field inactive" type="text" name="surname" value="Esposito">
                </div>
            </div>
            <div class="input-fields-row">
                <div class="field left">
                    <label for="birthdate">Data di nascita</label>
                    <input required id="birthdate" class="input-field inactive" type="date" name="birthdate" value="1987-03-29">
                </div>
                <div class="field right">
                    <label for="city">Città di nascita</label>
                    <input required id="city" class="input-field inactive" type="text" name="city" value="Napoli">
                </div>
            </div>
            <div class="input-fields-row">
                <div class="field left">
                    <label for="tax-code">Codice fiscale</label>
                    <input required id="tax-code" class="input-field inactive" type="text" name="taxCode" value="SPSLCU87C69F839Z">
                </div>
            </div>
        </div>
        <div id="user-credentials-form" class="form">
            <div class="title-section">
                <h2 class="title">Credenziali</h2>
                <div id="user-credentials-button" class="row">
                    <%--
                    <input type="button" id="save-user-credentials-button" class="button-primary-m rounded edit-button" value="Salva">
                    --%>
                    <input type="button" id="edit-user-credentials-button" class="button-secondary-s rounded edit-button" value="Modifica" onclick="editUserCredentials('userid')">
                </div>
            </div>
            <div class="input-fields-row">
                <div class="field left">
                    <label for="name">Username</label>
                    <input required id="username" class="input-field inactive" type="text" name="username" value="LuciaE">
                </div>
                <div class="field left">
                    <label for="name">Password</label>
                    <input required id="password" class="input-field inactive" type="password" name="password" value="PasswordLucia">
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>