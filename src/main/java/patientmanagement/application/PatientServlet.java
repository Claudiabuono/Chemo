package patientmanagement.application;

import connector.Facade;
import userManagement.application.UserBean;

import javax.servlet.RequestDispatcher;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Recupero l'action dalla request
        String id = request.getParameter("id");

        if (id != null) {
            //Recupero l'utente dalla sessione
            UserBean user = (UserBean) request.getSession().getAttribute("currentSessionUser");

            //Cerco il paziente richiesto
            ArrayList<PatientBean> patients = facade.findPatients("_id", id, user);

            //Imposto i dati del paziente come attributo
            request.setAttribute("patient", patients.get(0));

            //Reindirizzo alla pagina del paziente
            getServletContext().getRequestDispatcher(response.encodeURL(response.encodeURL("/patientDetails.jsp"))).forward(request, response);
        }
    }
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
                    PatientBean patient = facade.insertPatient(request.getParameter("taxCode"), request.getParameter("name"), request.getParameter("surname"), dateParser(request.getParameter("birthDate")),
                            request.getParameter("city"), request.getParameter("phoneNumber"), request.getParameter("notes"),user);

                    //Salvo l'id del paziente nell'header della response, così da poterlo reindirizzare alla sua pagina
                    response.addHeader("OPERATION_RESULT","true");
                    response.addHeader("PATIENT_ID", patient.getPatientId());
                }

                case "completePatientProfile" -> {  //Completamento profilo paziente
                    //Recupero l'id del paziente
                    String patientId = request.getParameter("id");

                    //Aggiorno il profilo paziente con la malattia
                    facade.updatePatient("_id", patientId, "condition", request.getParameter("condition"), user);

                    //Recupero i dati della terapia dalla request
                    int duration = Integer.parseInt(request.getParameter("duration"));
                    int frequency = Integer.parseInt(request.getParameter("frequency"));
                    int sessions = Integer.parseInt(request.getParameter("sessions"));
                    ArrayList<TherapyMedicineBean> medicines = new ArrayList<>();

                    //Recupero i medicinali dalla request e li aggiungo a medicines
                    int medicinesNumber = Integer.parseInt(request.getParameter("medicinesNumber"));
                    String currentMedicineId, currentMedicineDose;
                    for(int i = 0; i < medicinesNumber; i++) {
                        currentMedicineId = "medicineId" + i;
                        currentMedicineDose = "medicineDose" + i;
                        TherapyMedicineBean medicine = new TherapyMedicineBean(request.getParameter(currentMedicineId), Integer.parseInt(request.getParameter(currentMedicineDose)));
                        medicines.add(medicine);
                    }

                    //Inserisco la terapia
                    facade.insertTherapy(sessions, medicines, duration, frequency, patientId, user);

                    //Reindirizzo alla pagina del paziente appena creato
                    response.addHeader("OPERATION_RESULT","true");
                    response.addHeader("PATIENT_ID", request.getParameter("id"));
                }

                case "editPatientProfile" -> {  //Modifica profilo paziente
                    facade.updatePatient("_id", request.getParameter("id"), "condition", request.getParameter("condition"), user);
                    facade.updatePatient("_id", request.getParameter("id"), "therapy", request.getParameter("therapy"), user);
                    facade.updatePatient("_id", request.getParameter("id"), "notes", request.getParameter("notes"), user);


                    //Reindirizzo alla pagina del paziente appena creato
                    response.addHeader("OPERATION_RESULT","true");
                    response.addHeader("PATIENT_ID", request.getParameter("id"));
                }

                case "editPatientStatus" -> {
                    String operationResult = "false";
                    String patientId = request.getParameter("id");
                    boolean patientStatus = Boolean.parseBoolean(request.getParameter("status"));

                    System.out.println("Edit status: id=" + patientId);

                    ArrayList<PatientBean> patients = facade.findPatients("_id", patientId, user);
                    //controlla se esiste una condition e una terapia per quel paziente
                    if (patients.get(0).getTherapy() == null) {
                        //se non esiste c'è un errore e l'operazione fallisce
                        operationResult = "false";
                    } else {
                        //se esiste viene effettuata la modifica
                        operationResult = "true";
                        if (patients.get(0).getStatus() != patientStatus) {
                            //Aggiorno lo stato del paziente
                            facade.updatePatient("_id", patientId, "status", patientStatus, user);
                        }
                    }

                    System.out.println("Edit status: id=" + patientId + " result=" + operationResult);

                    //Reindirizzo alla pagina del paziente appena creato
                    response.addHeader("OPERATION_RESULT",operationResult);
                }

                case "searchPatient" -> {
                    //Recupero i pazienti
                    ArrayList<PatientBean> patients = facade.findPatients("name", request.getParameter("name"), user);

                    if(patients.size() == 1) {
                        //Aggiungo il parametro alla request
                        request.setAttribute("patientsResult", patients.get(0));

                        response.sendRedirect("PatientServlet?id=" + patients.get(0).getPatientId());
                    }
                    else {
                        //Aggiungo il parametro alla request
                        request.setAttribute("patientsResult", patients);

                        //Reindirizzo alla pagina del paziente appena trovato
                        RequestDispatcher requestDispatcher = request.getRequestDispatcher("patientList.jsp");
                        requestDispatcher.forward(request, response);
                    }
                }

                case "viewPatientList" -> {
                    //Recupero i pazienti
                    ArrayList<PatientBean> patients = facade.findAllPatients(user);

                    //####################################################################################################################################################
                    //## Va testato se mettendo dei filtri vuoti nel metodo findPatient si comporta come una findAll, se così allora si può rimuovere il metodo findAll ##
                    //## Da testare: ArrayList<PatientBean> patients = facade.findPatients("", "", user);                                                               ##
                    //####################################################################################################################################################

                    //Aggiungo il parametro alla request
                    request.setAttribute("patientsResult", patients);

                    //Reindirizzo alla pagina del paziente appena trovato
                    response.sendRedirect(""); //todo: aggiungere jsp una volta creata
                }

                case "viewAvailablePatients" -> {
                    //Recupero i pazienti
                    ArrayList<PatientBean> patients = facade.findPatients("status", "true", user);

                    //Aggiungo il parametro alla request
                    request.setAttribute("patientsResult", patients);

                    //Reindirizzo alla pagina del paziente appena trovato
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
