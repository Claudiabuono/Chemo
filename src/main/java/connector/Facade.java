package connector;

import medicinemanagement.application.PackageBean;
import medicinemanagement.application.MedicineBean;
import medicinemanagement.storage.MedicineQueryBean;
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
    public ArrayList<PlannerBean> findPlanners(String chiave, Object valore, UserBean user){
        ArrayList<PlannerBean> planners = new ArrayList<>();
        try {
            if(isUserAuthorized(user.getUsername(), 2) || isUserAuthorized(user.getUsername(), 1)) {
                if(chiave.equals("_id")) {
                    planners.add(plannerQueryBean.findDocumentById((String )valore));
                    return planners;
                } else {
                    return plannerQueryBean.findDocument(chiave, (String) valore);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public PlannerBean findLatestPlanner(UserBean user) {
        try {
            if(isUserAuthorized(user.getUsername(), 2) || isUserAuthorized(user.getUsername(), 1))
                return plannerQueryBean.findLastDocument();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    public ArrayList<PlannerBean> findAllPlanners(UserBean user) {
        try {
            if(isUserAuthorized(user.getUsername(), 1) || isUserAuthorized(user.getUsername(), 2))
                return plannerQueryBean.findAll();
            else
                throw new Exception("Utente non autorizzato alla modifica dei planner");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public void updatePlanner(String id, String valId, String chiave, Object valoreChiave, UserBean user){
        try {
            if(isUserAuthorized(user.getUsername(), 1))
                plannerQueryBean.updateDocument(id, valId, chiave, valoreChiave);
            else
                throw new Exception("Utente non autorizzato alla modifica di medicinali");
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void insertPlanner(Date startDate, Date endDate, ArrayList<AppointmentBean> appointments, UserBean user){
        try{
            if(isUserAuthorized(user.getUsername(), 1)) {
                PlannerBean planner = new PlannerBean(startDate, endDate, appointments);
                plannerQueryBean.insertDocument(planner);
            }else
                throw new Exception("Utente non autorizzato all'inserimento di un planner");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void insertPlanner(PlannerBean planner, UserBean user){
        try{
            if(isUserAuthorized(user.getUsername(), 1)) {
                plannerQueryBean.insertDocument(planner);
            }else
                throw new Exception("Utente non autorizzato all'inserimento di un planner");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /*
    OPERAZIONI CRUD PER ENTITA' MEDICINE
     */
    public MedicineBean insertMedicine(String name, String ingredients, UserBean user){
        try{
            if(isUserAuthorized(user.getUsername(), 2)){
                MedicineBean medicineBean = new MedicineBean(name, ingredients);
                medicineQueryBean.insertDocument(medicineBean);
                return medicineBean;
            }else
                throw new Exception("Utente non autorizzato all'inserimento di medicinali");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    public boolean insertMedicine(MedicineBean medicine, UserBean user){
        try{
            if(isUserAuthorized(user.getUsername(), 2)){
                medicineQueryBean.insertDocument(medicine);
                return true;
            }else
                throw new Exception("Utente non autorizzato all'inserimento di medicinali");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        return false;
    }

    public void insertMedicinePackage(String medicineId, PackageBean medicinePackage, UserBean user) {
        try{
            if(isUserAuthorized(user.getUsername(), 2)){
                medicineQueryBean.insertDocument(medicinePackage, medicineId);
            }else
                throw new Exception("Utente non autorizzato all'inserimento di medicinali");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void insertMedicinePackage(String medicineId, String boxId, boolean status, Date expiryDate, int capacity, UserBean user) {
        try{
            if(isUserAuthorized(user.getUsername(), 2)){
                PackageBean newPackage = new PackageBean(status, expiryDate, capacity, boxId);
                medicineQueryBean.insertDocument(newPackage, medicineId);
            }else
                throw new Exception("Utente non autorizzato all'inserimento di medicinali");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }


    public void removeMedicinePackage(String boxId, UserBean user) {
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

    public void updateMedicine(String id, String valId, String key, Object valKey, UserBean user){
        try{
            if(isUserAuthorized(user.getUsername(), 2)){
                medicineQueryBean.updateDocument(id, valId, key, valKey);
            }else
                throw new Exception("Utente non autorizzato alla modifica di medicinali");
        }catch(Exception e){
            e.printStackTrace();
        }
    };

    public ArrayList<MedicineBean> findMedicines(String key, Object value, UserBean user){
        ArrayList<MedicineBean> medicines = new ArrayList<>();
        try{
            if(isUserAuthorized(user.getUsername(), 2) || isUserAuthorized(user.getUsername(), 1)){
                if(key.equals("_id"))  {
                    medicines.add(medicineQueryBean.findDocumentById(String.valueOf(value)));
                    return medicines;
                }
                return medicineQueryBean.findDocument(key, value);
            }else
                throw new Exception("Utente non autorizzato alla modifica di medicinali");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        return medicines;
    };

    public ArrayList<MedicineBean> findMedicines(ArrayList<String> key, ArrayList<Object> value, UserBean user){
        ArrayList<MedicineBean> medicines = new ArrayList<>();
        try{
            if(isUserAuthorized(user.getUsername(), 2) || isUserAuthorized(user.getUsername(), 1)){
                return medicineQueryBean.findDocument(key, value);
            }else
                throw new Exception("Utente non autorizzato alla modifica di medicinali");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        return medicines;
    };

    public ArrayList<MedicineBean> findAllMedicines(UserBean user) {
        try {
            if(isUserAuthorized(user.getUsername(), 1) || isUserAuthorized(user.getUsername(), 2))
                return medicineQueryBean.findAll();
            else
                throw new Exception("Utente non autorizzato alla modifica di medicinali");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /*
    OPERAZIONI CRUD PER ENTITA' PATIENT
     */

    public PatientBean insertPatient(PatientBean patient, UserBean user){
        try{
            if(isUserAuthorized(user.getUsername(), 1)){
                patientQueryBean.insertDocument(patient);
                return patient;
            }
            else
                throw new Exception("Utente non autorizzato all'inserimento dei pazienti");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
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

    public void insertTherapy(String patientId, TherapyBean therapy, UserBean user) {
        try{
            if(isUserAuthorized(user.getUsername(), 1)) {
                patientQueryBean.insertDocument(therapy, patientId);
            }
            else
                throw new Exception("Utente non autorizzato all'inserimento dei pazienti");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

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

    public void updatePatient(String id, String valId, String key, Object valKey, UserBean user){
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
    public ArrayList<PatientBean> findPatients(String key, Object value, UserBean user){
        ArrayList<PatientBean> patients = new ArrayList<>();
        try{
            if(isUserAuthorized(user.getUsername(), 1)) {
                if (key.equals("_id")) {
                    patients.add(patientQueryBean.findDocumentById(String.valueOf(value)));
                    return patients;
                }
                return patientQueryBean.findDocument(key, value);
            }
            else
                throw new Exception("Utente non autorizzato alla visualizzazione dei pazienti");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        return patients;
    };

    public ArrayList<PatientBean> findPatients(ArrayList<String> key, ArrayList<Object> value, UserBean user){
        ArrayList<PatientBean> patients = new ArrayList<>();
        try{
            if(isUserAuthorized(user.getUsername(), 1)) {
                patients = patientQueryBean.findDocument(key, value);
            }
            else
                throw new Exception("Utente non autorizzato alla visualizzazione dei pazienti");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        return patients;
    }

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
