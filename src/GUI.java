import Database.Part;
import Database.PartOrder;
import UpdateTables.UpdatePartOrder;
import Database.Management;
import com.sun.org.apache.xpath.internal.SourceTree;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;

import Database.*;


import javax.swing.*;

import java.util.Random;

/**
 * This is where the program starts and where the GUI is built.
 */
public class GUI extends Application {

    /**
     * The main method that starts the login GUI and where the rest of the program is created from.
     * @param args The command line args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * This is method that is required to start a GUI using JavaFX.
     * It creates the login menu and all the associated buttons and text fields.
     * @param primaryStage The starting stage that is required for the class.
     */
    @Override
    public void start(Stage primaryStage)
    {
        Text loginWord = new Text("User Name:");
        Text passWord = new Text("Password:");

        TextArea loginArea = new TextArea();
        TextArea pass = new TextArea();

        primaryStage.setTitle("Login");

        GridPane grid = new GridPane();

        Scene scene = new Scene(grid, 300, 275);

        Button login = new Button("Login");

        loginArea.setMaxHeight(12);
        loginArea.setWrapText(true);

        pass.setMaxHeight(12);
        pass.setWrapText(true);

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));
        grid.setAlignment(Pos.CENTER);
        grid.add(loginWord, 0,0,2,1);
        grid.add(loginArea, 0,1,2,1);
        grid.add(passWord,0,2,2,1);
        grid.add(pass, 0,3,2,1);
        grid.add(loginArea, 0,4,2,1);

        primaryStage.setScene(scene);

        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MainGUI();
                primaryStage.close();
            }
        });

        primaryStage.show();
    }

    /**
     * This is the method that creates the main window of our GUI this is where all of the users will start after
     * logging in and where all the other tasks will start from.
     */
    private void MainGUI()
    {
        Stage primaryStage = new Stage();

        Text text = new Text("Error Area");

        TextArea errorArea = new TextArea();

        Button partOrder = new Button("Part Order");
        Button workOrder = new Button("Work Order");
        Button report = new Button("Report");
        Button inventory = new Button("Inventory");
        Button customers = new Button("Customers");
        Button suppliers = new Button("Suppliers");
        Button test = new Button("Test");

        VBox vBox = new VBox(10, inventory, suppliers, customers);
        VBox vbButtons = new VBox(10, partOrder, workOrder, report, test);

        GridPane grid = new GridPane();

        Scene scene = new Scene(grid, 600, 600);

        partOrder.setMaxWidth(Double.MAX_VALUE);
        workOrder.setMaxWidth(Double.MAX_VALUE);
        report.setMaxWidth(Double.MAX_VALUE);
        inventory.setMaxWidth(Double.MAX_VALUE);
        customers.setMaxWidth(Double.MAX_VALUE);
        suppliers.setMaxWidth(Double.MAX_VALUE);
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
        primaryStage.setMaximized(true);

        //This button opens the part order window
        partOrder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                PartOrder();
            }
        });

        //This button opens the work order window.
        workOrder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                WorkOrder();
            }
        });

        //This button opens the report window.
        report.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Report();
            }
        });

        //This button opens the inventory window
        inventory.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Inventory();
            }
        });

        //This button opens the customers window
        customers.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Customers();
            }
        });

        //This button opens the suppliers window
        suppliers.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Suppliers();
            }
        });

        test.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Test();
            }
        });

        primaryStage.show();
    }

    /**
     * This is the window that lets you browse the inventory and add or edit parts if you have admin privileges.
     */
    //TODO add edit and save functionality.
    private void Inventory()
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
        HBox hBox2 = new HBox(50, suggestCostArea, edit);
        HBox hBox3 = new HBox(50, onHandArea, save);
        VBox vBox = new VBox(10, partNumber, hBox,description, descriptionArea, suggestCost, hBox2, onHand, hBox3);

        BorderPane borderPane = new BorderPane(vBox);
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.setTitle("Inventory");
        stage.show();
    }

    /**
     * This is the window that opens to let you browse the customers and add or edit customers if you have
     * admin privileges.
     */
    private void Customers()
    {}

    /**
     * This is the window that opens to let you browse suppliers and add or edit suppliers if you have admin
     * privileges.
     */
    private void Suppliers()
    {

    }

    /**
     * This is the window that deals with the creation and tracking of part orders.
     */
    private void PartOrder()
    {
        Stage stage = new Stage();

        BorderPane borderPending = new BorderPane();

        Tab pending = new Tab("Pending");
        Tab outstanding = new Tab("Outstanding");
        Tab finalized = new Tab("Finalized");

        TableView<PartOrder> tableViewPending = new TableView<>();
        TableView<PartOrder> tableViewOutstanding = new TableView();
        TableView<PartOrder> tableViewFinalized = new TableView();

        Button editSelected = new Button("Edit Selected");
        Button createNew = new Button("Create New");

        HBox buttons = new HBox(10, editSelected, createNew);

        TabPane tabPane = new TabPane(pending, outstanding, finalized);

        Scene scene = new Scene(tabPane, 700, 800);

        pending.setClosable(false);
        outstanding.setClosable(false);
        finalized.setClosable(false);

        this.setTablesPartOrder(tableViewPending);
        this.setTablesPartOrder(tableViewOutstanding);
        this.setTablesPartOrder(tableViewFinalized);

        UpdatePartOrder.updatePending(tableViewPending);
        UpdatePartOrder.updateOutstanding(tableViewOutstanding);
        UpdatePartOrder.updateFinalized(tableViewFinalized);

        borderPending.setCenter(tableViewPending);
        borderPending.setBottom(buttons);

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
                PartOrder selected = tableViewPending.getItems().get(row);
                createNewPartOrder(tableViewPending, selected);
            }
        });


        createNew.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                PartOrder order = new PartOrder(null, 0, "Name", "Type", "Supplier");
                createNewPartOrder(tableViewPending, order);
            }
        });

        stage.show();
    }

    private void setTablesPartOrder(TableView table)
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
     * The window that is used to create a new part order.
     * @param table The table from the previous window so it can be updated when this window is closed.
     */
    private void createNewPartOrder(TableView table, PartOrder order)
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

    /**
     * This is the window that deals with the creation and tracking of work orders.
     */
    private void WorkOrder()
    {
        Stage stage = new Stage();
        stage.setTitle("Work Order");
        stage.setMaximized(true);
        stage.show();
    }

    /**
     * This is the window that deals with the generation of reports.
     */
    private void Report()
    {
        Stage stage = new Stage();
        stage.setTitle("Report");
        stage.setMaximized(true);
        stage.show();
    }

    private void Test()
    {
        Stage stage = new Stage();
        stage.setTitle("Test");

        TextArea one = new TextArea();

        TextArea two = new TextArea();

        TextArea three = new TextArea();

        Button save = new Button("Save");

        Button load = new Button("load");

        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (one.getText() == "" || two.getText() == "" || three.getText() == "") {
                    JOptionPane.showMessageDialog(null, "You can't save something with an empty field!");
                }
                else{
                    try {
                        System.out.println("Trying to write to file...");
                        File file = new File("CPSC300/src/Database/customers.ser");
                        System.out.println(file.exists());
                       // if (file.exists()){
                            Customer cOne = new Customer();
                        cOne.setName(one.getText());
                        cOne.setEmail(two.getText());
                        cOne.setPhoneNumber((three.getText()));

                            /*Customer cTwo = new Customer();
                            Customer cThree = new Customer();
                            cOne.setName(one.getText());
                            cTwo.setName(two.getText());
                            cThree.setName(three.getText()); */

                            ArrayList<Customer> list = new ArrayList<Customer>();
                            list.add(cOne);
                        /*
                            list.add(cTwo);
                            list.add(cThree);
                         */
                            Management.addObject(file, list);



                       // }

                    } catch (Exception e){
                        System.out.println("You broke it.");
                    }

                }
            }
        });

        load.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    // System.out.println("Trying to write to file...");
                    File file = new File("CPSC300/src/Database/customers.ser");
                    //int lineNumber = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter the line number"));
                    //String s = Management.loadFromDatabase(file, lineNumber);
                    //String[] array = s.split(" thisisaflag ");
                    ArrayList l = Management.readList(file);
                    ArrayList<Customer> list = new ArrayList<Customer>();
                    System.out.println("trying to loop");
                    for (int i=0; i<l.size(); i++){
                        list.add((Customer)l.get(i));
                        System.out.println("added "+list.get(i).getName());
                    }

                    one.setText(list.get(0).getName());
                    two.setText(list.get(0).getEmail());
                    three.setText(list.get(0).getPhoneNumber());
                    }

                catch(Exception e){
                    e.printStackTrace();

                }




            }


        });


        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));
        grid.add(one, 1,1);
        grid.add(two, 2, 1);
        grid.add(three, 3, 1);
        grid.add(save, 1, 2);
        grid.add (load, 2,2);
        Scene scene = new Scene(grid, 500, 600);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The window that opens when you need to add another part to the part order.
     * @param table The table from the previous window that the parts will be added to.
     * @param partNumber The column that contains the partNumbers from the previous window.
     * @param partDescription The column that contains the description of the part from the last window.
     * @param numberOrdered The column that contains the number of ordered parts from the last window
     * @param order The part order that has been created.
     */
    private void addPartToOrder(TableView table, TableColumn partNumber, TableColumn partDescription,
                                TableColumn numberOrdered, PartOrder order)
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


}
