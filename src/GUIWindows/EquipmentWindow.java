package GUIWindows;

import Database.Customer;
import Database.Equipment;
import Database.Management;
import UpdateTables.UpdateCustomers;
import UpdateTables.UpdateEquipment;
import UpdateTables.UpdateInventory;
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
 * Created by colto on 2017-12-01.
 */
public class EquipmentWindow
{
    public static void EquipmentWindow(String name, int privilege, String path)
    {
        Stage stage = new Stage();

        Button add = new Button("Add");

        TableView table = new TableView();
        TableColumn unitNumber = new TableColumn("Unit Number");
        TableColumn nameAndYear = new TableColumn("Name and Year");
        TableColumn odometer = new TableColumn("Odometer");

        table.getColumns().addAll(unitNumber, nameAndYear, odometer);

        UpdateEquipment.updateEquipment(table, unitNumber, nameAndYear, odometer);

        BorderPane borderPane = new BorderPane(table);

        borderPane.setBottom(add);

        Scene scene = new Scene(borderPane);

        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(privilege == 1) {
                    newEquipment(table, unitNumber, nameAndYear, odometer, name, privilege, path);
                }
            }
        });

        stage.setScene(scene);
        stage.setTitle("Equipment");
        stage.show();
    }
    private static void newEquipment(TableView table, TableColumn unitNumber, TableColumn nameAndYear,
                                     TableColumn odometer, String name, int privledge, String path)
    {
        Stage stage = new Stage();
        Text unitNumberText = new Text("Unit Number:");
        Text nameAndYearText = new Text("Name and Year:");
        Text odometerText = new Text("Odometer:");

        TextArea unitNumberArea = new TextArea();
        TextArea nameAndYearArea = new TextArea();
        TextArea odometerArea = new TextArea();

        Button save = new Button("Save");

        unitNumberArea.setMaxHeight(12);
        nameAndYearArea.setMaxHeight(12);
        odometerArea.setMaxHeight(12);

        unitNumberArea.setWrapText(true);
        nameAndYearArea.setWrapText(true);
        odometerArea.setWrapText(true);

        unitNumberArea.setMaxWidth(200);
        nameAndYearArea.setMaxWidth(200);
        odometerArea.setMaxWidth(200);


        HBox hBox2 = new HBox(10, save);

        VBox vBox = new VBox(10, unitNumberText,unitNumberArea, nameAndYearText,nameAndYearArea,
                odometerText, odometerArea, hBox2);

        BorderPane borderPane = new BorderPane(vBox);

        borderPane.setPadding(new Insets(10));

        Scene scene = new Scene(borderPane);

        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Equipment equipment = new Equipment(unitNumberArea.getText(), nameAndYearArea.getText(),
                        Integer.valueOf(odometerArea.getText()));
                UpdateEquipment.addEquipment(equipment);

                for ( int i = 0; i<table.getItems().size(); i++) {
                    table.getItems().clear();
                }
                UpdateEquipment.updateEquipment(table, unitNumber, nameAndYear, odometer);

                Management.addObject(path + "\\Equipment.ser", equipment);
                stage.close();
            }
        });


        stage.setScene(scene);
        stage.setTitle("New Equipment");
        stage.show();
    }
}

