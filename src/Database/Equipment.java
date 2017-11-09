package Database;

/**
 * Created by Travis on 2017-11-09.
 */
public class Equipment implements java.io.Serializable {
    private String unitNumber;
    private String nameAndYear;
    private int odometer;

    //constructors
    public Equipment(){
        unitNumber = null;
        nameAndYear = null;
        odometer = 0;
    }

    public Equipment(String unitNumber, String nameAndYear, int odometer){
        this.unitNumber = unitNumber;
        this.nameAndYear = nameAndYear;
        this.odometer = odometer;
    }

    public String getNameAndYear() {
        return nameAndYear;
    }

    public void setNameAndYear(String nameAndYear) {
        this.nameAndYear = nameAndYear;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }
}
