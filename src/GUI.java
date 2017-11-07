
import PartOrders.PartOrder;
import UpdateTables.UpdatePartOrder;



import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class GUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

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

        TextArea pass = new TextArea();

        Text loginWord = new Text();
        loginWord.setText("User Name:");

        Text passWord = new Text();
        passWord.setText("Password:");

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

    private void MainGUI()
    {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Inventory Management");

        Button btn = new Button();
        btn.setText("Part Order");
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                PartOrder();
            }
        });

        Button btn2 = new Button();
        btn2.setText("Work Order");
        btn2.setMaxWidth(Double.MAX_VALUE);
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                WorkOrder();
            }
        });

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

        TextArea area = new TextArea();
        area.setEditable(false);

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
        grid.add(area, 2,3,2,1);
        Scene scene = new Scene(grid, 600, 600);

        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    private void PartOrder()
    {
        Stage stage = new Stage();
        stage.setTitle("Part Order");

        BorderPane borderPending = new BorderPane();
        HBox buttons = new HBox();

        Button one = new Button("One");
        Button two = new Button("Two");

        buttons.getChildren().addAll(one, two);
        buttons.setSpacing(10);
        buttons.setAlignment(Pos.BOTTOM_CENTER);
        buttons.setMinHeight(40);

        Tab pending = new Tab("Pending");
        pending.setClosable(false);
        TableView tableViewPending = new TableView();
        TableColumn numberPending = new TableColumn("Number");
        TableColumn supplierPending = new TableColumn("Supplier");
        TableColumn orderedByPending = new TableColumn("Ordered By");
        UpdatePartOrder.updatePending(numberPending,supplierPending,orderedByPending);
        tableViewPending.getColumns().addAll(numberPending, supplierPending, orderedByPending);


        borderPending.setCenter(tableViewPending);
        borderPending.setBottom(buttons);
        pending.setContent(borderPending);

        Tab outstanding = new Tab("Outstanding");
        outstanding.setClosable(false);
        TableView tableViewOutstanding = new TableView();
        TableColumn numberOutstanding = new TableColumn("Number");
        TableColumn supplierOutstanding = new TableColumn("Supplier");
        TableColumn orderedByOutstanding = new TableColumn("Ordered By");
        tableViewOutstanding.getColumns().addAll(numberOutstanding, supplierOutstanding, orderedByOutstanding);
        outstanding.setContent(tableViewOutstanding);

        Tab finalized = new Tab("Finalized");
        finalized.setClosable(false);
        TableView tableViewFinalized = new TableView();
        TableColumn numberFinalized = new TableColumn("Number");
        TableColumn supplierFinalized = new TableColumn("Supplier");
        TableColumn orderedByFinalized = new TableColumn("Ordered By");
        tableViewFinalized.getColumns().addAll(numberFinalized, supplierFinalized, orderedByFinalized);
        finalized.setContent(tableViewFinalized);

        TabPane tabPane = new TabPane(pending, outstanding, finalized);
        Scene scene = new Scene(tabPane, 700, 800);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    private void WorkOrder()
    {
        Stage stage = new Stage();
        stage.setTitle("Work Order");
        stage.setMaximized(true);
        stage.show();
    }

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

        TextArea one = new TextArea("One");

        TextArea two = new TextArea("Two");

        TextArea three = new TextArea("Three");

        Button save = new Button("Save");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));
        grid.add(one, 1,1);
        grid.add(two, 2, 1);
        grid.add(three, 3, 1);
        grid.add(save, 1, 2);
        Scene scene = new Scene(grid, 500, 600);
        stage.setScene(scene);
        stage.show();
    }

}
