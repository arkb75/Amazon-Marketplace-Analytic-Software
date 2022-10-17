package ui;

import model.*;

import java.util.List;
import java.util.Scanner;

public class Menus {

    Scanner in = new Scanner(System.in);

    ManageProductDetails manageOne = new ManageProductDetails();
    ManagePurchaseOrders manageTwo = new ManagePurchaseOrders();
    ManageProductPerformance manageThree = new ManageProductPerformance();

    List<ProductDetails> productDetails = manageOne.getProductDetails();
    List<PurchaseOrders> purchaseOrders = manageTwo.getOrderDetails();
    List<ProductPerformance> productPerformance = manageThree.getOrderDetails();

    String asin;
    int orderIDOne;
    int orderIDTwo;

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void mainMenu() {

        boolean main = true;
        while (main) {

            System.out.println("0. Exit");
            System.out.println("1. Product Details");
            System.out.println("2. Purchase Orders");
            System.out.println("3. Product Performance");

            switch (in.nextInt()) {
                case 0:
                    main = false;
                    break;

                case 1:
                    productDetails();
                    boolean sub = true;

                    while (sub) {

                        switch (in.nextInt()) {
                            case 0:
                                sub = false;
                                break;

                            case 1:
                                productDetailsView();
                                sub = false;
                                break;

                            case 2:
                                productDetailsAdd();
                                sub = false;
                                break;

                            case 3:
                                productDetailsRemove();
                                sub = false;
                                break;
                        }
                    }
                    break;

                case 2:
                    purchaseOrders();
                    boolean subTwo = true;

                    while (subTwo) {

                        switch (in.nextInt()) {
                            case 0:
                                subTwo = false;
                                break;

                            case 1:
                                purchaseOrdersView();
                                subTwo = false;
                                break;

                            case 2:
                                purchaseOrdersAdd();
                                subTwo = false;
                                break;

                            case 3:
                                purchaseOrdersRemove();
                                subTwo = false;
                                break;
                        }
                    }
                    break;

                case 3:
                    productPerformance();
                    boolean subThree = true;

                    while (subThree) {

                        switch (in.nextInt()) {
                            case 0:
                                subThree = false;
                                break;

                            case 1:
                                productPerformanceView();
                                subThree = false;
                                break;

                            case 2:
                                productPerformanceAdd();
                                subThree = false;
                                break;

                            case 3:
                                productPerformanceRemove();
                                subThree = false;
                                break;
                        }
                    }
                    break;
            }
        }
    }

    public void productDetails() {

        System.out.println("0. Back");
        System.out.println("1. View");
        System.out.println("2. Add");
        System.out.println("3. Remove");
    }

    public void productDetailsView() {

        System.out.print("Type ASIN: ");
        asin = in.next();
        ProductDetails product = productDetails.get(manageOne.getProductIndex(asin));

        System.out.printf("ASIN: %s\n", product.getAsin());
        System.out.printf("Category: %s\n", product.getCategory());
        System.out.printf("Product Name: %s\n", product.getProductName());
        System.out.printf("List Price: %d\n", product.getListPrice());
        product.setRefFee();
        System.out.printf("Referral Fee: %f\n", product.getRefFee());
        System.out.printf("Referral Fee Percentage: %f\n", product.getRefFeePcntg());
    }

    public void productDetailsAdd() {

        System.out.print("ASIN: ");
        asin = in.next();
        System.out.print("Category: ");
        String category = in.next();
        System.out.print("Product Name: ");
        String productName = in.next();
        System.out.print("List Price: ");
        int listPrice = in.nextInt();
        System.out.print("Referral Fee %: ");
        int refFeePcntg = in.nextInt();

        manageOne.add(asin, category, productName, listPrice, refFeePcntg);
    }

    public void productDetailsRemove() {

        System.out.print("Type ASIN: ");
        asin = in.next();

        ProductDetails product = productDetails.get(manageOne.getProductIndex(asin));
        manageOne.remove(product.getAsin());
        System.out.printf("ASIN: %s has been removed.\n", product.getAsin());
    }

    public void purchaseOrders() {

        System.out.println("0. Back");
        System.out.println("1. View");
        System.out.println("2. Add");
        System.out.println("3. Remove");
    }

    public void purchaseOrdersView() {

        System.out.print("Type Order ID: ");
        orderIDOne = in.nextInt();
        PurchaseOrders order = purchaseOrders.get(manageTwo.getOrderIndex(orderIDOne));

        System.out.printf("Order ID: %d\n", order.getOrderID());
        System.out.printf("ASIN: %s\n", order.getAsin());
        System.out.printf("Quantity: %d\n", order.getQty());
        System.out.printf("Net Cost: %f\n", order.getNetCost());
        order.setCpu();
        System.out.printf("Cost Per Unit: %f\n", order.getCpu());
        System.out.printf("Delivery ETA: %s\n", order.getDeliveryETA());
    }

    public void purchaseOrdersAdd() {

        System.out.print("Order ID: ");
        orderIDOne = in.nextInt();
        System.out.print("ASIN: ");
        String asinTwo = in.next();
        System.out.print("Quantity: ");
        int qty = in.nextInt();
        System.out.print("Net Cost: ");
        int netCost = in.nextInt();
        System.out.print("Delivery ETA: ");
        String deliveryETA = in.next();

        manageTwo.add(asinTwo, deliveryETA, orderIDOne, qty, netCost);
    }

    public void purchaseOrdersRemove() {

        System.out.print("Type Order ID: ");
        orderIDOne = in.nextInt();

        PurchaseOrders order = purchaseOrders.get(manageTwo.getOrderIndex(orderIDOne));
        manageTwo.remove(order.getOrderID());
        System.out.printf("Order ID: %s has been removed.\n", order.getOrderID());
    }

    public void productPerformance() {

        System.out.println("0. Back");
        System.out.println("1. View");
        System.out.println("2. Add");
        System.out.println("3. Remove");
    }

    public void productPerformanceView() {

        System.out.print("Type Order ID: ");
        orderIDTwo = in.nextInt();
        ProductPerformance order = productPerformance.get(manageThree.getOrderIndex(orderIDTwo));

        System.out.printf("Order ID: %d\n", order.getOrderID());
        System.out.printf("ASIN: %s\n", order.getAsin());
        System.out.printf("Quantity Sold: %d\n", order.getQtySold());
        System.out.printf("Net Revenue: %f\n", order.getNetRev());
        System.out.printf("Cost Per Unit: %f\n", order.getCpu());
        order.setNetProfit();
        System.out.printf("Net Profit: %f\n", order.getNetProfit());
    }

    public void productPerformanceAdd() {

        System.out.print("Order ID: ");
        orderIDTwo = in.nextInt();
        System.out.print("ASIN: ");
        String asinThree = in.next();
        System.out.print("Quantity Sold: ");
        int qtySold = in.nextInt();
        System.out.print("Net Revenue: ");
        double netRev = in.nextInt();
        System.out.print("Cost Per Unit: ");
        double cpu = in.nextInt();

        manageThree.add(asinThree, orderIDTwo, qtySold, netRev, cpu);
    }

    public void productPerformanceRemove() {

        System.out.print("Type Order ID: ");
        orderIDTwo = in.nextInt();

        ProductPerformance order = productPerformance.get(manageThree.getOrderIndex(orderIDTwo));
        manageThree.remove(order.getOrderID());
        System.out.printf("Order ID: %s has been removed.\n", order.getOrderID());
    }
}
