package medicinemanagement.storage;

import java.util.ArrayList;

public class MedicineBean {

    //Parametri
    private String id;
    private String nome;
    private String ingredienti;
    private int quantita;
    private ArrayList<StockBean> lotti;


    //Costruttori
    public MedicineBean() {
        lotti = new ArrayList<>();
    }

    public MedicineBean(String id, String nome, String ingredienti, int quantita, ArrayList<StockBean> lotti) {
        this.id = id;
        this.nome = nome;
        this.ingredienti = ingredienti;
        this.quantita = quantita;
        this.lotti = lotti;
    }

    //Getters
    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getIngredienti() {
        return ingredienti;
    }

    public int getQuantita() {
        return quantita;
    }

    public ArrayList<StockBean> getLotti() {
        return lotti;
    }

    //Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIngredienti(String ingredienti) {
        this.ingredienti = ingredienti;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public void setLotti(ArrayList<StockBean> lotti) {
        this.lotti = lotti;
    }

    //Metodi ereditati da Object
    @Override
    public String toString() {
        return "MedicineBean{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", ingredienti='" + ingredienti + '\'' +
                ", quantita=" + quantita +
                ", lotti=" + lotti +
                '}';
    }

    //Altri metodi
    public void addLotto(StockBean stock) {
        lotti.add(stock);
    }
}
