<%--
  Created by IntelliJ IDEA.
  User: anton
  Date: 16/01/2023
  Time: 13:23
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="connector.Facade"
         import="java.util.ArrayList"
         import="userManagement.application.UserBean"
         import="patientmanagement.application.PatientBean"%>
<html>
<head>
    <title>Chemo | Storico pazienti</title>
    <script src="./static/scripts/search.js"></script>
    <script src="./static/scripts/patient.js"></script>
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
    <div id="patient-list-box" class="box">
        <div id="box-name-row" class="row">
            <h1 class="title">Storico pazienti</h1>
            <jsp:include page="./static/templates/loggedUserButtons.html"/>
        </div>
        <form id="search-form" class="form box" action="PatientServlet" method="post">
            <div class="title-section">
                <h2 class="title">Ricerca</h2>
            </div>
            <div class="input-fields-row">
                <div class="field left">
                    <input type="text" id="search-patient-name" class="search-field input-field" name="name" placeholder="Nome paziente">
                </div>
                <div class="field right">
                    <input type="text" id="search-patient-surname" class="search-field input-field" name="surname" placeholder="Cognome paziente">
                </div>
            </div>
            <div id="search-buttons">
                <input type="button" id="search-filters-button" class="button-tertiary-s rounded edit-button" value="Espandi filtri" onclick="expandSearchFilters()">
                <button type="submit" id="search-request-button" class="button-primary-m rounded edit-button" name="action" value="searchPatient">Cerca</button>
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
        </form>
        <div id="patient-list">
            <!-- Si itera fino a quando ci sono risultati-->
            <%
                ArrayList<PatientBean> patients = new ArrayList<PatientBean>();
                if (request.getAttribute("patientsResult") == null) {
                    //nessuna richiesta di ricerca
                    //si visualizzano tutti i pazienti
                    Facade facade = new Facade();
                    patients = facade.findAllPatients(user);
                } else {
                    patients = (ArrayList<PatientBean>) request.getAttribute("patientsResult");
                }

                if (patients.size() == 0) {
                    //visualizzazione messaggio nessun paziente trovato
            %>
            <div class="result-box-container">
                <h2 class="no-result">Nessun paziente trovato</h2>
            </div>
            <%
                    } else {
                        String patientStatus;
                        for (PatientBean patient:patients) {
                            //visualizzazione box singolo paziente
                            if (patient.getStatus())
                                patientStatus = "status-available";
                            else
                                patientStatus = "status-unavailable";
            %>
            <div class="result-box-container">
                <button type="submit" id="patient-box-id" class="box" onclick="redirectToPatientDetails('<%=patient.getPatientId()%>')">
                    <div class="first-row">
                        <div class="column left">
                            <h2 class="result-name"><%=patient.getName()%> <%=patient.getSurname()%></h2>
                            <p><%=patient.getTaxCode()%></p>
                        </div>
                        <div class="column icon <%=patientStatus%> right">
                            <svg xmlns="http://www.w3.org/2000/svg" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
                                <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
                                <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
                            </svg>
                        </div>
                    </div>
                    <%
                        if (patient.getCondition() != null){
                    %>
                    <div class="row">
                        <h3 class="left"><%=patient.getCondition()%></h3>
                        <p class="right">Appuntamenti necessari: <%=patient.getTherapy().getSessions()%></p>
                    </div>
                    <%
                        }
                    %>
                </button>
            </div>
            <%
                    }
                }
            %>
        </div>
    </div>
</div>
<%
        }
    }
%>
</body>
</html>
