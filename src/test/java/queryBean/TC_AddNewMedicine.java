package queryBean;

import medicinemanagement.application.BoxBean;
import medicinemanagement.application.MedicineBean;
import medicinemanagement.storage.MedicineQueryBean;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TC_AddNewMedicine {
    @Mock
    BoxBean boxBean;
    @Mock
    MedicineBean medicineBean;
    @Mock
    ArrayList<BoxBean> box;
    @InjectMocks
    MedicineQueryBean medicineQueryBean;

    @Before
    public final void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddCorrectMedicine(){
        boxBean = new BoxBean();
        box = new ArrayList<>();
        box.add(boxBean);
        ObjectId id = new ObjectId();
        when(medicineBean.getId()).thenReturn(String.valueOf(id));
        when(medicineBean.getName()).thenReturn("Dexorubicina");
        when(medicineBean.getIngredients()).thenReturn("Dexorubicina cloridrato");
        when(medicineBean.getAmount()).thenReturn(10);
        when(medicineBean.getBox()).thenReturn(box);
        assertEquals(true, medicineQueryBean.insertDocument(medicineBean));
        medicineQueryBean.deleteDocument("_id", String.valueOf(id));
    }

    //TC_SC_PM_03_06
    @Test
    public void testAddMedicineWithIncorrectName(){
        boxBean = new BoxBean();
        ObjectId id = new ObjectId();
        box = new ArrayList<>();
        box.add(boxBean);
        when(medicineBean.getId()).thenReturn(String.valueOf(id));
        when(medicineBean.getName()).thenReturn("");
        when(medicineBean.getIngredients()).thenReturn("Dexorubicina cloridrato");
        when(medicineBean.getAmount()).thenReturn(10);
        when(medicineBean.getBox()).thenReturn(box);
        assertEquals(true, medicineQueryBean.insertDocument(medicineBean));
        medicineQueryBean.deleteDocument("_id", String.valueOf(id));
    }

    @Test
    public void testAddMedicineWithIncorrectIngredients(){
        boxBean = new BoxBean();
        ObjectId id = new ObjectId();
        box = new ArrayList<>();
        box.add(boxBean);
        when(medicineBean.getId()).thenReturn(String.valueOf(id));
        when(medicineBean.getName()).thenReturn("Dexorubicina");
        when(medicineBean.getIngredients()).thenReturn("Dexorubicina cloridrato poliaccopiato disintegrato in piccolissime molecole di medicinale che non si vedono neanche con il microscopio");
        when(medicineBean.getAmount()).thenReturn(0);
        when(medicineBean.getBox()).thenReturn(box);
        assertEquals(false, medicineQueryBean.insertDocument(medicineBean));
        medicineQueryBean.deleteDocument("_id", String.valueOf(id));
    }
}
