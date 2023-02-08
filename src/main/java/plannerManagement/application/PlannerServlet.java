package plannerManagement.application;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import connector.CmdExec;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/PlannerServlet")
public class PlannerServlet extends HttpServlet {

    private static Facade facade = new Facade();

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
                    System.out.println("non ho minimamente idea di come si faccia");

                    //Recupero la lista degli id dei pazienti restituita dal modulo
                    List<String> patientIds = null;
                    try {
                        //Creo il file reader
                        Reader reader = Files.newBufferedReader(Path.of("D:\\Chemo\\py\\patients.json"));

                        //Converto l'array di JSON in una lista di String
                        patientIds = gson.fromJson(reader, new TypeToken<List<String>>(){}.getType());

                        //Chiudo il reader
                        reader.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    //Popolo la lista di appuntamente
                    //tutte le istanze di Date() sono placeholder per ora
                    ArrayList<AppointmentBean> appointments = new ArrayList<>();
                    for(int i = 0; i < patientIds.size(); i++) {
                        Date date = new Date();
                        int seat = 3;
                        appointments.add(new AppointmentBean(patientIds.get(0), date, String.valueOf(seat)));
                    }

                    //Creo l'istanza del planner settimanale e la aggiungo al database
                    PlannerBean planner = new PlannerBean("id", new Date(), new Date(), appointments);
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
