<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Chemo</title>
    <link rel="stylesheet" href="./static/styles/index.css">
</head>
<body>
<header>
    <jsp:include page="./static/templates/header.html"/>
</header>
<div id="page-content">
    <div id="welcome-picture">
        <div id="picture-container">
            <img src="./static/images/ruggi-welcome.jpg" alt="Foto ospedale Ruggi">
            <div class="text secondary">
                <h3 class="pic-title">San Giovanni di Dio Ruggi d'Aragona</h3>
            </div>
            <div class="text main">
                <h1 class="pic-title">La piattaforma Chemo</h1>
            </div>
        </div>
    </div>
    <div id="home-info">
        <div id="welcome-picture-spacer"></div>
        <div class="info-row left">
            <h2>Perché nasce Chemo</h2>
            <p>
                Chemo nasce per l’esigenza dell’ospedale di ridurre gli sprechi ed
                ottimizzare la qualità offerta al paziente.
            </p>
        </div>
    </div>
</div>
<footer>

</footer>
</body>
</html>