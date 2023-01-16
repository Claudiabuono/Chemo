package patientmanagement.application;

import patientmanagement.application.Medicines;

import java.util.ArrayList;

public class Therapy {

    //Parametri
    private int sessions;
    private ArrayList<Medicines> medicines;

    //Costruttori

    public Therapy() {
    }

    public Therapy(int sessions, ArrayList<Medicines> medicines) {
        this.sessions = sessions;
        this.medicines = medicines;
    }

    //Getters

    public int getSessions() {
        return sessions;
    }

    public ArrayList<Medicines> getMedicines() {
        return medicines;
    }

    //Setters

    public void setSessions(int sessions) {
        this.sessions = sessions;
    }

    public void setMedicines(ArrayList<Medicines> medicines) {
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
