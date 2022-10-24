package model;

import java.util.ArrayList;
import java.util.List;

public class PurchaseOrdersList {

    private List<PurchaseOrders> purchaseOrders = new ArrayList<>();

    // MODIFIES: this
    // EFFECTS: Adds a new purchase orders object to the purchaseOrders array list, with a set of given values.
    public void add(String asin, String deliveryETA, int orderID, int qty, int netCost) {
        purchaseOrders.add(new PurchaseOrders(asin, deliveryETA, orderID, qty, netCost));
    }

    // REQUIRES: index != -1
    // MODIFIES: this
    // EFFECTS: Removes an object from the arraylist given an order id. Returns true if object is found and removed,
    // false otherwise.
    public boolean remove(int orderID) {

        int index = getOrderIndex(orderID);

        if (index != -1) {

            purchaseOrders.remove(index);
            return true;
        }

        return false;
    }

    // EFFECTS: Searches for and returns the index of an object with a given order id. Returns -1 if object is not found
    public int getOrderIndex(int orderID) {

        for (int i = 0; i < purchaseOrders.size(); i++) {

            if (purchaseOrders.get(i).getOrderID() == orderID) {
                return i;
            }
        }

        return -1;
    }

    // EFFECTS: Returns an object given an order id.
    public PurchaseOrders getOrder(int orderID) {
        return purchaseOrders.get(getOrderIndex(orderID));
    }

    // EFFECTS: Returns the list purchaseOrders.
    public List getOrderDetails() {
        return purchaseOrders;
    }
}
