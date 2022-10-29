package persistence;

import model.*;
import org.json.JSONObject;
import java.io.*;

//Modelled after https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.
public class DataWriter {

    private static final int TAB = 4;
    private PrintWriter writer;
    private String path;

    // EFFECTS: constructs writer with path.
    public DataWriter(String path) {
        this.path = path;
    }

    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(path));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of productDetails to file.
    public void writeProductDetails(ProductDetailsList pd) {
        JSONObject json = pd.toJson();
        save(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of purchaseOrders to file.
    public void writePurchaseOrders(PurchaseOrdersList po) {
        JSONObject json = po.toJson();
        save(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of productPerformance to file.
    public void writeProductPerformance(ProductPerformanceList pp) {
        JSONObject json = pp.toJson();
        save(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer.
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes to file.
    private void save(String json) {
        writer.print(json);
    }
}
