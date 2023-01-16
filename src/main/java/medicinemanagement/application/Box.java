package medicinemanagement.application;

import java.util.Date;

public class Box {

    //Parametri
    private boolean stato;
    private Date scadenza;
    private int capacita;
    private String idLotto;

    //Costruttori
    public Box() {
    }

    public Box(boolean stato, Date scadenza, int capacita, String idLotto) {
        this.stato = stato;
        this.scadenza = scadenza;
        this.capacita = capacita;
        this.idLotto = idLotto;
    }

    //Getters

    public boolean getStato() {
        return stato;
    }

    public Date getScadenza() {
        return scadenza;
    }

    public int getCapacita() {
        return capacita;
    }

    public String getIdLotto() {
        return idLotto;
    }


    //Setters
    public void setStato(boolean stato) {
        this.stato = stato;
    }

    public void setScadenza(Date scadenza) {
        this.scadenza = scadenza;
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
                "stato=" + stato +
                ", scadenza=" + scadenza +
                ", capacita=" + capacita +
                ", idLotto='" + idLotto + '\'' +
                '}';
    }
}
