package Model;

import java.io.Serializable;

public class Sale implements Serializable {
    // variable instantiation
    private String productId;
    private String productName;
    private int totalAmount;
    private int numSold;
    private static final long serialVersionUID = 6529685098267757690L;

    // class constructor
    public Sale(String productId, String productName, int totalAmount, int numSold) {
        this.productId = productId;
        this.productName = productName;
        this.totalAmount = totalAmount;
        this.numSold = numSold;
    }

    /**
     * 
     * getter and setters for variables
     */
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getNumSold() {
        return numSold;
    }

    public void setNumSold(int numSold) {
        this.numSold = numSold;
    }

}
