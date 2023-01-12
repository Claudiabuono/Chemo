package patientmanagement.storage;

import java.util.ArrayList;
import java.util.Date;

public class PatientBean {
    //Parametri
    private String codiceFiscale;
    private String nome;
    private String cognome;
    private Date dataNascita;
    private String cittaNascita;
    private String numTelefono;
    private boolean stato;
    private String patologia;
    private ArrayList<Therapy> terapia;

    //Costruttori

    public PatientBean() {
    }

    public PatientBean(String codiceFiscale, String nome, String cognome, Date dataNascita, String cittaNascita, String numTelefono, boolean stato, String patologia, ArrayList<Therapy> terapia) {
        this.codiceFiscale = codiceFiscale;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.cittaNascita = cittaNascita;
        this.numTelefono = numTelefono;
        this.stato = stato;
        this.patologia = patologia;
        this.terapia = terapia;
    }

    //Getters

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public Date getDataNascita() {
        return dataNascita;
    }

    public String getCittaNascita() {
        return cittaNascita;
    }

    public String getNumTelefono() {
        return numTelefono;
    }

    public boolean getStato() {
        return stato;
    }

    public String getPatologia() {
        return patologia;
    }

    public ArrayList<Therapy> getTerapia() {
        return terapia;
    }

    //Setters

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setDataNascita(Date dataNascita) {
        this.dataNascita = dataNascita;
    }

    public void setCittaNascita(String cittaNascita) {
        this.cittaNascita = cittaNascita;
    }

    public void setNumTelefono(String numTelefono) {
        this.numTelefono = numTelefono;
    }

    public void setStato(boolean stato) {
        this.stato = stato;
    }

    public void setPatologia(String patologia) {
        this.patologia = patologia;
    }

    public void setTerapia(ArrayList<Therapy> terapia) {
        this.terapia = terapia;
    }

    //Metodi ereditati da Object

    @Override
    public String toString() {
        return "PatientBean{" +
                "codiceFiscale='" + codiceFiscale + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", dataNascita=" + dataNascita +
                ", cittaNascita='" + cittaNascita + '\'' +
                ", numTelefono='" + numTelefono + '\'' +
                ", stato=" + stato +
                ", patologia='" + patologia + '\'' +
                ", terapia=" + terapia +
                '}';
    }
}
