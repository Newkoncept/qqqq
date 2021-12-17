package Model;

import java.io.Serializable;

public class Item implements Serializable {
    private String productName;
    private String productId;
    private int productPrice;
    private int quantityLeft;
    private static final long serialVersionUID = 6529685098267757690L;

    // class constructor
    public Item(String productId, String productName, int productPrice, int quantityLeft) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantityLeft = quantityLeft;
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

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getQuantityLeft() {
        return quantityLeft;
    }

    public void setQuantityLeft(int quantityLeft) {
        this.quantityLeft = quantityLeft;
    }

}
