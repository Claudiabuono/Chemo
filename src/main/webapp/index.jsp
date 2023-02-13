<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Chemo</title>
    <link rel="stylesheet" href="./static/styles/index.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<body>
<header>
    <jsp:include page="static/templates/indexHeader.html"/>
</header>
<div id="page-content">
    <div id="welcome-picture">
        <div id="picture-container">
            <img src="./static/images/ruggi-welcome.jpg" alt="Foto ospedale Ruggi">
            <div class="text secondary">
                <h5 class="pic-title">San Giovanni di Dio Ruggi d'Aragona</h5>
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
                ottimizzare la qualità offerta al paziente. La gestione manuale
                degli appuntamenti per le chiemioterapie è inefficiente e porta ad
                un maggior consumo di medicinali, quindi a un maggiore inquinamento.
            </p>
        </div>
        <div class="info-row right">
            <h2>I vantaggi di Chemo</h2>
            <p>
                Chemo permette, attraverso la gestione digitale dei dati e la
                pianificazione automatizzata delle sedute di chemioterapia, numerosi
                vantaggi tra cui migliore organizzazione e minore impatto ambientale.
            </p>
        </div>
    </div>
</div>
<footer>

</footer>
</body>
</html>