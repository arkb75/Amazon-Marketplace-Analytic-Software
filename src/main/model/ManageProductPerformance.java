package model;

import java.util.ArrayList;
import java.util.List;

public class ManageProductPerformance {

    private List<ProductPerformance> productPerformance = new ArrayList<>();

    public void add(String asin, int orderID, int qtySold, double netRev, double cpu) {
        productPerformance.add(new ProductPerformance(asin, orderID, qtySold, netRev, cpu));
    }

    public boolean remove(int orderID) {

        int index = getOrderIndex(orderID);

        if (index != -1) {

            productPerformance.remove(index);
            return true;
        }

        return false;
    }

    public int getOrderIndex(int orderID) {

        for (int i = 0; i < productPerformance.size(); i++) {

            if (productPerformance.get(i).getOrderID() == orderID) {
                return i;
            }
        }

        return -1;
    }

    public ProductPerformance getOrder(int orderID) {
        return productPerformance.get(getOrderIndex(orderID));
    }

    public List getOrderDetails() {
        return productPerformance;
    }
}
