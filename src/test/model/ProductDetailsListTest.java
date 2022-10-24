package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductDetailsListTest {

    private ProductDetailsList productDetailsList;

    @BeforeEach
    void runBefore() {
        productDetailsList = new ProductDetailsList();
    }

    @Test
    void testAdd() {

        productDetailsList.add("test", "test1", "test2", 50, 5);
        assertEquals("test", productDetailsList.getProduct("test").getAsin());
    }

    @Test
    void testRemove() {

        assertFalse(productDetailsList.remove("test"));
        productDetailsList.add("test", "test1", "test2", 50, 5);
        assertFalse(productDetailsList.remove("test9"));
        assertTrue(productDetailsList.remove("test"));
    }

    @Test
    void testGetProductIndex() {

        assertEquals(productDetailsList.getProductIndex("test"), -1);
        productDetailsList.add("test", "test1", "test2", 50, 5);
        assertEquals(productDetailsList.getProductIndex("test"), 0);
        assertEquals(productDetailsList.getProductIndex("test9"), -1);
    }

    @Test
    void testGetProduct() {

        productDetailsList.add("test", "test1", "test2", 50, 5);
        assertEquals(productDetailsList.getProduct("test").getAsin(), "test");
    }

    @Test
    void testGetProductDetails() {

        productDetailsList.add("test", "test1", "test2", 50, 5);
        assertEquals(productDetailsList.getProductDetails(), productDetailsList.getProductDetails());
    }
}
