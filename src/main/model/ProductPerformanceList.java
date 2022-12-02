package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import java.util.ArrayList;
import java.util.List;

//Modelled after https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.
public class ProductPerformanceList implements Writable {

    private List<ProductPerformance> productPerformance = new ArrayList<>();
    private EventLog eventLog = EventLog.getInstance();

    // MODIFIES: this
    // EFFECTS: Adds a new product performance object to the productPerformance array list, with a set of given values.
    public void add(String asin, int orderID, int qtySold, double netRev, double cpu, boolean loaded) {
        productPerformance.add(new ProductPerformance(asin, orderID, qtySold, netRev, cpu));
        if (!loaded) {
            eventLog.logEvent(new Event("Added product performance to product performance list."));
        }
    }

    // REQUIRES: index != -1
    // MODIFIES: this
    // EFFECTS: Removes an object from the arraylist given an order id. Returns true if object is found and removed,
    // false otherwise.
    public boolean remove(int orderID) {

        int index = getOrderIndex(orderID);

        if (index != -1) {

            productPerformance.remove(index);
            eventLog.logEvent(new Event("Removed product performance from product performance list."));
            return true;
        }

        eventLog.logEvent(new Event("Failed to remove product performance from product performance list."));
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

    // EFFECTS: returns this as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", "state");
        json.put("productPerformance", productPerformanceToJson());
        return json;
    }

    // EFFECTS: returns productPerformance in this productPerformanceList as a JSON array
    public JSONArray productPerformanceToJson() {
        JSONArray jsonArray = new JSONArray();

        for (ProductPerformance p : productPerformance) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }
}
