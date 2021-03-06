package GUIWindows;

import Database.Management;
import Database.Part;
import UpdateTables.UpdateInventory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    public static void InventoryList(String nameUser, int privledge)
    {

        Stage stage = new Stage();

        Button add = new Button("Add");

        TableView table = new TableView();
        TableColumn partNumber = new TableColumn("Part Number");
        TableColumn name = new TableColumn("Name");
        TableColumn suggestedCost = new TableColumn("Suggested Cost");
        TableColumn numOnHand = new TableColumn("On Hand");

        HBox hBox = new HBox(add);

        table.getColumns().addAll(partNumber, name, suggestedCost, numOnHand);

        UpdateInventory.updateInventory(table, partNumber, name, suggestedCost, numOnHand);

        BorderPane borderPane = new BorderPane(table);

        hBox.setAlignment(Pos.BOTTOM_CENTER);
        hBox.setPadding(new Insets(10));

        borderPane.setBottom(hBox);
        borderPane.setPadding(new Insets(10));

        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(privledge == 1) {
                    Inventory(table, partNumber, name, suggestedCost, numOnHand, nameUser, privledge);
                }

            }
        });

        Scene scene = new Scene(borderPane, 500, 600);

        stage.setTitle("Inventory");
        stage.setScene(scene);
        stage.show();
    }
    /**
     * This is the window that lets you browse the inventory and add or edit parts if you have admin privileges.
     */
    //TODO add edit and save functionality.
    public static void Inventory(TableView table, TableColumn partNumber, TableColumn name,
                                 TableColumn suggestedCost, TableColumn numOnHand, String nameUser, int privledge)
    {
        Stage stage = new Stage();
        Text partNumberName = new Text("Part Number");
        Text description = new Text("Description");
        Text discontinued = new Text("Discontinued");
        Text suggestCostText = new Text("Suggested Cost");
        Text onHand = new Text("On Hand");
        Text error = new Text();

        TextArea partNumberArea = new TextArea();
        TextArea descriptionArea = new TextArea();
        TextArea suggestCostArea = new TextArea();
        TextArea onHandArea = new TextArea();
        CheckBox discontinuedBox = new CheckBox();

        Button save = new Button("Save");

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
        HBox hBox1 = new HBox(10, save);
        HBox hBox2 = new HBox(10, suggestCostArea, onHandArea);
        HBox hBox3 = new HBox(130, suggestCostText, onHand);

        hBox1.setAlignment(Pos.BOTTOM_CENTER);

        VBox vBox = new VBox(10, partNumberName, hBox,description, descriptionArea, hBox3, hBox2, error, hBox1);

        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (partNumberArea.getText().trim().isEmpty() || descriptionArea.getText().trim().isEmpty() ||
                        suggestCostArea.getText().trim().isEmpty() || onHandArea.getText().trim().isEmpty()) {
                    error.setText("Please make sure all the areas are filled");
                } else {
                    Part part = new Part(partNumberArea.getText(), descriptionArea.getText(), suggestCostArea.getText()
                            , Integer.valueOf(onHandArea.getText()));
                    UpdateInventory.addPart(part);
                    for (int i = 0; i < table.getItems().size(); i++) {
                        table.getItems().clear();
                    }
                    UpdateInventory.updateInventory(table, partNumber, name, suggestedCost, numOnHand);
                    Management.addObject("C:/CPSC300/CPSC300/src/Database/parts.ser", part);
                    stage.close();
                }
            }
        });

        BorderPane borderPane = new BorderPane(vBox);
        borderPane.setPadding(new Insets(10));
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.setTitle("Inventory");
        stage.show();
    }
}
