package GUIWindows;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    /**
     * This is the window that opens to let you browse the customers and add or edit customers if you have
     * admin privileges.
     */
    public static void Customers()
    {
        Stage stage = new Stage();
        Text name = new Text("Name:");
        Text first = new Text("First:");
        Text last = new Text("Last:");
        Text address = new Text("Address:");
        Text email = new Text("Email:");
        Text phone = new Text("Phone Number:");

        TextArea firstName = new TextArea();
        TextArea lastName = new TextArea();
        TextArea emailArea = new TextArea();
        TextArea phoneArea = new TextArea();
        TextArea addressArea = new TextArea();

        Button save = new Button("Save");
        Button edit = new Button("Edit");

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
        HBox hBox = new HBox(170, address, email);
        HBox hBox1 = new HBox(10, addressArea, emailArea);
        HBox hBox2 = new HBox(10, save, edit);
        HBox hBox3 = new HBox(190, first, last);

        VBox vBox = new VBox(10, name, hBox3, names,hBox, hBox1, phone, phoneArea, hBox2);

        BorderPane borderPane = new BorderPane(vBox);

        borderPane.setPadding(new Insets(10));

        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.setTitle("Customers");
        stage.show();

    }
}
