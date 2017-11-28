package GUIWindows;

import Database.Part;
import Database.PartOrder;
import UpdateTables.UpdatePartOrder;
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
public class PartOrderWindow
{
    public static void PartOrderWindow()
    {
        Stage stage = new Stage();

        BorderPane borderPending = new BorderPane();
        BorderPane borderOutstanding = new BorderPane();

        Tab pending = new Tab("Pending");
        Tab outstanding = new Tab("Outstanding");
        Tab finalized = new Tab("Finalized");

        TableView<PartOrder> tableViewPending = new TableView<>();
        TableView<Database.PartOrder> tableViewOutstanding = new TableView();
        TableView<Database.PartOrder> tableViewFinalized = new TableView();

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
        buttons.setMinHeight(40);

        buttons1.setPadding(new Insets(10));
        buttons1.setAlignment(Pos.BOTTOM_CENTER);
        buttons1.setMinHeight(40);

        pending.setClosable(false);
        outstanding.setClosable(false);
        finalized.setClosable(false);

        setTablesPartOrder(tableViewPending);
        setTablesPartOrder(tableViewOutstanding);
        setTablesPartOrder(tableViewFinalized);

        UpdatePartOrder.updatePending(tableViewPending);
        UpdatePartOrder.updateOutstanding(tableViewOutstanding);
        UpdatePartOrder.updateFinalized(tableViewFinalized);

        borderPending.setCenter(tableViewPending);
        borderPending.setBottom(buttons);
        borderPending.setPadding(new Insets(10));

        borderOutstanding.setCenter(tableViewOutstanding);
        borderOutstanding.setBottom(buttons1);
        borderOutstanding.setPadding(new Insets(10));

        pending.setContent(borderPending);
        outstanding.setContent(borderOutstanding);
        finalized.setContent(tableViewFinalized);



        stage.setTitle("Part Order");
        stage.setScene(scene);
        stage.setMaximized(true);

        editSelected.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TablePosition pos = tableViewPending.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();
                PartOrder selected = tableViewPending.getItems().get(row);
                createNewPartOrder(tableViewPending, selected);
            }
        });


        createNew.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                PartOrder order = new Database.PartOrder(null, 0, "Name", "Type", "Supplier");
                createNewPartOrder(tableViewPending, order);
            }
        });

        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TablePosition pos = tableViewPending.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();
                PartOrder selected = tableViewPending.getItems().get(row);
                UpdatePartOrder.removeOrderPending(selected);
                UpdatePartOrder.addOrderOutstanding(selected);
                UpdatePartOrder.updatePending(tableViewPending);
                UpdatePartOrder.updateOutstanding(tableViewOutstanding);
            }
        });

        finalize.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TablePosition pos = tableViewOutstanding.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();
                PartOrder selected = tableViewOutstanding.getItems().get(row);
                finalizeSelected(tableViewOutstanding, tableViewFinalized, selected);
            }
        });

        stage.show();
    }

    /**
     * The window that is used to create a new part order.
     * @param table The table from the previous window so it can be updated when this window is closed.
     */
    private static void createNewPartOrder(TableView table, PartOrder order)
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
                addPartToOrder(edit, number, description, ordered, order);
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
                    UpdatePartOrder.updatePending(table);
                    stage.close();
                }
                else {
                    UpdatePartOrder.addOrderPending(order);
                    UpdatePartOrder.updatePending(table);
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

    private static void setTablesPartOrder(TableView table)
    {
        TableColumn number = new TableColumn("Number");
        TableColumn supplier = new TableColumn("Supplier");
        TableColumn orderedBy = new TableColumn("Ordered By");
        TableColumn cost = new TableColumn("Cost");
        TableColumn orderedType = new TableColumn("Order Type");
        TableColumn partsOrdered = new TableColumn("Parts Ordered");
        table.getColumns().addAll(number, supplier, orderedBy,
                cost, orderedType, partsOrdered);
    }

    /**
     * The window that opens when you need to add another part to the part order.
     * @param table The table from the previous window that the parts will be added to.
     * @param partNumber The column that contains the partNumbers from the previous window.
     * @param partDescription The column that contains the description of the part from the last window.
     * @param numberOrdered The column that contains the number of ordered parts from the last window
     * @param order The part order that has been created.
     */
    public static void addPartToOrder(TableView table, TableColumn partNumber, TableColumn partDescription,
                                      TableColumn numberOrdered, Database.PartOrder order)
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

        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String partNum = numberArea.getText();
                String numOrder = amountArea.getText();
                //Checks to make sure that the strings are not empty and that the amount ordered only contains numbers
                if(numOrder.trim().isEmpty() || partNum.trim().isEmpty() || !numOrder.matches("^[0-9]*$"))
                {
                    error.setText("Please make sure all the lines are filled.");
                }
                else {
                    //Sets all the values of the part that is being created.
                    order.setPartsOrdered(new Part(numberArea.getText(), "Name", "Cost",
                            Integer.valueOf(amountArea.getText())));
                    //Clears the table before reloading the new information
                    for (int i = 0; i < table.getItems().size(); i++) {
                        table.getItems().clear();
                    }
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

    //TODO make the received column editable.
    private static void finalizeSelected(TableView outsandingTable, TableView finalizedTable, PartOrder order)
    {
        Stage stage = new Stage();

        Text poNumber = new Text("PO #:");
        Text supplier = new Text("Supplier:");

        TextArea poNumberArea = new TextArea();
        TextArea supplierArea = new TextArea();

        Button save = new Button("Save");

        HBox hBox = new HBox(10, poNumber, poNumberArea, supplier, supplierArea);
        HBox hBox1 = new HBox(10, save);

        BorderPane borderPane = new BorderPane();

        TableView<Part> table = new TableView<>();
        TableColumn partNumber = new TableColumn("Part Number");
        TableColumn name = new TableColumn("Name");
        TableColumn amountOrdered = new TableColumn("Amount Ordered");
        TableColumn received = new TableColumn("Received");
        TableColumn actualCost = new TableColumn("Actual Cost");
        TableColumn invoiceNumber = new TableColumn("Invoice Number");
        TableColumn date = new TableColumn("Date");

        table.getColumns().addAll(partNumber, name, amountOrdered, received, actualCost, invoiceNumber, date);

        Scene scene= new Scene(borderPane);

        borderPane.setCenter(table);
        borderPane.setBottom(hBox1);
        borderPane.setTop(hBox);
        borderPane.setPadding(new Insets(10));

        hBox.setPadding(new Insets(10));
        hBox1.setPadding(new Insets(10));

        poNumberArea.setWrapText(true);
        poNumberArea.setMaxHeight(12);
        poNumberArea.setMaxWidth(100);
        poNumberArea.setText(String.valueOf(order.getNumber()));

        supplierArea.setWrapText(true);
        supplierArea.setMaxHeight(12);
        supplierArea.setMaxWidth(100);
        supplierArea.setText(order.getSupplier());

        UpdatePartOrder.updateFinalizedPartOrder(table, order);

        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UpdatePartOrder.removeOrderOutstanding(order);
                UpdatePartOrder.addOrderFinalized(order);
                UpdatePartOrder.updateOutstanding(outsandingTable);
                UpdatePartOrder.updateFinalized(finalizedTable);
                stage.close();
            }
        });

        stage.setScene(scene);
        stage.setTitle("Finalize");
        stage.show();
    }
}
