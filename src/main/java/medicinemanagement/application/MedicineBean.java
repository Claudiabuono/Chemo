package medicinemanagement.application;

import java.util.ArrayList;

public class MedicineBean {

    //Parametri
    private String id;
    private String name;
    private String ingredients;
    private int amount;
    private ArrayList<Box> box;


    //Costruttori
    public MedicineBean() {
        box = new ArrayList<>();
    }

    public MedicineBean(String id, String name, String ingredients, int amount, ArrayList<Box> box) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.amount = amount;
        this.box = box;
    }

    //Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public int getAmount() {
        return amount;
    }

    public ArrayList<Box> getBox() {
        return box;
    }

    //Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setBox(ArrayList<Box> box) {
        this.box = box;
    }

    //Metodi ereditati da Object
    @Override
    public String toString() {
        return "MedicineBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", amount=" + amount +
                ", box=" + box +
                '}';
    }

    //Altri metodi
    public void addLotto(Box box) {
        this.box.add(box);
    }
}
