package plannerManagement.application;

import plannerManagement.application.AppointmentBean;

import java.util.ArrayList;
import java.util.Date;

public class PlannerBean {
    public String id;
    public Date startDate;
    public Date endDate;
    public ArrayList<AppointmentBean> appointments;

    public PlannerBean(){}

    //Non prevede l'inserimento diretto degli appuntamenti, istanziando solamente l'agenda senza popolarla
    public PlannerBean(String id, Date startDate, Date endDate) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.appointments = new ArrayList<>();
    }

    //Prevede l'inserimento diretto degli appuntamenti, instanziando e popolando l'agenda

    public PlannerBean(String id, Date startDate, Date endDate, ArrayList<AppointmentBean> appointments) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.appointments = appointments;
    }

    public String getId() {
        return id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public ArrayList<AppointmentBean> getAppointments() {
        return appointments;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setAppointments(ArrayList<AppointmentBean> appointments) {
        this.appointments = appointments;
    }

    @Override
    public String toString() {
        return "Agenda{" +
                "id='" + id + '\'' +
                ", dataInizio=" + startDate +
                ", dataFine=" + endDate +
                ", appuntamenti=" + appointments +
                '}';
    }
}
