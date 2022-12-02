package ui;

import model.*;
import persistence.DataLoader;
import persistence.DataWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menus {

    private static final String pdPath = "./data/productdetails.json";
    private static final String poPath = "./data/purchaseorders.json";
    private static final String ppPath = "./data/productperformance.json";
    private DataLoader pdLoader = new DataLoader(pdPath);
    private DataLoader poLoader = new DataLoader(poPath);
    private DataLoader ppLoader = new DataLoader(ppPath);
    private DataWriter pdWriter = new DataWriter(pdPath);
    private DataWriter poWriter = new DataWriter(poPath);
    private DataWriter ppWriter = new DataWriter(ppPath);
    Scanner in = new Scanner(System.in);

    ProductDetailsList manageOne = new ProductDetailsList();
    PurchaseOrdersList manageTwo = new PurchaseOrdersList();
    ProductPerformanceList manageThree = new ProductPerformanceList();

    List<ProductDetails> productDetails = manageOne.getProductDetails();
    List<PurchaseOrders> purchaseOrders = manageTwo.getOrderDetails();
    List<ProductPerformance> productPerformance = manageThree.getOrderDetails();

    String asin;
    int orderIDOne;
    int orderIDTwo;

    // EFFECTS: Displays main menu options to user and calls the menuSelection method.
    public void mainMenu() {

        boolean main = true;
        while (main) {

            System.out.println("0. Exit");
            System.out.println("1. Product Details");
            System.out.println("2. Purchase Orders");
            System.out.println("3. Product Performance");
            System.out.println("4. Load data");
            System.out.println("5. Save Data");

            main = menuSelection();
        }
    }

    // EFFECTS: Process user input for the main menu and call the appropriate method or exit the program by returning
    // false, otherwise, return true.
    public boolean menuSelection() {

        boolean main = true;

        switch (in.nextInt()) {
            case 0:
                main = false;
                break;

            case 1:
                productDetailsSelection();
                break;

            case 2:
                purchaseOrdersSelection();
                break;

            case 3:
                productPerformanceSelection();
                break;

            case 4:
                load();
                break;

            case 5:
                save();
                break;
        }

        return main;
    }

    // EFFECTS: Loads data for each list from their respective files.
    // MODIFIES: this
    private void load() {

        try {

            if (pdLoader.has("productDetails")) {

                manageOne = pdLoader.readProductDetails();
                productDetails = manageOne.getProductDetails();
            }

            if (poLoader.has("purchaseOrders")) {

                manageTwo = poLoader.readPurchaseOrders();
                purchaseOrders = manageTwo.getOrderDetails();
            }

            if (ppLoader.has("productPerformance")) {

                manageThree = ppLoader.readProductPerformance();
                productPerformance = manageThree.getOrderDetails();
            }

            System.out.println("Loaded");
        } catch (IOException e) {
            System.out.println("Unable to read from file");
        }
    }

    // EFFECTS: Saves data for each list to their respective files.
    // MODIFIES: this
    private void save() {

        try {
            pdWriter.open();
            pdWriter.writeProductDetails(manageOne);
            pdWriter.close();
            poWriter.open();
            poWriter.writePurchaseOrders(manageTwo);
            poWriter.close();
            ppWriter.open();
            ppWriter.writeProductPerformance(manageThree);
            ppWriter.close();
            System.out.println("Saved");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file");
        }
    }

    // EFFECTS: Calls the productDetails and productDetailsMenu methods in a loop.
    public void productDetailsSelection() {

        System.out.println();
        boolean sub = true;

        while (sub) {

            productDetails();
            sub = productDetailsMenu();
        }
    }

    // EFFECTS: Presents the product details submenu option to the user.
    public void productDetails() {

        System.out.println("0. Back");
        System.out.println("1. View");
        System.out.println("2. Add");
        System.out.println("3. Remove");
    }

    // EFFECTS: Process user input for the product details menu and call the appropriate method or exit the program by
    // returning false, otherwise, return true.
    public boolean productDetailsMenu() {

        boolean sub = true;

        switch (in.nextInt()) {
            case 0:
                System.out.println();
                sub = false;
                break;

            case 1:
                System.out.println();
                productDetailsView();
                enter();
                break;

            case 2:
                System.out.println();
                productDetailsAdd();
                enter();
                break;

            case 3:
                System.out.println();
                productDetailsRemove();
                enter();
                break;
        }

        return sub;
    }

    // EFFECTS: Displays the product details fields to the user. If no product details have been added, then the user is
    // shown an error message.
    public void productDetailsView() {

        if (productDetails.size() == 0) {

            System.out.println("No products have been added.");
            return;
        }

        System.out.print("Type ASIN: ");
        Scanner inOne = new Scanner(System.in);

        try {

            asin = inOne.next();
            ProductDetails product = productDetails.get(manageOne.getProductIndex(asin));

            System.out.printf("ASIN: %s\n", product.getAsin());
            System.out.printf("Category: %s\n", product.getCategory());
            System.out.printf("Product Name: %s\n", product.getProductName());
            System.out.printf("List Price: %d\n", product.getListPrice());
            product.setRefFee();
            System.out.printf("Referral Fee: %f\n", product.getRefFee());
            System.out.printf("Referral Fee Percentage: %f\n", product.getRefFeePcntg());
        } catch (IndexOutOfBoundsException e) {

            System.out.println("Product not found.");
            return;
        }
    }

    // EFFECTS: Prompts user for data for each product details field and adds a new object to the productDetailsList
    // with the inputted data. Shows an error message if invalid data is inputted.
    public void productDetailsAdd() {

        Scanner inOne = new Scanner(System.in);

        try {

            System.out.print("ASIN: ");
            asin = inOne.next();
            System.out.print("Category: ");
            String category = inOne.next();
            System.out.print("Product Name: ");
            String productName = inOne.next();
            System.out.print("List Price: ");
            int listPrice = inOne.nextInt();
            System.out.print("Referral Fee %: ");
            int refFeePcntg = inOne.nextInt();

            manageOne.add(asin, category, productName, listPrice, refFeePcntg, false);
        } catch (InputMismatchException e) {

            System.out.println("Invalid input.");
            return;
        }
    }

    // EFFECTS: Prompts user for an asin and removes the object with the corresponding asin. Shows an error message if
    // no objects are found or are present in the list.
    public void productDetailsRemove() {

        if (productDetails.size() == 0) {

            System.out.println("No products have been added.");
            return;
        }

        System.out.print("Type ASIN: ");
        Scanner inOne = new Scanner(System.in);

        try {

            asin = inOne.next();
            ProductDetails product = productDetails.get(manageOne.getProductIndex(asin));
            manageOne.remove(product.getAsin());
            System.out.printf("ASIN: %s has been removed.\n", product.getAsin());
        } catch (IndexOutOfBoundsException e) {

            System.out.println("Product not found.");
            return;
        }
    }

    // EFFECTS: Calls the purchaseOrders and purchaseOrdersMenu methods in a loop.
    public void purchaseOrdersSelection() {

        System.out.println();
        boolean subTwo = true;

        while (subTwo) {

            purchaseOrders();
            subTwo = purchaseOrdersMenu();
        }
    }

    // EFFECTS: Presents the purchase orders submenu option to the user.
    public void purchaseOrders() {

        System.out.println("0. Back");
        System.out.println("1. View");
        System.out.println("2. Add");
        System.out.println("3. Remove");
    }

    // EFFECTS: Process user input for the purchase orders menu and call the appropriate method or exit the program by
    // returning false, otherwise, return true.
    public boolean purchaseOrdersMenu() {

        boolean subTwo = true;

        switch (in.nextInt()) {
            case 0:
                System.out.println();
                subTwo = false;
                break;

            case 1:
                System.out.println();
                purchaseOrdersView();
                enter();
                break;

            case 2:
                System.out.println();
                purchaseOrdersAdd();
                enter();
                break;

            case 3:
                System.out.println();
                purchaseOrdersRemove();
                enter();
                break;
        }

        return subTwo;
    }

    // EFFECTS: Displays the purchase orders fields to the user. If no purchase orders have been added, then the user is
    // shown an error message.
    public void purchaseOrdersView() {

        if (purchaseOrders.size() == 0) {

            System.out.println("No purchase orders have been added.");
            return;
        }

        System.out.print("Type Order ID: ");
        Scanner inTwo = new Scanner(System.in);

        try {

            orderIDOne = inTwo.nextInt();
            PurchaseOrders order = purchaseOrders.get(manageTwo.getOrderIndex(orderIDOne));

            System.out.printf("Order ID: %d\n", order.getOrderID());
            System.out.printf("ASIN: %s\n", order.getAsin());
            System.out.printf("Quantity: %d\n", order.getQty());
            System.out.printf("Net Cost: %f\n", order.getNetCost());
            order.setCpu();
            System.out.printf("Cost Per Unit: %f\n", order.getCpu());
            System.out.printf("Delivery ETA: %s\n", order.getDeliveryETA());
        } catch (IndexOutOfBoundsException e) {

            System.out.println("Purchase Order not found.");
            return;
        } catch (InputMismatchException e) {

            System.out.println("Invalid input.");
            return;
        }
    }

    // EFFECTS: Prompts user for data for each purchase orders field and adds a new object to the purchaseOrdersList
    // with the inputted data. Shows an error message if invalid data is inputted.
    public void purchaseOrdersAdd() {

        Scanner inTwo = new Scanner(System.in);

        try {

            System.out.print("Order ID: ");
            orderIDOne = inTwo.nextInt();
            System.out.print("ASIN: ");
            String asinTwo = inTwo.next();
            System.out.print("Quantity: ");
            int qty = inTwo.nextInt();
            System.out.print("Net Cost: ");
            int netCost = inTwo.nextInt();
            System.out.print("Delivery ETA: ");
            String deliveryETA = inTwo.next();

            manageTwo.add(asinTwo, deliveryETA, orderIDOne, qty, netCost, false);
        } catch (InputMismatchException e) {

            System.out.println("Invalid input.");
            return;
        }
    }

    // EFFECTS: Prompts user for an order id and removes the object with the corresponding order id. Shows an error
    // message if no objects are found or are present in the list.
    public void purchaseOrdersRemove() {

        if (purchaseOrders.size() == 0) {

            System.out.println("No purchase orders have been added.");
            return;
        }

        System.out.print("Type Order ID: ");
        Scanner inTwo = new Scanner(System.in);

        try {

            orderIDOne = inTwo.nextInt();
            PurchaseOrders order = purchaseOrders.get(manageTwo.getOrderIndex(orderIDOne));
            manageTwo.remove(order.getOrderID());
            System.out.printf("Order ID: %s has been removed.\n", order.getOrderID());
        } catch (IndexOutOfBoundsException e) {

            System.out.println("Purchase Order not found.");
            return;
        } catch (InputMismatchException e) {

            System.out.println("Invalid input.");
            return;
        }
    }

    // EFFECTS: Calls the productPerformance and productPerformanceMenu methods in a loop.
    public void productPerformanceSelection() {

        System.out.println();
        boolean subThree = true;

        while (subThree) {

            productPerformance();
            subThree = productPerformanceMenu();
        }
    }

    // EFFECTS: Presents the product performance submenu option to the user.
    public void productPerformance() {

        System.out.println("0. Back");
        System.out.println("1. View");
        System.out.println("2. Add");
        System.out.println("3. Remove");
    }

    // EFFECTS: Process user input for the product performance menu and call the appropriate method or exit the program
    // by returning false, otherwise, return true.
    public boolean productPerformanceMenu() {

        boolean subThree = true;

        switch (in.nextInt()) {
            case 0:
                System.out.println();
                subThree = false;
                break;

            case 1:
                System.out.println();
                productPerformanceView();
                enter();
                break;

            case 2:
                System.out.println();
                productPerformanceAdd();
                enter();
                break;

            case 3:
                System.out.println();
                productPerformanceRemove();
                enter();
                break;
        }

        return subThree;
    }

    // EFFECTS: Displays the product performance fields to the user. If no product performance objects have been added,
    // then the user is shown an error message.
    public void productPerformanceView() {

        if (productPerformance.size() == 0) {

            System.out.println("No product performance data has been added.");
            return;
        }

        System.out.print("Type Order ID: ");
        Scanner inThree = new Scanner(System.in);

        try {

            orderIDTwo = inThree.nextInt();
            ProductPerformance order = productPerformance.get(manageThree.getOrderIndex(orderIDTwo));

            System.out.printf("Order ID: %d\n", order.getOrderID());
            System.out.printf("ASIN: %s\n", order.getAsin());
            System.out.printf("Quantity Sold: %d\n", order.getQtySold());
            System.out.printf("Net Revenue: %f\n", order.getNetRev());
            System.out.printf("Cost Per Unit: %f\n", order.getCpu());
            order.setNetProfit();
            System.out.printf("Net Profit: %f\n", order.getNetProfit());
        } catch (IndexOutOfBoundsException e) {

            System.out.println("Purchase Order not found.");
            return;
        } catch (InputMismatchException e) {

            System.out.println("Invalid input.");
            return;
        }
    }

    // EFFECTS: Prompts user for data for each product performance field and adds a new object to the
    // productPerformanceList with the inputted data. Shows an error message if invalid data is inputted.
    public void productPerformanceAdd() {

        Scanner inThree = new Scanner(System.in);

        try {

            System.out.print("Order ID: ");
            orderIDTwo = inThree.nextInt();
            System.out.print("ASIN: ");
            String asinThree = inThree.next();
            System.out.print("Quantity Sold: ");
            int qtySold = inThree.nextInt();
            System.out.print("Net Revenue: ");
            double netRev = inThree.nextInt();
            System.out.print("Cost Per Unit: ");
            double cpu = inThree.nextInt();

            manageThree.add(asinThree, orderIDTwo, qtySold, netRev, cpu, false);
        } catch (InputMismatchException e) {

            System.out.println("Invalid input.");
            return;
        }
    }

    // EFFECTS: Prompts user for an order id and removes the object with the corresponding order id. Shows an error
    // message if no objects are found or are present in the list.
    public void productPerformanceRemove() {

        if (productPerformance.size() == 0) {

            System.out.println("No product performance data has been added.");
            return;
        }

        System.out.print("Type Order ID: ");
        Scanner inThree = new Scanner(System.in);

        try {

            orderIDTwo = inThree.nextInt();
            ProductPerformance order = productPerformance.get(manageThree.getOrderIndex(orderIDTwo));
            manageThree.remove(order.getOrderID());
            System.out.printf("Order ID: %s has been removed.\n", order.getOrderID());
        } catch (IndexOutOfBoundsException e) {

            System.out.println("Purchase Order not found.");
            return;
        } catch (InputMismatchException e) {

            System.out.println("Invalid input.");
            return;
        }
    }

    // EFFECTS: Prompts the user to hit the enter key. Continues execution of the program after.
    public void enter() {

        System.out.println("Press the enter key to continue...");
        Scanner enter = new Scanner(System.in);
        enter.nextLine();
    }
}
