package medicinemanagement.application;

import connector.Facade;
import patientmanagement.application.TherapyBean;
import patientmanagement.application.TherapyMedicineBean;
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
import java.util.ArrayList;
import java.util.Date;

@WebServlet("/MedicineServlet")
public class MedicineServlet extends HttpServlet {
    private static final Facade facade = new Facade();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Recupero dalla request il parametro id del medicinale
        String id = request.getParameter("id");

        if (id != null) {
            //Recupero l'utente dalla sessione
            UserBean user = (UserBean) request.getSession().getAttribute("currentSessionUser");

            //Cerco il medicinale richiesto
            ArrayList<MedicineBean> medicine = facade.findMedicines("_id", id, user);

            //Imposto i dati del paziente come attributo
            request.setAttribute("medicine", medicine.get(0));

            //Reindirizzo alla pagina del paziente
            getServletContext().getRequestDispatcher(response.encodeURL(response.encodeURL("/medicineDetails.jsp"))).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Recupero dalla request il parametro action
        String action = request.getParameter("action");

        //Recupero l'utente dalla sessione
        UserBean user = (UserBean) request.getSession().getAttribute("currentSessionUser");

        try {
            switch (action) {
                case "insertMedicine" -> { //Inserimento medicinale
                    //Inserisco il medicinale
                    MedicineBean medicine = new MedicineBean(request.getParameter("name"), request.getParameter("ingredients"));

                    //Controllo di validità
                    if(!(medicineValidation(medicine))) {
                        response.addHeader("OPERATION_RESULT","false");
                        request.setAttribute("errorMessage", "I dati inseriti non sono validi");
                    } else {
                        facade.insertMedicine(medicine, user);

                        //Salvo l'id del medicinale nell'header della response, così da poterlo reindirizzare alla sua pagina
                        response.addHeader("OPERATION_RESULT","true");
                        response.addHeader("MEDICINE_ID", medicine.getId());
                    }
                }

                case "insertPackage" -> { //Inserimento confezione medicinale
                    //Recupero i parametri dalla request
                    String medicineId = request.getParameter("medicineId");
                    int capacity = Integer.parseInt(request.getParameter("capacity"));
                    Date expiryDate = dateParser(request.getParameter("expiryDate"));

                    //Creo una confezione
                    PackageBean medicinePackage = new PackageBean(true, expiryDate, capacity, "");

                    //Controllo validazione
                    if (!packageValidation(medicinePackage)) {
                        response.addHeader("OPERATION_RESULT","false");
                        request.setAttribute("errorMessage", "I dati inseriti non sono validi");
                    } else {
                        // Inserisco la confezione nel medicinale
                        facade.insertMedicinePackage(medicineId, medicinePackage, user);

                        response.addHeader("OPERATION_RESULT","true");
                    }
                }

                case "searchMedicine" -> { //Ricerca medicinale
                    //Recupero i medicinali
                    ArrayList<MedicineBean> medicines = facade.findMedicines("name", request.getParameter("name"), user);

                    if(medicines.size() == 1) { //Un solo medicinale trovato
                        //Aggiungo il parametro alla request
                        request.setAttribute("medicineResults", medicines.get(0));

                        //Reindirizzo alla pagina paziente
                        response.sendRedirect("MedicineServlet?id=" + medicines.get(0).getId());
                    } else { //Più medicinali trovati
                        //Aggiungo il parametro alla request
                        request.setAttribute("medicineResults", medicines);

                        //Mando la richiesta con il dispatcher
                        RequestDispatcher requestDispatcher = request.getRequestDispatcher("medicinesList.jsp");
                        requestDispatcher.forward(request, response);
                    }
                }

                case "findAllMedicines" -> {
                    //Recupero i medicinali
                    ArrayList<MedicineBean> medicines = facade.findAllMedicines(user);

                    //Aggiungo lista di medicinali e numero di medicinali alla response
                    response.addHeader("medicineNumber", String.valueOf(medicines.size()));
                    for (int i = 0; i < medicines.size(); i++) {
                        response.addHeader("medicineId" + i, medicines.get(i).getId());
                        response.addHeader("medicineName" + i, medicines.get(i).getName());
                    }

                    //Aggiungo l'header
                    response.addHeader("OPERATION_RESULT","true");
                }
            }
        }
        catch (Throwable e) {
            System.out.println(e.getMessage());
        }

    }

    //Metodi di supporto
    private Date dateParser(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(date);
        }
        catch (Exception e) {
            return null;
        }
    }

    private boolean medicineValidation(MedicineBean medicine) {
        if (!nameValidity(medicine.getName())) {
            return false;
        }
        if (!ingredientsValidity(medicine.getIngredients())) {
            return false;
        }
        return true;
    }
    private boolean packageValidation(PackageBean medicinePackage) {
        if (!capacityValidity(medicinePackage.getCapacity())) {
            return false;
        }
        if (!dateValidity(medicinePackage.getParsedExpiryDate())) {
            return false;
        }
        return true;
    }
    private boolean numberValidity(String notes) {
        String format = "^[0-9]+$";
        return notes.matches(format);
    }
    private boolean dateValidity(String date) {
        String format = "^(19|20)[0-9]{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$";
        return date.matches(format);
    }
    private boolean nameValidity(String name) {
        String format = "^[A-Za-z][A-Za-z'-]+([ A-Za-z][A-Za-z'-]+)*$";
        return name.matches(format);
    }
    private boolean ingredientsValidity(String ingredients) {
        String format = "^[A-Za-z0-9][A-Za-z0-9'\\-]+([ A-Za-z0-9][A-Za-z0-9'-]+)*$";
        return ingredients.matches(format);
    }
    private boolean capacityValidity(Integer capacity) {
        return numberValidity(String.valueOf(capacity));
    }
}
