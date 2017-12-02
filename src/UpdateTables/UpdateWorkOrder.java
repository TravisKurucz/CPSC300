package UpdateTables;

import Database.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by colto on 2017-11-26.
 */
public class UpdateWorkOrder
{
    private static ObservableList<WorkOrder> pending;
    private static ObservableList<WorkOrder> outstanding;
    private static ObservableList<WorkOrder> finalized;


    public static void setObservableList(String path)
    {
        try {
            ArrayList array = Management.readList(path + "\\workOrders.ser");
            ArrayList<WorkOrder> array0 = new ArrayList();
            ArrayList<WorkOrder> array1 = new ArrayList();
            ArrayList<WorkOrder> array2 = new ArrayList<>();
            pending = FXCollections.observableArrayList(array0);
            outstanding = FXCollections.observableArrayList(array1);
            finalized = FXCollections.observableArrayList(array2);
            for(int i = 0; i<array.size(); i++)
            {
                Object obj = array.get(i);
                WorkOrder order = (WorkOrder) obj;
                switch (order.getStatus())
                {
                    case ('P'):
                        pending.add(order);
                        break;
                    case  ('O'):
                        outstanding.add(order);
                        break;
                    case ('F'):
                        finalized.add(order);
                        break;
                }
            }
        }
        catch (Exception io)
        {
            ArrayList<WorkOrder> array = new ArrayList();
            ArrayList<WorkOrder> array1 = new ArrayList();
            ArrayList<WorkOrder> array2 = new ArrayList<>();
            pending = FXCollections.observableArrayList(array);
            outstanding = FXCollections.observableArrayList(array1);
            finalized = FXCollections.observableArrayList(array2);
        }
    }

    public static ObservableList getFinalized(){return finalized;}

    public static void writeToFile(String path)
    {
        File file = new File(path + "\\workOrders.ser");
        ArrayList array = new ArrayList();
        array.addAll(pending);
        array.addAll(outstanding);
        array.addAll(finalized);
        try{
            Management.writeList(file, array);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void updatePending(TableView table)
    {
        //The table is created in a specific order so it is known what the format will be ahead of time so you can
        //name the columns here to create the cell factories.
        List<TableColumn> list = table.getColumns();
        TableColumn numberCol = list.get(0);
        TableColumn suppCol = list.get(1);
        TableColumn orderByCol = list.get(2);
        TableColumn costCol = list.get(3);
        numberCol.setCellValueFactory(
                new PropertyValueFactory<>("unitNumber"));
        suppCol.setCellValueFactory(
                new PropertyValueFactory<>("unitName"));
        orderByCol.setCellValueFactory(
                new PropertyValueFactory<>("costCode"));
        costCol.setCellValueFactory(
                new PropertyValueFactory<>("partOrders"));
        //Sets the items of each column from the observable list pending.
        table.setItems(pending);
    }

    /**
     * This method updates the tabs with all the outstanding part order information
     * @param table The table that all the columns are on.
     */
    public static void updateOutstanding(TableView table)
    {
        //The table is created in a specific order so it is known what the format will be ahead of time so you can
        //name the columns here to create the cell factories.
        List<TableColumn> list = table.getColumns();
        TableColumn numberCol = list.get(0);
        TableColumn suppCol = list.get(1);
        TableColumn orderByCol = list.get(2);
        TableColumn costCol = list.get(3);

        numberCol.setCellValueFactory(
                new PropertyValueFactory<>("unitNumber"));
        suppCol.setCellValueFactory(
                new PropertyValueFactory<>("unitName"));
        orderByCol.setCellValueFactory(
                new PropertyValueFactory<>("costCode"));
        costCol.setCellValueFactory(
                new PropertyValueFactory<>("partOrders"));
        //Sets the items of each column from the observable list outstanding.
        table.setItems(outstanding);
    }

    /**
     * This method updates the tabs with all the finalized part order information
     * @param table The table that all the columns are on.
     */
    public static void updateFinalized(TableView table)
    {
        //The table is created in a specific order so it is known what the format will be ahead of time so you can
        //name the columns here to create the cell factories.
        List<TableColumn> list = table.getColumns();
        TableColumn numberCol = list.get(0);
        TableColumn suppCol = list.get(1);
        TableColumn orderByCol = list.get(2);
        TableColumn costCol = list.get(3);

        numberCol.setCellValueFactory(
                new PropertyValueFactory<>("unitNumber"));
        suppCol.setCellValueFactory(
                new PropertyValueFactory<>("unitName"));
        orderByCol.setCellValueFactory(
                new PropertyValueFactory<>("costCode"));
        costCol.setCellValueFactory(
                new PropertyValueFactory<>("partOrders"));
        //Sets the items of each column from the observable list finalized.
        table.setItems(finalized);
    }
    public static void updateWorkOrder(TableView table, WorkOrder order)
    {
        List<TableColumn> list = table.getColumns();
        TableColumn typeCol = list.get(0);
        TableColumn partNumberCol = list.get(1);
        TableColumn partNameCol = list.get(2);
        TableColumn amountCol = list.get(3);
        TableColumn costCol = list.get(4);

        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        partNumberCol.setCellValueFactory(new PropertyValueFactory<>("partNumber"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("numberOrdered"));
        costCol.setCellValueFactory(new PropertyValueFactory<>("suggestedCost"));

        table.getItems().addAll(order.getParts());
    }

    public static void addPending(WorkOrder order)
    {
        pending.add(order);
    }
    public static void addOutstanding(WorkOrder order)
    {
        outstanding.add(order);
    }
    public static void addFinalize(WorkOrder order)
    {
        finalized.add(order);
    }
    public static void removePending(WorkOrder order)
    {
        pending.remove(order);
    }
    public static void removeOutstanding(WorkOrder order)
    {
        outstanding.remove(order);
    }
    public static void removeFinalized(WorkOrder order)
    {
        finalized.remove(order);
    }

    public static void updateWorkOrderFinalized(TableView tableView, WorkOrder order)
    {
        List<TableColumn> list  = tableView.getColumns();
        TableColumn number = list.get(0);
        TableColumn supplier = list.get(1);
        TableColumn orderedBy = list.get(2);

        number.setCellValueFactory(new PropertyValueFactory<>("number"));
        supplier.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        orderedBy.setCellValueFactory(new PropertyValueFactory<>("orderBy"));

        tableView.getItems().addAll(order.getPartsOrderArray());
    }

}
