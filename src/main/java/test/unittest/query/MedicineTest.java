package test.unittest.query;

import connector.Facade;
import medicinemanagement.application.MedicineBean;
import medicinemanagement.storage.MedicineQueryBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import userManagement.application.UserBean;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MedicineTest {
    MedicineQueryBean query;
    Facade facade;
    MedicineBean medicine;
    UserBean user;

    @BeforeEach
    void setUp() {
        query = new MedicineQueryBean();
        facade = new Facade();
        //String id, String name, String surname, Date birthDate, String birthplace, String username, String password, String specialization, int type
        user = new UserBean("2","Carla", "Bianchi", new Date(), "Agropoli", "CarlaBianchi", "CarlaBianchi", "", 2);
    }

    private MedicineBean getMedicine() {
        return new MedicineBean("1", "test", "test, test", 10, null);
    }

    @Test
    void insertMedicine() {
        MedicineBean medicine = getMedicine();
        boolean flag = facade.insertMedicine("1", "test", "test,test", 10, user);
        assertTrue(flag);
    }
}
