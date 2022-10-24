package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PurchaseOrdersTest {

    private PurchaseOrders purchaseOrders;

    @BeforeEach
    void runBefore() {
        purchaseOrders = new PurchaseOrders("test", "test1", 1, 500, 300);
    }

    @Test
    void testSetAndGetCpu() {

        purchaseOrders.setCpu();
        assertEquals(purchaseOrders.getCpu(), 0.6);
    }

    @Test
    void testGetAsin() {

        assertEquals(purchaseOrders.getAsin(), "test");
    }

    @Test
    void testDeliveryEta() {

        assertEquals(purchaseOrders.getDeliveryETA(), "test1");
    }

    @Test
    void testGetOrderID() {

        assertEquals(purchaseOrders.getOrderID(), 1);
    }

    @Test
    void testGetQty() {

        assertEquals(purchaseOrders.getQty(), 500);
    }

    @Test
    void testGetNetCost() {

        assertEquals(purchaseOrders.getNetCost(), 300);
    }
}
