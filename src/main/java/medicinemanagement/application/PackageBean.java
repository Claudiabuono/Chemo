package medicinemanagement.application;

import java.util.Date;

public class PackageBean {

    //Parametri
    private boolean status;
    private Date expiryDate;
    private int capacity;
    private String packageId;

    //Costruttori
    public PackageBean() {
    }

    public PackageBean(boolean status, Date expiryDate, int capacity, String packageId) {
        this.status = status;
        this.expiryDate = expiryDate;
        this.capacity = capacity;
        this.packageId = packageId;
    }

    //Getters

    public boolean getStatus() {
        return status;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getPackageId() {
        return packageId;
    }


    //Setters
    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    //Metodi ereditati da Object

    @Override
    public String toString() {
        return "PackageBean{" +
                "status=" + status +
                ", expiryDate=" + expiryDate +
                ", capacity=" + capacity +
                ", packageId='" + packageId + '\'' +
                '}';
    }
}
