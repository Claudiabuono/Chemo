<%--
  Created by IntelliJ IDEA.
  User: anton
  Date: 19/01/2023
  Time: 20:35
--%>
<%@ page contentType="text/html;charset=UTF-8"
         import="userManagement.application.UserBean"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Chemo Nuovo medicinale</title>
</head>
<body>
<%
    HttpSession sessione=request.getSession(false);
    if (sessione == null) {
        //redirect alla pagina di error 401 Unauthorized
        response.sendRedirect("./error401.jsp");
    } else {
        UserBean user = (UserBean) sessione.getAttribute("currentSessionUser");
        if (user == null || user.getType() != 2) {
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
    <div id="add-medicine-box" class="box">
        <div id="box-name-row" class="row">
            <h1 class="title">Nuovo medicinale</h1>
            <jsp:include page="./static/templates/loggedUserButtons.html"/>
        </div>
        <div id="add-medicine-form" class="form">
            <div class="title-section">
                <h2 class="title">Dati del medicinale</h2>
            </div>
            <div class="input-fields-row">
                <div class="field left">
                    <label for="name">Nome</label>
                    <input required id="name" class="input-field" type="text" name="name">
                    <p id="name-validity" class="validity-paragraph status-unavailable"></p>
                </div>
            </div>
            <label for="ingredients">Ingredienti</label>
            <input required id="ingredients" class="input-field" type="text" name="ingredients">
            <p id="ingredients-validity" class="validity-paragraph status-unavailable"></p>
            <input type="button" class="button-primary-m submit-button" value="Aggiungi medicinale" onclick="addMedicine()">
        </div>
    </div>
</div>
<%
        }
    }
%>
</body>
</html>
