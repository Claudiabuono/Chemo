package queryBean;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import patientmanagement.application.PatientBean;
import patientmanagement.storage.PatientQueryBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TC_CreateNewPatient {
    @Mock
    PatientBean patientBean;

    @InjectMocks
    PatientQueryBean patientQueryBean;
    @Before
    public final void setUp() {
        patientQueryBean = new PatientQueryBean();
        patientBean = new PatientBean();
        MockitoAnnotations.initMocks(this);
    }

    //TC_UC_PM_01_2
    @Test
    public void testPatientCreationWithIncorrectNameLenght() {
        try {
            Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse("21/06/1984");
            ObjectId id = new ObjectId();
            when(patientBean.getPatientId()).thenReturn(String.valueOf(id));
            when(patientBean.getTaxCode()).thenReturn("RS5MRAC4H21F8R9S");
            when(patientBean.getName()).thenReturn("");
            when(patientBean.getSurname()).thenReturn("Rossi");
            when(patientBean.getBirthDate()).thenReturn(birthDate);
            when(patientBean.getCity()).thenReturn("Napoli");
            when(patientBean.getPhoneNumber()).thenReturn("3933358960");
            when(patientBean.getStatus()).thenReturn(true);
            when(patientBean.getNotes()).thenReturn("Allergia ad alcuni farmaci");
            assertEquals(false, patientQueryBean.insertDocument(patientBean));
            patientQueryBean.deleteDocument("_id", patientBean.getPatientId());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


    //TC_UC_PM_01_3
    @Test
    public void testPatientCreationWithIncorrectSurameLenght() {
        try {
            Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse("21/06/1984");
            ObjectId id = new ObjectId();
            when(patientBean.getPatientId()).thenReturn(String.valueOf(id));
            when(patientBean.getTaxCode()).thenReturn("RS5MRAC4H21F8R9S");
            when(patientBean.getName()).thenReturn("Mario");
            when(patientBean.getSurname()).thenReturn("");
            when(patientBean.getBirthDate()).thenReturn(birthDate);
            when(patientBean.getCity()).thenReturn("Napoli");
            when(patientBean.getPhoneNumber()).thenReturn("3933358960");
            when(patientBean.getStatus()).thenReturn(true);
            when(patientBean.getNotes()).thenReturn("Allergia ad alcuni farmaci");
            assertEquals(false, patientQueryBean.insertDocument(patientBean));
            patientQueryBean.deleteDocument("_id", patientBean.getPatientId());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    //TC_UC_PM_01_5
    @Test
    public void testPatientCreationWithIncorrectBirthDate() {
        try {
            Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse("30/05/2025");
            ObjectId id = new ObjectId();
            when(patientBean.getPatientId()).thenReturn(String.valueOf(id));
            when(patientBean.getTaxCode()).thenReturn("RS5MRAC4H21F8R9S");
            when(patientBean.getName()).thenReturn("Mario");
            when(patientBean.getSurname()).thenReturn("Rossi");
            when(patientBean.getBirthDate()).thenReturn(birthDate);
            when(patientBean.getCity()).thenReturn("Napoli");
            when(patientBean.getPhoneNumber()).thenReturn("3933358960");
            when(patientBean.getStatus()).thenReturn(true);
            when(patientBean.getNotes()).thenReturn("Allergia ad alcuni farmaci");
            assertEquals(false, patientQueryBean.insertDocument(patientBean));
            patientQueryBean.deleteDocument("_id", patientBean.getPatientId());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    //TC_UC_PM_01_7
    @Test
    public void testPatientCreationWithIncorrectCityLenght() {
        try {
            Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse("21/06/1984");
            ObjectId id = new ObjectId();
            when(patientBean.getPatientId()).thenReturn(String.valueOf(id));
            when(patientBean.getTaxCode()).thenReturn("RS5MRAC4H21F8R9S");
            when(patientBean.getName()).thenReturn("Mario");
            when(patientBean.getSurname()).thenReturn("Rossi");
            when(patientBean.getBirthDate()).thenReturn(birthDate);
            when(patientBean.getCity()).thenReturn("");
            when(patientBean.getPhoneNumber()).thenReturn("3933358960");
            when(patientBean.getStatus()).thenReturn(true);
            when(patientBean.getNotes()).thenReturn("Allergia ad alcuni farmaci");
            assertEquals(false, patientQueryBean.insertDocument(patientBean));
            patientQueryBean.deleteDocument("_id", patientBean.getPatientId());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    //TC_UC_PM_01_9
    @Test
    public void testPatientCreationWithIncorrectTaxCodeLenght() {
        try {
            Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse("21/06/1984");
            ObjectId id = new ObjectId();
            when(patientBean.getPatientId()).thenReturn(String.valueOf(id));
            when(patientBean.getTaxCode()).thenReturn("");
            when(patientBean.getName()).thenReturn("Mario");
            when(patientBean.getSurname()).thenReturn("Rossi");
            when(patientBean.getBirthDate()).thenReturn(birthDate);
            when(patientBean.getCity()).thenReturn("Napoli");
            when(patientBean.getPhoneNumber()).thenReturn("3933358960");
            when(patientBean.getStatus()).thenReturn(true);
            when(patientBean.getNotes()).thenReturn("Allergia ad alcuni farmaci");
            assertEquals(false, patientQueryBean.insertDocument(patientBean));
            patientQueryBean.deleteDocument("_id", patientBean.getPatientId());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    //TC_UC_PM_01_11--> da fare
    @Test
    public void testPatientCreationWithIncorrectPhoneNumberLenght() {
        try {
            Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse("21/06/1984");
            ObjectId id = new ObjectId();
            when(patientBean.getPatientId()).thenReturn(String.valueOf(id));
            when(patientBean.getTaxCode()).thenReturn("RS5MRAC4H21F8R9S");
            when(patientBean.getName()).thenReturn("Mario");
            when(patientBean.getSurname()).thenReturn("Rossi");
            when(patientBean.getBirthDate()).thenReturn(birthDate);
            when(patientBean.getCity()).thenReturn("Napoli");
            when(patientBean.getPhoneNumber()).thenReturn("");
            when(patientBean.getStatus()).thenReturn(true);
            when(patientBean.getNotes()).thenReturn("Allergia ad alcuni farmaci");
            assertEquals(false, patientQueryBean.insertDocument(patientBean));
            patientQueryBean.deleteDocument("_id", patientBean.getPatientId());
        } catch (ParseException e){
            throw new RuntimeException(e);
        }
    }

    //TC_UC_PM_01_15
    @Test
    public void testPatientCreationWithCorrectInput() {
        try {
            Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse("21/06/1984");
            ObjectId id = new ObjectId();
            when(patientBean.getPatientId()).thenReturn(String.valueOf(id));
            when(patientBean.getTaxCode()).thenReturn("RS5MRAC4H21F8R9S");
            when(patientBean.getName()).thenReturn("Mario");
            when(patientBean.getSurname()).thenReturn("Rossi");
            when(patientBean.getBirthDate()).thenReturn(birthDate);
            when(patientBean.getCity()).thenReturn("Napoli");
            when(patientBean.getPhoneNumber()).thenReturn("3933358960");
            when(patientBean.getStatus()).thenReturn(true);
            when(patientBean.getNotes()).thenReturn("Allergia ad alcuni farmaci");
            assertEquals(true, patientQueryBean.insertDocument(patientBean));
            patientQueryBean.deleteDocument("_id", patientBean.getPatientId());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}

