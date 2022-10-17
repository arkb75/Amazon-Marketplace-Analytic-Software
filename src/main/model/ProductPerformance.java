package model;

public class ProductPerformance {

    private String asin;
    private int orderID;
    private int qtySold;
    private double netRev;
    private double cpu;
    private double netProfit;

    public ProductPerformance(String asin, int orderID, int qtySold, double netRev, double cpu) {

        this.asin = asin;
        this.orderID = orderID;
        this.qtySold = qtySold;
        this.netRev = netRev;
        this.cpu = cpu;

        setNetProfit();
    }

    public void setNetProfit() {
        netProfit = netRev - (cpu * qtySold);
    }

    public String getAsin() {
        return asin;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getQtySold() {
        return qtySold;
    }

    public double getNetRev() {
        return netRev;
    }

    public double getCpu() {
        return cpu;
    }

    public double getNetProfit() {
        return netProfit;
    }
}
