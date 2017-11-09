package Database;

/**
 * Created by Travis on 2017-11-09.
 */
public class Part implements java.io.Serializable {
    private String partNumber;
    private String name;
    private String suggestedCost;

    //constructors:

    public Part(){
        partNumber = null;
        name = null;
        suggestedCost = null;
    }

    public Part(String partNumber, String name, String suggestedCost){
        this.partNumber = partNumber;
        this.name = name;
        this.suggestedCost = suggestedCost;
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
}
