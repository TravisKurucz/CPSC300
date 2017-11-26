package GUIWindows;

import Database.Part;
import UpdateTables.UpdatePartOrder;
import UpdateTables.UpdateWorkOrder;
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

import java.util.Random;

/**
 * Created by colto on 2017-11-26.
 */
public class WorkOrderWindow
{
    public static void WorkOrderWindow()
    {
        Stage stage = new Stage();

        BorderPane borderPending = new BorderPane();

        Tab pending = new Tab("Pending");
        Tab outstanding = new Tab("Outstanding");
        Tab finalized = new Tab("Finalized");

        TableView<Database.PartOrder> tableViewPending = new TableView<>();
        TableView<Database.PartOrder> tableViewOutstanding = new TableView();
        TableView<Database.PartOrder> tableViewFinalized = new TableView();

        Button editSelected = new Button("Edit Selected");
        Button createNew = new Button("Create New");

        HBox buttons = new HBox(10, editSelected, createNew);

        TabPane tabPane = new TabPane(pending, outstanding, finalized);

        Scene scene = new Scene(tabPane, 700, 800);

        buttons.setPadding(new Insets(10));

        pending.setClosable(false);
        outstanding.setClosable(false);
        finalized.setClosable(false);

        SetTablesWorkOrder(tableViewPending);
        SetTablesWorkOrder(tableViewOutstanding);
        SetTablesWorkOrder(tableViewFinalized);

        UpdateWorkOrder.updatePending(tableViewPending);
        UpdateWorkOrder.updateOutstanding(tableViewOutstanding);
        UpdateWorkOrder.updateFinalized(tableViewFinalized);

        borderPending.setCenter(tableViewPending);
        borderPending.setBottom(buttons);
        borderPending.setPadding(new Insets(10));

        pending.setContent(borderPending);
        outstanding.setContent(tableViewOutstanding);
        finalized.setContent(tableViewFinalized);

        buttons.setAlignment(Pos.BOTTOM_CENTER);
        buttons.setMinHeight(40);

        stage.setTitle("Part Order");
        stage.setScene(scene);
        stage.setMaximized(true);

        editSelected.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TablePosition pos = tableViewPending.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();
                Database.PartOrder selected = tableViewPending.getItems().get(row);
                CreateNewWorkOrder(tableViewPending, selected);
            }
        });


        createNew.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Database.PartOrder order = new Database.PartOrder(null, 0, "Name", "Type", "Supplier");
                CreateNewWorkOrder(tableViewPending, order);
            }
        });

        stage.show();
    }
    
    /**
     * This is the window that deals with the creation of work orders.
     */
    public static void CreateNewWorkOrder(TableView tableVeiw, Database.PartOrder order)
    {
        Stage stage = new Stage();

        Text unitNumber = new Text("Unit #:");
        Text unitName = new Text("Unit Name:");
        Text costCode = new Text("Cost Code:");

        TextArea unitNumberArea = new TextArea();
        TextArea unitNameArea = new TextArea();
        TextArea costCodeArea = new TextArea();

        TableView table = new TableView();
        TableColumn typeCol = new TableColumn("Type");
        TableColumn partNumberCol = new TableColumn("Part Number");
        TableColumn partNameCol = new TableColumn("Part Name");
        TableColumn amountCol = new TableColumn("Amount");
        TableColumn costCol = new TableColumn("Cost");

        BorderPane borderPane = new BorderPane();

        Button save = new Button("Save");
        Button generate = new Button("Generate PO");
        Button receive = new Button("Receive");

        HBox hBox = new HBox(10, unitName, unitNameArea);
        HBox hBox1 = new HBox(10, unitNumber, unitNumberArea);
        HBox hBox2 = new HBox(10, costCode, costCodeArea);
        HBox hBox3 = new HBox(50, hBox, hBox1);
        HBox buttons = new HBox(10, save, generate, receive);

        VBox vBox = new VBox(10, hBox3, hBox2);

        Scene scene = new Scene(borderPane);

        unitNameArea.setWrapText(true);
        unitNumberArea.setWrapText(true);
        costCodeArea.setWrapText(true);

        unitNameArea.setMaxWidth(200);
        unitNumberArea.setMaxWidth(200);
        costCodeArea.setMaxWidth(200);

        unitNameArea.setMaxHeight(12);
        unitNumberArea.setMaxHeight(12);
        costCodeArea.setMaxHeight(12);

        table.getColumns().addAll(typeCol, partNumberCol, partNameCol, amountCol, costCol);

        borderPane.setCenter(table);
        borderPane.setTop(vBox);
        borderPane.setBottom(buttons);
        borderPane.setPadding(new Insets(10));

        vBox.setPadding(new Insets(10));

        buttons.setPadding(new Insets(10));

        stage.setTitle("Work Order");
        stage.setScene(scene);
        stage.setMaximized(true);
        generate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Database.PartOrder order = new Database.PartOrder(null, 0, null, null, null);
                WorkOrderPO(table, order);
            }
        });

        stage.show();
    }

    private static void WorkOrderPO(TableView table, Database.PartOrder order)
    {
        Stage stage = new Stage();

        //Currently using a random number generator for the part order number
        Random random = new Random();
        //Upper bound of 2000
        int i = random.nextInt(2000);

        BorderPane border = new BorderPane();

        Text supplier = new Text(" Supplier:  ");
        Text poNumber = new Text(" PO #: ");

        //Where the user will enter the name of the supplier
        TextArea supplierArea = new TextArea();
        //The area where the part order number is displayed.
        TextArea poArea = new TextArea(String.valueOf(i));

        HBox hBox = new HBox();
        HBox button = new HBox();

        TableView<Part> edit = new TableView<>();
        //The column of all the part numbers
        TableColumn<Part, String> number = new TableColumn<>("Number");
        // The column of all the descriptions of the parts
        TableColumn<Part, String> description = new TableColumn<>("Description");
        //The column of all the number of the part that will be ordered.
        TableColumn<Part, Integer> ordered = new TableColumn<>("Ordered #");
        Scene scene = new Scene(border, 700, 800);

        //If the the supplied part is already made get the available information
        if(order.getCost() != null)
        {
            supplierArea.setText(order.getSupplier());
            poArea.setText(String.valueOf(order.getNumber()));
            UpdatePartOrder.updateNewPartOrder(edit, number, description, ordered, order);
        }

        //Used to add a new part to the part order
        Button add = new Button("Add");
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                PartOrderWindow.addPartToOrder(edit, number, description, ordered, order);
            }
        });

        Button delete = new Button("Delete");
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TablePosition position =  edit.getSelectionModel().getSelectedCells().get(0);
                int row = position.getRow();
                Part part = edit.getItems().get(row);
                order.removePart(part);
                for ( int i = 0; i<edit.getItems().size(); i++) {
                    edit.getItems().clear();
                }
                UpdatePartOrder.updateNewPartOrder(edit, number, description, ordered, order);
            }
        });

        Button save = new Button("Save");
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                order.setNumber(Integer.valueOf(poArea.getText()));
                order.setSupplier(supplierArea.getText());
                order.setCost("Cost");
                order.setOrderType("Type");
                order.setOrderBy("Name");
                if(table.getItems().contains(order))
                {
                    UpdateWorkOrder.updateWorkOrder(table);
                    stage.close();
                }
                else {
                    UpdateWorkOrder.updateWorkOrder(table);
                    stage.close();
                }
            }
        });

        stage.setTitle("Create New");

        supplier.setStyle("-fx-font: 18 arial");

        supplierArea.setStyle("-fx-font: 18 arial");
        supplierArea.setWrapText(true);
        supplierArea.setMaxWidth(200);
        supplierArea.setMaxHeight(12);

        poNumber.setStyle("-fx-font: 18 arial");

        poArea.setStyle("-fx-font: 18 arial");
        poArea.setWrapText(true);
        poArea.setMaxHeight(12);
        poArea.setMaxWidth(200);
        poArea.setEditable(false);

        hBox.getChildren().addAll(supplier, supplierArea, poNumber, poArea);

        button.getChildren().setAll(add, delete, save);
        button.setSpacing(10);
        button.setAlignment(Pos.BOTTOM_CENTER);

        edit.getColumns().addAll(number, description, ordered);

        border.setCenter(edit);
        border.setTop(hBox);
        border.setBottom(button);

        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    private static void SetTablesWorkOrder(TableView table)
    {
        TableColumn unitNumber = new TableColumn("Unit Number");
        TableColumn unitName = new TableColumn("Unit Name");
        TableColumn costCode = new TableColumn("Cost Code");
        TableColumn partOrders = new TableColumn("Part Orders");
        table.getColumns().addAll(unitNumber, unitName, costCode,
                partOrders);
    }
}
