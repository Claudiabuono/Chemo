package patientmanagement;

class Medicines {

    //Parametri
    private String idMedicinale;
    private int dose;

    //Costruttori

    Medicines() {
    }

    Medicines(String idMedicinale, int dose) {
        this.idMedicinale = idMedicinale;
        this.dose = dose;
    }

    //Getters

    String getIdMedicinale() {
        return idMedicinale;
    }

    int getDose() {
        return dose;
    }

    //Setters

     void setIdMedicinale(String idMedicinale) {
        this.idMedicinale = idMedicinale;
    }

    void setDose(int dose) {
        this.dose = dose;
    }

    //Metodi ereditati da Object

    @Override
    public String toString() {
        return "Medicines{" +
                "idMedicinale='" + idMedicinale + '\'' +
                ", dose=" + dose +
                '}';
    }
}
