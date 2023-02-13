package plannerManagement.application;

import java.util.Date;

public class AppointmentBean {
    private String idPatient;
    private String idMedicine;
    private Date date;
    private String chair;

    private int duration;

    public AppointmentBean() {}

    public AppointmentBean(String idPatient, String idMedicine ,Date date, String chair, int duration) {
        this.idPatient = idPatient;
        this.idMedicine = idMedicine;
        this.date = date;
        this.chair = chair;
        this.duration = duration;
    }

    public String getIdPatient() {
        return idPatient;
    }

    public Date getDate() {
        return date;
    }

    public String getChair() {
        return chair;
    }

    public int getDuration() {
        return duration;
    }

    public String getIdMedicine() {
        return idMedicine;
    }

    public void setIdPatient(String idPatient) {
        this.idPatient = idPatient;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setChair(String chair) {
        this.chair = chair;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setIdMedicine(String idMedicine) {
        this.idMedicine = idMedicine;
    }

    @Override
    public String toString() {
        return "AppointmentBean{" +
                "idPatient='" + idPatient + '\'' +
                ", idMedicine='" + idMedicine + '\'' +
                ", date=" + date +
                ", chair='" + chair + '\'' +
                ", duration=" + duration +
                '}';
    }
}
