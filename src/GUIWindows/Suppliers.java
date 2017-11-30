package GUIWindows;

import Database.Management;
import Database.Supplier;
import UpdateTables.UpdateCustomers;
import UpdateTables.UpdateSuppliers;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by colto on 2017-11-26.
 */
public class Suppliers
{


    public static void SupplierList()
    {
        Stage stage = new Stage();

        Button add = new Button("Add");

        TableView table = new TableView();
        TableColumn name = new TableColumn("Name");
        TableColumn address = new TableColumn("Address");
        TableColumn phoneNumber = new TableColumn("Phone Number");
        TableColumn email = new TableColumn("Email");

        table.getColumns().addAll(name, address, phoneNumber, email);

        UpdateSuppliers.updateSuppliers(table, name, address, phoneNumber, email);

        BorderPane borderPane = new BorderPane(table);

        borderPane.setBottom(add);

        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Suppliers(table, name, address, phoneNumber, email);

            }
        });

        Scene scene = new Scene(borderPane);

        stage.setTitle("Suppliers");
        stage.setScene(scene);
        stage.show();
    }
    /**
     * This is the window that opens to let you browse suppliers and add or edit suppliers if you have admin
     * privileges.
     */
    public static void Suppliers(TableView table, TableColumn name, TableColumn address, TableColumn phoneNumber,
                                 TableColumn email)
    {
        Stage stage = new Stage();
        Text supplierCode = new Text("Supplier Code:");
        Text addressText = new Text("Address:");
        Text emailText = new Text("Email:");
        Text phone = new Text("Phone Number:");

        TextArea supplierCodeArea = new TextArea();
        TextArea phoneArea = new TextArea();
        TextArea addressArea = new TextArea();
        TextArea emailArea = new TextArea();

        Button save = new Button("Save");
        Button edit = new Button("Edit");

        supplierCodeArea.setMaxHeight(12);
        phoneArea.setMaxHeight(12);
        addressArea.setMaxHeight(12);
        emailArea.setMaxHeight(12);

        supplierCodeArea.setWrapText(true);
        phoneArea.setWrapText(true);
        addressArea.setWrapText(true);
        emailArea.setWrapText(true);

        supplierCodeArea.setMaxWidth(200);
        phoneArea.setMaxWidth(200);
        addressArea.setMaxWidth(200);
        emailArea.setMaxWidth(200);

        HBox hBox = new HBox(170, addressText, emailText);
        HBox hBox1 = new HBox(10, addressArea, emailArea);
        HBox hBox2 = new HBox(10, save, edit);

        VBox vBox = new VBox(10, supplierCode, supplierCodeArea, hBox, hBox1, phone, phoneArea, hBox2);

        BorderPane borderPane = new BorderPane(vBox);

        borderPane.setPadding(new Insets(10));

        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Supplier supplier = new Supplier(addressArea.getText(), emailArea.getText(), supplierCodeArea.getText(),
                        phoneArea.getText());
                UpdateSuppliers.addSupplier(supplier);
                for ( int i = 0; i<table.getItems().size(); i++) {
                    table.getItems().clear();
                }
                UpdateSuppliers.updateSuppliers(table, name, address, phoneNumber, email);
                Management.addObject("C:\\CPSC300\\CPSC300\\src\\Database\\suppliers.ser", supplier);
                stage.close();
            }
        });
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.setTitle("Suppliers");
        stage.show();
    }
}
