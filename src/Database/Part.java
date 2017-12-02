package Database;

/**
 * Created by Travis on 2017-11-09.
 */
public class Part implements java.io.Serializable {
    private String partNumber;
    private String name;
    private String suggestedCost;
    private int numberOrdered;
    private int numOnHand;
    private String type;
    private String date;
    private String invoiceNumber;
    private String amountReceived;

    //constructors:

    public Part(){
        partNumber = null;
        name = null;
        suggestedCost = null;
        numberOrdered = 0;
    }

    public Part(String partNumber, String name, String suggestedCost, int numberOrdered){
        this.partNumber = partNumber;
        this.name = name;
        this.suggestedCost = suggestedCost;
        this.numberOrdered = numberOrdered;
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

    public int getNumOnHand() {
        return numOnHand;
    }

    public void setNumOnHand(int numOnHand) {
        this.numOnHand = numOnHand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getAmountReceived() {
        return amountReceived;
    }

    public void setAmountReceived(String amountReceived) {
        this.amountReceived = amountReceived;
    }
}
