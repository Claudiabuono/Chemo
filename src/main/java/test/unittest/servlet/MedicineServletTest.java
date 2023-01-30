package test.unittest.servlet;

import connector.Facade;
import medicinemanagement.application.MedicineServlet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import userManagement.application.UserBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MedicineServletTest {
    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    HttpSession session;

    @InjectMocks
    MedicineServlet servlet;

    @Mock
    UserBean user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    //TC_SC_PM_03_06
    //Oracolo: corretto
    @Test
    public void insertTest6() throws Exception{
        //Recupero la sessione
        when(request.getSession()).thenReturn(session);

        //Reindirizzo al metodo della servlet
        when(request.getParameter("action")).thenReturn("insertMedicine");

        //Recupero i parametri del medicinale
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("name")).thenReturn("test");
        when(request.getParameter("ingredients")).thenReturn("test,si");
        when(request.getParameter("amount")).thenReturn(String.valueOf(100));
        when(request.getSession().getAttribute("currentSessionUser")).thenReturn(user);

        //Chiamo il metodo della servlet
        servlet.doGet(request,response);


        //Verifico la corrispondenza con l'oracolo
        ArgumentCaptor<String> captor= ArgumentCaptor.forClass(String.class);
        verify(response).encodeURL(captor.capture());
        assertEquals("medicineDetails.jsp",captor.getValue());
    }

    //TC_SC_PM_03_06
    //Oracolo: lunghezza nome non valida
    @Test
    public void insertTest1() throws Exception{
        //Recupero la sessione
        when(request.getSession()).thenReturn(session);

        //Reindirizzo al metodo della servlet
        when(request.getParameter("action")).thenReturn("insertMedicine");

        //Recupero i parametri del medicinale
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("name")).thenReturn("test");
        when(request.getParameter("ingredients")).thenReturn("test,si");
        when(request.getParameter("amount")).thenReturn(String.valueOf(100));
        when(request.getSession().getAttribute("currentSessionUser")).thenReturn(user);


        //Chiamo il metodo della servlet
        servlet.doGet(request,response);


        //Verifico la corrispondenza con l'oracolo
        ArgumentCaptor<String> captor= ArgumentCaptor.forClass(String.class);
        verify(response).encodeURL(captor.capture());
        assertEquals("medicineDetails.jsp",captor.getValue());
    }
}
