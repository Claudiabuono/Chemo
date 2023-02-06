<%--
  Created by IntelliJ IDEA.
  User: anton
  Date: 17/01/2023
  Time: 17:50
--%>
<%@ page contentType="text/html;charset=UTF-8"
         import="userManagement.application.UserBean"%>
<%@ page import="medicinemanagement.application.MedicineBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="connector.Facade" %>
<html>
<head>
    <title>Chemo | Medicinali</title>
    <script src="./static/scripts/search.js"></script>
</head>
<body>
<%
    HttpSession sessione=request.getSession(false);
    if (sessione == null) {
        //redirect alla pagina di error 401 Unauthorized
        response.sendRedirect("./error401.jsp");
    } else {
        UserBean user = (UserBean) sessione.getAttribute("currentSessionUser");
        if (user == null) {
            //è presente una sessione senza utente
            response.sendRedirect("./error401.jsp");
        } else {
%>
<header>
    <jsp:include page="./static/templates/userHeaderLogged.html"/>
</header>
<div id="page-content">
    <div id="medicines-list-box" class="box">
        <div id="box-name-row" class="row">
            <h1 class="title">Medicinali</h1>
            <jsp:include page="./static/templates/loggedUserButtons.html"/>
        </div>
        <form id="search-form" class="form box" action="MedicineServlet" method="post">
            <div class="title-section">
                <h2 class="title">Ricerca</h2>
            </div>
            <input type="text" id="search-medicine-name" class="search-field input-field" name="medicineName" placeholder="Nome medicinale">
            <div id="search-buttons">
                <input type="button" id="search-filters-button" class="button-tertiary-s rounded edit-button" value="Espandi filtri" onclick="expandSearchFilters()">
                <button type="submit" id="search-request-button" class="button-primary-m rounded edit-button" name="action" value="searchMedicine">Cerca</button>
            </div>
            <div id="search-filters" class="hidden">
                <div class="input-fields-row">
                    <div class="field left">
                        <label for="search-medicine-expiry-date">Scadenza</label>
                        <input type="date" id="search-medicine-expiry-date" class="input-field" name="expiryDate">
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
        </form>
        <div id="medicines-list">
            <!-- Si itera fino a quando ci sono risultati-->
            <%
                ArrayList<MedicineBean> medicines = new ArrayList<MedicineBean>();
                if (request.getAttribute("medicineResults") == null) {
                    //nessuna richiesta di ricerca
                    //si visualizzano tutti i pazienti
                    Facade facade = new Facade();
                    medicines = facade.findAllMedicines(user);
                } else {
                    medicines = (ArrayList<MedicineBean>) request.getAttribute("medicineResults");
                }

                if (medicines.size() == 0) {
                    //visualizzazione messaggio nessun medicinale trovato
            %>
            <div class="result-box-container">
                <h2 class="no-result">Nessun medicinale trovato</h2>
            </div>
            <%
            } else {
                String patientStatus;
                for (MedicineBean medicine:medicines) {
                    //visualizzazione box singolo paziente
                    if (medicine.getAmount() > 0)
                        patientStatus = "status-available";
                    else
                        patientStatus = "status-unavailable";
            %>
            <div class="result-box-container">
                <button type="submit" id="medicine-box-id" class="box" onclick="redirectToMedicineDetails('<%=medicine.getId()%>')">
                    <div class="first-row">
                        <div class="column left">
                            <h2 class="result-name"><%=medicine.getName()%></h2>
                        </div>
                        <div class="column icon <%=patientStatus%> right">
                            <svg xmlns="http://www.w3.org/2000/svg" fill="currentColor" class="bi bi-capsule-pill" viewBox="0 0 16 16">
                                <path d="M11.02 5.364a3 3 0 0 0-4.242-4.243L1.121 6.778a3 3 0 1 0 4.243 4.243l5.657-5.657Zm-6.413-.657 2.878-2.879a2 2 0 1 1 2.829 2.829L7.435 7.536 4.607 4.707ZM12 8a4 4 0 1 1 0 8 4 4 0 0 1 0-8Zm-.5 1.042a3 3 0 0 0 0 5.917V9.042Zm1 5.917a3 3 0 0 0 0-5.917v5.917Z"/>
                            </svg>
                        </div>
                    </div>
                    <div class="column">
                        <h3 class="result-title left">Ingredienti</h3>
                        <p class="left"><%=medicine.getIngredients()%></p>
                    </div>
                    <div class="row">
                        <p class="left">Confezioni: <%=medicine.getAmount()%></p>
                    </div>
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
