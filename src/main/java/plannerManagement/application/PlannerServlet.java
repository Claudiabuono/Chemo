package plannerManagement.application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Format;
import java.text.SimpleDateFormat;
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
            request.setAttribute("latestPlannerId", lastestPlannerId);
            request.setAttribute("beforeVisualizedId", beforeVisualizedId);
            request.setAttribute("afterVisualizedId", afterVisualizedId);
            request.setAttribute("weekDate", getWeekDate(plannerToVisualize.getStartDate(), plannerToVisualize.getEndDate()));

            //Reindirizzo alla pagina del calendario
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
                case "addAppointments" -> {
                    //Recupero il numero di pazienti dalla request
                    int patientNumber = Integer.parseInt(request.getParameter("patientsNumber"));

                    System.out.println("PatientNumber: " +patientNumber);

                    //Inserisco ogni paziente nell'array di PatientJSON
                    List<PatientJSON> patientsJson = new ArrayList<>();
                    for(int i = 0; i < patientNumber; i++) {
                        //Recupero l'id paziente
                        String patientId = request.getParameter("patient" + i);

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

//                    System.out.println("PatientsJSON:");
//                    System.out.println(patientsJson);
//                    System.out.println("-------------");

                    //Creazione file della directory
                    String dirName = "D:\\Chemo\\py\\";
                    File dir = new File(dirName);

                    //Recupero il file di input
                    String inputFileName = "patients.json";
                    File inputFile = new File(dir, inputFileName);

                    //Inizializzo GSON
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    String json = gson.toJson(patientsJson, new TypeToken<List<PatientJSON>>(){}.getType());

                    //Scrivo nel file JSON
                    try (FileWriter writer = new FileWriter(inputFile)) {
                        writer.append(json);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    //Faccio eseguire il processo del modulo di IA
                    String pythonFileName = "module.py";
                    String pythonFilePath = "D:\\Chemo\\py\\module.py";
                    Runtime.getRuntime().exec(new String[]{"py", pythonFilePath});
                    //String path = Paths.get(System.getProperty("user.dir"), "py", "module.py").toString();
                    //Process process = Runtime.getRuntime().exec(new String[]{"py", path});

                    //Recupero la lista degli id dei pazienti restituita dal modulo
                    List<String> patientIds = null;

                    //Recupero il file di output
                    String outputFileName = "resultSchedule.json";
                    File outputFile = new File(dir, outputFileName);

                    //Apro il file JSON contenente i risultati del modulo di IA
                    try (FileReader reader = new FileReader(outputFile)){

                        //Converto l'array di JSON in una lista di String
                        patientIds = gson.fromJson(reader, new TypeToken<List<String>>(){}.getType());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    //Popolo la lista di appuntamenti
                    Calendar calendar = Calendar.getInstance();
                    ArrayList<AppointmentBean> appointments = new ArrayList<>();
                    for (String patientId : patientIds) {
                        Date date = new Date(); //Placeholder data dell'appuntamento
                        int seat = 3;
                        appointments.add(new AppointmentBean(patientId, date, String.valueOf(seat), 1));
                    }
                    System.out.println(appointments);

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
            e.printStackTrace();
        }

    }

    private String getWeekDate(Date startDate, Date endDate){
        //Formattazione della data da visualizzare nel calendario
        Format formatterMonth = new SimpleDateFormat("MM");
        String startMonth = formatterMonth.format(startDate);
        String endMonth = formatterMonth.format(endDate);

        //Aggiunta primo mese
        String date = convertMonth(startMonth);

        if (!startMonth.equals(endMonth)) {
            //Aggiunta secondo mese
            date += "- ";
            date += convertMonth(startMonth);
        }
        //Aggiunta anno
        Format formatterYear = new SimpleDateFormat("yyyy");
        date += formatterYear.format(startDate);

        return date;
    }

    private String convertMonth(String month){
        switch (month) {
            case "01" -> {
                return "Gennaio ";
            }
            case "02" -> {
                return "Febbraio ";
            }
            case "03" -> {
                return "Marzo ";
            }
            case "04" -> {
                return "Aprile ";
            }
            case "05" -> {
                return "Maggio ";
            }
            case "06" -> {
                return "Giugno ";
            }
            case "07" -> {
                return "Luglio ";
            }
            case "08" -> {
                return "Agosto ";
            }
            case "09" -> {
                return "Settembre ";
            }
            case "10" -> {
                return "Ottobre ";
            }
            case "11" -> {
                return "Novembre ";
            }
            case "12" -> {
                return "Dicembre ";
            }
        }
        return null;
    }


    //Inner Class che contiene i dati da passare al JSON
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

        @Override
        public String toString() {
            return "PatientJSON{" +
                    "patientId='" + patientId + '\'' +
                    ", medicineId='" + medicineId + '\'' +
                    ", dose=" + dose +
                    '}';
        }
    }
}
