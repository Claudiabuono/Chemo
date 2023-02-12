<%--
  Created by IntelliJ IDEA.
  User: anton
  Date: 24/01/2023
  Time: 17:38
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="userManagement.application.UserBean" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Chemo | Dati personali</title>
    <script src="./static/scripts/user.js"></script>
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
            System.out.println("Errore: sessione senza utente");
            //è presente una sessione senza utente
            session.invalidate();
            response.sendRedirect("./error401.jsp");
        } else {
%>
<header>
    <jsp:include page="./static/templates/userHeaderLogged.html"/>
</header>
<div id="page-content">
    <div id="alert-box"></div>
    <div id="user-data-box" class="box">
        <div id="box-name-row" class="row">
            <h1 class="title">Dati personali</h1>
            <jsp:include page="./static/templates/loggedUserButtons.html"/>
        </div>
        <div id="user-data-form" class="form">
            <div class="title-section">
                <h2 class="title">Anagrafica</h2>
            </div>
            <div class="input-fields-row">
                <div class="field left">
                    <label for="name">Nome</label>
                    <input required id="name" class="input-field inactive" type="text" name="name" value="<%=user.getName()%>">
                </div>
                <div class="field right">
                    <label for="surname">Cognome</label>
                    <input required id="surname" class="input-field inactive" type="text" name="surname" value="<%=user.getSurname()%>">
                </div>
            </div>
            <div class="input-fields-row">
                <div class="field left">
                    <label for="birthdate">Data di nascita</label>
                    <input required id="birthdate" class="input-field inactive" type="date" name="birthdate" value="<%=user.getParsedBirthDate()%>">
                </div>
                <div class="field right">
                    <label for="city">Città di nascita</label>
                    <input required id="city" class="input-field inactive" type="text" name="city" value="<%=user.getBirthplace()%>">
                </div>
            </div>
        </div>
        <div id="user-credentials-form" class="form">
            <div class="title-section">
                <h2 class="title">Credenziali</h2>
                <div id="user-credentials-button" class="row">
                    <%--
                    <input type="button" id="save-user-credentials-button" class="button-primary-m rounded edit-button" value="Salva">
                    --%>
                    <input type="button" id="edit-user-credentials-button" class="button-secondary-s rounded edit-button" value="Modifica" onclick="editUserCredentials('userid')">
                </div>
            </div>
            <div class="input-fields-row">
                <div class="field left">
                    <label for="name">Username</label>
                    <input required id="username" class="input-field inactive" type="text" name="username" value="<%=user.getUsername()%>">
                </div>
                <div class="field left">
                    <label for="name">Password</label>
                    <input required id="password" class="input-field inactive" type="password" name="password" value="<%=user.getPassword()%>">
                </div>
            </div>
        </div>
    </div>
</div>
<%
        }
    }
%>
</body>
</html>
