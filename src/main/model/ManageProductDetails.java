package model;

import java.util.ArrayList;
import java.util.List;

public class ManageProductDetails {

    private List<ProductDetails> productDetails = new ArrayList<>();

    public void add(String asin, String category, String productName, int listPrice, int refFeePcntg) {
        productDetails.add(new ProductDetails(asin, category, productName, listPrice, refFeePcntg));
    }

    public boolean remove(String asin) {

        int index = getProductIndex(asin);

        if (index != -1) {

            productDetails.remove(index);
            return true;
        }

        return false;
    }

    public int getProductIndex(String asin) {

        for (int i = 0; i < productDetails.size(); i++) {

            if (productDetails.get(i).getAsin().equals(asin)) {
                return i;
            }
        }

        return -1;
    }

    public ProductDetails getProduct(String asin) {
        return productDetails.get(getProductIndex(asin));
    }

    public List getProductDetails() {
        return productDetails;
    }
}
