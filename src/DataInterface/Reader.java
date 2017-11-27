package DataInterface;

import Database.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;



/**
 * Created by Travis Kurucz on 2017-11-26.
 */
public class Reader {


    public static boolean checkPassword(User user) {
        ArrayList list = Management.readList("CPSC300/src/Database/users.ser");
        ArrayList<User> userList = CastingMethods.castToUsers(list);
        for (int i = 0; i < userList.size(); i++) {
            if(user.getUserName() == userList.get(i).getUserName()){
                if(user.getPassword() == userList.get(i).getPassword()){
                    return true;
                }
                JOptionPane.showMessageDialog(null,"Error: Password does not match database. Please contact your system administrator"+
                " for assistance.");
                return false;
            }
        }//end for
        JOptionPane.showMessageDialog(null,"Error: Username not found. Contact your system administrator for assisstance.");
        return false;
    }//end check password

}
