package model;

public class ProductDetails {

    private String asin;
    private String category;
    private String productName;
    private int listPrice;
    private double refFeePcntg;
    private double refFee;

    public ProductDetails(String asin, String category, String productName, int listPrice, int refFeePcntg) {

        this.asin = asin;
        this.category = category;
        this.productName = productName;
        this.listPrice = listPrice;
        this.refFeePcntg = refFeePcntg;

        setRefFee();
    }

    public void setRefFee() {
        refFee = listPrice * (refFeePcntg / 100);
    }

    public String getAsin() {
        return asin;
    }

    public String getCategory() {
        return category;
    }

    public String getProductName() {
        return productName;
    }

    public int getListPrice() {
        return listPrice;
    }

    public double getRefFee() {
        return refFee;
    }

    public double getRefFeePcntg() {
        return refFeePcntg;
    }
}
