<%--
  Created by IntelliJ IDEA.
  User: anton
  Date: 17/01/2023
  Time: 17:50
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chemo | Medicinali</title>
    <script src="./static/scripts/search.js"></script>
</head>
<body>
<header>
    <jsp:include page="./static/templates/userHeaderLogged.html"/>
</header>
<div id="page-content">
    <div id="medicines-list-box" class="box">
        <div id="box-name-row" class="row">
            <h1 class="title">Medicinali</h1>
            <jsp:include page="./static/templates/loggedUserButtons.html"/>
        </div>
        <div id="search-form" class="form box">
            <div class="title-section">
                <h2 class="title">Ricerca</h2>
            </div>
            <select id="search-medicine-name" class="search-field input-field" name="medicineName">
                <option value="na" selected>Nome medicinale</option>
                <option value="id-medicinale-1">Nome medicinale 1</option>
                <option value="id-medicinale-2">Nome medicinale 2</option>
            </select>
            <div id="search-buttons">
                <input type="button" id="search-filters-button" class="button-tertiary-s rounded edit-button" value="Espandi filtri" onclick="expandSearchFilters()">
                <input type="button" id="search-request-button" class="button-primary-m rounded edit-button" value="Cerca">
            </div>
            <div id="search-filters" class="hidden">
                <div class="input-fields-row">
                    <div class="field left">
                        <label for="search-medicine-expiry-date">Scadenza</label>
                        <input type="date" id="search-medicine-expiry-date" class="input-field">
                    </div>
                    <div class="field right">
                        <label for="search-medicine-status">Stato</label>
                        <select id="search-medicine-status" class="input-field" name="medicineStatus">
                            <option value="na" selected>Seleziona stato</option>
                            <option value="true">Disponibile</option>
                            <option value="false">Esaurito</option>
                        </select>
                    </div>
                </div>
            </div>
        </div>
        <div id="medicines-list">
            <form class="result-box-container">
                <button id="medicine-box-id" class="box">
                    <div class="first-row">
                        <div class="column left">
                            <h2 class="result-name">Ciclofosfamide</h2>
                        </div>
                        <div class="column icon status-avaliable right">
                            <svg xmlns="http://www.w3.org/2000/svg" fill="currentColor" class="bi bi-capsule-pill" viewBox="0 0 16 16">
                                <path d="M11.02 5.364a3 3 0 0 0-4.242-4.243L1.121 6.778a3 3 0 1 0 4.243 4.243l5.657-5.657Zm-6.413-.657 2.878-2.879a2 2 0 1 1 2.829 2.829L7.435 7.536 4.607 4.707ZM12 8a4 4 0 1 1 0 8 4 4 0 0 1 0-8Zm-.5 1.042a3 3 0 0 0 0 5.917V9.042Zm1 5.917a3 3 0 0 0 0-5.917v5.917Z"/>
                            </svg>
                        </div>
                    </div>
                    <div class="column">
                        <h3 class="result-title left">Ingredienti</h3>
                        <p class="left">ciclososfamide, monoidrata</p>
                    </div>
                    <div class="row">
                        <p class="left">Quantità: 1</p>
                    </div>
                </button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
