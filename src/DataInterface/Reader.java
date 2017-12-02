package DataInterface;

import Database.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;



/**
 * This class is more of a generic catch-all reader method for interacting with the database in precise ways
 * more often then not, the methods in Casting should be sufficient for reading and writing to the database
 * through GUI fields, this class is for methods that are more specific.
 *
 * Created by Travis Kurucz on 2017-11-19.
 */
public class Reader {


    /**
     * This function takes in a created user object and compares the hashed password to the list of users,
     * checking for matching usernames then passwords
     * if it finds a matching username it immediately checks the password. If the passwords do not match, it returns false
     * with an applicable error message.
     * If the user was not found, it also returns false and with a different error message for the user not existing
     *
     *
     * @param user
     * @return
     */
    public static boolean checkPassword(User user, String path) {
        try {
            ArrayList list = Management.readList(path +"\\users.ser");
            ArrayList<User> userList = Casting.castToUsers(list);
            for (int i = 0; i < userList.size(); i++) {
                if (user.getUserName().equals(userList.get(i).getUserName())) {
                    if (user.getPassword().equals(userList.get(i).getPassword())) {
                        return true;
                    }
                    return false;
                }
            }//end for
        }
        catch (Exception e)
        {
            System.out.println(e.getStackTrace());
        }
        return false;
    }//end check password
}
