package test.unittest.query;

import connector.Facade;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import patientmanagement.application.PatientBean;
import patientmanagement.storage.PatientQueryBean;
import userManagement.application.UserBean;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PatientTest {

    PatientQueryBean query;
    PatientBean patient;

    @BeforeEach
    void setUp() {
        query = new PatientQueryBean();
        //String taxCode, String name, String surname, Date dataNascita, String city, String phoneNumber, boolean status, String condition, String notes, ArrayList<TherapyBean> therapy
        patient = new PatientBean("a", "aa", "aa", null, "aa", "111", false, "aaa", "aaa", null);
        patient.setPatientId(String.valueOf(new ObjectId()));
    }

    @Test
    void insertPatient() {
        //String taxCode, String name, String surname, Date birthDate, String city, String phoneNumber, String condition, String notes, UserBean user
        boolean flag = query.insertDocument(patient);
        assertTrue(flag);
    }
}
