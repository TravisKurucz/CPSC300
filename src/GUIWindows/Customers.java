package GUIWindows;

import Database.Customer;
import Database.Management;
import UpdateTables.UpdateCustomers;
import UpdateTables.UpdateInventory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
public class Customers
{
    public static void CustomerList(String nameUser, int privilege, String path)
    {
        Stage stage = new Stage();

        Button add = new Button("Add");

        TableView table = new TableView();
        TableColumn name = new TableColumn("Name");
        TableColumn address = new TableColumn("Address");
        TableColumn phoneNumber = new TableColumn("Phone Number");
        TableColumn email = new TableColumn("Email");

        table.getColumns().addAll(name, address, phoneNumber, email);

        UpdateCustomers.updateCustomer(table, name, address, phoneNumber, email);

        BorderPane borderPane = new BorderPane(table);

        HBox hBox = new HBox(add);

        hBox.setAlignment(Pos.BOTTOM_CENTER);
        hBox.setPadding(new Insets(10));

        borderPane.setBottom(hBox);
        borderPane.setPadding(new Insets(10));

        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(privilege == 1) {
                    Customers(table, name, address, phoneNumber, email, nameUser, privilege, path);
                }

            }
        });

        Scene scene = new Scene(borderPane, 500, 600);

        stage.setTitle("Customers");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This is the window that opens to let you browse the customers and add or edit customers if you have
     * admin privileges.
     */
    public static void Customers(TableView table, TableColumn name, TableColumn address, TableColumn phoneNumber,
    TableColumn email, String nameUser, int privledge, String path)
    {
        Stage stage = new Stage();
        Text nameText = new Text("Name:");
        Text first = new Text("First:");
        Text last = new Text("Last:");
        Text addressText = new Text("Address:");
        Text emailText = new Text("Email:");
        Text phone = new Text("Phone Number:");
        Text error = new Text();

        TextArea firstName = new TextArea();
        TextArea lastName = new TextArea();
        TextArea emailArea = new TextArea();
        TextArea phoneArea = new TextArea();
        TextArea addressArea = new TextArea();

        Button save = new Button("Save");

        firstName.setMaxHeight(12);
        lastName.setMaxHeight(12);
        emailArea.setMaxHeight(12);
        phoneArea.setMaxHeight(12);
        addressArea.setMaxHeight(12);

        firstName.setWrapText(true);
        lastName.setWrapText(true);
        emailArea.setWrapText(true);
        phoneArea.setWrapText(true);
        addressArea.setWrapText(true);

        firstName.setMaxWidth(200);
        lastName.setMaxWidth(200);
        emailArea.setMaxWidth(200);
        phoneArea.setMaxWidth(200);
        addressArea.setMaxWidth(200);

        HBox names = new HBox(10, firstName, lastName);
        HBox hBox = new HBox(170, addressText, emailText);
        HBox hBox1 = new HBox(10, addressArea, emailArea);
        HBox hBox2 = new HBox(10, save);
        HBox hBox3 = new HBox(190, first, last);

        VBox vBox = new VBox(10, nameText, hBox3, names,hBox, hBox1, phone, phoneArea, error, hBox2);

        BorderPane borderPane = new BorderPane(vBox);

        hBox2.setAlignment(Pos.BOTTOM_CENTER);

        borderPane.setPadding(new Insets(10));

        Scene scene = new Scene(borderPane);

        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (firstName.getText().trim().isEmpty() || lastName.getText().trim().isEmpty() || addressArea.getText().trim().isEmpty() ||
                        emailArea.getText().trim().isEmpty() || phoneArea.getText().trim().isEmpty()) {
                    error.setText("Please make sure all the areas are filled in");
                } else {
                    Customer customer = new Customer();
                    customer.setName(firstName.getText() + " " + lastName.getText());
                    customer.setAddress(addressArea.getText());
                    customer.setEmail(emailArea.getText());
                    customer.setPhoneNumber(phoneArea.getText());

                    UpdateCustomers.addCustomer(customer);
                    for (int i = 0; i < table.getItems().size(); i++) {
                        table.getItems().clear();
                    }
                    UpdateCustomers.updateCustomer(table, name, address, phoneNumber, email);
                    Management.addObject(path + "\\customers.ser", customer);
                    stage.close();
                }
            }
        });


        stage.setScene(scene);
        stage.setTitle("Customers");
        stage.show();

    }
}
