package model;

import java.util.ArrayList;
import java.util.List;

public class ProductPerformanceList {

    private List<ProductPerformance> productPerformance = new ArrayList<>();

    // MODIFIES: this
    // EFFECTS: Adds a new product performance object to the productPerformance array list, with a set of given values.
    public void add(String asin, int orderID, int qtySold, double netRev, double cpu) {
        productPerformance.add(new ProductPerformance(asin, orderID, qtySold, netRev, cpu));
    }

    // REQUIRES: index != -1
    // MODIFIES: this
    // EFFECTS: Removes an object from the arraylist given an order id. Returns true if object is found and removed,
    // false otherwise.
    public boolean remove(int orderID) {

        int index = getOrderIndex(orderID);

        if (index != -1) {

            productPerformance.remove(index);
            return true;
        }

        return false;
    }

    // EFFECTS: Searches for and returns the index of an object with a given order id. Returns -1 if object is not found
    public int getOrderIndex(int orderID) {

        for (int i = 0; i < productPerformance.size(); i++) {

            if (productPerformance.get(i).getOrderID() == orderID) {
                return i;
            }
        }

        return -1;
    }

    // EFFECTS: Returns an object given an order id.
    public ProductPerformance getOrder(int orderID) {
        return productPerformance.get(getOrderIndex(orderID));
    }

    // EFFECTS: Returns the list productPerformance.
    public List getOrderDetails() {
        return productPerformance;
    }
}
