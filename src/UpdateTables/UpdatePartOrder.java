package UpdateTables;

import PartOrders.PartOrder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import javax.swing.text.TableView;


/**
 * Created by colto on 2017-11-07.
 */
public class UpdatePartOrder
{
    private static ObservableList<PartOrder> data = FXCollections.observableArrayList(new PartOrder(123 , "Sup 1", "Bob")
            , new PartOrder(456, "Sup 2", "Joe"));
    public static void updatePending(TableColumn numberCol, TableColumn suppCol, TableColumn orderByCol)
    {
        numberCol.setCellValueFactory(
                new PropertyValueFactory<PartOrder, Integer>("number"));
    }
}
