package UpdateTables;

import Database.Management;
import Database.Part;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by colto on 2017-11-28.
 */
public class UpdateInventory
{
    private static ObservableList inventory;

    public static void setObservableList()
    {
        try {
            inventory = FXCollections.observableArrayList(Management.readList("C:/CPSC300/CPSC300/src/Database/parts.ser"));
        }
        catch (Exception io)
        {
            ArrayList array = new ArrayList();
            inventory = FXCollections.observableArrayList(array);
        }
    }

    public static void updateInventory(TableView table, TableColumn partNumber, TableColumn name,
                                       TableColumn suggestedCost, TableColumn numOnHand)
    {
        partNumber.setCellValueFactory(new PropertyValueFactory<>("partNumber"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        suggestedCost.setCellValueFactory(new PropertyValueFactory<>("suggestedCost"));
        numOnHand.setCellValueFactory(new PropertyValueFactory<>("numOnHand"));

        table.getItems().addAll(inventory);
    }

    public static void addPart(Part part)
    {
        inventory.addAll(part);
    }
}
