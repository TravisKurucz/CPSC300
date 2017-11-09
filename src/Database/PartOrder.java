package Database;


import javafx.beans.property.SimpleStringProperty;
import java.util.ArrayList;

/**
 * Created by colto on 2017-11-07.
 */
public class PartOrder
{
    private SimpleStringProperty orderType;
    private SimpleStringProperty cost;
    private ArrayList<Part> partsOrdered;
    private int number;
    private SimpleStringProperty supplier;
    private SimpleStringProperty orderBy;


    //constructor
    public PartOrder(String cost, int number, String orderBy, String orderType, ArrayList<Part> partsOrdered, String supplier) {
        this.cost = new SimpleStringProperty(cost);
        this.number = number;
        this.supplier = new SimpleStringProperty(supplier);
        this.orderBy = new SimpleStringProperty(orderBy);
        this.orderType = new SimpleStringProperty(orderType);
        this.partsOrdered = partsOrdered;

    }


    //getters and setters

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getSupplier() {
        return supplier.get();
    }

    public void setSupplier(String supplier) {
        this.supplier.set(supplier);
    }

    public String getOrderBy() {
        return orderBy.get();
    }

    public void setOrderBy(String orderBy) {
        this.orderBy.set(orderBy);
    }

    public String getCost() {
        return cost.get();
    }

    public void setCost(String cost) {
        this.cost.set(cost);
    }

    public String getOrderType() {
        return orderType.get();
    }

    public void setOrderType(String orderType) {
        this.orderType.set(orderType);
    }

    public SimpleStringProperty supplierProperty() {
        return supplier;
    }

    public ArrayList<Part> getPartsOrdered() {
        return partsOrdered;
    }

    public void setPartsOrdered(ArrayList<Part> partsOrdered) {
        this.partsOrdered = partsOrdered;
    }
}
