package model;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsList {

    private List<ProductDetails> productDetails = new ArrayList<>();

    // MODIFIES: this
    // EFFECTS: Adds a new product details object to the productDetails array list, with a set of given values.
    public void add(String asin, String category, String productName, int listPrice, int refFeePcntg) {
        productDetails.add(new ProductDetails(asin, category, productName, listPrice, refFeePcntg));
    }

    // REQUIRES: index != -1
    // MODIFIES: this
    // EFFECTS: Removes an object from the arraylist given an asin. Returns true if object is found and removed, false
    // otherwise.
    public boolean remove(String asin) {

        int index = getProductIndex(asin);

        if (index != -1) {

            productDetails.remove(index);
            return true;
        }

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
}
