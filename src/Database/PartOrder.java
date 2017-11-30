package Database;


import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by colto on 2017-11-07.
 */
public class PartOrder implements Serializable
{
    private String orderType;
    private String cost;
    private ArrayList<Part> partsOrdered;
    private int number;
    private String supplier;
    private String orderBy;
    private char status;


    //constructor
    public PartOrder(String cost, int number, String orderBy, String orderType, String supplier) {
        this.cost = cost;
        this.number = number;
        this.supplier = supplier;
        this.orderBy = orderBy;
        this.orderType = orderType;
        this.partsOrdered = new ArrayList<>();

    }


    //getters and setters

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }


    public ArrayList<Part> getPartsOrdered() {
        return partsOrdered;
    }

    public void setPartsOrdered(Part part) {
        partsOrdered.add(part);
    }

    public void removePart (Part part){partsOrdered.remove(part);}

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
}
