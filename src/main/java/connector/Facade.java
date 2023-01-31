package connector;

import medicinemanagement.application.BoxBean;
import medicinemanagement.application.MedicineBean;
import medicinemanagement.storage.MedicineQueryBean;
import org.bson.types.ObjectId;
import patientmanagement.application.PatientBean;
import patientmanagement.application.TherapyBean;
import patientmanagement.application.TherapyMedicineBean;
import patientmanagement.storage.PatientQueryBean;
import plannerManagement.application.AppointmentBean;
import plannerManagement.application.PlannerBean;
import plannerManagement.storage.PlannerQueryBean;
import userManagement.application.UserBean;
import userManagement.storage.UserQueryBean;

import java.util.ArrayList;
import java.util.Date;

public class Facade {
    private UserQueryBean userQueryBean = new UserQueryBean();
    private PlannerQueryBean plannerQueryBean = new PlannerQueryBean();
    private MedicineQueryBean medicineQueryBean = new MedicineQueryBean();
    private PatientQueryBean patientQueryBean = new PatientQueryBean();

    public Facade(){
    }

    /*
    Il metodo verifica se l'utente, una volta individuato, ha i permessi per effettuare determinate operazioni,
    ovvero se si tratta di un membro del personale medico (type = 1) o del gestore dei farmaci (type = 2)
     */
    private boolean isUserAuthorized(String username, int type){
        boolean valid = false;
        ArrayList<UserBean> users = findUsers("username", username);
        if(users.get(0) != null){
            if(users.get(0).getType() == type)
                valid = true;
        }
        return valid;
    }

    /*
    OPERAZIONI CRUD PER ENTITA' USER
     */
    public ArrayList<UserBean> findUsers(String chiave, String valore){
        return userQueryBean.findDocument(chiave, valore);
    }

    public void updateUser(String id, String valId, String chiave, String valoreChiave){
        userQueryBean.updateDocument(id, valId, chiave, valoreChiave);
    }

    public void deleteUser(String chiave, String valore){
        userQueryBean.deleteDocument(chiave, valore);
    }

    public void insertUsers(ArrayList<UserBean> utenti){
        userQueryBean.insertDocuments(utenti);
    }

    public void insertUser(UserBean userBean){
        userQueryBean.insertDocument(userBean);
    }

    /*
    OPERAZIONI CRUD PER ENTITA' PLANNER
     */
    public ArrayList<PlannerBean> findPlanners(String chiave, String valore){
        return plannerQueryBean.findDocument(chiave, valore);
    }

    public void updatePlanner(String id, String valId, String chiave, String valoreChiave){
        plannerQueryBean.updateDocument(id, valId, chiave, valoreChiave);
    }

    public void deletePlanner(String chiave, String valore){
        plannerQueryBean.deleteDocument(chiave, valore);
    }

