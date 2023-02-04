package medicinemanagement.application;

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

@WebServlet("/MedicineServlet")
public class MedicineServlet extends HttpServlet {
    private static final Facade facade = new Facade();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Recupero l'action dalla request
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
        //Recupero l'action dalla request
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

                case "insertMedicinePackage" -> { //Inserimento confezione medicinale
                    //Inserisco il box
                    facade.insertMedicinePackage(request.getParameter("id"), request.getParameter("BoxId"), Boolean.parseBoolean(request.getParameter("status")), dateParser(request.getParameter("expiryDate")), Integer.parseInt(request.getParameter("capacity")), user);

                    //Reindirizzo alla pagina lista dei medicinali
                    response.sendRedirect(""); //todo: aggiungere jsp una volta creata
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
        return true;
    }
}
