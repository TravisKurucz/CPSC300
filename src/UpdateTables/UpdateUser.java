package UpdateTables;

import Database.Equipment;
import Database.Management;
import Database.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

/**
 * Created by colto on 2017-12-01.
 */
public class UpdateUser
{
    private static ObservableList users;

    public static void setObservableList(String path)
    {
        try {
            users = FXCollections.observableArrayList(Management.readList(path + "\\users.ser"));
        }
        catch (Exception io)
        {
            ArrayList array = new ArrayList();
            users = FXCollections.observableArrayList(array);
        }
    }
    public static void updateUser(TableView table, TableColumn name)
    {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        table.getItems().addAll(users);
    }

    public static void addUser(User user)
    {
        users.add(user);
    }


    public static ObservableList getList()
    {
        return users;
    }
    public static boolean contains(String userName)
    {
        for(int i = 0; i < users.size(); i++)
        {
            User user = (User) users.get(i);

            if(user.getUserName().equals(userName))
            {
                return true;
            }

        }
        return false;
    }

    public static User getUser(String userName)
    {
        for(int i = 0; i < users.size(); i++)
        {
            User user = (User) users.get(i);

            if(user.getUserName().equals(userName))
            {
                return user;
            }
        }
        return null;
    }

}
