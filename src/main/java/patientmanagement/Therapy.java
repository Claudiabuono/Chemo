package patientmanagement;

import java.util.ArrayList;

class Therapy {

    //Parametri
    private int sedute;
    private ArrayList<Medicines> medicinali;

    //Costruttori

    Therapy() {
    }

    Therapy(int sedute, ArrayList<Medicines> medicinali) {
        this.sedute = sedute;
        this.medicinali = medicinali;
    }

    //Getters

    int getSedute() {
        return sedute;
    }

    ArrayList<Medicines> getMedicinali() {
        return medicinali;
    }

    //Setters

    void setSedute(int sedute) {
        this.sedute = sedute;
    }

    void setMedicinali(ArrayList<Medicines> medicinali) {
        this.medicinali = medicinali;
    }

    //Metodi ereditati da Object

    @Override
    public String toString() {
        return "Therapy{" +
                "sedute=" + sedute +
                ", medicinali=" + medicinali +
                '}';
    }
}
