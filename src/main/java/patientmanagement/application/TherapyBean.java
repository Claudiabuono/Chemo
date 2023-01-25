package patientmanagement.application;

import java.util.ArrayList;

public class TherapyBean {

    //Parametri
    private int sessions;
    private ArrayList<MedicinesBean> medicines;

    private int duration;
    private int frequency;

    //Costruttori

    public TherapyBean() {
    }

    public TherapyBean(int sessions, ArrayList<MedicinesBean> medicines, int duration, int frequency) {
        this.sessions = sessions;
        this.medicines = medicines;
        this.duration = duration;
        this.frequency = frequency;
    }

    //Getters

    public int getSessions() {
        return sessions;
    }

    public ArrayList<MedicinesBean> getMedicines() {
        return medicines;
    }

    public int getDuration() {
        return duration;
    }

    public int getFrequency() {
        return frequency;
    }

    //Setters

    public void setSessions(int sessions) {
        this.sessions = sessions;
    }

    public void setMedicines(ArrayList<MedicinesBean> medicines) {
        this.medicines = medicines;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    //Metodi ereditati da Object

    @Override
    public String toString() {
        return "TherapyBean{" +
                "sessions=" + sessions +
                ", medicines=" + medicines +
                ", duration=" + duration +
                ", frequency=" + frequency +
                '}';
    }
}
