<%--
  Created by IntelliJ IDEA.
  User: anton
  Date: 26/01/2023
  Time: 12:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="userManagement.application.UserBean" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Errore 503</title>
</head>
<body>
<header>
    <jsp:include page="static/templates/errorHeader.html"/>
</header>
<div id="page-content">
    <div id="error-box" class="box">
        <h1 id="error-code">Errore 503</h1>
        <h3 id="error-name">Service Unavailable</h3>
        <p id="error-message">La richiesta non può essere completata in questo momento.</p>
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