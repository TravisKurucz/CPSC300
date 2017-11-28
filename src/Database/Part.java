package Database;

import javafx.scene.Scene;

/**
 * Created by Travis on 2017-11-09.
 */
public class Part implements java.io.Serializable {
    private String partNumber;
    private String name;
    private String suggestedCost;
    private int numberOrdered;
    private static int numOnHand;
    private String type;

    //constructors:

    public Part(){
        partNumber = null;
        name = null;
        suggestedCost = null;
        numberOrdered = 0;
    }

    public Part(String partNumber, String name, String suggestedCost, int numberOnHand){
        this.partNumber = partNumber;
        this.name = name;
        this.suggestedCost = suggestedCost;
        this.numOnHand = numberOnHand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getSuggestedCost() {
        return suggestedCost;
    }

    public void setSuggestedCost(String suggestedCost) {
        this.suggestedCost = suggestedCost;
    }

    public int getNumberOrdered() {
        return numberOrdered;
    }

    public void setNumberOrdered(int numberOrdered) {
        this.numberOrdered = numberOrdered;
    }

    public static int getNumOnHand() {
        return numOnHand;
    }

    public static void setNumOnHand(int numOnHand) {
        Part.numOnHand = numOnHand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
