package medicinemanagement.application;

import java.util.ArrayList;

public class MedicineBean {

    //Parametri
    private String id;
    private String name;
    private String ingredients;
    private int amount;
    private ArrayList<BoxBean> boxes;


    //Costruttori
    public MedicineBean() {
        boxes = new ArrayList<>();
    }

    public MedicineBean(String name, String ingredients) {
        this.name = name;
        this.ingredients = ingredients;
        this.amount = 0;
        this.boxes = new ArrayList<>();
    }

    public MedicineBean(String id, String name, String ingredients, int amount, ArrayList<BoxBean> boxes) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.amount = amount;
        this.boxes = boxes;
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

    public BoxBean getBox(int index) {
        return boxes.get(index);
    }

    public ArrayList<BoxBean> getBoxes() {
        return boxes;
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

    public void setBox(ArrayList<BoxBean> boxes) {
        this.boxes = boxes;
    }

    //Metodi ereditati da Object
    @Override
    public String toString() {
        return "MedicineBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", amount=" + amount +
                ", boxes=" + boxes +
                '}';
    }

    //Altri metodi
    public void addBox(BoxBean box) {
        this.boxes.add(box);
    }
}
