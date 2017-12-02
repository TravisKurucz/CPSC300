package UpdateTables;

import Database.Management;
import Database.Part;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by colto on 2017-11-28.
 */
public class UpdateInventory
{
    private static ObservableList inventory;

    public static void setObservableList(String path)
    {
        try {
            inventory = FXCollections.observableArrayList(Management.readList(path + "/parts.ser"));
        }
        catch (Exception io)
        {
            ArrayList array = new ArrayList();
            inventory = FXCollections.observableArrayList(array);
        }
    }
    public static void writeFile(String path)
    {
        File file = new File(path + "\\parts.ser");
        ArrayList array = new ArrayList();
        array.addAll(inventory);
        try{
            Management.writeList(file, array);
        }
        catch (Exception e)
        {
            e.printStackTrace();
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

    public static boolean contains(String number)
    {
        for(Object obj: inventory)
        {
            Part part = (Part) obj;
            if (part.getPartNumber().trim().equals(number))
            {
                return true;
            }
        }
        return false;
    }

    public static Part getPart(String number)
    {
        for(Object obj: inventory)
        {
            Part part = (Part) obj;
            if (part.getPartNumber().equals(number))
            {
                return part;
            }
        }
        return null;
    }

    public static ObservableList getArray()
    {
        return inventory;
    }

}
