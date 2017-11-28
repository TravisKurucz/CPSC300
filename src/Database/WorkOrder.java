package Database;


import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

/**
 * Created by Travis on 2017-11-09.
 */
public class WorkOrder implements java.io.Serializable
{
    private SimpleStringProperty unitNumber;
    private SimpleStringProperty unitName;
    private SimpleStringProperty costCode;
    private ArrayList<PartOrder> partOrders;
    private ArrayList<Part> parts;


    public WorkOrder(String unitNumber, String unitName, String costCode) {
        this.unitNumber = new SimpleStringProperty(unitNumber);
        this.unitName = new SimpleStringProperty(unitName);
        this.costCode = new SimpleStringProperty(costCode);
        partOrders = new ArrayList<>();
        parts = new ArrayList<>();
    }

    public String getUnitNumber() {
        return unitNumber.get();
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber.set(unitNumber);
    }

    public String getUnitName() {
        return unitName.get();
    }

    public void setUnitName(String unitName) {
        this.unitName.set(unitName);
    }

    public String getCostCode() {
        return costCode.get();
    }

    public void setCostCode(String costCode) {
        this.costCode.set(costCode);
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
}
