package Model;

import java.io.Serializable;

/**
 * Return
 */
public class Return implements Serializable {
    // variable instantiation
    private double totalAmount;
    private String dateReturned;
    private int numReturned;
    private static final long serialVersionUID = 6529685098267757690L;

    // class constructor
    public Return(double totalAmount, String dateReturned, int numReturned) {
        this.totalAmount = totalAmount;
        this.dateReturned = dateReturned;
        this.numReturned = numReturned;
    }

    /****
     * 
     * Getter and setters for the variables
     */
    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDateReturned() {
        return dateReturned;
    }

    public void setDateReturned(String dateReturned) {
        this.dateReturned = dateReturned;
    }

    public int getNumReturned() {
        return numReturned;
    }

    public void setNumReturned(int numReturned) {
        this.numReturned = numReturned;
    }

}