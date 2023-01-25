<%--
  Created by IntelliJ IDEA.
  User: anton
  Date: 19/01/2023
  Time: 20:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Chemo | Nuovo medicinale</title>
</head>
<body>
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
                </div>
            </div>
            <label for="ingredients">Ingredienti</label>
            <input required id="ingredients" class="input-field" type="text" name="ingredients">
            <input type="button" class="button-primary-m submit-button" value="Aggiungi medicinale">
        </div>
    </div>
</div>
</body>
</html>
