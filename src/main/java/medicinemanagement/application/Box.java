package medicinemanagement.application;

import java.util.Date;

public class Box {

    //Parametri
    private boolean status;
    private Date expiryDate;
    private int capacita;
    private String idLotto;

    //Costruttori
    public Box() {
    }

    public Box(boolean status, Date expiryDate, int capacita, String idLotto) {
        this.status = status;
        this.expiryDate = expiryDate;
        this.capacita = capacita;
        this.idLotto = idLotto;
    }

    //Getters

    public boolean getStatus() {
        return status;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public int getCapacita() {
        return capacita;
    }

    public String getIdLotto() {
        return idLotto;
    }


    //Setters
    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setCapacita(int capacita) {
        this.capacita = capacita;
    }

    public void setIdLotto(String idLotto) {
        this.idLotto = idLotto;
    }

    //Metodi ereditati da Object

    @Override
    public String toString() {
        return "StockBean{" +
                "status=" + status +
                ", expiryDate=" + expiryDate +
                ", capacita=" + capacita +
                ", idLotto='" + idLotto + '\'' +
                '}';
    }
}
