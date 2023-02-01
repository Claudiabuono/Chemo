package medicinemanagement.application;

import java.util.ArrayList;

public class MedicineBean {

    //Parametri
    private String id;
    private String name;
    private String ingredients;
    private int amount;
    private ArrayList<PackageBean> packages;


    //Costruttori
    public MedicineBean() {
        packages = new ArrayList<>();
    }

    public MedicineBean(String name, String ingredients) {
        this.name = name;
        this.ingredients = ingredients;
        this.amount = 0;
        this.packages = new ArrayList<>();
    }

    public MedicineBean(String id, String name, String ingredients, int amount, ArrayList<PackageBean> packages) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.amount = amount;
        this.packages = packages;
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

    public PackageBean getPackage(int index) {
        return packages.get(index);
    }

    public ArrayList<PackageBean> getPackages() {
        return packages;
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

    public void setPackages(ArrayList<PackageBean> packages) {
        this.packages = packages;
    }

    //Metodi ereditati da Object
    @Override
    public String toString() {
        return "MedicineBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", amount=" + amount +
                ", packages=" + packages +
                '}';
    }

    //Altri metodi
    public void addPackage(PackageBean newPackage) {
        this.packages.add(newPackage);
    }
}
