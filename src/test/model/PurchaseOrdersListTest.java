package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PurchaseOrdersListTest {

    private PurchaseOrdersList purchaseOrdersList;

    @BeforeEach
    void runBefore() {
        purchaseOrdersList = new PurchaseOrdersList();
    }

    @Test
    void testAdd() {

        purchaseOrdersList.add("test", "test1", 1, 500, 300, false);
        assertEquals(1, purchaseOrdersList.getOrder(1).getOrderID());
    }

    @Test
    void testRemove() {

        assertFalse(purchaseOrdersList.remove(1));
        purchaseOrdersList.add("test", "test1", 1, 500, 300, false);
        assertFalse(purchaseOrdersList.remove(5));
        assertTrue(purchaseOrdersList.remove(1));
    }

    @Test
    void testGetOrderIndex() {

        assertEquals(purchaseOrdersList.getOrderIndex(1), -1);
        purchaseOrdersList.add("test", "test1", 1, 500, 300, false);
        assertEquals(purchaseOrdersList.getOrderIndex(1), 0);
        assertEquals(purchaseOrdersList.getOrderIndex(5), -1);
    }

    @Test
    void testGetOrder() {

        purchaseOrdersList.add("test", "test1", 1, 500, 300, false);
        assertEquals(purchaseOrdersList.getOrder(1).getOrderID(), 1);
    }

    @Test
    void testGetOrderDetails() {

        purchaseOrdersList.add("test", "test1", 1, 500, 300, false);
        assertEquals(purchaseOrdersList.getOrderDetails(), purchaseOrdersList.getOrderDetails());
    }

    @Test
    void testToJSon() {

        assertTrue(purchaseOrdersList.toJson().has("purchaseOrders"));
    }
}
