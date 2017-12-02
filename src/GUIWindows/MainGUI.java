package GUIWindows;
import UpdateTables.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by colto on 2017-11-26.
 */
public class MainGUI
{
    /**
     * This is the method that creates the main window of our GUI this is where all of the users will start after
     * logging in and where all the other tasks will start from.
     */
    public static void MainGUI(String name, int privilege)
    {
        UpdateInventory.setObservableList();
        UpdateCustomers.setObservableList();
        UpdateSuppliers.setObservableList();
        UpdatePartOrder.setObservableList();
        UpdateWorkOrder.setObservableList();
        UpdateEquipment.setObservableList();

        Stage primaryStage = new Stage();

        Text text = new Text("Error Area");

        TextArea errorArea = new TextArea();

        Button partOrder = new Button("Part Order");
        Button workOrder = new Button("Work Order");
        Button report = new Button("Report");
        Button inventory = new Button("Inventory");
        Button customers = new Button("Customers");
        Button suppliers = new Button("Suppliers");
        Button equipment = new Button("Equipment");
        Button user = new Button("User");
        Button test = new Button("Test");

        VBox vBox = new VBox(10, inventory, suppliers, customers, equipment, user);
        VBox vbButtons = new VBox(10, partOrder, workOrder, report, test);

        GridPane grid = new GridPane();

        Scene scene = new Scene(grid, 600, 400);

        partOrder.setMaxWidth(Double.MAX_VALUE);
        workOrder.setMaxWidth(Double.MAX_VALUE);
        report.setMaxWidth(Double.MAX_VALUE);
        inventory.setMaxWidth(Double.MAX_VALUE);
        equipment.setMaxWidth(Double.MAX_VALUE);
        customers.setMaxWidth(Double.MAX_VALUE);
        suppliers.setMaxWidth(Double.MAX_VALUE);
        user.setMaxWidth(Double.MAX_VALUE);
        test.setMaxWidth(Double.MAX_VALUE);

        primaryStage.setTitle("Inventory Management");

        errorArea.setEditable(false);

        vbButtons.setPadding(new Insets(0, 20, 10, 20));

        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));
        grid.add(vbButtons, 0,0,2,1);
        grid.add(text,2,2,2,1);
        grid.add(vBox, 2,0,2,1);
        grid.add(errorArea, 2,3,2,1);


        primaryStage.setScene(scene);

        //This button opens the part order window
        partOrder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                PartOrderWindow.PartOrderWindow(name, privilege);
            }
        });

        //This button opens the work order window.
        workOrder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                WorkOrderWindow.WorkOrderWindow(name, privilege);
            }
        });

        //This button opens the report window.
        report.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Report.Report(name, privilege);
            }
        });

        //This button opens the inventory window
        inventory.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Inventory.InventoryList(name, privilege);
            }
        });

        //This button opens the customers window
        customers.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Customers.CustomerList(name, privilege);
            }
        });

        //This button opens the suppliers window
        suppliers.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Suppliers.SupplierList(name, privilege);
            }
        });

        equipment.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                EquipmentWindow.EquipmentWindow(name, privilege);
            }
        });

        user.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UserWindow.userWindow(name, privilege);
            }
        });

        test.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Test.Test();
            }
        });
        primaryStage.show();
    }
}
