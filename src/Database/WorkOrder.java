package Database;


import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

/**
 * Created by Travis on 2017-11-09.
 */
public class WorkOrder implements java.io.Serializable
{
    private String unitNumber;
    private String unitName;
    private String costCode;
    private char status;
    private ArrayList<PartOrder> partOrders;
    private ArrayList<Part> parts;


    public WorkOrder(String unitNumber, String unitName, String costCode) {
        this.unitNumber = unitNumber;
        this.unitName = unitName;
        this.costCode = costCode;
        partOrders = new ArrayList<>();
        parts = new ArrayList<>();
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getCostCode() {
        return costCode;
    }

    public void setCostCode(String costCode) {
        this.costCode = costCode;
    }

    public PartOrder getPartOrder() {
        return partOrders.get(0);
    }

    public ArrayList<PartOrder> getPartsOrderArray()
    {
        return partOrders;
    }

    public void addPartOrders(PartOrder partOrder) {
        this.partOrders.add(partOrder);
    }

    public ArrayList<Part> getParts() {
        return parts;
    }

    public void addParts(Part parts) {
        this.parts.add(parts);
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
}
