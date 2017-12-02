package GUIWindows;

import Database.Management;
import Database.User;
import UpdateTables.UpdateInventory;
import UpdateTables.UpdateUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

/**
 * Created by colto on 2017-12-01.
 */
public class UserWindow
{
    public static void userWindow(String nameUser, int privilege)
    {
        Stage stage = new Stage();

        Button add = new Button("Add");

        TableView table = new TableView();
        TableColumn name = new TableColumn("Name: ");

        HBox hBox = new HBox(add);

        table.getColumns().addAll(name);

        UpdateUser.updateUser(table, name);

        BorderPane borderPane = new BorderPane(table);

        hBox.setAlignment(Pos.BOTTOM_CENTER);
        hBox.setPadding(new Insets(10));

        borderPane.setBottom(hBox);
        borderPane.setPadding(new Insets(10));

        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(privilege == 1) {
                    User(table, name);
                }

            }
        });

        Scene scene = new Scene(borderPane, 500, 600);

        stage.setTitle("Users");
        stage.setScene(scene);
        stage.show();
    }

    private static void User(TableView tableView, TableColumn name)
    {
        Stage stage = new Stage();
        ObservableList<Integer> options = FXCollections.observableArrayList(0, 1);

        Text nameText = new Text("Name: ");
        Text userName = new Text("User Name: ");
        Text password = new Text("Password: ");
        Text error = new Text();

        TextArea nameArea = new TextArea();
        TextArea userNameArea = new TextArea();
        TextArea passwordArea = new TextArea();

        userNameArea.setWrapText(false);
        nameArea.setWrapText(false);
        passwordArea.setWrapText(false);

        userNameArea.setMaxHeight(12);
        nameArea.setMaxHeight(12);
        passwordArea.setMaxHeight(12);

        Button save = new Button("Save");

        HBox hBox = new HBox(10, save);

        hBox.setAlignment(Pos.BOTTOM_CENTER);
        hBox.setPadding(new Insets(10));

        ComboBox<Integer> privilege = new ComboBox<>(options);

        VBox vBox = new VBox(10, privilege, nameText, nameArea, userName, userNameArea, password, passwordArea, error, hBox);

        BorderPane borderPane = new BorderPane(vBox);

        borderPane.setPadding(new Insets(10));

        Scene scene = new Scene(borderPane);

        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(nameArea.getText().trim().isEmpty() || userNameArea.getText().trim().isEmpty() ||
                        passwordArea.getText().trim().isEmpty())
                {
                    error.setText("Please make sure that all areas are filled in.");
                }
                else
                {
                    User user = new User();
                    user.setName(nameArea.getText());
                    user.setPassword(passwordArea.getText());
                    user.setPrivilege(privilege.getSelectionModel().getSelectedItem());
                    user.setUserName(userNameArea.getText());

                    Management.addObject("C:\\CPSC300\\CPSC300\\src\\Database\\users.ser", user);
                    UpdateUser.addUser(user);
                    UpdateUser.updateUser(tableView, name);
                    stage.close();
                }
            }
        });

        stage.setScene(scene);
        stage.setTitle("New User");
        stage.show();
    }
}
