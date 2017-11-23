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

/**
 * This is where the program starts and where the GUI is built.
 */
public class GUI extends Application {

    /**
     * The main method that starts the login GUI and where the rest of the program is created from.
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * This is method that is required to start a GUI using JavaFX.
     * It creates the login menu and all the associated buttons and text fields.
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Login");

        GridPane grid = new GridPane();

        Button btn = new Button();
        btn.setText("Login");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MainGUI();
                primaryStage.close();
            }
        });

        TextArea login = new TextArea();
        login.setMaxHeight(12);
        login.setWrapText(true);

        TextArea pass = new TextArea();
        pass.setMaxHeight(12);
        pass.setWrapText(true);

        Text loginWord = new Text("User Name:");

        Text passWord = new Text("Password:");

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));
        grid.setAlignment(Pos.CENTER);
        grid.add(loginWord, 0,0,2,1);
        grid.add(login, 0,1,2,1);
        grid.add(passWord,0,2,2,1);
        grid.add(pass, 0,3,2,1);
        grid.add(btn, 0,4,2,1);

        Scene scene = new Scene(grid, 300, 275);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * This is the method that creates the main window of our GUI this is where all of the users will start after
     * logging in and where all the other tasks will start from.
     */
    private void MainGUI()
    {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Inventory Management");

        //This button opens the part order window.
        Button btn = new Button();
        btn.setText("Part Order");
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                PartOrder();
            }
        });

        //This button opens the work order window.
        Button btn2 = new Button();
        btn2.setText("Work Order");
        btn2.setMaxWidth(Double.MAX_VALUE);
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                WorkOrder();
            }
        });

        //This button opens the report window.
        Button btn3 = new Button();
        btn3.setText("Report");
        btn3.setMaxWidth(Double.MAX_VALUE);
        btn3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Report();
            }
        });

        Button btn4 = new Button();
        btn4.setText("Test");
        btn4.setMaxWidth(Double.MAX_VALUE);
        btn4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Test();
            }
        });

        Text text = new Text();
        text.setText("Error Area");

        TextArea errorArea = new TextArea();
        errorArea.setEditable(false);

        VBox vbButtons = new VBox();
        vbButtons.setSpacing(10);
        vbButtons.setPadding(new Insets(0, 20, 10, 20));
        vbButtons.getChildren().addAll(btn, btn2, btn3, btn4);


        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));
        grid.add(vbButtons, 0,0,2,1);
        grid.add(text,2,2,2,1);
        grid.add(errorArea, 2,3,2,1);
        Scene scene = new Scene(grid, 600, 600);

        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    /**
     * This is the window that deals with the creation and tracking of part orders.
     */
    private void PartOrder()
    {
        Stage stage = new Stage();
        BorderPane borderPending = new BorderPane();
        HBox buttons = new HBox();

        stage.setTitle("Part Order");

        Button editSelected = new Button("Edit Selected");
        editSelected.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                editPartOrder();
            }
        });

        Button createNew = new Button("Create New");
        createNew.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                createNewPartOrder();
            }
        });

        buttons.getChildren().addAll(editSelected, createNew);
        buttons.setSpacing(10);
        buttons.setAlignment(Pos.BOTTOM_CENTER);
        buttons.setMinHeight(40);

        //This tab displays all the pending part orders
        Tab pending = new Tab("Pending");
        pending.setClosable(false);
        TableView<PartOrder> tableViewPending = new TableView<>();
        TableColumn numberPending = new TableColumn("Number");
        TableColumn supplierPending = new TableColumn("Supplier");
        TableColumn orderedByPending = new TableColumn("Ordered By");
        TableColumn costPending = new TableColumn("Cost");
        TableColumn orderedTypePending = new TableColumn("Order Type");
        TableColumn partsOrderedPending = new TableColumn("Parts Ordered");

        UpdatePartOrder.updatePending(tableViewPending ,numberPending,supplierPending,orderedByPending, costPending,
                orderedTypePending, partsOrderedPending);
        tableViewPending.getColumns().addAll(numberPending, supplierPending, orderedByPending, costPending,
                orderedTypePending, partsOrderedPending);


        borderPending.setCenter(tableViewPending);
        borderPending.setBottom(buttons);
        pending.setContent(borderPending);

        //This tab displays all the outstanding work orders
        Tab outstanding = new Tab("Outstanding");
        outstanding.setClosable(false);
        TableView tableViewOutstanding = new TableView();
        TableColumn numberOutstanding = new TableColumn("Number");
        TableColumn supplierOutstanding = new TableColumn("Supplier");
        TableColumn orderedByOutstanding = new TableColumn("Ordered By");
        TableColumn costOutstanding = new TableColumn("Cost");
        TableColumn orderedTypeOutstanding = new TableColumn("Order Type");
        TableColumn partsOrderedOutstanding = new TableColumn("Parts Ordered");

        UpdatePartOrder.updateOutstanding(tableViewOutstanding ,numberOutstanding,supplierOutstanding,orderedByOutstanding,
                costOutstanding, orderedTypeOutstanding, partsOrderedOutstanding);
        tableViewOutstanding.getColumns().addAll(numberOutstanding, supplierOutstanding, orderedByOutstanding,
                costOutstanding, orderedTypeOutstanding, partsOrderedOutstanding);
        outstanding.setContent(tableViewOutstanding);

        //This tab displays all the finalized part orders.
        Tab finalized = new Tab("Finalized");
        finalized.setClosable(false);
        TableView tableViewFinalized = new TableView();
        TableColumn numberFinalized = new TableColumn("Number");
        TableColumn supplierFinalized = new TableColumn("Supplier");
        TableColumn orderedByFinalized = new TableColumn("Ordered By");
        TableColumn costFinalized = new TableColumn("Cost");
        TableColumn orderedTypeFinalized = new TableColumn("Order Type");
        TableColumn partsOrderedFinalized = new TableColumn("Parts Ordered");

        UpdatePartOrder.updateFinalized(tableViewFinalized ,numberFinalized,supplierFinalized,orderedByFinalized,
                costFinalized, orderedTypeFinalized, partsOrderedFinalized);
        tableViewFinalized.getColumns().addAll(numberFinalized, supplierFinalized, orderedByFinalized,
                costFinalized, orderedTypeFinalized, partsOrderedFinalized);
        finalized.setContent(tableViewFinalized);

        TabPane tabPane = new TabPane(pending, outstanding, finalized);
        Scene scene = new Scene(tabPane, 700, 800);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    private void editPartOrder()
    {
        //TODO need to have selected information transfer
        Stage stage = new Stage();
        stage.setTitle("Edit");

        stage.setMaximized(true);
        stage.show();
    }

    /**
     * This is the window that opens to create a new part order
     */
    private void createNewPartOrder()
    {
        Stage stage = new Stage();
        BorderPane border = new BorderPane();
        Text supplier = new Text(" Supplier:  ");
        Text poNumber = new Text(" PO #: ");
        TextArea supplierArea = new TextArea();
        TextArea poArea = new TextArea();
        HBox hBox = new HBox();
        HBox button = new HBox();
        TableView edit = new TableView();
        TableColumn number = new TableColumn("Number");
        TableColumn description = new TableColumn("Description");
        TableColumn ordered = new TableColumn("Ordered #");
        Scene scene = new Scene(border, 700, 800);

        //Used to add a new part to the part order
        Button add = new Button("Add");
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addPartToOrder();
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

        hBox.getChildren().addAll(supplier, supplierArea, poNumber, poArea);

        button.getChildren().setAll(add);
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

    private void addPartToOrder()
    {
        Stage stage = new Stage();
        stage.setTitle("Add Part");
        stage.show();
    }


}
