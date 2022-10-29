package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductDetailsTest {

    private ProductDetails productDetails;

    @BeforeEach
    void runBefore() {
        productDetails = new ProductDetails("test", "test1", "test2", 50, 5);
    }

    @Test
    void testSetAndGetRefFee() {

        productDetails.setRefFee();
        assertEquals(productDetails.getRefFee(), 2.5);
    }

    @Test
    void testGetAsin() {

        assertEquals(productDetails.getAsin(), "test");
    }

    @Test
    void testGetCategory() {

        assertEquals(productDetails.getCategory(), "test1");
    }

    @Test
    void testGetProductName() {

        assertEquals(productDetails.getProductName(), "test2");
    }

    @Test
    void testGetListPrice() {

        assertEquals(productDetails.getListPrice(), 50);
    }

    @Test
    void testGetRefFeePcntg() {

        assertEquals(productDetails.getRefFeePcntg(), 5);
    }

    @Test
    void testToJSon() {

        assertTrue(productDetails.toJson().has("productDetails"));
    }
}
