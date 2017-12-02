package Database;


/**
 * Created by Travis on 2017-11-09.
 */

//TODO change this to use hashing for the password!

public class User implements java.io.Serializable {
    private String userName;
    private String password;
    private String name;
    private int privilege;

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getPrivilege() {
        return privilege;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
    }
}
