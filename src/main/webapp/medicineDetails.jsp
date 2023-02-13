<%--
  Created by IntelliJ IDEA.
  User: anton
  Date: 20/01/2023
  Time: 17:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"
         import="userManagement.application.UserBean"%>
<%@ page import="medicinemanagement.application.MedicineBean" %>
<%@ page import="medicinemanagement.application.PackageBean" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Chemo Scheda medicinale</title>
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
        if (user == null) {
            //è presente una sessione senza utente
            response.sendRedirect("./error401.jsp");
        } else {
            MedicineBean medicine = (MedicineBean) request.getAttribute("medicine");
            if (medicine == null) {
                response.sendRedirect("./error403.jsp");
            } else {
%>
<header>
    <jsp:include page="./static/templates/userHeaderLogged.html"/>
</header>
<div id="page-content">
    <div id="alert-box"></div>
    <div id="medicine-data-box" class="box">
        <div id="box-name-row" class="row">
            <h1 class="title">Scheda medicinale</h1>
            <jsp:include page="./static/templates/loggedUserButtons.html"/>
        </div>
        <div id="medicine-data-form" class="form">
            <div class="title-section">
                <h2 class="title">Dati del medicinale</h2>
                <div id="medicine-data-buttons" class="form-section-buttons">
                    <%--
                    <input type="button" id="delete-medicine-button" class="button-tertiary-m rounded edit-button" value="Elimina medicinale">
                    <input type="button" id="save-medicine-button" class="button-primary-m edit-button save-button" value="Salva">
                    --%>
                    <%
                        if (user.getType() == 2) {
                    %>
                    <input type="button" id="edit-medicine-data-button" class="button-secondary-s rounded edit-button" value="Modifica" onclick="editMedicineButton()">
                    <%
                        }
                    %>
                </div>
            </div>
            <div class="input-fields-row">
                <div class="field left">
                    <label for="name">Nome</label>
                    <input required id="name" class="input-field inactive" type="text" name="name" value="<%=medicine.getName()%>">
                    <p id="name-validity" class="validity-paragraph status-unavailable"></p>
                </div>
                <div class="field right">
                    <label for="amount">Quantità</label>
                    <input required id="amount" class="input-field inactive" type="text" name="amount" value="<%=medicine.getAmount()%>">
                </div>
            </div>
            <label for="ingredients">Ingredienti</label>
            <input required id="ingredients" class="input-field inactive" type="text" name="ingredients" value="<%=medicine.getIngredients()%>">
            <p id="ingredients-validity" class="validity-paragraph status-unavailable"></p>
        </div>
        <div class="title-section">
            <h2 class="title">Magazzino</h2>
        </div>
        <%
            if (user.getType() == 2) {
        %>
        <input type="button" id="new-package-button" class="button-primary-m submit-button" value="Aggiungi confezione" onclick="addPackageForm()">
        <%
            }
        %>
        <div id="new-package-form" class="box hidden">
            <div class="form">
                <div class="title-section">
                    <h3 class="title">Aggiunta confezione</h3>
                </div>
                <div class="input-fields-row">
                    <div class="field left">
                        <label for="package-new-capacity">Capacità (in ml)</label>
                        <input required id="package-new-capacity" class="input-field" type="number" min="0" name="capacity">
                        <p id="package-new-capacity-validity" class="validity-paragraph status-unavailable"></p>
                    </div>
                    <div class="field right">
                        <label for="package-new-expiry-date">Scadenza</label>
                        <input required id="package-new-expiry-date" class="input-field" type="date" name="expiryDate">
                        <p id="package-new-expiry-date-validity" class="validity-paragraph status-unavailable"></p>
                    </div>
                </div>
                <input type="button" class="button-primary-m submit-button" value="Salva confezione" onclick="addPackage('<%=medicine.getId()%>')">
            </div>
        </div>
        <div id="medicine-available-packages">
            <div class="title-section">
                <h3 class="title">Confezioni in magazzino</h3>
            </div>
            <%
                if (medicine.getPackages().size() == 0) {
            %>
            <div class="result-box-container">
                <h2 class="no-result">Nessuna confezione in magazzino</h2>
            </div>
            <%
            } else {
                String packageStatus;
                for (PackageBean medicinePackage:medicine.getPackages()) {
                    //visualizzazione box singolo paziente
                    if (medicinePackage.getStatus())
                        packageStatus = "status-available";
                    else
                        packageStatus = "status-unavailable";
            %>
            <div id="package-idconfezione" class="box form">
                <div class="title-section">
                    <h3 class="title">ID <%=medicinePackage.getPackageId()%></h3>
                    <div id="package-data-buttons" class="form-section-buttons">
                        <%--
                        <input type="button" id="delete-package-button" class="button-tertiary-m rounded edit-button" value="Elimina confezione">
                        <input type="button" id="save-package-button" class="button-primary-m edit-button" value="Salva">
                        --%>
                        <%
                            if (user.getType() == 2) {
                        %>
                        <input type="button" id="edit-package-data-button" class="button-secondary-s rounded edit-button" value="Modifica" onclick="editPackageButton('id')">
                        <%
                            }
                        %>
                    </div>
                </div>
                <div class="input-fields-row">
                    <div class="field left">
                        <label for="id-package-capacity">Capacità (in ml)</label>
                        <input required id="id-package-capacity" class="input-field inactive" type="number" name="capacity" min="0" value="<%=medicinePackage.getCapacity()%>">
                        <p id="package-idconfezione-capacity-validity" class="validity-paragraph status-unavailable"></p>
                    </div>
                    <div class="field right">
                        <label for="id-package-expiry-date">Scadenza</label>
                        <input required id="id-package-expiry-date" class="input-field inactive" type="date" name="expiryDate" value="<%=medicinePackage.getParsedExpiryDate()%>">
                        <p id="package-idconfezione-expiry-date-validity" class="validity-paragraph status-unavailable"></p>
                    </div>
                </div>
                <div class="input-fields-row">
                    <div id="status-icon" class="icon <%=packageStatus%>">
                        <svg xmlns="http://www.w3.org/2000/svg" fill="currentColor" class="bi bi-capsule" viewBox="0 0 16 16">
                            <path d="M1.828 8.9 8.9 1.827a4 4 0 1 1 5.657 5.657l-7.07 7.071A4 4 0 1 1 1.827 8.9Zm9.128.771 2.893-2.893a3 3 0 1 0-4.243-4.242L6.713 5.429l4.243 4.242Z"/>
                        </svg>
                    </div>
                    <div class="field">
                        <label for="id-package-status">Stato della confezione</label>
                        <select id="id-package-status" class="input-field inactive" name="status">
                            <%
                                if (medicinePackage.getStatus()){
                            %>
                            <option value="true" selected>Valido</option>
                            <option value="false">Scaduto</option>
                            <%
                                } else {
                            %>
                            <option value="true">Valido</option>
                            <option value="false" selected>Scaduto</option>
                            <%
                                }
                            %>
                        </select>
                    </div>
                </div>
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
    }
%>
</body>
</html>
