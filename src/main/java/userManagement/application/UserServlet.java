package userManagement.application;

import connector.Facade;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    static Facade facade = new Facade();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try{
            if(action != null){
                if(action.equalsIgnoreCase("login")){
                    //Recupero dal form username e password
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");

                    //Effettuo il check se l'utente è presente nel db o meno
                    if(isUserValid(username, password)){
                        ArrayList<UserBean> users = facade.findUsers("username", username);
                        UserBean user = users.get(0);
                        if (user != null) {
                            HttpSession session = request.getSession(true);
                            session.setAttribute("currentSessionUser",user);
                            if(user.getType() == 1){
                                response.sendRedirect(""); //jsp accesso personale medico
                            }else if(user.getType() == 2)
                                response.sendRedirect(""); //jsp accesso gestore medicinali
                        }
                    }else
                        response.sendRedirect(""); //jsp di errore?
                }else if(action.equalsIgnoreCase("logout")){
                    request.getSession().removeAttribute("currentSessionUser");
                    request.getSession().invalidate();

                    response.sendRedirect(request.getContextPath() + "/index.jsp");
                }else if(action.equalsIgnoreCase("viewProfile")){
                    /*Questa parte sella servlet indirizza semplicemente l'utente alla jsp di visualizzazione profilo
                    dove sarà recuperato il currentSessionUser e, quindi, tutti i suoi dati
                    */
                    HttpSession session = request.getSession(true);
                    UserBean user = (UserBean) request.getSession().getAttribute("currentSessionUser");
                    session.setAttribute("currentSessionUser", user);

                    response.sendRedirect(""); //jsp visualizzazione informazioni utente
                }
            }
        }catch(Throwable e){
            System.out.println(e.getMessage());
        }
    }

    /*
    Il metodo recupera dal db le istanze di 'utente' in cui appare 'username': se username e password sono
    entrambi corretti, allora il metodo restituisce true, perchÃ¨ l'utente ha accesso al sito
     */
    private boolean isUserValid(String username, String password) throws Exception{
        Facade facade = new Facade();
        ArrayList<UserBean> users = facade.findUsers("username", username);
        boolean valid = false;
        for(UserBean us : users){
            if(us.getUsername().equals(username) && us.getPassword().equals(password))
                valid = true;
        }

        return valid;
    }
}
