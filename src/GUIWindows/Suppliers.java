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
public class Suppliers
{
    /**
     * This is the window that opens to let you browse suppliers and add or edit suppliers if you have admin
     * privileges.
     */
    public static void Suppliers()
    {
        Stage stage = new Stage();
        Text supplierCode = new Text("Supplier Code:");
        Text address = new Text("Address:");
        Text email = new Text("Email:");
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

        HBox hBox = new HBox(170, address, email);
        HBox hBox1 = new HBox(10, addressArea, emailArea);
        HBox hBox2 = new HBox(10, save, edit);

        VBox vBox = new VBox(10, supplierCode, supplierCodeArea, hBox, hBox1, phone, phoneArea, hBox2);

        BorderPane borderPane = new BorderPane(vBox);

        borderPane.setPadding(new Insets(10));

        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.setTitle("Suppliers");
        stage.show();
    }
}
