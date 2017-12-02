package UpdateTables;

import Database.Customer;
import Database.Equipment;
import Database.Management;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

/**
 * Created by colto on 2017-12-01.
 */
public class UpdateEquipment
{
    private static ObservableList equipment;

    public static void setObservableList(String path)
    {
        try {
            equipment = FXCollections.observableArrayList(Management.readList(path + "\\Equipment.ser"));
        }
        catch (Exception io)
        {
            ArrayList array = new ArrayList();
            equipment = FXCollections.observableArrayList(array);
        }
    }
    public static void updateEquipment(TableView table, TableColumn unitNumber, TableColumn nameAndYear, TableColumn odometer)
    {
        unitNumber.setCellValueFactory(new PropertyValueFactory<>("unitNumber"));
        nameAndYear.setCellValueFactory(new PropertyValueFactory<>("nameAndYear"));
        odometer.setCellValueFactory(new PropertyValueFactory<>("odometer"));

        table.getItems().addAll(equipment);
    }

    public static void addEquipment(Equipment equip)
    {
        equipment.add(equip);
    }
}

