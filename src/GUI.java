
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
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
        TextArea login = new TextArea();
        TextArea pass = new TextArea();
        Text loginWord = new Text();
        Text passWord = new Text();
        loginWord.setText("User Name:");
        passWord.setText("Password");
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MainGUI();
                primaryStage.close();
            }
        });
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
        Button btn2 = new Button();
        Button btn3 = new Button();
        btn.setText("Park Order");
        btn2.setText("Work Order");
        btn3.setText("Report");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                PartOrder();
            }
        });
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                WorkOrder();
            }
        });
        btn3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Report();
            }
        });
        Text text = new Text();
        text.setText("Error Area");
        TextArea area = new TextArea();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));
        grid.add(btn, 0, 0, 2, 1);
        grid.add(btn2, 0,1,2,1);
        grid.add(btn3,0,2,2,1);
        grid.add(text,3,2,2,1);
        grid.add(area, 3,3,2,1);
        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void PartOrder()
    {
        Stage stage = new Stage();
        stage.setTitle("Part Order");
        stage.show();
    }

    private void WorkOrder()
    {
        Stage stage = new Stage();
        stage.setTitle("Work Order");
        stage.show();
    }

    private void Report()
    {
        Stage stage = new Stage();
        stage.setTitle("Report");
        stage.show();
    }
}
