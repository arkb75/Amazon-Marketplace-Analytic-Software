package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

//Modelled after https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.
public class DataLoader {
    private String path;

    // EFFECTS: constructs reader with path.
    public DataLoader(String path) {
        this.path = path;
    }

    // EFFECTS: Checks if given key is in file.
    public boolean has(String key) throws IOException {

        String data = readData(path);
        JSONObject jsonObject = new JSONObject(data);

        return jsonObject.has(key);
    }

    // EFFECTS: reads product details from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ProductDetailsList readProductDetails() throws IOException {

        String data = readData(path);
        JSONObject jsonObject = new JSONObject(data);
        return parseProductDetailsList(jsonObject);
    }

    // EFFECTS: reads purchase orders from file and returns it;
    // throws IOException if an error occurs reading data from file
    public PurchaseOrdersList readPurchaseOrders() throws IOException {

        String data = readData(path);
        JSONObject jsonObject = new JSONObject(data);
        return parsePurchaseOrdersList(jsonObject);
    }

    // EFFECTS: reads product performance from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ProductPerformanceList readProductPerformance() throws IOException {

        String data = readData(path);
        JSONObject jsonObject = new JSONObject(data);
        return parseProductPerformanceList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readData(String path) throws IOException {

        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(path), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses product details from JSON object and returns it
    private ProductDetailsList parseProductDetailsList(JSONObject jsonObject) {

        ProductDetailsList pd = new ProductDetailsList();
        addProductDetails(pd, jsonObject);
        return pd;
    }

    // MODIFIES: pd
    // EFFECTS: parses productDetails from JSON object and adds them to productDetailsList.
    private void addProductDetails(ProductDetailsList pd, JSONObject jsonObject) {

        JSONArray jsonArray = jsonObject.getJSONArray("productDetails");
        for (Object json : jsonArray) {
            JSONObject nextProduct = (JSONObject) json;
            addProduct(pd, nextProduct);
        }
    }

    // MODIFIES: pd
    // EFFECTS: parses productDetails from JSON object and adds it to productDetailsList.
    private void addProduct(ProductDetailsList pd, JSONObject jsonObject) {
        String asin = jsonObject.getString("asin");
        String category = jsonObject.getString("category");
        String productName = jsonObject.getString("product name");
        int listPrice = jsonObject.getInt("list price");
        int refFeePcntg = jsonObject.getInt("ref fee %");
        pd.add(asin, category, productName, listPrice, refFeePcntg, true);
    }

    // EFFECTS: parses purchase orders from JSON object and returns it
    private PurchaseOrdersList parsePurchaseOrdersList(JSONObject jsonObject) {

        PurchaseOrdersList po = new PurchaseOrdersList();
        addPurchaseOrders(po, jsonObject);
        return po;
    }

    // MODIFIES: po
    // EFFECTS: parses purchaseOrders from JSON object and adds them to purchaseOrdersList.
    private void addPurchaseOrders(PurchaseOrdersList po, JSONObject jsonObject) {

        JSONArray jsonArray = jsonObject.getJSONArray("purchaseOrders");
        for (Object json : jsonArray) {
            JSONObject nextProduct = (JSONObject) json;
            addPurchaseOrder(po, nextProduct);
        }
    }

    // MODIFIES: po
    // EFFECTS: parses purchaseOrders from JSON object and adds it to purchaseOrdersList.
    private void addPurchaseOrder(PurchaseOrdersList po, JSONObject jsonObject) {
        String asin = jsonObject.getString("asin");
        String deliveryEta = jsonObject.getString("delivery eta");
        int orderID = jsonObject.getInt("order id");
        int qty = jsonObject.getInt("qty");
        int netCost = jsonObject.getInt("net cost");
        po.add(asin, deliveryEta, orderID, qty, netCost, true);
    }

    // EFFECTS: parses product performance from JSON object and returns it
    private ProductPerformanceList parseProductPerformanceList(JSONObject jsonObject) {

        ProductPerformanceList pp = new ProductPerformanceList();
        addProductPerformance(pp, jsonObject);
        return pp;
    }

    // MODIFIES: pp
    // EFFECTS: parses productPerformance from JSON object and adds them to productPerformanceList.
    private void addProductPerformance(ProductPerformanceList pp, JSONObject jsonObject) {

        JSONArray jsonArray = jsonObject.getJSONArray("productPerformance");
        for (Object json : jsonArray) {
            JSONObject nextProduct = (JSONObject) json;
            addPerformance(pp, nextProduct);
        }
    }

    // MODIFIES: pp
    // EFFECTS: parses productPerformance from JSON object and adds it to productPerformanceList.
    private void addPerformance(ProductPerformanceList pp, JSONObject jsonObject) {
        String asin = jsonObject.getString("asin");
        int orderID = jsonObject.getInt("order id");
        int qtySold = jsonObject.getInt("qtySold");
        int netRevenue = jsonObject.getInt("net revenue");
        int cpu = jsonObject.getInt("cpu");
        pp.add(asin, orderID, qtySold, netRevenue, cpu, true);
    }
}
