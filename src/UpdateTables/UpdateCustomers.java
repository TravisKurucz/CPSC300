package UpdateTables;

import Database.Customer;
import Database.Management;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

/**
 * Created by colto on 2017-11-29.
 */
public class UpdateCustomers
{
    private static ObservableList customers;

    public static void setObservableList()
    {
        try {
            customers = FXCollections.observableArrayList(Management.readList("C:/CPSC300/CPSC300/src/Database/customers.ser"));
        }
        catch (Exception io)
        {
            ArrayList array = new ArrayList();
            customers = FXCollections.observableArrayList(array);
        }
    }
    public static void updateCustomer(TableView table, TableColumn name, TableColumn address, TableColumn phoneNumber,
                                      TableColumn email)
    {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));

        table.getItems().addAll(customers);
    }

    public static void addCustomer(Customer customer)
    {
        customers.add(customer);
    }
}
