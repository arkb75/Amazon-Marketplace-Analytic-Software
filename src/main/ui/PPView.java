package ui;

import model.ProductPerformance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PPView extends MainMenu {

    private JPanel panel;
    private JFrame frame;
    private JLabel heading;
    private JLabel typeOrderID;
    private JLabel noProducts;
    private JLabel orderIDLabel;
    private JLabel asinLabel;
    private JLabel qtysoldLabel;
    private JLabel cpuLabel;
    private JLabel nrevLabel;
    private JLabel nprofitLabel;
    private JLabel notfoundLabel;
    private JButton search;
    private JButton back;
    private JTextField orderIDText;

    // EFFECTS: Creates JFrame, JPanel, and Elements.
    // MODIFIES: this.
    public PPView() {

        panel = new JPanel();
        frame = new JFrame();
        panel.setBorder(BorderFactory.createEmptyBorder());
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        back = new JButton("Back");
        heading = new JLabel("View");

        typeOrderID = new JLabel("Type Order ID:");
        orderIDText = new JTextField();
        noProducts = new JLabel("No orders have been added.");

        setElements();
        setLabels();
        setVisibility();

        frame.add(panel);
        panel.add(Box.createVerticalGlue());
        addElements();
        panel.add(Box.createVerticalStrut(600));
        frame.setBounds(100,100,700,700);
        frame.setVisible(true);
    }

    // EFFECTS: Sets elements.
    // MODIFIES: this.
    private void setElements() {

        heading.setFont(new Font("Calibri", Font.BOLD, 35));
        heading.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        typeOrderID.setFont(new Font("Calibri", Font.PLAIN, 20));
        typeOrderID.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        orderIDText.setFont(new Font("Calibri", Font.PLAIN, 20));
        orderIDText.setAlignmentX(JTextField.CENTER_ALIGNMENT);
        orderIDText.setMinimumSize(new Dimension(200, 25));
        orderIDText.setPreferredSize(new Dimension(200, 25));
        orderIDText.setMaximumSize(new Dimension(200, 25));
        noProducts.setFont(new Font("Calibri", Font.PLAIN, 20));
        noProducts.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        search = new JButton("Search");
        search.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        search.setMinimumSize(new Dimension(200, 25));
        search.setPreferredSize(new Dimension(200, 25));
        search.setMaximumSize(new Dimension(200, 25));
        back.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        back.setMinimumSize(new Dimension(200, 25));
        back.setPreferredSize(new Dimension(200, 25));
        back.setMaximumSize(new Dimension(200, 25));
        load.addActionListener(chooseAction);
        search.addActionListener(chooseAction);
        back.addActionListener(chooseAction);
    }

    // EFFECTS: Sets labels.
    // MODIFIES: this.
    private void setLabels() {

        asinLabel = new JLabel("ASIN:");
        asinLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        asinLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        orderIDLabel = new JLabel("Order ID:");
        orderIDLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        orderIDLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        qtysoldLabel = new JLabel("Quantity Sold:");
        qtysoldLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        qtysoldLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        cpuLabel = new JLabel("Cost Per Unit:");
        cpuLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        cpuLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        nrevLabel = new JLabel("Net Revenue:");
        nrevLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        nrevLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        nprofitLabel = new JLabel("Net Profit:");
        nprofitLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        nprofitLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        notfoundLabel = new JLabel("Order Not Found.");
        notfoundLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        notfoundLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    }

    // EFFECTS: Sets visibility of elements.
    // MODIFIES: this.
    private void setVisibility() {

        asinLabel.setVisible(false);
        typeOrderID.setVisible(false);
        orderIDText.setVisible(false);
        noProducts.setVisible(false);
        search.setVisible(false);
        nprofitLabel.setVisible(false);
        orderIDLabel.setVisible(false);
        qtysoldLabel.setVisible(false);
        cpuLabel.setVisible(false);
        nrevLabel.setVisible(false);
        notfoundLabel.setVisible(false);
    }

    // EFFECTS: Adds elements to the JPanel.
    // MODIFIES: this.
    private void addElements() {

        panel.add(heading);
        panel.add(load);
        panel.add(typeOrderID);
        panel.add(orderIDText);
        panel.add(search);
        panel.add(orderIDLabel);
        panel.add(asinLabel);
        panel.add(qtysoldLabel);
        panel.add(cpuLabel);
        panel.add(nrevLabel);
        panel.add(nprofitLabel);
        panel.add(noProducts);
        panel.add(notfoundLabel);
        panel.add(back);
    }

    // EFFECTS: Handles button input.
    ActionListener chooseAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(load)) {
                load();
                checkProductPerformance();
            }
            if (e.getSource().equals(search)) {
                search();
            }
            if (e.getSource().equals(back)) {
                new SubMenuGUI("Product Performance");
                frame.dispose();
            }
        }
    };

    // EFFECTS: Searches for an asin and displays the corresponding details.
    // MODIFIES: this.
    private void search() {

        try {

            orderIDTwo = Integer.parseInt(orderIDText.getText());
            ProductPerformance order = productPerformance.get(manageThree.getOrderIndex(orderIDTwo));

            notfoundLabel.setVisible(false);

            orderIDLabel.setText("Order ID: " + order.getOrderID());
            orderIDLabel.setVisible(true);
            asinLabel.setText("ASIN: " + order.getAsin());
            asinLabel.setVisible(true);
            qtysoldLabel.setText("Quantity Sold: " + order.getQtySold());
            qtysoldLabel.setVisible(true);
            nrevLabel.setText("Net Revenue: " + order.getNetRev());
            nrevLabel.setVisible(true);
            cpuLabel.setText("Cost Per Unit: " + order.getCpu());
            cpuLabel.setVisible(true);
            order.setNetProfit();
            nprofitLabel.setText("Net Profit: " + order.getNetProfit());
            nprofitLabel.setVisible(true);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {

            setErrorLabels();
            return;
        }
    }

    // EFFECTS: Searches for an asin and displays the corresponding details.
    // MODIFIES: this.
    private void setErrorLabels() {

        orderIDLabel.setVisible(false);
        asinLabel.setVisible(false);
        qtysoldLabel.setVisible(false);
        nrevLabel.setVisible(false);
        cpuLabel.setVisible(false);
        nprofitLabel.setVisible(false);
        notfoundLabel.setVisible(true);
    }

    // EFFECTS: Checks if array is empty, shows error if true or unhides labels if false.
    private void checkProductPerformance() {

        if (productPerformance.size() == 0) {
            noProducts.setVisible(true);
        } else {
            load.setVisible(false);
            typeOrderID.setVisible(true);
            orderIDText.setVisible(true);
            search.setVisible(true);
        }
    }
}
