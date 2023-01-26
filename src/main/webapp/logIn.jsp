<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Chemo</title>
</head>
<body>
<header>
    <jsp:include page="./static/templates/userHeader.html"/>
</header>
<div id="page-content">
    <div id="login-box" class="box">
        <div id="box-name-row" class="row">
            <h1 class="title">Entra nell'area riservata</h1>
            <a id="homepage-link" class="icon right" href="./index.jsp">
                <svg xmlns="http://www.w3.org/2000/svg" class="bi bi-house" viewBox="0 0 16 16">
                    <path d="M8.707 1.5a1 1 0 0 0-1.414 0L.646 8.146a.5.5 0 0 0 .708.708L2 8.207V13.5A1.5 1.5 0 0 0 3.5 15h9a1.5 1.5 0 0 0 1.5-1.5V8.207l.646.647a.5.5 0 0 0 .708-.708L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293L8.707 1.5ZM13 7.207V13.5a.5.5 0 0 1-.5.5h-9a.5.5 0 0 1-.5-.5V7.207l5-5 5 5Z"/>
                </svg>
            </a>
        </div>
        <form id="login-form" class="form" action="UserServlet" method="post">
            <label for="username">Nome utente</label>
            <input required id="username" class="input-field" type="text" name="username">
            <label for="password">Password</label>
            <input required id="password" class="input-field" type="password" name="password">
            <!--<input type="button" class="button-primary-m submit-button" value="Accedi" onclick="logInRequest()">-->
            <button type="submit" class="button-primary-m submit-button" name="submit" value="login">Accedi</button>
        </form>
    </div>
</div>
<footer>

</footer>
</body>
</html>