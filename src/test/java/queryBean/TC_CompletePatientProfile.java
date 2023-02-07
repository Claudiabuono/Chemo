package queryBean;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import patientmanagement.application.PatientBean;
import patientmanagement.application.TherapyBean;
import patientmanagement.application.TherapyMedicineBean;
import patientmanagement.storage.PatientQueryBean;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TC_CompletePatientProfile {
    @Mock
    TherapyMedicineBean therapyMedicineBean;
    @Mock
    ArrayList<TherapyMedicineBean> meds;
    @Mock
    TherapyBean therapyBean;
    @Mock
    PatientBean patientBean;

    @InjectMocks
    PatientQueryBean patientQueryBean;


    @Before
    public final void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    //TC_UC_PM_05_10
    @Test
    public void testCorrectCompletePatientProfile(){
        therapyMedicineBean = new TherapyMedicineBean();
        meds = new ArrayList<>();
        meds.add(therapyMedicineBean);
        when(therapyBean.getSessions()).thenReturn(10);
        when(therapyBean.getMedicines()).thenReturn(meds);
        when(therapyBean.getDuration()).thenReturn(60);
        when(therapyBean.getFrequency()).thenReturn(2);
        assertEquals(true, patientQueryBean.insertDocument(therapyBean, "63dd3eac74c697331b1f019a"));
        assertEquals(true, patientQueryBean.updateDocument("_id", "63dd3eac74c697331b1f019a", "condition", "Tumore al cervello"));
        assertEquals(true, patientQueryBean.updateDocument("_id", "63dd3eac74c697331b1f019a", "notes", "Allergia ai famrci blu"));
    }

    //TC_UC_PM_05_3
    @Test
    public void testIncorrectConditionLength(){
        therapyMedicineBean = new TherapyMedicineBean();
        meds = new ArrayList<>();
        meds.add(therapyMedicineBean);
        when(therapyBean.getSessions()).thenReturn(10);
        when(therapyBean.getMedicines()).thenReturn(meds);
        when(therapyBean.getDuration()).thenReturn(60);
        when(therapyBean.getFrequency()).thenReturn(2);
        assertEquals(true, patientQueryBean.insertDocument(therapyBean, "63dd3eac74c697331b1f019a"));
        assertEquals(false, patientQueryBean.updateDocument("_id", "63dd3eac74c697331b1f019a", "condition", ""));
        assertEquals(true, patientQueryBean.updateDocument("_id", "63dd3eac74c697331b1f019a", "notes", "Allergia ai famrci blu"));
    }

    //TC_UC_PM_05_1
    @Test
    public void testIncorrectNotesLength(){
        therapyMedicineBean = new TherapyMedicineBean();
        meds = new ArrayList<>();
        meds.add(therapyMedicineBean);
        when(therapyBean.getSessions()).thenReturn(10);
        when(therapyBean.getMedicines()).thenReturn(meds);
        when(therapyBean.getDuration()).thenReturn(60);
        when(therapyBean.getFrequency()).thenReturn(2);
        assertEquals(true, patientQueryBean.insertDocument(therapyBean, "63dd3eac74c697331b1f019a"));
        assertEquals(true, patientQueryBean.updateDocument("_id", "63dd3eac74c697331b1f019a", "condition", "Tumore al cervello"));
        assertEquals(false, patientQueryBean.updateDocument("_id", "63dd3eac74c697331b1f019a", "notes", ""));
    }

    //TC_UC_PM_05_8
    @Test
    public void testIncorrectSession(){
        therapyMedicineBean = new TherapyMedicineBean();
        meds = new ArrayList<>();
        meds.add(therapyMedicineBean);
        when(therapyBean.getSessions()).thenReturn(0);
        when(therapyBean.getMedicines()).thenReturn(meds);
        when(therapyBean.getDuration()).thenReturn(60);
        when(therapyBean.getFrequency()).thenReturn(2);
        System.out.println(therapyBean);
        assertEquals(false, patientQueryBean.insertDocument(therapyBean, "63dd3eac74c697331b1f019a"));
        assertEquals(true, patientQueryBean.updateDocument("_id", "63dd3eac74c697331b1f019a", "condition", "Tumore al cervello"));
        assertEquals(true, patientQueryBean.updateDocument("_id", "63dd3eac74c697331b1f019a", "notes", "Allergia ai famrci blu"));
    }

    //TC_UC_PM_05_9
    @Test
    public void testIncorrectDuration(){
        therapyMedicineBean = new TherapyMedicineBean();
        meds = new ArrayList<>();
        meds.add(therapyMedicineBean);
        when(therapyBean.getSessions()).thenReturn(10);
        when(therapyBean.getMedicines()).thenReturn(meds);
        when(therapyBean.getDuration()).thenReturn(0);
        when(therapyBean.getFrequency()).thenReturn(2);
        assertEquals(false, patientQueryBean.insertDocument(therapyBean, "63dd3eac74c697331b1f019a"));
        assertEquals(true, patientQueryBean.updateDocument("_id", "63dd3eac74c697331b1f019a", "condition", "Tumore al cervello"));
        assertEquals(true, patientQueryBean.updateDocument("_id", "63dd3eac74c697331b1f019a", "notes", "Allergia ai famrci blu"));
    }

    //TC_UC_PM_05_10
    @Test
    public void testIncorrectFrequency(){
        therapyMedicineBean = new TherapyMedicineBean();
        meds = new ArrayList<>();
        meds.add(therapyMedicineBean);
        when(therapyBean.getSessions()).thenReturn(10);
        when(therapyBean.getMedicines()).thenReturn(meds);
        when(therapyBean.getDuration()).thenReturn(60);
        when(therapyBean.getFrequency()).thenReturn(0);
        assertEquals(false, patientQueryBean.insertDocument(therapyBean, "63dd3eac74c697331b1f019a"));
        assertEquals(true, patientQueryBean.updateDocument("_id", "63dd3eac74c697331b1f019a", "condition", "Tumore al cervello"));
        assertEquals(true, patientQueryBean.updateDocument("_id", "63dd3eac74c697331b1f019a", "notes", "Allergia ai famrci blu"));
    }
}


