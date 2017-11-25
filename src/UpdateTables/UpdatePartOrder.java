package UpdateTables;

import Database.Part;
import Database.PartOrder;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by colton on 2017-11-07.
 */
public class UpdatePartOrder
{
    //These list hold all the information that will be written to each table.
    private static ObservableList<PartOrder> pending = FXCollections.observableArrayList(new PartOrder("123" ,25, "Bob", "Inventory", "NAPA" )
            , new PartOrder("123" , 66, "Smith", "WorkOrder", "Aro" ));

    private static ObservableList<PartOrder> outstanding = FXCollections.observableArrayList(new PartOrder("456" ,25, "Bob", "Inventory", "NAPA" )
            , new PartOrder("456" , 66, "Smith", "WorkOrder", "Aro" ));

    private static ObservableList<PartOrder> finalized = FXCollections.observableArrayList(new PartOrder("789" ,25, "Bob", "Inventory", "NAPA" )
            , new PartOrder("789" , 66, "Smith", "WorkOrder", "Aro" ));

    public static void addOrderPending(PartOrder order)
    {
        pending.addAll(order);
    }
    /**
     * This method updates the tabs with all the pending part order information
     * @param table The table that all the columns are on.
     * @param numberCol column containing the numbers of the part orders
     * @param suppCol column containing the suppliers of the part orders
     * @param orderByCol column containing the name of the person who ordered the part
     * @param costCol the cost of the order
     * @param typeCol the typr of the order
     * @param partsOrderedCol list of the parts ordered
     */
    public static void updatePending(TableView table, TableColumn numberCol, TableColumn suppCol, TableColumn orderByCol,
                                     TableColumn costCol, TableColumn typeCol, TableColumn partsOrderedCol)
    {
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
     * @param numberCol column containing the numbers of the part orders
     * @param suppCol column containing the suppliers of the part orders
     * @param orderByCol column containing the name of the person who ordered the part
     * @param costCol the cost of the order
     * @param typeCol the typr of the order
     * @param partsOrderedCol list of the parts ordered
     */
    public static void updateOutstanding(TableView table, TableColumn numberCol, TableColumn suppCol, TableColumn orderByCol,
                                     TableColumn costCol, TableColumn typeCol, TableColumn partsOrderedCol)
    {
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
     * @param numberCol column containing the numbers of the part orders
     * @param suppCol column containing the suppliers of the part orders
     * @param orderByCol column containing the name of the person who ordered the part
     * @param costCol the cost of the order
     * @param typeCol the typr of the order
     * @param partsOrderedCol list of the parts ordered
     */
    public static void updateFinalized(TableView table, TableColumn numberCol, TableColumn suppCol, TableColumn orderByCol,
                                         TableColumn costCol, TableColumn typeCol, TableColumn partsOrderedCol)
    {
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

        table.getItems().addAll(order.getPartsOrdered());

    }

}
