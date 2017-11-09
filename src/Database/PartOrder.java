package Database;


import javafx.beans.property.SimpleStringProperty;

/**
 * Created by colto on 2017-11-07.
 */
public class PartOrder
{
    private int number;
    private SimpleStringProperty supplier;
    private SimpleStringProperty orderBy;

    public PartOrder(int number, String supplier, String orderBy)
    {
        this.number = number;
        this.supplier = new SimpleStringProperty(supplier);
        this.orderBy = new SimpleStringProperty(orderBy);
    }

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





}
