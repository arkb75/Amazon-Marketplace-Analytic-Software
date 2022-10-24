package model;

public class ProductPerformance {

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
}
