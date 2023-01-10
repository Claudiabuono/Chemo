package medicinemanagement.storage;

import java.util.Date;

class Stock {

    //Parametri
    private boolean stato;
    private Date scadenza;
    private int capacita;
    private String idLotto;

    //Costruttori
    Stock() {
    }

    Stock(boolean stato, Date scadenza, int capacita, String idLotto) {
        this.stato = stato;
        this.scadenza = scadenza;
        this.capacita = capacita;
        this.idLotto = idLotto;
    }

    //Getters

    boolean getStato() {
        return stato;
    }

    Date getScadenza() {
        return scadenza;
    }

    int getCapacita() {
        return capacita;
    }

    String getIdLotto() {
        return idLotto;
    }


    //Setters
    void setStato(boolean stato) {
        this.stato = stato;
    }

    void setScadenza(Date scadenza) {
        this.scadenza = scadenza;
    }

    void setCapacita(int capacita) {
        this.capacita = capacita;
    }

    void setIdLotto(String idLotto) {
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
