package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

//Modelled after https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.
public class PurchaseOrdersList implements Writable {

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

    // EFFECTS: returns this as JSON objects
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", "state");
        json.put("purchaseOrders", purchaseOrdersToJson());
        return json;
    }

    // EFFECTS: returns purchaseOrders in this purchaseOrdersList as a JSON array
    private JSONArray purchaseOrdersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (PurchaseOrders p : purchaseOrders) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }
}
