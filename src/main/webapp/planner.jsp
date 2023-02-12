<%--
  Created by IntelliJ IDEA.
  User: anton
  Date: 23/01/2023
  Time: 18:45
--%>
<%@ page contentType="text/html;charset=UTF-8"
         import="userManagement.application.UserBean"
         import="plannerManagement.application.PlannerBean"
         import="plannerManagement.application.AppointmentBean"
         import="java.util.Date"
         import="java.text.Format"
         import="java.text.SimpleDateFormat"
         import="java.time.LocalTime"
         import="java.time.LocalDate"
         import="java.time.ZoneId"
         import="patientmanagement.application.PatientBean"
         import="medicinemanagement.application.MedicineBean"
         import="connector.Facade"
%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Chemo Calendario</title>
    <link rel="stylesheet" href="./static/styles/planner.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
</head>
<body>
<%
    HttpSession sessione=request.getSession(false);
    if (sessione == null) {
        //redirect alla pagina di error 401 Unauthorized
        response.sendRedirect("./error401.jsp");
    } else {
        UserBean user = (UserBean) sessione.getAttribute("currentSessionUser");
        if (user == null ) {
            response.sendRedirect("./error401.jsp");
        } else {
            //Controllo se la request ha l'attributo di un planner specifico
            if (sessione.getAttribute("plannerToVisualize") == null) {
                //Redirect alla servlet
            }
            //Se è presente recupera quelli disponibili
            String nextPlanner = (String) request.getAttribute("afterVisualizedId");
            String previousPlanner = (String) request.getAttribute("beforeVisualizedId");
            String latestPlanner = (String) request.getAttribute("latestPlannerId");
            PlannerBean planner = (PlannerBean) request.getAttribute("plannerToVisualize");
            String weekDate = (String) request.getAttribute("weekDate");

            Facade facade = new Facade();

            // Estrapolazione di giorno, mese e anno dalla data
            Date slotFullDate = planner.getStartDate();
            Format formatterYear = new SimpleDateFormat("yyyy");
            Format formatterMonth = new SimpleDateFormat("MM");
            Format formatterDay = new SimpleDateFormat("dd");
            int year = Integer.parseInt(formatterYear.format(slotFullDate));
            int month = Integer.parseInt(formatterMonth.format(slotFullDate));
            int day = Integer.parseInt(formatterDay.format(slotFullDate));

            int calendarDay = day;
%>
<header>
    <jsp:include page="static/templates/userHeaderLogged.html"/>
</header>
<div id="page-content">
    <div id="alert-box"></div>
    <div id="user-box" class="box">
        <div id="box-name-row" class="row">
            <h1 class="title">Calendario sedute</h1>
            <jsp:include page="./static/templates/loggedUserButtons.html"/>
        </div>
        <div id="planner-content">
            <div id="planner-top-bar" class="box-in">
                <input type="button" id="today-button" class="button-secondary-m" value="Oggi" onclick="redirectToPlanner('<%=latestPlanner%>')">
                <div id="week-selector">
                    <%
                        if (previousPlanner.equals("")) {
                    %>
                    <div id="previous-week" class="icon chevron-inactive">
                        <svg xmlns="http://www.w3.org/2000/svg" class="bi bi-chevron-left" viewBox="0 0 16 16">
                            <path fill-rule="evenodd" d="M11.354 1.646a.5.5 0 0 1 0 .708L5.707 8l5.647 5.646a.5.5 0 0 1-.708.708l-6-6a.5.5 0 0 1 0-.708l6-6a.5.5 0 0 1 .708 0z"/>
                        </svg>
                    </div>
                    <%
                    } else {
                    %>
                    <div id="previous-week" class="icon chevron" onclick="redirectToPlanner('<%=previousPlanner%>')">
                        <svg xmlns="http://www.w3.org/2000/svg" class="bi bi-chevron-left" viewBox="0 0 16 16">
                            <path fill-rule="evenodd" d="M11.354 1.646a.5.5 0 0 1 0 .708L5.707 8l5.647 5.646a.5.5 0 0 1-.708.708l-6-6a.5.5 0 0 1 0-.708l6-6a.5.5 0 0 1 .708 0z"/>
                        </svg>
                    </div>
                    <%
                        }
                    %>
                    <h3 id="actual-week"><%=weekDate%></h3>
                    <%
                        if (nextPlanner.equals("")) {
                    %>
                    <div id="next-week" class="icon chevron-inactive">
                        <svg xmlns="http://www.w3.org/2000/svg" class="bi bi-chevron-right" viewBox="0 0 16 16">
                            <path fill-rule="evenodd" d="M4.646 1.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708-.708L10.293 8 4.646 2.354a.5.5 0 0 1 0-.708z"/>
                        </svg>
                    </div>
                    <%
                        } else {
                    %>
                    <div id="next-week" class="icon chevron" onclick="redirectToPlanner('<%=nextPlanner%>')">
                        <svg xmlns="http://www.w3.org/2000/svg" class="bi bi-chevron-right" viewBox="0 0 16 16">
                            <path fill-rule="evenodd" d="M4.646 1.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708-.708L10.293 8 4.646 2.354a.5.5 0 0 1 0-.708z"/>
                        </svg>
                    </div>
                    <%
                        }
                    %>
                </div>
                <a id="add-new-appointments" class="icon" href="addAppointments.jsp">
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
                            <h5 class="planner-day-name">Lunedì</h5>
                            <h5 class="planner-day-number"><%=calendarDay%></h5>
                        </div>
                        <div class="planner-column">
                            <h5 class="planner-day-name">Martedì</h5>
                            <h5 class="planner-day-number"><%=calendarDay+1%></h5>
                        </div>
                        <div class="planner-column">
                            <h5 class="planner-day-name">Mercoledì</h5>
                            <h5 class="planner-day-number"><%=calendarDay+2%></h5>
                        </div>
                        <div class="planner-column">
                            <h5 class="planner-day-name">Giovedì</h5>
                            <h5 class="planner-day-number"><%=calendarDay+3%></h5>
                        </div>
                        <div class="planner-column">
                            <h5 class="planner-day-name">Venerdì</h5>
                            <h5 class="planner-day-number"><%=calendarDay+4%></h5>
                        </div>
                    </div>
                    <div class="planner-column planner-scroll">

                    </div>
                </div>
                <div id="planner-appointments-rows">
                    <%
                        // Creazione e assegnazione dei parametri di base per il ciclo degli appuntamenti
                        int hours = 9;  // Orario d'inizio degli appuntamenti
                        int hoursNext = hours + 1;
                        String minutes = "00";
                        int appointmentsSlotsNumber = 5;    // Numero di righe/slot orari nel calendario


                        // Creazione del giorno per il singolo slot
                        int slotDay = day;

                        //Creazione dei dati necessari ai singoli slot
                        // Formattazione della data semplice senza orario per il singolo slot
                        LocalDate slotDate = LocalDate.of(year,month,slotDay);

                        // Formattazione orari d'inizio e fine per il singolo slot
                        LocalTime slotTimeStart = LocalTime.of(hours , Integer.parseInt(minutes));
                        LocalTime slotTimeEnd = LocalTime.of(hoursNext , Integer.parseInt(minutes));

                        int slotNumber = 0;

                        for (int i = 0; i < appointmentsSlotsNumber; i++, hours++, hoursNext++) {
                            // Setting dell'orario della riga
                            slotTimeStart = LocalTime.of(hours , Integer.parseInt(minutes));
                            slotTimeEnd = LocalTime.of(hoursNext , Integer.parseInt(minutes));
                    %>
                    <div id="planner-<%=i%>-row" class="planner-row">
                        <div class="planner-column planner-time">
                            <h5 class="time"><%=hours%>:<%=minutes%></h5>
                        </div>
                        <div id="planner-<%=hours%><%=minutes%>-row" class="planner-row appointments-row">
                            <%
                                // Assegnazione dell'ora della riga
                                for (int j = 0; j < 5; j++) {
                                    slotNumber = 0;
                                    // Assegnazione del giorno della colonna
                                    slotDay = day + j;
                                    // Assegnazione della data allo slot
                                    slotDate = LocalDate.of(year,month,slotDay);

                                    // Recupero di tutti gli appuntamenti che hanno data e ora valida
                                    LocalDate appointmentDate;
                                    LocalTime appointmentStart;
                                    LocalTime appointmentEnd;
                                    for (AppointmentBean appointment:
                                            planner.getAppointments()) {
                                        // Recupero dei dati dell'appuntamento
                                        // Recupero data
                                        appointmentDate = LocalDate.ofInstant(appointment.getDate().toInstant(), ZoneId.of("Europe/Paris"));
                                        System.out.println("Data appuntamento: " + appointmentDate);
                                        // Recupero orario inizio
                                        appointmentStart = LocalTime.ofInstant(appointment.getDate().toInstant(), ZoneId.of("Europe/Paris"));
                                        System.out.println("Inizio appuntamento: " + appointmentStart);
                                        // Recupero orario fine
                                        appointmentEnd = appointmentStart.plusMinutes(appointment.getDuration());
                                        System.out.println("Fine appuntamento: " + appointmentEnd);
                                        if (appointmentDate.equals(slotDate) &&
                                                (appointmentStart.isAfter(slotTimeStart) || appointmentStart.equals(slotTimeStart)) &&
                                                (appointmentEnd.isBefore(slotTimeEnd) || appointmentEnd.equals(slotTimeEnd))) {
                                            slotNumber++;
                                            System.out.println("Incremento contatore");
                                        }
                                        System.out.println("Appuntamento " + appointment.getDate());
                                    }
                                    System.out.println("Fine appuntamenti");

                                    if (slotNumber == 0) {

                            %>
                            <div class="planner-column empty">

                            </div>
                            <%
                                } else {
                            %>
                            <!-- Button trigger modal -->
                            <button type="button" class="planner-column planner-appointment" data-bs-toggle="modal" data-bs-target="#slot-info-<%=i%>-<%=j%>">
                                <div class="row">
                                    <div class="icon">
                                        <svg xmlns="http://www.w3.org/2000/svg" class="bi bi-person-fill" viewBox="0 0 16 16">
                                            <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3Zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6Z"/>
                                        </svg>
                                    </div>
                                    <h3 class="appointment-patients-number"><%=slotNumber%></h3>
                                    <div class="hidden">

                                    </div>
                                </div>
                            </button>
                            <!-- Modal -->
                            <div class="modal fade" id="slot-info-<%=i%>-<%=j%>" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-scrollable">

                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h1 class="modal-title fs-5" id="exampleModalLabel">Informazioni appuntamento</h1>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <div id="slot-i-j-patients">
                                                <%
                                                    if (user.getType() == 1) {
                                                %>
                                                <h3>Pazienti</h3>
                                                <%
                                                    PatientBean patient;
                                                    for (AppointmentBean appointment:
                                                            planner.getAppointments()) {
                                                        // Recupero dei dati dell'appuntamento
                                                        // Recupero data
                                                        appointmentDate = LocalDate.ofInstant(appointment.getDate().toInstant(), ZoneId.of("Europe/Paris"));
                                                        // Recupero orario inizio
                                                        appointmentStart = LocalTime.ofInstant(appointment.getDate().toInstant(), ZoneId.of("Europe/Paris"));
                                                        // Recupero orario fine
                                                        appointmentEnd = appointmentStart.plusMinutes(appointment.getDuration());
                                                        if (appointmentDate.equals(slotDate) &&
                                                                (appointmentStart.isAfter(slotTimeStart) || appointmentStart.equals(slotTimeStart)) &&
                                                                (appointmentEnd.isBefore(slotTimeEnd) || appointmentEnd.equals(slotTimeEnd))) {
                                                            //Recupero il paziente tramite l'id
                                                            patient = facade.findPatients("_id", appointment.getIdPatient(), user).get(0);
                                                            //Aggiungo i dati del paziente alla pagina
                                                %>
                                                <p><%=patient.getName()%> <%=patient.getSurname()%> - Poltrona <%=appointment.getChair()%> - <%=appointmentStart%>-<%=appointmentEnd%></p>
                                                <%
                                                            }
                                                        }
                                                    }
                                                %>
                                            </div>
                                            <div id="slot-i-j-medicines">
                                                <h3>Medicinali</h3>
                                                <%
                                                    MedicineBean medicine;
                                                    for (AppointmentBean appointment:
                                                            planner.getAppointments()) {
                                                        // Recupero dei dati dell'appuntamento
                                                        // Recupero data
                                                        appointmentDate = LocalDate.ofInstant(appointment.getDate().toInstant(), ZoneId.of("Europe/Paris"));
                                                        // Recupero orario inizio
                                                        appointmentStart = LocalTime.ofInstant(appointment.getDate().toInstant(), ZoneId.of("Europe/Paris"));
                                                        // Recupero orario fine
                                                        appointmentEnd = appointmentStart.plusMinutes(appointment.getDuration());
                                                        if (appointmentDate.equals(slotDate) &&
                                                                (appointmentStart.isAfter(slotTimeStart) || appointmentStart.equals(slotTimeStart)) &&
                                                                (appointmentEnd.isBefore(slotTimeEnd) || appointmentEnd.equals(slotTimeEnd))) {
                                                            //Recupero il medicinale tramite l'id
                                                            medicine = facade.findMedicines("_id", appointment.getIdMedicine(), user).get(0);
                                                            //Aggiungo i dati del medicinale alla pagina
                                                %>
                                                <p><%=medicine.getName()%> - Poltrona <%=appointment.getChair()%></p>
                                                <%
                                                        }
                                                    }
                                                %>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Chiudi</button>
                                        </div>
                                    </div>

                                </div>
                            </div>
                            <%
                                    }
                                }
                            %>
                        </div>
                        <div class="planner-column planner-scroll">

                        </div>
                    </div>
                    <%
                        }
                    %>
                    <hr>


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
