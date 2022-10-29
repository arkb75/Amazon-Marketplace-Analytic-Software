package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductPerformanceListTest {

    private ProductPerformanceList productPerformanceList;

    @BeforeEach
    void runBefore() {
        productPerformanceList = new ProductPerformanceList();
    }

    @Test
    void testAdd() {

        productPerformanceList.add("test", 1, 150, 800, 5);
        assertEquals(1, productPerformanceList.getOrder(1).getOrderID());
    }

    @Test
    void testRemove() {

        assertFalse(productPerformanceList.remove(1));
        productPerformanceList.add("test", 1, 150, 800, 5);
        assertFalse(productPerformanceList.remove(5));
        assertTrue(productPerformanceList.remove(1));
    }

    @Test
    void testGetOrderIndex() {

        assertEquals(productPerformanceList.getOrderIndex(1), -1);
        productPerformanceList.add("test", 1, 150, 800, 5);
        assertEquals(productPerformanceList.getOrderIndex(1), 0);
        assertEquals(productPerformanceList.getOrderIndex(5), -1);
    }

    @Test
    void testGetOrder() {

        productPerformanceList.add("test", 1, 150, 800, 5);
        assertEquals(productPerformanceList.getOrder(1).getOrderID(), 1);
    }

    @Test
    void testGetOrderDetails() {

        productPerformanceList.add("test", 1, 150, 800, 5);
        assertEquals(productPerformanceList.getOrderDetails(), productPerformanceList.getOrderDetails());
    }

    @Test
    void testToJSon() {

        assertTrue(productPerformanceList.toJson().has("productPerformance"));
    }
}
