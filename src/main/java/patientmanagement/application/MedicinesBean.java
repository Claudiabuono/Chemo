package patientmanagement.application;

class MedicinesBean {

    //Parametri
    private String medicineId;
    private int dose;

    //Costruttori

    public MedicinesBean() {
    }

    public MedicinesBean(String medicineId, int dose) {
        this.medicineId = medicineId;
        this.dose = dose;
    }

    //Getters

    public String getMedicineId() {
        return medicineId;
    }

    public int getDose() {
        return dose;
    }

    //Setters

    public void setMedicineId(String medicineId) {
        this.medicineId = medicineId;
    }

    public void setDose(int dose) {
        this.dose = dose;
    }

    //Metodi ereditati da Object

    @Override
    public String toString() {
        return "Medicines{" +
                "medicineId='" + medicineId + '\'' +
                ", dose=" + dose +
                '}';
    }
}
