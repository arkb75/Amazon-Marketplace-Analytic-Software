package model;

import java.util.ArrayList;
import java.util.List;

public class ManagePurchaseOrders {

    private List<PurchaseOrders> purchaseOrders = new ArrayList<>();

    public void add(String asin, String deliveryETA, int orderID, int qty, int netCost) {
        purchaseOrders.add(new PurchaseOrders(asin, deliveryETA, orderID, qty, netCost));
    }

    public boolean remove(int orderID) {

        int index = getOrderIndex(orderID);

        if (index != -1) {

            purchaseOrders.remove(index);
            return true;
        }

        return false;
    }

    public int getOrderIndex(int orderID) {

        for (int i = 0; i < purchaseOrders.size(); i++) {

            if (purchaseOrders.get(i).getOrderID() == orderID) {
                return i;
            }
        }

        return -1;
    }

    public PurchaseOrders getOrder(int orderID) {
        return purchaseOrders.get(getOrderIndex(orderID));
    }

    public List getOrderDetails() {
        return purchaseOrders;
    }
}
