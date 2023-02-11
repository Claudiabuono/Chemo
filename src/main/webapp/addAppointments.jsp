<%--
  Created by IntelliJ IDEA.
  User: anton
  Date: 21/01/2023
  Time: 20:07
--%>
<%@ page contentType="text/html;charset=UTF-8"
         import="userManagement.application.UserBean"%>
<%@ page import="patientmanagement.application.PatientBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="connector.Facade" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Chemo Nuove sedute</title>
    <link rel="stylesheet" href="./static/styles/checkbox.css">
</head>
<body>
<%
    HttpSession sessione=request.getSession(false);
    if (sessione == null) {
        //redirect alla pagina di error 401 Unauthorized
        response.sendRedirect("./error401.jsp");
    } else {
        if (sessione.getAttribute("currentSessionUser") == null) {
            response.sendRedirect("./error401.jsp");
        }
        UserBean user = (UserBean) sessione.getAttribute("currentSessionUser");
        if (user.getType() != 1) {
            response.sendRedirect("./error403.jsp");
        } else {
%>
<header>
    <jsp:include page="static/templates/userHeaderLogged.html"/>
</header>
<div id="page-content">
    <div id="alert-box"></div>
    <div id="add-appointments-box" class="box">
        <div id="box-name-row" class="row">
            <h1 class="title">Nuove sedute</h1>
            <jsp:include page="./static/templates/loggedUserButtons.html"/>
        </div>
        <form id="add-appointments-content">
            <div class="title-section">
                <h2 class="title">Selezione pazienti</h2>
            </div>
            <div id="patient-list" class="result-box-container">
                <%
                    Facade facade = new Facade();
                    ArrayList<PatientBean> patients = new ArrayList<PatientBean>();

                    patients = facade.findPatients("status", true, user);

                    if (patients.size() == 0) {
                        //visualizzazione messaggio nessun paziente trovato
                %>
                <div class="result-box-container">
                    <h2 class="no-result">Nessun paziente disponibile</h2>
                </div>
                <%
                } else {
                    for (PatientBean patient:patients) {
                %>
                <div id="patient-box-id" class="box form">
                    <div class="first-row">
                        <div class="column left">
                            <h2 class="result-name"><%=patient.getName()%> <%=patient.getSurname()%></h2>
                            <p><%=patient.getTaxCode()%></p>
                        </div>
                        <div id="patient-check-id" class="column right checkbox-container">
                            <label class="checkbox-label">
                                <input type="checkbox" name="patient-id" value="<%=patient.getPatientId()%>">
                                <span class="checkbox-custom rectangular"></span>
                            </label>
                        </div >
                    </div>
                    <div class="row">
                        <h3 class="left"><%=patient.getCondition()%></h3>
                        <p class="right">Numero sedute: <%=patient.getTherapy().getSessions()%></p>
                    </div>
                </div>
                <%
                        }
                    }
                %>
            </div>
            <input type="button" class="button-primary-m submit-button" value="Crea calendario" onclick="addAppointments()">
        </form>
    </div>
</div>
<%
        }
    }
%>
</body>
</html>
