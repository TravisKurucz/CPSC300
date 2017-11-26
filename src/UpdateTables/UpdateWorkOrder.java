package UpdateTables;

import Database.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

/**
 * Created by colto on 2017-11-26.
 */
public class UpdateWorkOrder
{
    private static ObservableList<WorkOrder> pending = FXCollections.observableArrayList(new Database.WorkOrder("123", "Name", "12"));
    private static ObservableList<WorkOrder> outstanding = FXCollections.observableArrayList(new Database.WorkOrder("123", "Name", "12"));
    private static ObservableList<WorkOrder> finalized = FXCollections.observableArrayList(new Database.WorkOrder("123", "Name", "12"));


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
    public static void updateWorkOrder(TableView tableView)
    {

    }
}
