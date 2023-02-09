package queryBean;

import org.bson.types.ObjectId;
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

public class TC_UpdatePatientProfile {
    @Mock
    TherapyBean therapyBean;
    @Mock
    TherapyMedicineBean therapyMedicineBean;
    @Mock
    ArrayList<TherapyMedicineBean> meds;
    @InjectMocks
    PatientQueryBean patientQueryBean;

    @Before
    public final void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    //TC_UC_PM_05_3
    @Test
    public void testUpdatePatientWithIncorrectConditionLength(){
        assertEquals(false, patientQueryBean.updateDocument("_id", "63de1815d74b4d04cf039760", "condition", ""));
    }

    //TC_UC_PM_05_7
    @Test
    public void testUpdatePatientWithIncorrectTherapyDose(){
        assertEquals(false, patientQueryBean.updateDocument("_id", "63de1815d74b4d04cf039760", "dose", 0));
    }

    //TC_UC_PM_05_8
    @Test
    public void testUpdatePatientWithIncorrectTherapySession(){
        assertEquals(true, patientQueryBean.updateDocument("_id", "63de1815d74b4d04cf039760", "session", 0));
    }

    //TC_UC_PM_05_9
    @Test
    public void testUpdatePatientWithIncorrectTherapyDuration(){
        assertEquals(false, patientQueryBean.updateDocument("_id", "63de1815d74b4d04cf039760", "duration", 0));
    }

    //TC_UC_PM_05_10
    @Test
    public void testUpdatePatientWithIncorrectTherapyFrequency(){
        assertEquals(false, patientQueryBean.updateDocument("_id", "63de1815d74b4d04cf039760", "frequency", 0));
    }
}
