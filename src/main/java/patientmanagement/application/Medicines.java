package patientmanagement.application;

class Medicines {

    //Parametri
    private String idMedicinale;
    private int dose;

    //Costruttori

    public Medicines() {
    }

    public Medicines(String idMedicinale, int dose) {
        this.idMedicinale = idMedicinale;
        this.dose = dose;
    }

    //Getters

    public String getIdMedicinale() {
        return idMedicinale;
    }

    public int getDose() {
        return dose;
    }

    //Setters

    public void setIdMedicinale(String idMedicinale) {
        this.idMedicinale = idMedicinale;
    }

    public void setDose(int dose) {
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
