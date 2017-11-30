package UpdateTables;

import Database.Management;
import Database.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

/**
 * Created by colto on 2017-11-29.
 */
public class UpdateSuppliers
{
    private static ObservableList suppliers;

    public static void setObservableList()
    {
        try {
            suppliers = FXCollections.observableArrayList(Management.readList("C:\\CPSC300\\CPSC300\\src\\Database\\suppliers.ser"));
        }
        catch (Exception io)
        {
            ArrayList array = new ArrayList();
            suppliers = FXCollections.observableArrayList(array);
        }
    }

    public static void updateSuppliers(TableView table, TableColumn name, TableColumn address, TableColumn phoneNumber,
                                       TableColumn email)
    {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));

        table.getItems().addAll(suppliers);
    }

    public static void addSupplier(Supplier supplier)
    {
        suppliers.addAll(supplier);
    }

}
