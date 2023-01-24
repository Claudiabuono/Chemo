<%--
  Created by IntelliJ IDEA.
  User: anton
  Date: 23/01/2023
  Time: 18:45
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Chemo | Calendario</title>
    <link rel="stylesheet" href="./static/styles/planner.css">
</head>
<body>
<header>
    <jsp:include page="static/templates/userHeaderLogged.html"/>
</header>
<div id="page-content">
    <div id="user-box" class="box">
        <div id="box-name-row" class="row">
            <h1 class="title">Calendario sedute</h1>
            <jsp:include page="./static/templates/loggedUserButtons.html"/>
        </div>
        <div id="planner-content">
            <div id="planner-top-bar" class="box-in">
                <input type="button" id="today-button" class="button-secondary-m" value="Oggi">
                <div id="week-selector">
                    <div id="previous-week" class="icon chevron">
                        <svg xmlns="http://www.w3.org/2000/svg" class="bi bi-chevron-left" viewBox="0 0 16 16">
                            <path fill-rule="evenodd" d="M11.354 1.646a.5.5 0 0 1 0 .708L5.707 8l5.647 5.646a.5.5 0 0 1-.708.708l-6-6a.5.5 0 0 1 0-.708l6-6a.5.5 0 0 1 .708 0z"/>
                        </svg>
                    </div>
                    <h2 id="actual-week">Gennaio 2023</h2>
                    <div id="next-week" class="icon chevron">
                        <svg xmlns="http://www.w3.org/2000/svg" class="bi bi-chevron-right" viewBox="0 0 16 16">
                            <path fill-rule="evenodd" d="M4.646 1.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708-.708L10.293 8 4.646 2.354a.5.5 0 0 1 0-.708z"/>
                        </svg>
                    </div>
                </div>
                <a id="add-new-appointments" class="icon">
                    <svg xmlns="http://www.w3.org/2000/svg" class="bi bi-plus" viewBox="0 0 16 16">
                        <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
                    </svg>
                </a>
            </div>
            <div id="planner-appointments-section" class="box-in">
                <div id="planner-first-row" class="planner-row">
                    <div class="planner-column planner-time">

                    </div>
                    <div id="planner-days-row" class="planner-row box">
                        <div class="planner-column">
                            <h3 class="planner-day-name">Lunedì</h3>
                            <h3 class="planner-day-number">23</h3>
                        </div>
                        <div class="planner-column">
                            <h3 class="planner-day-name">Martedì</h3>
                            <h3 class="planner-day-number">24</h3>
                        </div>
                        <div class="planner-column">
                            <h3 class="planner-day-name">Mercoledì</h3>
                            <h3 class="planner-day-number">25</h3>
                        </div>
                        <div class="planner-column">
                            <h3 class="planner-day-name">Giovedì</h3>
                            <h3 class="planner-day-number">26</h3>
                        </div>
                        <div class="planner-column">
                            <h3 class="planner-day-name">Venerdì</h3>
                            <h3 class="planner-day-number">27</h3>
                        </div>
                    </div>
                    <div class="planner-column planner-scroll">

                    </div>
                </div>
                <div id="planner-appointments-rows">
                    <div id="planner-2nd-row" class="planner-row">
                        <div class="planner-column planner-time">
                            <h3 class="time">7:00</h3>
                        </div>
                        <div id="planner-0700-row" class="planner-row appointments-row">
                            <div class="planner-column planner-appointment">
                                <div class="row">
                                    <div class="icon">
                                        <svg xmlns="http://www.w3.org/2000/svg" class="bi bi-person-fill" viewBox="0 0 16 16">
                                            <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3Zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6Z"/>
                                        </svg>
                                    </div>
                                    <h3 class="appointment-patients-number">7</h3>
                                </div>
                            </div>
                            <div class="planner-column planner-appointment">
                                <div class="row">
                                    <div class="icon">
                                        <svg xmlns="http://www.w3.org/2000/svg" class="bi bi-person-fill" viewBox="0 0 16 16">
                                            <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3Zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6Z"/>
                                        </svg>
                                    </div>
                                    <h3 class="appointment-patients-number">10</h3>
                                </div>
                            </div>
                            <div class="planner-column planner-appointment">
                                <div class="row">
                                    <div class="icon">
                                        <svg xmlns="http://www.w3.org/2000/svg" class="bi bi-person-fill" viewBox="0 0 16 16">
                                            <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3Zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6Z"/>
                                        </svg>
                                    </div>
                                    <h3 class="appointment-patients-number">5</h3>
                                </div>
                            </div>
                            <div class="planner-column planner-appointment">
                                <div class="row">
                                    <div class="icon">
                                        <svg xmlns="http://www.w3.org/2000/svg" class="bi bi-person-fill" viewBox="0 0 16 16">
                                            <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3Zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6Z"/>
                                        </svg>
                                    </div>
                                    <h3 class="appointment-patients-number">18</h3>
                                </div>
                            </div>
                            <div class="planner-column empty">

                            </div>
                        </div>
                        <div class="planner-column planner-scroll">

                        </div>
                    </div>
                    <hr>
                    <div id="planner-3nd-row" class="planner-row">
                        <div class="planner-column planner-time">
                            <h3 class="time">7:30</h3>
                        </div>
                        <div id="planner-0730-row" class="planner-row appointments-row">
                            <div class="planner-column planner-appointment">
                                <div class="row">
                                    <div class="icon">
                                        <svg xmlns="http://www.w3.org/2000/svg" class="bi bi-person-fill" viewBox="0 0 16 16">
                                            <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3Zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6Z"/>
                                        </svg>
                                    </div>
                                    <h3 class="appointment-patients-number">26</h3>
                                </div>
                            </div>
                            <div class="planner-column empty">

                            </div>
                            <div class="planner-column planner-appointment">
                                <div class="row">
                                    <div class="icon">
                                        <svg xmlns="http://www.w3.org/2000/svg" class="bi bi-person-fill" viewBox="0 0 16 16">
                                            <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3Zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6Z"/>
                                        </svg>
                                    </div>
                                    <h3 class="appointment-patients-number">10</h3>
                                </div>
                            </div>
                            <div class="planner-column planner-appointment">
                                <div class="row">
                                    <div class="icon">
                                        <svg xmlns="http://www.w3.org/2000/svg" class="bi bi-person-fill" viewBox="0 0 16 16">
                                            <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3Zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6Z"/>
                                        </svg>
                                    </div>
                                    <h3 class="appointment-patients-number">12</h3>
                                </div>
                            </div>
                            <div class="planner-column empty">

                            </div>
                        </div>
                        <div class="planner-column planner-scroll">

                        </div>
                    </div>
                    <hr>
                    <div id="planner-4nd-row" class="planner-row">
                        <div class="planner-column planner-time">
                            <h3 class="time">8:00</h3>
                        </div>
                        <div id="planner-0800-row" class="planner-row appointments-row">
                            <div class="planner-column planner-appointment">
                                <div class="row">
                                    <div class="icon">
                                        <svg xmlns="http://www.w3.org/2000/svg" class="bi bi-person-fill" viewBox="0 0 16 16">
                                            <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3Zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6Z"/>
                                        </svg>
                                    </div>
                                    <h3 class="appointment-patients-number">7</h3>
                                </div>
                            </div>
                            <div class="planner-column planner-appointment">
                                <div class="row">
                                    <div class="icon">
                                        <svg xmlns="http://www.w3.org/2000/svg" class="bi bi-person-fill" viewBox="0 0 16 16">
                                            <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3Zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6Z"/>
                                        </svg>
                                    </div>
                                    <h3 class="appointment-patients-number">10</h3>
                                </div>
                            </div>
                            <div class="planner-column planner-appointment">
                                <div class="row">
                                    <div class="icon">
                                        <svg xmlns="http://www.w3.org/2000/svg" class="bi bi-person-fill" viewBox="0 0 16 16">
                                            <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3Zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6Z"/>
                                        </svg>
                                    </div>
                                    <h3 class="appointment-patients-number">5</h3>
                                </div>
                            </div>
                            <div class="planner-column planner-appointment">
                                <div class="row">
                                    <div class="icon">
                                        <svg xmlns="http://www.w3.org/2000/svg" class="bi bi-person-fill" viewBox="0 0 16 16">
                                            <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3Zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6Z"/>
                                        </svg>
                                    </div>
                                    <h3 class="appointment-patients-number">18</h3>
                                </div>
                            </div>
                            <div class="planner-column empty">

                            </div>
                        </div>
                        <div class="planner-column planner-scroll">

                        </div>
                    </div>
                    <hr>
                    <div id="planner-5nd-row" class="planner-row">
                        <div class="planner-column planner-time">
                            <h3 class="time">8:30</h3>
                        </div>
                        <div id="planner-0830-row" class="planner-row appointments-row">
                            <div class="planner-column planner-appointment">
                                <div class="row">
                                    <div class="icon">
                                        <svg xmlns="http://www.w3.org/2000/svg" class="bi bi-person-fill" viewBox="0 0 16 16">
                                            <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3Zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6Z"/>
                                        </svg>
                                    </div>
                                    <h3 class="appointment-patients-number">10</h3>
                                </div>
                            </div>
                            <div class="planner-column planner-appointment">
                                <div class="row">
                                    <div class="icon">
                                        <svg xmlns="http://www.w3.org/2000/svg" class="bi bi-person-fill" viewBox="0 0 16 16">
                                            <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3Zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6Z"/>
                                        </svg>
                                    </div>
                                    <h3 class="appointment-patients-number">15</h3>
                                </div>
                            </div>
                            <div class="planner-column empty">

                            </div>
                            <div class="planner-column planner-appointment">
                                <div class="row">
                                    <div class="icon">
                                        <svg xmlns="http://www.w3.org/2000/svg" class="bi bi-person-fill" viewBox="0 0 16 16">
                                            <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3Zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6Z"/>
                                        </svg>
                                    </div>
                                    <h3 class="appointment-patients-number">14</h3>
                                </div>
                            </div>
                            <div class="planner-column planner-appointment">
                                <div class="row">
                                    <div class="icon">
                                        <svg xmlns="http://www.w3.org/2000/svg" class="bi bi-person-fill" viewBox="0 0 16 16">
                                            <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3Zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6Z"/>
                                        </svg>
                                    </div>
                                    <h3 class="appointment-patients-number">23</h3>
                                </div>
                            </div>
                        </div>
                        <div class="planner-column planner-scroll">

                        </div>
                    </div>
                    <hr>
                    <div id="planner-6nd-row" class="planner-row">
                        <div class="planner-column planner-time">
                            <h3 class="time">12:30</h3>
                        </div>
                        <div id="planner-1230-row" class="planner-row appointments-row">
                            <div class="planner-column planner-appointment">
                                <div class="row">
                                    <div class="icon">
                                        <svg xmlns="http://www.w3.org/2000/svg" class="bi bi-person-fill" viewBox="0 0 16 16">
                                            <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3Zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6Z"/>
                                        </svg>
                                    </div>
                                    <h3 class="appointment-patients-number">10</h3>
                                </div>
                            </div>
                            <div class="planner-column planner-appointment">
                                <div class="row">
                                    <div class="icon">
                                        <svg xmlns="http://www.w3.org/2000/svg" class="bi bi-person-fill" viewBox="0 0 16 16">
                                            <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3Zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6Z"/>
                                        </svg>
                                    </div>
                                    <h3 class="appointment-patients-number">15</h3>
                                </div>
                            </div>
                            <div class="planner-column empty">

                            </div>
                            <div class="planner-column planner-appointment">
                                <div class="row">
                                    <div class="icon">
                                        <svg xmlns="http://www.w3.org/2000/svg" class="bi bi-person-fill" viewBox="0 0 16 16">
                                            <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3Zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6Z"/>
                                        </svg>
                                    </div>
                                    <h3 class="appointment-patients-number">14</h3>
                                </div>
                            </div>
                            <div class="planner-column planner-appointment">
                                <div class="row">
                                    <div class="icon">
                                        <svg xmlns="http://www.w3.org/2000/svg" class="bi bi-person-fill" viewBox="0 0 16 16">
                                            <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3Zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6Z"/>
                                        </svg>
                                    </div>
                                    <h3 class="appointment-patients-number">23</h3>
                                </div>
                            </div>
                        </div>
                        <div class="planner-column planner-scroll">

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
