package model;

import org.json.JSONObject;
import persistence.Writable;

//Modelled after https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.
public class ProductDetails implements Writable {

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

    // EFFECTS: returns this as JSON object
    @Override
    public JSONObject toJson() {

        JSONObject json = new JSONObject();
        json.put("asin", asin);
        json.put("category", category);
        json.put("product name", productName);
        json.put("list price", listPrice);
        json.put("ref fee", refFee);
        json.put("ref fee %", refFeePcntg);
        return json;
    }
}
