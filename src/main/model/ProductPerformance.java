package model;

import org.json.JSONObject;
import persistence.Writable;

//Modelled after https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.
public class ProductPerformance implements Writable {

    private String asin;
    private int orderID;
    private int qtySold;
    private double netRev;
    private double cpu;
    private double netProfit;

    // MODIFIES: this
    // EFFECTS: Initializes a new ProductPerformance object with given values.
    public ProductPerformance(String asin, int orderID, int qtySold, double netRev, double cpu) {

        this.asin = asin;
        this.orderID = orderID;
        this.qtySold = qtySold;
        this.netRev = netRev;
        this.cpu = cpu;

        setNetProfit();
    }

    // MODIFIES: this
    // EFFECTS: Calculates and sets the net profit.
    public void setNetProfit() {
        netProfit = netRev - (cpu * qtySold);
    }

    // EFFECTS: Returns the asin.
    public String getAsin() {
        return asin;
    }

    // EFFECTS: Returns the order id.
    public int getOrderID() {
        return orderID;
    }

    // EFFECTS: Returns the quantity sold.
    public int getQtySold() {
        return qtySold;
    }

    // EFFECTS: Returns the net revenue.
    public double getNetRev() {
        return netRev;
    }

    // EFFECTS: Returns the cost per unit.
    public double getCpu() {
        return cpu;
    }

    // EFFECTS: Returns the net profit.
    public double getNetProfit() {
        return netProfit;
    }

    // EFFECTS: returns this as JSON object
    @Override
    public JSONObject toJson() {

        JSONObject json = new JSONObject();
        json.put("asin", asin);
        json.put("order id", orderID);
        json.put("qtySold", qtySold);
        json.put("net revenue", netRev);
        json.put("cpu", cpu);
        json.put("net profit", netProfit);
        return json;
    }
}