    public void insertPlanners(ArrayList<PlannerBean> planners, UserBean user){
        try {
            if (isUserAuthorized(user.getUsername(), 1))
                plannerQueryBean.insertDocuments(planners);
            else
                throw new Exception("Utente non autorizzato per la produzione del planner");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void insertPlanner(String id, Date startDate, Date endDate, ArrayList<AppointmentBean> appointments, UserBean user){
        try{
            if(isUserAuthorized(user.getUsername(), 1)) {
                PlannerBean planner = new PlannerBean(id, startDate, endDate, appointments);
                plannerQueryBean.insertDocument(planner);
            }else
                throw new Exception("Utente non autorizzato all'inserimento di un planner");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    /*
    OPERAZIONI CRUD PER ENTITA' MEDICINE
     */
    public void insertMedicine( String id, String name, String ingredients, int amount, UserBean user){
        try{
            if(isUserAuthorized(user.getUsername(), 2)){
                MedicineBean medicineBean = new MedicineBean(id,name, ingredients, amount, new ArrayList<BoxBean>());
                medicineQueryBean.insertDocument(medicineBean);
            }else
                throw new Exception("Utente non autorizzato all'inserimento di medicinali");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void insertMedicineBox(String medicineId, String boxId, boolean status, Date expiryDate, int capacity, UserBean user) {
        try{
            if(isUserAuthorized(user.getUsername(), 2)){
                BoxBean box = new BoxBean(status, expiryDate, capacity, boxId);
                medicineQueryBean.insertDocument(box, medicineId);
            }else
                throw new Exception("Utente non autorizzato all'inserimento di medicinali");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }


    public void removeMedicineBox(String boxId, UserBean user) {
        try{
            if(isUserAuthorized(user.getUsername(), 2)){
                medicineQueryBean.deleteDocument("boxId", boxId);
            }else
                throw new Exception("Utente non autorizzato all'inserimento di medicinali");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void insertMedicines(ArrayList<MedicineBean> medicines){medicineQueryBean.insertDocuments(medicines);}

    public void deleteMedicine(String key, String value, UserBean user){
        try{
            if(isUserAuthorized(user.getUsername(), 2)){
                medicineQueryBean.deleteDocument(key, value);
            }else
                throw new Exception("Utente non autorizzato all'eliminazione di medicinali");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    };

    public void updateMedicine(String id, String valId, String key, String valKey, UserBean user){
        try{
            if(isUserAuthorized(user.getUsername(), 2)){
                medicineQueryBean.updateDocument(id, valId, key, valKey);
            }else
                throw new Exception("Utente non autorizzato alla modifica di medicinali");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    };

    public ArrayList<MedicineBean> findMedicines(String key, String value){return medicineQueryBean.findDocument(key, value);};

    /*
    OPERAZIONI CRUD PER ENTITA' PATIENT
     */

    public void insertPatient(PatientBean patientBean, UserBean user){
        try{
            if(isUserAuthorized(user.getUsername(), 1))
                patientQueryBean.insertDocument(patientBean);
            else
                throw new Exception("Utente non autorizzato all'inserimento dei pazienti");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    };

    public PatientBean insertPatient(String taxCode, String name, String surname, Date birthDate, String city, String phoneNumber, String notes, UserBean user){
        System.out.println("Nome "+ name + " con taxcode " + taxCode + "city " + city);
        try{
            PatientBean patient = new PatientBean(taxCode, name, surname, birthDate, city, phoneNumber, false, notes);
            if(isUserAuthorized(user.getUsername(), 1)) {
                patientQueryBean.insertDocument(patient);
                return  patient;
            }
            else
                throw new Exception("Utente non autorizzato all'inserimento dei pazienti");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    };

    public void insertTherapy(int sessions, ArrayList<TherapyMedicineBean> medicines, int duration, int frequency, String patientId, UserBean user) {
        try{
            TherapyBean therapy = new TherapyBean(sessions, medicines, duration, frequency);
            if(isUserAuthorized(user.getUsername(), 1)) {
                patientQueryBean.insertDocument(therapy, patientId);
            }
            else
                throw new Exception("Utente non autorizzato all'inserimento dei pazienti");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void insertPatients(ArrayList<PatientBean> patients, UserBean user){
        try{
            if(isUserAuthorized(user.getUsername(), 1))
                patientQueryBean.insertDocuments(patients);
            else
                throw new Exception("Utente non autorizzato all'inserimento dei pazienti");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    };

    public void deletePatient(String key, String value, UserBean user){
        try{
            if(isUserAuthorized(user.getUsername(), 1))
                patientQueryBean.deleteDocument(key, value);
            else
                throw new Exception("Utente non autorizzato all'eliminazione dei pazienti");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    };

    public void updatePatient(String id, String valId, String key, String valKey, UserBean user){
        try{
            if(isUserAuthorized(user.getUsername(), 1))
                patientQueryBean.updateDocument(id, valId, key, valKey);
            else
                throw new Exception("Utente non autorizzato alla modifica dei pazienti");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    };

    //modificare il metodo in patientQueryBean in modo che restituisca ArrayList<PatientBean>
    public ArrayList<PatientBean> findPatients(String key, String value, UserBean user){
        ArrayList<PatientBean> patients = new ArrayList<>();
        try{
            if(isUserAuthorized(user.getUsername(), 1)) {
                if (key.equals("_id")) {
                    patients.add(patientQueryBean.findDocumentById(value));
                    return patients;
                }
                return patients = patientQueryBean.findDocument(key, value);
            }
            else
                throw new Exception("Utente non autorizzato alla visualizzazione dei pazienti");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        return patients;
    };

    public ArrayList<PatientBean> findAllPatients(UserBean user){
        try{
            if(isUserAuthorized(user.getUsername(), 1))
                return patientQueryBean.findAll();
            else
                throw new Exception("Utente non autorizzato alla visualizzazione dei pazienti");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    };

}
