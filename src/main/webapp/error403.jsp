<%--
  Created by IntelliJ IDEA.
  User: anton
  Date: 26/01/2023
  Time: 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="userManagement.application.UserBean" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Errore 403</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<body>
<header>
    <jsp:include page="static/templates/errorHeader.html"/>
</header>
<div id="page-content">
    <div id="error-box" class="box">
        <h1 id="error-code">Errore 403</h1>
        <h3 id="error-name">Forbidden</h3>
        <p id="error-message">Ti è negato accedere alla risorsa.</p>
        <!-- Controllare se esiste una sessione ed un utente collegato -->
        <!-- Se non esiste inserire collegamento a index.jsp -->
        <!-- Altrimenti inserire collegamento a dashboard.jsp -->
        <%
            HttpSession sessione=request.getSession(false);
            if (sessione == null) {
                //link ad index
        %>
        <a href="./index.jsp">
            <input type="button" class="button-primary-m submit-button" value="Torna alla homepage">
        </a>
        <%
        } else {
        %>
        <%
            UserBean user = (UserBean) sessione.getAttribute("currentSessionUser");
            if (user == null) {
                //link ad index
        %>
        <a href="./index.jsp">
            <input type="button" class="button-primary-m submit-button" value="Torna alla homepage">
        </a>
        <%
        } else {
            //link alla dashboard
        %>
        <a href="./dashboard.jsp">
            <input type="button" class="button-primary-m submit-button" value="Torna all'area personale">
        </a>
        <%
                }
            }

        %>

    </div>
</div>
</body>
</html>
