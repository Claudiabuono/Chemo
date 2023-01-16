<%--
  Created by IntelliJ IDEA.
  User: anton
  Date: 16/01/2023
  Time: 13:23
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chemo | Storico pazienti</title>
    <script src="./static/scripts/search.js"></script>
</head>
<body>
<header>
    <jsp:include page="./static/templates/userHeaderLogged.html"/>
</header>
<div id="page-content">
    <div id="patient-list-box" class="box">
        <div id="box-name-row" class="row">
            <h1 class="title">Storico pazienti</h1>
            <jsp:include page="./static/templates/loggedUserButtons.html"/>
        </div>
        <div id="patient-search-form" class="form box">
            <div class="title-section">
                <h2 class="title">Ricerca</h2>
            </div>
            <input type="text" id="search-patient-name" class="search-field input-field" placeholder="Nome paziente">
            <div id="search-buttons">
                <input type="button" id="search-filters-button" class="button-tertiary-s edit-button" value="Espandi filtri" onclick="expandSearchFilters()">
                <input type="button" id="search-request-button" class="button-primary-m edit-button" value="Cerca">
            </div>
            <div id="search-filters" class="hidden">
                <div class="input-fields-row">
                    <div class="field left">
                        <label for="search-patient-medicine">Medicinale</label>
                        <select id="search-patient-medicine" class="input-field" name="patientMedicine">
                            <option value="na" selected>Seleziona medicinale</option>
                            <option value="id-medicinale-1">Nome medicinale 1</option>
                            <option value="id-medicinale-2">Nome medicinale 2</option>
                        </select>
                    </div>
                    <div class="field right">
                        <label for="search-patient-status">Stato</label>
                        <select id="search-patient-status" class="input-field" name="patientStatus">
                            <option value="na" selected>Seleziona stato</option>
                            <option value="true">Disponibile</option>
                            <option value="false">Non disponibile</option>
                        </select>
                    </div>
                </div>
            </div>
        </div>
        <div id="patient-list">
            <form class="result-box-container">
                <button id="patient-box-id" class="box">
                    <div class="first-row">
                        <div class="column left">
                            <h2 class="result-name">Mario Rossi</h2>
                            <p>RSSMRA68E01H703R</p>
                        </div>
                        <div class="column status-avaliable right">
                            <i class="fa-solid fa-circle-user fa-3x"></i>
                        </div>
                    </div>
                    <div class="row">
                        <h3 class="left">Tumore al pancreas</h3>
                        <p class="right">Numero sedute: 6</p>
                    </div>
                </button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
