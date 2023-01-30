package patientmanagement.application;

import java.util.ArrayList;
import java.util.Date;

public class PatientBean {
    //Parametri
    String patientId;
    private String taxCode;
    private String name;
    private String surname;
    private Date birthDate;
    private String city;
    private String phoneNumber;
    private boolean status;
    private String condition;
    private String notes;
    private ArrayList<TherapyBean> therapy;

    //Costruttori

    public PatientBean() {
        this.therapy = null;
    }

    public PatientBean(String taxCode, String name, String surname, Date dataNascita, String city, String phoneNumber, boolean status, String condition, String notes, ArrayList<TherapyBean> therapy) {
        this.taxCode = taxCode;
        this.name = name;
        this.surname = surname;
        this.birthDate = dataNascita;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.condition = condition;
        this.therapy = therapy;
        this.notes = notes;
    }

    //Getters

    public String getTaxCode() {
        return taxCode;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getCity() {
        return city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean getStatus() {
        return status;
    }

    public String getCondition() {
        return condition;
    }

    public ArrayList<TherapyBean> getTherapy() {
        return therapy;
    }

    public String getNotes() {
        return notes;
    }

    public String getPatientId() {
        return patientId;
    }

    //Setters

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setTherapy(ArrayList<TherapyBean> therapy) {
        this.therapy = therapy;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    //Metodi ereditati da Object

    @Override
    public String toString() {
        return "PatientBean{" +
                "taxCode='" + taxCode + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate=" + birthDate +
                ", city='" + city + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", status=" + status +
                ", condition='" + condition + '\'' +
                ", notes='" + notes + '\'' +
                ", therapy=" + therapy +
                '}';
    }
}
