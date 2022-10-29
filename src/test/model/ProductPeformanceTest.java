package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductPerformanceTest {

    private ProductPerformance productPerformance;

    @BeforeEach
    void runBefore() {
        productPerformance = new ProductPerformance("test", 1, 150, 800, 5);
    }

    @Test
    void testSetAndGetNetProfit() {

        productPerformance.setNetProfit();
        assertEquals(productPerformance.getNetProfit(), 50);
    }

    @Test
    void testGetAsin() {

        assertEquals(productPerformance.getAsin(), "test");
    }

    @Test
    void testGetOrderID() {

        assertEquals(productPerformance.getOrderID(), 1);
    }

    @Test
    void testGetQtySold() {

        assertEquals(productPerformance.getQtySold(), 150);
    }

    @Test
    void testGetNetRev() {

        assertEquals(productPerformance.getNetRev(), 800);
    }

    @Test
    void testGetCpu() {

        assertEquals(productPerformance.getCpu(), 5);
    }

    @Test
    void testToJSon() {

        assertTrue(productPerformance.toJson().has("productPerformance"));
    }
}
