package UpdateTables;

import Database.Part;
import Database.PartOrder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;


/**
 * Created by colto on 2017-11-07.
 */
public class UpdatePartOrder
{
    private static ObservableList<PartOrder> data = FXCollections.observableArrayList(new PartOrder("123" , 25, "Bob", "Inventory", new ArrayList<Part>(0), "NAPA" )
            , new PartOrder("123" , 66, "Smith", "WorkOrder", new ArrayList<Part>(0), "Aro" ));
    public static void updatePending(TableColumn numberCol, TableColumn suppCol, TableColumn orderByCol)
    {
        numberCol.setCellValueFactory(
                new PropertyValueFactory<PartOrder, Integer>("number"));
    }
}
