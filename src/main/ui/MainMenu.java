package ui;

import model.*;
import model.Event;
import persistence.DataLoader;
import persistence.DataWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class MainMenu {

    private static final String pdPath = "./data/productdetails.json";
    private static final String poPath = "./data/purchaseorders.json";
    private static final String ppPath = "./data/productperformance.json";
    private DataLoader pdLoader = new DataLoader(pdPath);
    private DataLoader poLoader = new DataLoader(poPath);
    private DataLoader ppLoader = new DataLoader(ppPath);
    private DataWriter pdWriter = new DataWriter(pdPath);
    private DataWriter poWriter = new DataWriter(poPath);
    private DataWriter ppWriter = new DataWriter(ppPath);

    ProductDetailsList manageOne = new ProductDetailsList();
    PurchaseOrdersList manageTwo = new PurchaseOrdersList();
    ProductPerformanceList manageThree = new ProductPerformanceList();

    List<ProductDetails> productDetails = manageOne.getProductDetails();
    List<PurchaseOrders> purchaseOrders = manageTwo.getOrderDetails();
    List<ProductPerformance> productPerformance = manageThree.getOrderDetails();

    String asin;
    int orderIDOne;
    int orderIDTwo;

    private JPanel panel;
    private JFrame frame;
    private JButton productDetailsButton;
    private JButton productPerformanceButton;
    private JButton purchaseOrdersButton;
    private JButton exit;
    protected JButton load;
    protected JButton save;
    private JLabel heading;

    // EFFECTS: Creates JFrame, JPanel, and Elements.
    // MODIFIES: this.
    public MainMenu() {

        panel = new JPanel();
        frame = new JFrame();
        panel.setBorder(BorderFactory.createEmptyBorder());
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        load = new JButton("Load");
        save = new JButton("Save");
        productDetailsButton = new JButton("Product Details");
        productPerformanceButton = new JButton("Product Performance");
        purchaseOrdersButton = new JButton("Purchase Orders");
        heading = new JLabel("Main Menu");
        heading.setFont(new Font("Calibri", Font.BOLD, 35));
        heading.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        setElements();

        panel.add(Box.createVerticalGlue());
        panel.add(heading);
        panel.add(productDetailsButton);
        panel.add(productPerformanceButton);
        panel.add(purchaseOrdersButton);
        exitButton();
        panel.add(Box.createVerticalStrut(600));
        frame.add(panel);
        frame.setBounds(100,100,700,700);
        frame.setVisible(true);
    }

    private void exitButton() {

        exit = new JButton("Exit");

        exit.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        exit.setMinimumSize(new Dimension(200, 25));
        exit.setPreferredSize(new Dimension(200, 25));
        exit.setMaximumSize(new Dimension(200, 25));

        panel.add(exit);
        exit.addActionListener(chooseSubmenu);
    }

    // EFFECTS: Sets elements.
    // MODIFIES: this.
    private void setElements() {

        load.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        load.setMinimumSize(new Dimension(200, 25));
        load.setPreferredSize(new Dimension(200, 25));
        load.setMaximumSize(new Dimension(200, 25));
        save.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        save.setMinimumSize(new Dimension(200, 25));
        save.setPreferredSize(new Dimension(200, 25));
        save.setMaximumSize(new Dimension(200, 25));
        productDetailsButton.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        productDetailsButton.setMinimumSize(new Dimension(200, 25));
        productDetailsButton.setPreferredSize(new Dimension(200, 25));
        productDetailsButton.setMaximumSize(new Dimension(200, 25));
        productPerformanceButton.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        productPerformanceButton.setMinimumSize(new Dimension(200, 25));
        productPerformanceButton.setPreferredSize(new Dimension(200, 25));
        productPerformanceButton.setMaximumSize(new Dimension(200, 25));
        purchaseOrdersButton.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        purchaseOrdersButton.setMinimumSize(new Dimension(200, 25));
        purchaseOrdersButton.setPreferredSize(new Dimension(200, 25));
        purchaseOrdersButton.setMaximumSize(new Dimension(200, 25));
        productDetailsButton.addActionListener(chooseSubmenu);
        productPerformanceButton.addActionListener(chooseSubmenu);
        purchaseOrdersButton.addActionListener(chooseSubmenu);
    }

    // EFFECTS: Handles button input.
    ActionListener chooseSubmenu = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(productDetailsButton)) {
                new SubMenuGUI("Product Details");
                frame.dispose();
            }
            if (e.getSource().equals(productPerformanceButton)) {
                new SubMenuGUI("Product Performance");
                frame.dispose();
            }
            if (e.getSource().equals(purchaseOrdersButton)) {
                new SubMenuGUI("Purchase Orders");
                frame.dispose();
            }
            if (e.getSource().equals(exit)) {
                Iterator<Event> iterator = EventLog.getInstance().iterator();
                while (iterator.hasNext()) {
                    System.out.println(iterator.next());
                }
                System.exit(0);
            }
        }
    };

    // EFFECTS: Loads data for each list from their respective files.
    // MODIFIES: this
    protected void load() {

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
        } catch (IOException e) {
            System.out.println("Unable to read from file");
        }
    }

    // EFFECTS: Saves data for each list to their respective files.
    // MODIFIES: this
    protected void save() {

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
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file");
        }
    }
}
