package model;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

//Modelled after https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.
public class ProductDetailsList implements Writable {

    private List<ProductDetails> productDetails = new ArrayList<>();
    private EventLog eventLog = EventLog.getInstance();

    // MODIFIES: this
    // EFFECTS: Adds a new product details object to the productDetails array list, with a set of given values.
    public void add(String asin, String category, String productName, int listPrice, int refFeePcntg, boolean loaded) {
        productDetails.add(new ProductDetails(asin, category, productName, listPrice, refFeePcntg));
        if (!loaded) {
            eventLog.logEvent(new Event("Added product to product details list."));
        }
    }

    // REQUIRES: index != -1
    // MODIFIES: this
    // EFFECTS: Removes an object from the arraylist given an asin. Returns true if object is found and removed, false
    // otherwise.
    public boolean remove(String asin) {

        int index = getProductIndex(asin);

        if (index != -1) {

            productDetails.remove(index);
            eventLog.logEvent(new Event("Removed product from product details list."));
            return true;
        }

        eventLog.logEvent(new Event("Failed to remove product from product details list."));
        return false;
    }

    // EFFECTS: Searches for and returns the index of an object with a given asin. Returns -1 if object is not found.
    public int getProductIndex(String asin) {

        for (int i = 0; i < productDetails.size(); i++) {

            if (productDetails.get(i).getAsin().equals(asin)) {
                return i;
            }
        }

        return -1;
    }

    // EFFECTS: Returns an object given an asin.
    public ProductDetails getProduct(String asin) {
        return productDetails.get(getProductIndex(asin));
    }

    // EFFECTS: Returns the list productDetails.
    public List getProductDetails() {
        return productDetails;
    }

    // EFFECTS: returns this as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", "state");
        json.put("productDetails", productDetailsToJson());
        return json;
    }

    // EFFECTS: returns productDetails in this productDetailsList as a JSON array
    private JSONArray productDetailsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (ProductDetails p : productDetails) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }
}
