<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Chemo</title>
    <link rel="stylesheet" href="static/styles/page.css">
    <link rel="stylesheet" href="./static/styles/form.css">
</head>
<body>
<header>
    <jsp:include page="./static/templates/userHeader.html"/>
</header>
<div id="page-content">
    <div id="login-box" class="box">
        <h1 class="title">Entra nell'area riservata</h1>
        <div id="login-form" class="form">
            <label for="username">Nome utente</label>
            <input required id="username" class="input-field" type="text" name="username">
            <label for="password">Password</label>
            <input required id="password" class="input-field" type="password" name="password">
            <input type="button" class="button-primary-m submit-button" value="Accedi">
        </div>
    </div>
</div>
<footer>

</footer>
</body>
</html>