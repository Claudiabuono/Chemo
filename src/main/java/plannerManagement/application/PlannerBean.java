package plannerManagement.application;

import plannerManagement.application.AppointmentBean;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PlannerBean {
    private String id;
    private Date startDate;
    private Date endDate;
    private ArrayList<AppointmentBean> appointments;

    public PlannerBean(){}

    //Non prevede l'inserimento diretto degli appuntamenti, istanziando solamente l'agenda senza popolarla
    public PlannerBean(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.appointments = new ArrayList<>();
    }

    //Prevede l'inserimento diretto degli appuntamenti, instanziando e popolando l'agenda

    public PlannerBean(Date startDate, Date endDate, ArrayList<AppointmentBean> appointments) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.appointments = appointments;
    }

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

    public String getParsedStartDate() {
        return dateParser(startDate);
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getParsedEndDate() {
        return dateParser(endDate);
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

    private String dateParser(Date date) {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }
}
