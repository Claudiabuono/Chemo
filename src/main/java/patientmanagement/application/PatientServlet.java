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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

@WebServlet("/PatientServlet")
public class PatientServlet extends HttpServlet {
    static Facade facade = new Facade();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Recupero l'id dalla request
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
                    PatientBean patient = new PatientBean(
                            request.getParameter("taxCode"),
                            request.getParameter("name"),
                            request.getParameter("surname"),
                            dateParser(request.getParameter("birthDate")),
                            request.getParameter("city"),
                            request.getParameter("phoneNumber"),
                            false ,
                            request.getParameter("notes"));

                    if (!patientValidation(patient)) {
                        response.addHeader("OPERATION_RESULT","false");
                        request.setAttribute("errorMessage","I dati inseriti non sono validi.");
                    } else {
                        patient = facade.insertPatient(patient,user);

                        //salvataggio id paziente nel response header così da poterlo reindirizzare alla sua pagina
                        response.addHeader("OPERATION_RESULT","true");
                        response.addHeader("PATIENT_ID", patient.getPatientId());
                    }
                }

                case "completePatientProfile" -> {  //Completamento profilo paziente e modifica terapia
                    //Recupero l'id del paziente
                    String patientId = request.getParameter("id");

                    //Recupero i dati della terapia dalla request
                    String condition = request.getParameter("condition");
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

                    TherapyBean therapy = new TherapyBean(
                            sessions,
                            medicines,
                            duration,
                            frequency
                    );

                    if (!therapyValidation(condition, therapy)) {
                        response.addHeader("OPERATION_RESULT","false");
                        request.setAttribute("errorMessage","I dati inseriti non sono validi.");
                    } else {
                        //Aggiorno il profilo paziente con la malattia
                        facade.updatePatient("_id", patientId, "condition", request.getParameter("condition"), user);
                        //Inserisco la terapia
                        facade.insertTherapy(patientId, therapy, user);

                        //Reindirizzo alla pagina del paziente appena creato
                        response.addHeader("OPERATION_RESULT","true");
                        response.addHeader("PATIENT_ID", request.getParameter("id"));
                    }
                }

                case "editPatientStatus" -> { //Modifica stato paziente
                    String operationResult;
                    String patientId = request.getParameter("id");

                    ArrayList<PatientBean> patients = facade.findPatients("_id", patientId, user);
                    //controlla se esiste una condition e una terapia per quel paziente
                    if (patients.get(0).getTherapy() == null || request.getParameter("status") == null) {
                        //se non esiste c'è un errore e l'operazione fallisce
                        operationResult = "false";
                    } else {
                        boolean patientStatus = Boolean.parseBoolean(request.getParameter("status"));
                        //se esiste viene effettuata la modifica
                        operationResult = "true";
                        if (patients.get(0).getStatus() != patientStatus) {
                            //Aggiorno lo stato del paziente
                            facade.updatePatient("_id", patientId, "status", patientStatus, user);
                        }
                    }

                    //Reindirizzo alla pagina del paziente appena creato
                    response.addHeader("OPERATION_RESULT",operationResult);
                }

                case "searchPatient" -> { //Ricerca paziente
                    //Recupero i filtri
                    ArrayList<String> keys = new ArrayList<>();
                    ArrayList<Object> values = new ArrayList<>();
                    String parameter;
                    boolean findAll = true; //booleano che ci serve per capire se non sono stati selezionati parametri nella ricerca, quindi per indicare che serve una findAll

                    //Nome
                    parameter = request.getParameter("name");
                    if(parameter != null && !(parameter.equals(""))) {
                        keys.add("name");
                        values.add(parameter);
                        findAll = false;
                    }

                    //Cognome
                    parameter = request.getParameter("surname");
                    if(parameter != null && !(parameter.equals(""))) {
                        keys.add("surname");
                        values.add(parameter);
                        findAll = false;
                    }

                    //Medicinale
                    parameter = request.getParameter("patientMedicine");
                    if(parameter != null && !(parameter.equals("null"))) {
                        keys.add("medicine");
                        values.add(parameter);
                        findAll = false;
                    }

                    //Stato
                    parameter = request.getParameter("patientStatus");
                    if(parameter != null && !(parameter.equals("na"))) {
                        keys.add("status");
                        values.add(Boolean.parseBoolean(parameter));
                        findAll = false;
                    }


                    //Creo l'ArrayList da restituire
                    ArrayList<PatientBean> patients;

                    //Se non sono stati selezionati parametri, allora dobbiamo effettuare una ricerca di tutti i pazienti
                    if(findAll)
                        patients = facade.findAllPatients(user);
                    //Altrimenti ci serve una ricerca in base ai parametri selezionati
                    else
                        patients = facade.findPatients(keys, values, user);

                    //Se ho trovato un solo paziente, allora reindirizzo alla sua pagina paziente
                    if(patients.size() == 1) {
                        //Aggiungo il parametro alla request
                        request.setAttribute("patientsResult", patients.get(0));

                        //Reindirizzo alla pagina paziente
                        response.sendRedirect("PatientServlet?id=" + patients.get(0).getPatientId());
                    }
                    else {
                        //Aggiungo il parametro alla request
                        request.setAttribute("patientsResult", patients);

                        //Mando la richiesta con il dispatcher
                        RequestDispatcher requestDispatcher = request.getRequestDispatcher("patientList.jsp");
                        requestDispatcher.forward(request, response);
                    }
                }
            }
        }
        catch (Throwable e) {
            System.out.println(e.getMessage());
        }
    }

    //Metodi di supporto
    private Date dateParser(String date) {
        SimpleDateFormat pattern = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return pattern.parse(date);
        }
        catch (Exception e) {
            return null;
        }
    }
    private String dateReverseParser(Date date) {
        DateFormat pattern = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return pattern.format(date);
        }
        catch (Exception e) {
            return null;
        }
    }

    private boolean patientValidation(PatientBean patient){
        if (!namesValidity(patient.getName())) {
            return false;
        }
        if (!namesValidity(patient.getSurname())) {
            return false;
        }
        if (!dateValidity(dateReverseParser(patient.getBirthDate()))) {
            return false;
        }
        if (!cityValidity(patient.getCity())) {
            return false;
        }
        if (!taxCodeValidity(patient.getTaxCode())) {
            return false;
        }
        if (!phoneNumberValidity(patient.getPhoneNumber())) {
            return false;
        }
        if (!notesValidity(patient.getNotes())) {
            return false;
        }
        return true;
    }
    private boolean therapyValidation(String condition, TherapyBean therapy){
        if (!conditionValidity(condition)) {
            return false;
        }
        if (!sessionsValidity(therapy.getSessions())) {
            return false;
        }
        if (!frequencyValidity(therapy.getFrequency())) {
            return false;
        }
        if (!durationValidity(therapy.getDuration())) {
            return false;
        }
        for (TherapyMedicineBean medicine: therapy.getMedicines()) {
            if (!idValidity(medicine.getMedicineId())) {
                return false;
            }
            if (!doseValidity(medicine.getDose())) {
                return false;
            }
        }

        return true;
    }
    private boolean idValidity(String id) {
        String format = "^[a-f\\d]{24}$";
        return id.matches(format);
    }
    private boolean numberValidity(String notes) {
        String format = "^[0-9]+$";
        return notes.matches(format);
    }
    private boolean dateValidity(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate newDate = LocalDate.parse(date, formatter );
        LocalDate currentDate = LocalDate.now();
        if (newDate.isAfter(currentDate))
            return false;
        String format = "^(19|20)[0-9]{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$";
        return date.matches(format);
    }
    private boolean nameValidity(String name) {
        String format = "^[A-Za-z][A-Za-z'-]+([ A-Za-z][A-Za-z'-]+)*$";
        return name.matches(format);
    }
    private boolean namesValidity(String name) {
        if (name.length() > 32)
            return false;
        return nameValidity(name);
    }
    private boolean cityValidity(String city) {
        if (city.length() > 32)
            return false;
        return nameValidity(city);
    }
    private boolean taxCodeValidity(String taxCode) {
        String format = "^[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]$";
        return taxCode.matches(format);
    }
    private boolean phoneNumberValidity(String phoneNumber) {
        String format = "^[+]?[(]?[0-9]{2,3}[)]?[-\\s.]?[0-9]{3}[-\\s.]?[0-9]{4,7}$";
        return phoneNumber.matches(format);
    }
    private boolean notesValidity(String notes) {
        if (notes.length() > 255)
            return false;
        String format = "^[A-Za-z0-9][A-Za-z0-9'.,\\n-]+([ A-Za-z0-9][A-Za-z0-9'.,\\n-]+)*$";
        return notes.matches(format);
    }
    private boolean conditionValidity(String condition) {
        return nameValidity(condition);
    }
    private boolean sessionsValidity(Integer sessions) {
        return numberValidity(String.valueOf(sessions));
    }
    private boolean frequencyValidity(Integer frequency) {
        return numberValidity(String.valueOf(frequency));
    }
    private boolean durationValidity(Integer duration) {
        return numberValidity(String.valueOf(duration));
    }
    private boolean doseValidity(Integer dose) {
        return numberValidity(String.valueOf(dose));
    }
}
