package Database;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Travis on 2017-11-09.
 */

//TODO change this to use hashing for the password!

public class User implements java.io.Serializable {
    private SimpleStringProperty userName;
    private SimpleStringProperty password;
    private SimpleStringProperty name;
    private int privilege;

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getUserName() {
        return userName.get();
    }

    public SimpleStringProperty userNameProperty() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public int getPrivilege() {
        return privilege;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
    }
}
