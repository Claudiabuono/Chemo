package patientmanagement.application;

import connector.Facade;
import userManagement.application.UserBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet("/PatientServlet")
public class PatientServlet extends HttpServlet {
    static Facade facade = new Facade();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Recupero l'action dalla request
        String action = request.getParameter("action");

        //Recupero l'utente dalla sessione
        UserBean user = (UserBean) request.getSession().getAttribute("currentSessionUser");

        try {
            switch (action) {
                case "createPatientProfile" -> {  //Creazione profilo paziente
                    //Creo il profilo del paziente
                    facade.insertPatient(request.getParameter("taxCode"), request.getParameter("name"), request.getParameter("surname"), dateParser(request.getParameter("birthDate")),
                            request.getParameter("city"), request.getParameter("phoneNumber"), request.getParameter("condition"), request.getParameter("notes"),user);

                    //Reindirizzo alla pagina del paziente appena creato
                    response.sendRedirect(""); //todo: aggiungere jsp una volta creata
                }

                case "completePatientProfile" -> {  //Completamento profilo paziente
                    //Completo il profilo paziente inserendo patologia e terapia
                    facade.updatePatient("id", request.getParameter("id"), "condition", request.getParameter("condition"), user);
                    facade.updatePatient("id", request.getParameter("id"), "therapy", request.getParameter("therapy"), user);

                    //Reindirizzo alla pagina del paziente appena creato
                    response.sendRedirect(""); //todo: aggiungere jsp una volta creata
                }

                case "editPatientProfile" -> {  //Modifica profilo paziente
                    //facade.updatePatient(""); //todo: parametri qua non so cosa metterci ho mal di testa
                    //facade.updatePatient(""); //todo: parametri qua non so cosa metterci ho mal di testa


                    //Reindirizzo alla pagina del paziente appena creato
                    response.sendRedirect(""); //todo: aggiungere jsp una volta creata
                }

                case "searchPatient" -> {
                    //Recupero i pazienti
                    ArrayList<PatientBean> patients = new ArrayList<>();
                    patients = facade.findPatients("id", request.getParameter("id"), user);

                    if(patients.size() == 1) {
                        //Aggiungo il parametro alla request
                        request.setAttribute("patientsResult", patients.get(0));

                        //Reindirizzo alla pagina del paziente appena trovato
                        response.sendRedirect(""); //todo: aggiungere jsp una volta creata
                    }
                    else {
                        //Aggiungo il parametro alla request
                        request.setAttribute("patientsResult", patients);

                        //Reindirizzo alla pagina del paziente appena trovato
                        response.sendRedirect(""); //todo: aggiungere jsp una volta creata
                    }
                }

                case "viewPatientList" -> {
                    //Recupero i pazienti
                    ArrayList<PatientBean> patients = new ArrayList<>();
                    patients = facade.findPatients("", "", user);

                    //Aggiungo il parametro alla request
                    request.setAttribute("patientsResult", patients);

                    //Reindirizzo alla pagina del paziente appena trovato
                    response.sendRedirect(""); //todo: aggiungere jsp una volta creata
                }

                case "viewAvailablePatients" -> {
                    //Recupero i pazienti
                    ArrayList<PatientBean> patients = new ArrayList<>();
                    patients = facade.findPatients("status", request.getParameter("status"), user);

                    //Aggiungo il parametro alla request
                    request.setAttribute("patientsResult", patients);

                    //Reindirizzo alla pagina del paziente appena trovato
                    response.sendRedirect(""); //todo: aggiungere jsp una volta creata
                }

                case "editPatientStatus" -> {
                    //Aggiorno lo stato del paziente
                    facade.updatePatient("id", request.getParameter("id"), "status", request.getParameter("status"), user);

                    //Reindirizzo alla pagina del paziente appena aggiornato
                    response.sendRedirect(""); //todo: aggiungere jsp una volta creata
                }
            }
        }
        catch (Throwable e) {
            System.out.println(e.getMessage());
        }
    }

    //Metodi di supporto
    private Date dateParser(String date) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return format.parse(date);
        }
        catch (Exception e) {
            return null;
        }
    }
}
