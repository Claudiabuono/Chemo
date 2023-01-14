package userManagement.application;

import java.util.Date;

public class UserBean {
    private String id;
    private String name;
    private String surname;
    private Date birthDate;
    private String birthplace;
    private String username;
    private String password;
    private String specialization;
    private int type;

    public UserBean(){}

    public UserBean(String id, String name, String surname, Date birthDate, String birthplace, String username, String password, String specialization, int type) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.birthplace = birthplace;
        this.username = username;
        this.password = password;
        this.specialization = specialization;
        this.type = type;
    }

    public String getId() {
        return id;
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

    public String getBirthplace() {
        return birthplace;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSpecialization() {
        return specialization;
    }

    public int getType() {
        return type;
    }

    public void setId(String id) {
        this.id = id;
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

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "id='" + id + '\'' +
                ", nome='" + name + '\'' +
                ", cognome='" + surname + '\'' +
                ", dataNascita=" + birthDate +
                ", cittaNascita='" + birthplace + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", specializzazione='" + specialization + '\'' +
                ", tipo=" + type +
                '}';
    }
}
