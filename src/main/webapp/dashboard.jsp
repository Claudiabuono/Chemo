<%--
  Created by IntelliJ IDEA.
  User: anton
  Date: 08/01/2023
  Time: 23:04
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Chemo Area Personale</title>
</head>
<body>
<header>
    <jsp:include page="static/templates/userHeaderLogged.html"/>
</header>
<div id="page-content">
    <div id="user-box" class="box">
        <div id="box-name-row" class="row">
            <h1 class="title">Benvenuta, Lucia</h1>
            <a id="user-details-link" class="icon" href="./userDetails.jsp"><i class="fa-regular fa-user fa-2x"></i></a>
            <a id="user-logout-link" class="icon" href="#"><i class="fa-solid fa-arrow-right-from-bracket fa-2x"></i></a>
        </div>
        <div id="dashboard-links" class="grid-container">
            <a id="add-patient-link" class="grid-item item1" href="./addPatient.jsp">
                <i class="fa-solid fa-plus fa-6x"></i>
                <label>Aggiunta paziente</label>
            </a>
            <a id="patient-list-link" class="grid-item item2" href="./patientList.jsp">
                <i class="fa-solid fa-chart-column fa-6x"></i>
                <label>Storico pazienti</label>
            </a>
            <a id="medicines-list-link" class="grid-item item3" href="./medicinesList.jsp">
                <i class="fa-solid fa-pills fa-6x"></i>
                <label>Medicinali</label>
            </a>
            <a id="add-appointments-link" class="grid-item item4" href="./addAppointments.jsp">
                <i class="fa-regular fa-calendar-plus fa-6x"></i>
                <label>Nuove sedute</label>
            </a>
            <a id="planner-link" class="grid-item item5" href="./planner.jsp">
                <i class="fa-regular fa-calendar fa-6x"></i>
                <label>Calendario sedute</label>
            </a>
        </div>
    </div>
</div>
</body>
</html>
