package persistence;

import model.ProductDetailsList;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

//Modelled after https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.
class DataLoaderTest {

    private DataLoader dataLoader;

    @Test
    void testReaderNonExistentFile() {
        dataLoader = new DataLoader("./data/error.json");
        try {
            ProductDetailsList pd = dataLoader.readProductDetails();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }
}
