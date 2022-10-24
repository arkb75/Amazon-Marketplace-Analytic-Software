package model;

public class ProductDetails {

    private String asin;
    private String category;
    private String productName;
    private int listPrice;
    private double refFeePcntg;
    private double refFee;

    // MODIFIES: this
    // EFFECTS: Initializes a new ProductDetails object with given values.
    public ProductDetails(String asin, String category, String productName, int listPrice, int refFeePcntg) {

        this.asin = asin;
        this.category = category;
        this.productName = productName;
        this.listPrice = listPrice;
        this.refFeePcntg = refFeePcntg;

        setRefFee();
    }

    // MODIFIES: this
    // EFFECTS: Calculates and sets the referral fee.
    public void setRefFee() {
        refFee = listPrice * (refFeePcntg / 100);
    }

    // EFFECTS: Returns the asin.
    public String getAsin() {
        return asin;
    }

    // EFFECTS: Returns the category.
    public String getCategory() {
        return category;
    }

    // EFFECTS: Returns the product name.
    public String getProductName() {
        return productName;
    }

    // EFFECTS: Returns the list price.
    public int getListPrice() {
        return listPrice;
    }

    // EFFECTS: Returns the referral fee.
    public double getRefFee() {
        return refFee;
    }

    // EFFECTS: Returns the referral fee percentage.
    public double getRefFeePcntg() {
        return refFeePcntg;
    }
}
