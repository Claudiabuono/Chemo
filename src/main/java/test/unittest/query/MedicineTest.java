package test.unittest.query;

import connector.Facade;
import medicinemanagement.application.MedicineBean;
import medicinemanagement.storage.MedicineQueryBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MedicineTest {
    MedicineQueryBean query;
    Facade facade;
    MedicineBean medicine;

    @BeforeEach
    void setUp() {
        query = new MedicineQueryBean();
        facade = new Facade();
    }

    private MedicineBean getMedicine() {
        return new MedicineBean("1", "test", "test, test", 10, null);
    }

    @Test
    void insertMedicine() {
        MedicineBean medicine = getMedicine();
        boolean flag = facade.insertMedicine("1", "test", "test,test", 10, null);
        assertTrue(flag);
    }
}
