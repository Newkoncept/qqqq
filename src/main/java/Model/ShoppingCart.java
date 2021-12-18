package Model;

public class ShoppingCart {
    private String productName;
    private int productPrice;
    private int quantity;
    private int total;

    // class constructor
    public ShoppingCart(String productName, int productPrice, int quantity, int total) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.total = total;
    }

    /**
     * 
     * getter and setters for variables
     */
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

}
