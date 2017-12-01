package GUIWindows;

import Database.Management;
import Database.Part;
import Database.PartOrder;
import Database.WorkOrder;
import UpdateTables.UpdateInventory;
import UpdateTables.UpdatePartOrder;
import UpdateTables.UpdateWorkOrder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;

/**
 * Created by colton on 2017-11-26.
 */
public class WorkOrderWindow {
    public static void WorkOrderWindow() {
        Stage stage = new Stage();

        BorderPane borderPending = new BorderPane();
        BorderPane borderOutstanding = new BorderPane();

        Tab pending = new Tab("Pending");
        Tab outstanding = new Tab("Outstanding");
        Tab finalized = new Tab("Finalized");

        TableView<WorkOrder> tableViewPending = new TableView<>();
        TableView<WorkOrder> tableViewOutstanding = new TableView();
        TableView<WorkOrder> tableViewFinalized = new TableView();

        Button editSelected = new Button("Edit Selected");
        Button createNew = new Button("Create New");
        Button submit = new Button("Submit");
        Button finalize = new Button("Finalize");

        HBox buttons = new HBox(10, editSelected, createNew, submit);
        HBox buttons1 = new HBox(10, finalize);

        TabPane tabPane = new TabPane(pending, outstanding, finalized);

        Scene scene = new Scene(tabPane, 700, 800);

        buttons.setPadding(new Insets(10));
        buttons.setAlignment(Pos.BOTTOM_CENTER);

        buttons1.setPadding(new Insets(10));
        buttons1.setAlignment(Pos.BASELINE_CENTER);

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

        borderOutstanding.setCenter(tableViewOutstanding);
        borderOutstanding.setBottom(buttons1);
        borderOutstanding.setPadding(new Insets(10));

        pending.setContent(borderPending);
        outstanding.setContent(borderOutstanding);
        finalized.setContent(tableViewFinalized);

        buttons.setAlignment(Pos.BOTTOM_CENTER);
        buttons.setMinHeight(40);

        stage.setTitle("Work Order");
        stage.setScene(scene);
        stage.setMaximized(true);

        editSelected.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TablePosition pos = tableViewPending.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();
                WorkOrder selected = tableViewPending.getItems().get(row);
                CreateNewWorkOrder(tableViewPending, selected);
            }
        });


        createNew.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                WorkOrder workOrder = new WorkOrder(null, null, null);
                workOrder.setStatus('P');
                CreateNewWorkOrder(tableViewPending, workOrder);
            }
        });

        finalize.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TablePosition pos = tableViewOutstanding.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();
                WorkOrder selected = tableViewOutstanding.getItems().get(row);
                finalizeSelected(tableViewOutstanding, tableViewFinalized, selected);
            }
        });

        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TablePosition pos = tableViewPending.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();
                WorkOrder selected = tableViewPending.getItems().get(row);
                selected.setStatus('O');
                for(PartOrder partOrder : selected.getPartsOrderArray())
                {
                    UpdatePartOrder.removeOrderPending(partOrder);
                    UpdatePartOrder.addOrderOutstanding(partOrder);
                }
                UpdateWorkOrder.removePending(selected);
                UpdateWorkOrder.addOutstanding(selected);
                UpdateWorkOrder.updatePending(tableViewPending);
                UpdateWorkOrder.updateOutstanding(tableViewOutstanding);
                UpdateWorkOrder.writeToFile();
            }
        });

        stage.show();
    }

    private static void finalizeSelected(TableView tableOutstanding, TableView tableFinalize, WorkOrder order)
    {
        Stage stage = new Stage();

        TableView table = new TableView();





        stage.setTitle("Finalize");
        stage.show();
    }

    /**
     * This is the window that deals with the creation of work orders.
     */
    public static void CreateNewWorkOrder(TableView tableView, WorkOrder order)
    {
        Stage stage = new Stage();

        Text unitNumber = new Text("Unit #:");
        Text unitName = new Text("Unit Name:");
        Text costCode = new Text("Cost Code:");

        TextArea unitNumberArea = new TextArea();
        TextArea unitNameArea = new TextArea();
        TextArea costCodeArea = new TextArea();

        TableView newWorkOrderTable = new TableView();
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

        newWorkOrderTable.getColumns().addAll(typeCol, partNumberCol, partNameCol, amountCol, costCol);

        borderPane.setCenter(newWorkOrderTable);
        borderPane.setTop(vBox);
        borderPane.setBottom(buttons);
        borderPane.setPadding(new Insets(10));

        vBox.setPadding(new Insets(10));

        buttons.setPadding(new Insets(10));

        if(order.getUnitName() != null)
        {
            unitNameArea.setText(order.getUnitName());
            unitNumberArea.setText(order.getUnitNumber());
            costCodeArea.setText(order.getCostCode());
            UpdateWorkOrder.updateWorkOrder(newWorkOrderTable, order);
        }

        stage.setTitle("Create New Work Order");
        stage.setScene(scene);
        stage.setMaximized(true);

        generate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                WorkOrderPO(newWorkOrderTable, order);
            }
        });

        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                order.setCostCode(costCodeArea.getText());
                order.setUnitName(unitNameArea.getText());
                order.setUnitNumber(unitNumberArea.getText());
                for(PartOrder partOrder : order.getPartsOrderArray())
                {
                    if(UpdatePartOrder.getPending().contains(partOrder))
                    {
                        UpdatePartOrder.writeToFile();
                    }
                    else
                    {
                        UpdatePartOrder.addOrderPending(partOrder);
                        Management.addObject("C:\\CPSC300\\CPSC300\\src\\Database\\partOrders.ser", partOrder);
                    }
                }

                if(tableView.getItems().contains(order))
                {
                    UpdateWorkOrder.writeToFile();
                    UpdateWorkOrder.updatePending(tableView);
                    stage.close();
                }
                else
                {
                    Management.addObject("C:\\CPSC300\\CPSC300\\src\\Database\\workOrders.ser", order);
                    UpdateWorkOrder.addPending(order);
                    UpdateWorkOrder.updatePending(tableView);
                    stage.close();
                }
            }
        });

        stage.show();
    }

    private static void WorkOrderPO(TableView table, WorkOrder workOrder)
    {
        Stage stage = new Stage();
        PartOrder order = new Database.PartOrder(null, 0, null, "Work Order", null);
        order.setStatus('P');

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
        if (order.getCost() != null) {
            supplierArea.setText(order.getSupplier());
            poArea.setText(String.valueOf(order.getNumber()));
            UpdatePartOrder.updateNewPartOrder(edit, number, description, ordered, order);
        }

        //Used to add a new part to the part order
        Button add = new Button("Add");
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addPartToOrder(edit, number, description, ordered, order, workOrder);
            }
        });

        Button delete = new Button("Delete");
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TablePosition position = edit.getSelectionModel().getSelectedCells().get(0);
                int row = position.getRow();
                Part part = edit.getItems().get(row);
                order.removePart(part);
                for (int i = 0; i < edit.getItems().size(); i++) {
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
                order.setOrderBy("Name");
                workOrder.addPartOrders(order);
                if (table.getItems().contains(order)) {
                    UpdateWorkOrder.updateWorkOrder(table, workOrder);
                    stage.close();
                } else {
                    UpdateWorkOrder.updateWorkOrder(table, workOrder);
                    stage.close();
                }
            }
        });

        stage.setTitle("Generate PO");

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


    /**
     * The window that opens when you need to add another part to the part order.
     *
     * @param table           The table from the previous window that the parts will be added to.
     * @param partNumber      The column that contains the partNumbers from the previous window.
     * @param partDescription The column that contains the description of the part from the last window.
     * @param numberOrdered   The column that contains the number of ordered parts from the last window
     * @param order           The part order that has been created.
     */
    private static void addPartToOrder(TableView table, TableColumn partNumber, TableColumn partDescription,
                                       TableColumn numberOrdered, PartOrder order, WorkOrder workOrder)
    {
        Stage stage = new Stage();
        Text number = new Text("Number: ");
        Text description = new Text("Description");
        Text amount = new Text("Amount");
        Text error = new Text();
        //The are where the user will input the part number
        TextArea numberArea = new TextArea();
        //The area where the description of the part will be displayed
        TextArea descriptionArea = new TextArea();
        //The are where the user will input the number of parts they want to order.
        TextArea amountArea = new TextArea();
        BorderPane borderPane = new BorderPane();
        Button save = new Button("Save");
        HBox hBox = new HBox();

        hBox.setAlignment(Pos.BASELINE_CENTER);

        amountArea.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton() == MouseButton.PRIMARY)
                {
                    if(UpdateInventory.contains(numberArea.getText()))
                    {
                        descriptionArea.setText(UpdateInventory.getPart(numberArea.getText()).getName());
                    }
                    else
                    {
                        error.setText("Please enter a valid part number");
                    }
                }
            }
        });

        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String partNum = numberArea.getText();
                String numOrder = amountArea.getText();

                //Checks to make sure that the strings are not empty and that the amount ordered only contains numbers
                if (numOrder.trim().isEmpty() || partNum.trim().isEmpty() || !numOrder.matches("^[0-9]*$")) {
                    error.setText("Please make sure all the lines are filled.");
                } else {
                    //Sets all the values of the part that is being created.
                    order.setPartsOrdered(new Part(numberArea.getText(), descriptionArea.getText(),
                            UpdateInventory.getPart(numberArea.getText()).getSuggestedCost(),
                            Integer.valueOf(amountArea.getText())));
                    //Clears the table before reloading the new information
                    for (int i = 0; i < table.getItems().size(); i++) {
                        table.getItems().clear();
                    }
                    Part part = new Part(partNum, "Name", "cost", Integer.valueOf(numOrder));
                    workOrder.addParts(part);
                    //Updates the table with the new addition
                    UpdatePartOrder.updateNewPartOrder(table, partNumber, partDescription, numberOrdered, order);
                    //Closes the stage afterwards
                    stage.close();
                }
            }
        });

        numberArea.setWrapText(true);
        numberArea.setMaxHeight(12);
        numberArea.setMaxWidth(300);

        descriptionArea.setWrapText(true);
        descriptionArea.setMaxHeight(12);
        descriptionArea.setMaxWidth(300);

        amountArea.setWrapText(true);
        amountArea.setMaxHeight(12);
        amountArea.setMaxWidth(300);

        VBox vBox = new VBox(number, numberArea, description, descriptionArea, amount, amountArea, error);
        vBox.setAlignment(Pos.CENTER);

        borderPane.setCenter(vBox);
        borderPane.setBottom(save);
        Scene scene = new Scene(borderPane, 350, 300);
        stage.setScene(scene);
        stage.setTitle("Add Part");
        stage.show();
    }

}
