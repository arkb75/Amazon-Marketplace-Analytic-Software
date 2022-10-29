package model;

import org.json.JSONObject;
import persistence.Writable;

//Modelled after https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.
public class PurchaseOrders implements Writable {

    private String asin;
    private String deliveryETA;
    private int orderID;
    private int qty;
    private double netCost;
    private double cpu;

    // MODIFIES: this
    // EFFECTS: Initializes a new ProductPerformance object with given values.
    public PurchaseOrders(String asin, String deliveryETA, int orderID, int qty, int netCost) {

        this.asin = asin;
        this.deliveryETA = deliveryETA;
        this.orderID = orderID;
        this.qty = qty;
        this.netCost = netCost;

        setCpu();
    }

    // MODIFIES: this
    // EFFECTS: Calculates and sets the cost per unit.
    public void setCpu() {
        cpu = netCost / qty;
    }

    // EFFECTS: Returns the asin.
    public String getAsin() {
        return asin;
    }

    // EFFECTS: Returns the delivery eta.
    public String getDeliveryETA() {
        return deliveryETA;
    }

    // EFFECTS: Returns the order id.
    public int getOrderID() {
        return orderID;
    }

    // EFFECTS: Returns the quantity.
    public int getQty() {
        return qty;
    }

    // EFFECTS: Returns the net cost.
    public double getNetCost() {
        return netCost;
    }

    // EFFECTS: Returns the cost per unit.
    public double getCpu() {
        return cpu;
    }

    // EFFECTS: returns this as JSON object
    @Override
    public JSONObject toJson() {

        JSONObject json = new JSONObject();
        json.put("asin", asin);
        json.put("delivery eta", deliveryETA);
        json.put("order id", orderID);
        json.put("qty", qty);
        json.put("net cost", netCost);
        json.put("cpu", cpu);
        return json;
    }
}
