package medicinemanagement.application;

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

@WebServlet("/MedicineServlet")
public class MedicineServlet extends HttpServlet {
    private static final Facade facade = new Facade();

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
                    facade.insertMedicine(request.getParameter("id"), request.getParameter("name"), request.getParameter("ingredients"), Integer.parseInt(request.getParameter("amount")), user);

                    //Reindirizzo alla pagina lista dei medicinali
                    response.sendRedirect(""); //todo: aggiungere jsp una volta creata
                }

                case "insertMedicineBox" -> { //Inserimento confezione medicinale
                    //Inserisco il box
                    facade.insertMedicineBox(request.getParameter("id"), request.getParameter("BoxId"), Boolean.parseBoolean(request.getParameter("status")), dateParser(request.getParameter("expiryDate")), Integer.parseInt(request.getParameter("capacity")), user);

                    //Reindirizzo alla pagina lista dei medicinali
                    response.sendRedirect(""); //todo: aggiungere jsp una volta creata
                }

                case "deleteMedicineBox" -> {
                    //Rimuovo il box
                    facade.removeMedicineBox(request.getParameter("boxId"), user);

                    //Reindirizzo alla pagina lista dei medicinali
                    response.sendRedirect(""); //todo: aggiungere jsp una volta creata
                }

                case "searchMedicine" -> { //Ricerca medicinale
                    //Recupero i medicinali
                    ArrayList<MedicineBean> medicines = facade.findMedicines("name", request.getParameter("name"));

                    if(medicines.size() == 1) { //Un solo medicinale trovato
                        //Aggiungo il parametro alla request
                        request.setAttribute("medicineResults", medicines.get(0));

                        //Reindirizzo alla pagina del singolo medicinale
                        response.sendRedirect(""); //todo: aggiungere jsp una volta creata
                    } else { //Più medicinali trovati
                        //Aggiungo il parametro alla request
                        request.setAttribute("medicineResults", medicines);

                        //Reindirizzo alla pagina della lista medicinali
                        response.sendRedirect(""); //todo: aggiungere jsp una volta creata
                    }
                }

                case "viewMedicineList" -> { //Visualizza lista medicinali
                    //Recupero i medicinali
                    ArrayList<MedicineBean> medicines = facade.findMedicines("", request.getParameter(""));

                    //Aggiungo il parametro alla request
                    request.setAttribute("medicinesResults", medicines);

                    //Reindirizzo alla pagina della lista medicinali
                    response.sendRedirect(""); //todo: aggiungere jsp una volta creata
                }

                case "viewAvailableMedicinesList" -> { //Visualizza lista medicinali disponibili
                    //Recupero i medicinali
                    ArrayList<MedicineBean> medicines = facade.findMedicines("status", "true");

                    //Aggiungo il parametro alla request
                    request.setAttribute("medicinesResults", medicines);

                    //Reindirizzo alla pagina della lista medicinali
                    response.sendRedirect(""); //todo: aggiungere jsp una volta creata
                }

                case "viewMedicinePage" -> { //Visualizza pagina medicinale
                    //Recupero il medicinale
                    ArrayList<MedicineBean> medicines = facade.findMedicines("", request.getParameter(""));

                    //Aggiungo il parametro alla request
                    request.setAttribute("medicinesResults", medicines.get(0));

                    //Reindirizzo alla pagina della lista medicinali
                    response.sendRedirect(""); //todo: aggiungere jsp una volta creata
                }
            }
        }
        catch (Throwable e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
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
