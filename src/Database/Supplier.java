package Database;

/**
 * Created by Travis on 2017-11-09.
 */
public class Supplier implements java.io.Serializable {
    private String name;
    private String address;
    private String phoneNumber;
    private String email;


    //constructor
    public Supplier(String address, String email, String name, String phoneNumber) {
        this.address = address;
        this.email = email;
        this.name = name;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
