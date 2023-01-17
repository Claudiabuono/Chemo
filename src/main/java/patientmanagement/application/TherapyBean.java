package patientmanagement.application;

import java.util.ArrayList;

public class TherapyBean {

    //Parametri
    private int sessions;
    private ArrayList<MedicinesBean> medicines;

    //Costruttori

    public TherapyBean() {
    }

    public TherapyBean(int sessions, ArrayList<MedicinesBean> medicines) {
        this.sessions = sessions;
        this.medicines = medicines;
    }

    //Getters

    public int getSessions() {
        return sessions;
    }

    public ArrayList<MedicinesBean> getMedicines() {
        return medicines;
    }

    //Setters

    public void setSessions(int sessions) {
        this.sessions = sessions;
    }

    public void setMedicines(ArrayList<MedicinesBean> medicines) {
        this.medicines = medicines;
    }

    //Metodi ereditati da Object

    @Override
    public String toString() {
        return "Therapy{" +
                "sessions=" + sessions +
                ", medicines=" + medicines +
                '}';
    }
}
