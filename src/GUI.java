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
        grid.add(login, 0,4,2,1);

        primaryStage.setScene(scene);

        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GUIWindows.MainGUI.MainGUI();
                primaryStage.close();
            }
        });

        primaryStage.show();
    }
}
