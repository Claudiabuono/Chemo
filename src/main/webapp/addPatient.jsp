<%--
  Created by IntelliJ IDEA.
  User: anton
  Date: 09/01/2023
  Time: 20:32
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <title>Chemo | Aggiunta paziente</title>
</head>
<body>
<header>
  <jsp:include page="./static/templates/userHeaderLogged.html"/>
</header>
<div id="page-content">
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
        </div>
        <div class="field right">
          <label for="surname">Cognome</label>
          <input required id="surname" class="input-field" type="text" name="surname">
        </div>
      </div>
      <div class="input-fields-row">
        <div class="field left">
          <label for="birthdate">Data di nascita</label>
          <input required id="birthdate" class="input-field" type="date" name="birthdate">
        </div>
        <div class="field right">
          <label for="city">Città di nascita</label>
          <input required id="city" class="input-field" type="text" name="city">
        </div>
      </div>
      <div class="input-fields-row">
        <div class="field left">
          <label for="tax-code">Codice fiscale</label>
          <input required id="tax-code" class="input-field" type="text" name="taxCode">
        </div>
      </div>
      <div class="title-section">
        <h2 class="title">Contatti</h2>
      </div>
      <div class="input-fields-row">
        <div class="field left">
          <label for="phone-number">Numero di telefono</label>
          <input required id="phone-number" class="input-field" type="tel" name="phoneNumber">
        </div>
      </div>
      <div class="title-section">
        <h2 class="title">Note</h2>
      </div>
      <label for="notes">Allergie ed intolleranze</label>
      <input required id="notes" class="input-field" type="text" name="notes">
      <input type="button" class="button-primary-m submit-button" value="Aggiungi paziente">
    </div>
  </div>
</div>
</body>
</html>
