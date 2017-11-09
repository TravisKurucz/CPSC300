package Database;

/**
 * Created by Travis on 2017-11-09.
 */
public class Customer implements java.io.Serializable
{
    private String name;
    private String address;
    private String phoneNumber;
    private String email;


    //default constructor:
    public Customer(){
        name = null;
        address = null;
        phoneNumber = null;
        email = null;
    }

    public Customer(String name, String address, String phoneNumber, String email){
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }




    //getters and setters
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
