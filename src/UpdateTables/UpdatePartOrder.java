package UpdateTables;

import Database.Management;
import Database.Part;
import Database.PartOrder;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import jdk.nashorn.internal.codegen.CompilerConstants;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by colton on 2017-11-07.
 * Used to update the tables found in the part order window.
 */
public class UpdatePartOrder
{
    //These list hold all the information that will be written to each table.
    private static ObservableList<PartOrder> pending;

    private static ObservableList<PartOrder> outstanding;

    private static ObservableList<PartOrder> finalized;


    public static void setObservableList()
    {
        try {
            ArrayList array = Management.readList("C:\\CPSC300\\CPSC300\\src\\Database\\partOrders.ser");
            ArrayList<PartOrder> array0 = new ArrayList();
            ArrayList<PartOrder> array1 = new ArrayList();
            ArrayList<PartOrder> array2 = new ArrayList<>();
            pending = FXCollections.observableArrayList(array0);
            outstanding = FXCollections.observableArrayList(array1);
            finalized = FXCollections.observableArrayList(array2);
            for(int i = 0; i<array.size(); i++)
            {
                Object obj = array.get(i);
                PartOrder order = (PartOrder) obj;
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
            ArrayList<PartOrder> array = new ArrayList();
            ArrayList<PartOrder> array1 = new ArrayList();
            ArrayList<PartOrder> array2 = new ArrayList<>();
            pending = FXCollections.observableArrayList(array);
            outstanding = FXCollections.observableArrayList(array1);
            finalized = FXCollections.observableArrayList(array2);
        }
    }

    public static void writeToFile()
    {
        File file = new File("C:\\CPSC300\\CPSC300\\src\\Database\\partOrders.ser");
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
    public static void addOrderPending(PartOrder order)
    {
        pending.addAll(order);
    }
    public static void addOrderFinalized(PartOrder order)
    {
        finalized.addAll(order);
    }
    public static void addOrderOutstanding(PartOrder order)
    {
        outstanding.addAll(order);
    }
    public static void removeOrderPending(PartOrder order)
    {
        pending.remove(order);
    }
    public static void removeOrderFinalized(PartOrder order)
    {
        finalized.remove(order);
    }
    public static void removeOrderOutstanding(PartOrder order)
    {
        outstanding.remove(order);
    }
    public static ObservableList getPending()
    {
        return pending;
    }
    /**
     * This method updates the tabs with all the pending part order information
     * @param table The table that all the columns are on.

     */
    public static void updatePending(TableView table)
    {
        //The table is created in a specific order so it is known what the format will be ahead of time so you can
        //name the columns here to create the cell factories.
        List<TableColumn> list = table.getColumns();
        TableColumn numberCol = list.get(0);
        TableColumn suppCol = list.get(1);
        TableColumn orderByCol = list.get(2);
        TableColumn costCol = list.get(3);
        TableColumn typeCol = list.get(4);
        TableColumn partsOrderedCol = list.get(5);
        numberCol.setCellValueFactory(
                new PropertyValueFactory<>("number"));
        suppCol.setCellValueFactory(
                new PropertyValueFactory<>("supplier"));
        orderByCol.setCellValueFactory(
                new PropertyValueFactory<>("orderBy"));
        costCol.setCellValueFactory(
                new PropertyValueFactory<>("cost"));
        typeCol.setCellValueFactory(
                new PropertyValueFactory<>("orderType"));
        partsOrderedCol.setCellValueFactory(
                new PropertyValueFactory<>("partsOrdered"));
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
        TableColumn typeCol = list.get(4);
        TableColumn partsOrderedCol = list.get(5);

        numberCol.setCellValueFactory(
                new PropertyValueFactory<>("number"));
        suppCol.setCellValueFactory(
                new PropertyValueFactory<>("supplier"));
        orderByCol.setCellValueFactory(
                new PropertyValueFactory<>("orderBy"));
        costCol.setCellValueFactory(
                new PropertyValueFactory<>("cost"));
        typeCol.setCellValueFactory(
                new PropertyValueFactory<>("orderType"));
        partsOrderedCol.setCellValueFactory(
                new PropertyValueFactory<>("partsOrdered"));
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
        TableColumn typeCol = list.get(4);
        TableColumn partsOrderedCol = list.get(5);

        numberCol.setCellValueFactory(
                new PropertyValueFactory<>("number"));
        suppCol.setCellValueFactory(
                new PropertyValueFactory<>("supplier"));
        orderByCol.setCellValueFactory(
                new PropertyValueFactory<>("orderBy"));
        costCol.setCellValueFactory(
                new PropertyValueFactory<>("cost"));
        typeCol.setCellValueFactory(
                new PropertyValueFactory<>("orderType"));
        partsOrderedCol.setCellValueFactory(
                new PropertyValueFactory<>("partsOrdered"));
        //Sets the items of each column from the observable list finalized.
        table.setItems(finalized);
    }

    public static void updateNewPartOrder(TableView table, TableColumn partNumber, TableColumn partDescription,
                                          TableColumn numberOrdered, PartOrder order)
    {
        partNumber.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Part, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Part, String> p) {
                return new SimpleStringProperty(p.getValue().getPartNumber());
            }
        });

        partDescription.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Part, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Part, String> p) {
                return new SimpleStringProperty(p.getValue().getName());
            }
        });

        numberOrdered.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Part, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Part, String> p) {
                return new SimpleStringProperty(String.valueOf(p.getValue().getNumberOrdered()));
            }
        });

        table.getItems().addAll(order.getPartsOrdered());

    }

    public static void updateFinalizedPartOrder(TableView table, PartOrder order)
    {
        List<TableColumn> list = table.getColumns();
        TableColumn partNumberCol = list.get(0);
        TableColumn nameCol = list.get(1);
        TableColumn amountOrderedCol = list.get(2);
        TableColumn amountReceivedCol = list.get(3);
        TableColumn actualCostCol = list.get(4);
        TableColumn invoiceNumberCol = list.get(5);
        TableColumn dateCol = list.get(6);


        partNumberCol.setCellValueFactory(new PropertyValueFactory<>("partNumber"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        amountOrderedCol.setCellValueFactory(new PropertyValueFactory<>("numberOrdered"));
        amountReceivedCol.setCellValueFactory(new PropertyValueFactory<>("amountReceived"));
        actualCostCol.setCellValueFactory(new PropertyValueFactory<>("suggestedCost"));
        invoiceNumberCol.setCellValueFactory(new PropertyValueFactory<>("invoiceNumber"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));



        table.getItems().addAll(order.getPartsOrdered());
    }


}
