package patientmanagement.application;

import java.util.ArrayList;
import java.util.Date;

public class PatientBean {
    //Parametri
    private String taxCode;
    private String name;
    private String surname;
    private Date birthDate;
    private String city;
    private String phoneNumber;
    private boolean status;
    private String condition;
    private ArrayList<Therapy> therapy;

    //Costruttori

    public PatientBean() {
    }

    public PatientBean(String taxCode, String name, String surname, Date dataNascita, String city, String phoneNumber, boolean status, String condition, ArrayList<Therapy> therapy) {
        this.taxCode = taxCode;
        this.name = name;
        this.surname = surname;
        this.birthDate = dataNascita;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.condition = condition;
        this.therapy = therapy;
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

    public ArrayList<Therapy> getTherapy() {
        return therapy;
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

    public void setTherapy(ArrayList<Therapy> therapy) {
        this.therapy = therapy;
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
                ", therapy=" + therapy +
                '}';
    }
}
