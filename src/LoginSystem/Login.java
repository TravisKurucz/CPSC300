package LoginSystem;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import Database.*;
import DataInterface.*;

public class Login {

    //take username and password from the login attempt, and check if password matches hashed password
    //uses Java's MD5 message digest algorithm to hash the password, which generates a hexadecimal number
    public boolean login(String username, String password)
    {
        //the password user input
        String passwordInput = password;
        //hashed password on file
        String hashedPassword = null;

        try {
            //create instance
            MessageDigest md = MessageDigest.getInstance("MD5");
            //add password digest to bytes
            md.update(passwordInput.getBytes());
            //get hashes bytes
            byte[] bytes = md.digest();
            //convert to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < bytes.length; i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //get complete hashed password in hex format
            hashedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        //get password in database based on username
        String currentPassword = null;
        User check = new User();
        check.setUserName(username);
        check.setPassword(hashedPassword);
        //check if it matches
        return Reader.checkPassword(check);
    }

    //this method will be used to create a hashed password when a new user makes an account
    //privilege is either 0 for general users, 1 for admin
    public void createUser(String username, String password, String name, int privilege)
    {
        //uses same hashing method as above, and saves into database
        String passwordToHash = password;
        String generatedPassword = null;

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(passwordToHash.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < bytes.length; i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        //put information into database based on username
        User user = new User();
        user.setUserName(username);
        user.setName(name);
        user.setPassword(generatedPassword);
        user.setPrivilege(privilege);
        Management.addObject("CPSC300/SRC/Database/users.ser", user);
    }
}
