package GUIWindows;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by colto on 2017-11-26.
 */
public class Inventory
{
    /**
     * This is the window that lets you browse the inventory and add or edit parts if you have admin privileges.
     */
    //TODO add edit and save functionality.
    public static void Inventory()
    {
        Stage stage = new Stage();
        Text partNumber = new Text("Part Number");
        Text description = new Text("Description");
        Text discontinued = new Text("Discontinued");
        Text suggestCost = new Text("Suggested Cost");
        Text onHand = new Text("On Hand");

        TextArea partNumberArea = new TextArea();
        TextArea descriptionArea = new TextArea();
        TextArea suggestCostArea = new TextArea();
        TextArea onHandArea = new TextArea();
        CheckBox discontinuedBox = new CheckBox();

        Button save = new Button("Save");
        Button edit = new Button("Edit");

        partNumberArea.setMaxHeight(12);
        descriptionArea.setMaxHeight(12);
        suggestCostArea.setMaxHeight(12);
        onHandArea.setMaxHeight(12);

        partNumberArea.setWrapText(true);
        descriptionArea.setWrapText(true);
        suggestCostArea.setWrapText(true);
        onHandArea.setWrapText(true);

        partNumberArea.setMaxWidth(200);
        suggestCostArea.setMaxWidth(200);
        onHandArea.setMaxWidth(200);

        HBox dis = new HBox(10, discontinued, discontinuedBox);
        HBox hBox = new HBox(50, partNumberArea, dis);
        HBox hBox1 = new HBox(10, save, edit);
        HBox hBox2 = new HBox(10, suggestCostArea, onHandArea);
        HBox hBox3 = new HBox(130, suggestCost, onHand);
        VBox vBox = new VBox(10, partNumber, hBox,description, descriptionArea, hBox3, hBox2, hBox1);

        BorderPane borderPane = new BorderPane(vBox);
        borderPane.setPadding(new Insets(10));
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.setTitle("Inventory");
        stage.show();
    }
}
