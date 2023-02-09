package queryBean;

import medicinemanagement.application.MedicineBean;
import medicinemanagement.application.PackageBean;
import medicinemanagement.storage.MedicineQueryBean;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TC_AddNewMedicine {
    @Mock
    MedicineBean medicineBean;
    @InjectMocks
    MedicineQueryBean medicineQueryBean;

    @Before
    public final void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    //TC_UC_PM_03_6
    @Test
    public void testAddCorrectMedicine(){
        ObjectId id = new ObjectId();
        when(medicineBean.getId()).thenReturn(String.valueOf(id));
        when(medicineBean.getName()).thenReturn("Dexorubicina");
        when(medicineBean.getIngredients()).thenReturn("Doxorubicina cloridrato");
        when(medicineBean.getAmount()).thenReturn(5);
        when(medicineBean.getPackages()).thenReturn(null);
        assertEquals(true, medicineQueryBean.insertDocument(medicineBean));
        medicineQueryBean.deleteDocument("_id", String.valueOf(id));
    }

}
