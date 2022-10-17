package model;

public class PurchaseOrders {

    private String asin;
    private String deliveryETA;
    private int orderID;
    private int qty;
    private double netCost;
    private double cpu;

    public PurchaseOrders(String asin, String deliveryETA, int orderID, int qty, int netCost) {

        this.asin = asin;
        this.deliveryETA = deliveryETA;
        this.orderID = orderID;
        this.qty = qty;
        this.netCost = netCost;

        setCpu();
    }

    public void setCpu() {
        cpu = netCost / qty;
    }

    public String getAsin() {
        return asin;
    }

    public String getDeliveryETA() {
        return deliveryETA;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getQty() {
        return qty;
    }

    public double getNetCost() {
        return netCost;
    }

    public double getCpu() {
        return cpu;
    }
}
