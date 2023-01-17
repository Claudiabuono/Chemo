package plannerManagement.application;

import java.util.Date;

public class AppointmentBean {
    private String idPatient;
    private Date date;
    private String chair;

    public AppointmentBean() {}

    public AppointmentBean(String idPatient, Date date, String chair) {
        this.idPatient = idPatient;
        this.date = date;
        this.chair = chair;
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

    public void setIdPatient(String idPatient) {
        this.idPatient = idPatient;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setChair(String chair) {
        this.chair = chair;
    }

    @Override
    public String toString() {
        return "AppointmentBean{" +
                "idPatient='" + idPatient + '\'' +
                ", date=" + date +
                ", chair='" + chair + '\'' +
                '}';
    }
}
