package plannerManagement.application;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import connector.Facade;
import patientmanagement.application.PatientBean;
import userManagement.application.UserBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@WebServlet("/PlannerServlet")
public class PlannerServlet extends HttpServlet {

    private static Facade facade = new Facade();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Recupero l'utente dalla sessione
        UserBean user = (UserBean) request.getSession().getAttribute("currentSessionUser");

        //Recupero l'id dalla request
        String id = request.getParameter("id");

        ArrayList<PlannerBean> planners = facade.findAllPlanners(user);
        PlannerBean plannerToVisualize;
        String beforeVisualizedId, lastestPlannerId, afterVisualizedId;

        if(id == null) {
            plannerToVisualize = planners.get(planners.size()-1);

            int beforeLastestIndex = planners.indexOf(plannerToVisualize)-1;
            beforeVisualizedId = planners.get(beforeLastestIndex).getId();
            lastestPlannerId = plannerToVisualize.getId();
            afterVisualizedId = "";
        } else {
            plannerToVisualize = facade.findPlanners("_id", id, user).get(0);
            PlannerBean lastestPlanner = planners.get(planners.size()-1);
            lastestPlannerId = lastestPlanner.getId();

            if(plannerToVisualize.equals(lastestPlanner)) {
                int beforeLastestIndex = planners.indexOf(plannerToVisualize)-1;
                beforeVisualizedId = planners.get(beforeLastestIndex).getId();
                afterVisualizedId = "";
            } else if(plannerToVisualize.equals(planners.get(0))) {
                beforeVisualizedId = "";
                afterVisualizedId = planners.get(1).getId();
            } else {
                int beforeVisualizedIndex = planners.indexOf(plannerToVisualize) -1;
                int afterVisualizedIndex = planners.indexOf(plannerToVisualize) +1;
                beforeVisualizedId = planners.get(beforeVisualizedIndex).getId();
                afterVisualizedId = planners.get(afterVisualizedIndex).getId();
            }

            //Imposto i dati nella request
            request.setAttribute("plannerToVisualize", plannerToVisualize);
            request.setAttribute("lastestPlannerId", lastestPlannerId);
            request.setAttribute("beforeVisualizedId", beforeVisualizedId);
            request.setAttribute("afterVisualizedId", afterVisualizedId);

            //Reindirizzo alla pagina del paziente
            getServletContext().getRequestDispatcher(response.encodeURL(response.encodeURL("/planner.jsp"))).forward(request, response);

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
                case "createPlanner" -> {
                    //Inizializzo GSON
                    Gson gson = new Gson();

                    //Recupero il numero di pazienti dalla request
                    int patientNumber = Integer.parseInt(request.getParameter("patientNumber"));

                    //Inserisco ogni paziente nell'array di PatientJSON
                    ArrayList<PatientJSON> patientsJson = new ArrayList<>();
                    for(int i = 0; i < patientNumber; i++) {
                        //Recupero l'id paziente
                        String patientId = request.getParameter("patientId" + i);

                        //Recupero il paziente corrispondente
                        PatientBean patient = facade.findPatients("_id", patientId, user).get(0);

                        //Inserisco i dati del paziente in PatientJSON
                        patientId = patient.getPatientId();
                        String medicineId = patient.getTherapy().getMedicines().get(0).getMedicineId(); //Assumiamo 1 medicinale per seduta, prendo il primo a scopo di esempio
                        int dose = patient.getTherapy().getMedicines().get(0).getDose();
                        PatientJSON patientJSON = new PatientJSON(patientId, medicineId, dose);

                        //Aggiungo il paziente all'array di pazientiJSON
                        patientsJson.add(patientJSON);
                    }

                    //Scrivo il contenuto nel file JSON
                    gson.toJson(patientsJson, new FileWriter("D:\\Chemo\\py\\patients.json"));

                    //Faccio eseguire il processo del modulo di IA
                    String path = Paths.get(System.getProperty("user.dir"), "py", "module.py").toString();
                    Process process = Runtime.getRuntime().exec(new String[]{"py", path});

                    //Recupero la lista degli id dei pazienti restituita dal modulo
                    List<String> patientIds = null;
                    try {
                        //Creo il file reader
                        Reader reader = Files.newBufferedReader(Path.of("D:\\Chemo\\py\\resultSchedule.json"));

                        //Converto l'array di JSON in una lista di String
                        patientIds = gson.fromJson(reader, new TypeToken<List<String>>(){}.getType());

                        //Chiudo il reader
                        reader.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    //Popolo la lista di appuntamenti
                    Calendar calendar = Calendar.getInstance();
                    ArrayList<AppointmentBean> appointments = new ArrayList<>();
                    for(int i = 0; i < patientIds.size(); i++) {
                        Date date = new Date(); //Placeholder data dell'appuntamento
                        int seat = 3;
                        appointments.add(new AppointmentBean(patientIds.get(0), date, String.valueOf(seat)));
                    }

                    //Recupero primo e ultimo giorno della settimana
                    calendar.set(Calendar.DAY_OF_WEEK, calendar.getActualMinimum(Calendar.DAY_OF_WEEK));
                    Date firstDay = calendar.getTime();
                    calendar.set(Calendar.DAY_OF_WEEK, calendar.getActualMaximum(Calendar.DAY_OF_WEEK));
                    Date lastDay = calendar.getTime();

                    //Creo l'istanza del planner settimanale e la aggiungo al database
                    PlannerBean planner = new PlannerBean("id", firstDay, lastDay, appointments);
                    facade.insertPlanner(planner, user);

                    //Aggiungo la lista di id alla request
                    request.setAttribute("patientIds", patientIds);

                    //Mando la richiesta con il dispatcher
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("planner.jsp");
                    requestDispatcher.forward(request, response);
                }

                case "deletePlanner" -> {
                    System.out.println("WIP");
                }
            }
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }

    }


    //Inner Class per scrivere tutto l'array in patients.json, contiene solo i dati necessari al file
    private static class PatientJSON {
        String patientId;
        String medicineId;
        int dose;

        //Costruttori

        public PatientJSON() {
        }

        public PatientJSON(String patientId, String medicineId, int dose) {
            this.patientId = patientId;
            this.medicineId = medicineId;
            this.dose = dose;
        }

        //Getters

        public String getPatientId() {
            return patientId;
        }

        public String getMedicineId() {
            return medicineId;
        }

        public int getDose() {
            return dose;
        }
    }
}
