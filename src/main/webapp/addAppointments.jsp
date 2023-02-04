<%--
  Created by IntelliJ IDEA.
  User: anton
  Date: 21/01/2023
  Time: 20:07
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Chemo | Nuove sedute</title>
    <link rel="stylesheet" href="./static/styles/checkbox.css">
</head>
<body>
<header>
    <jsp:include page="static/templates/userHeaderLogged.html"/>
</header>
<div id="page-content">
    <div id="add-appointments-box" class="box">
        <div id="box-name-row" class="row">
            <h1 class="title">Chemo Nuove sedute</h1>
            <jsp:include page="./static/templates/loggedUserButtons.html"/>
        </div>
        <form id="add-appointments-content">
            <div class="title-section">
                <h2 class="title">Selezione pazienti</h2>
            </div>
            <div id="patient-list" class="result-box-container">
                <div id="patient-box-id" class="box form">
                    <div class="first-row">
                        <div class="column left">
                            <h2 class="result-name">Mario Rossi</h2>
                            <p>RSSMRA68E01H703R</p>
                        </div>
                        <div id="patient-check-id" class="column right checkbox-container">
                            <label class="checkbox-label">
                                <input type="checkbox" name="patient-checkbox" value="idpaziente">
                                <span class="checkbox-custom rectangular"></span>
                            </label>
                        </div >
                    </div>
                    <div class="row">
                        <h3 class="left">Tumore al pancreas</h3>
                        <p class="right">Numero sedute: 6</p>
                    </div>
                </div>
            </div>
            <input type="button" class="button-primary-m submit-button" value="Crea calendario" onclick="addAppointments()">
        </form>
    </div>
</div>
</body>
</html>
