package UpdateTables;

import Database.Part;
import Database.PartOrder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;

/**
 * Created by colton on 2017-11-07.
 */
public class UpdatePartOrder
{
    //These list hold all the information that will be written to each table.
    private static ObservableList<PartOrder> pending = FXCollections.observableArrayList(new PartOrder("123" ,25, "Bob", "Inventory", new ArrayList<Part>(0), "NAPA" )
            , new PartOrder("123" , 66, "Smith", "WorkOrder", new ArrayList<Part>(0), "Aro" ));

    private static ObservableList<PartOrder> outstanding = FXCollections.observableArrayList(new PartOrder("456" ,25, "Bob", "Inventory", new ArrayList<Part>(0), "NAPA" )
            , new PartOrder("456" , 66, "Smith", "WorkOrder", new ArrayList<Part>(0), "Aro" ));

    private static ObservableList<PartOrder> finalized = FXCollections.observableArrayList(new PartOrder("789" ,25, "Bob", "Inventory", new ArrayList<Part>(0), "NAPA" )
            , new PartOrder("789" , 66, "Smith", "WorkOrder", new ArrayList<Part>(0), "Aro" ));

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


}
