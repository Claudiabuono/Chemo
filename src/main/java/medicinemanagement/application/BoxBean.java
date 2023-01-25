package medicinemanagement.application;

import java.util.Date;

public class BoxBean {

    //Parametri
    private boolean status;
    private Date expiryDate;
    private int capacity;
    private String boxId;

    //Costruttori
    public BoxBean() {
    }

    public BoxBean(boolean status, Date expiryDate, int capacity, String boxId) {
        this.status = status;
        this.expiryDate = expiryDate;
        this.capacity = capacity;
        this.boxId = boxId;
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

    public String getBoxId() {
        return boxId;
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

    public void setBoxId(String boxId) {
        this.boxId = boxId;
    }

    //Metodi ereditati da Object

    @Override
    public String toString() {
        return "StockBean{" +
                "status=" + status +
                ", expiryDate=" + expiryDate +
                ", capacity=" + capacity +
                ", boxId='" + boxId + '\'' +
                '}';
    }
}
