package ui;

import model.ProductDetails;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PDView extends MainMenu {

    private JPanel panel;
    private JFrame frame;
    private JLabel heading;
    private JLabel typeAsin;
    private JLabel noProducts;
    private JLabel asinLabel;
    private JLabel catLabel;
    private JLabel pnameLabel;
    private JLabel lpriceLabel;
    private JLabel reffeeLabel;
    private JLabel refpcntgLabel;
    private JLabel notfoundLabel;
    private JButton search;
    private JButton back;
    private JTextField asinText;

    // EFFECTS: Creates JFrame, JPanel, and Elements.
    // MODIFIES: this.
    public PDView() {

        panel = new JPanel();
        frame = new JFrame();
        panel.setBorder(BorderFactory.createEmptyBorder());
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        back = new JButton("Back");
        heading = new JLabel("View");

        typeAsin = new JLabel("Type ASIN:");
        asinText = new JTextField();
        noProducts = new JLabel("No products have been added.");

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
        typeAsin.setFont(new Font("Calibri", Font.PLAIN, 20));
        typeAsin.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        asinText.setFont(new Font("Calibri", Font.PLAIN, 20));
        asinText.setAlignmentX(JTextField.CENTER_ALIGNMENT);
        asinText.setMinimumSize(new Dimension(200, 25));
        asinText.setPreferredSize(new Dimension(200, 25));
        asinText.setMaximumSize(new Dimension(200, 25));
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
        catLabel = new JLabel("Category:");
        catLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        catLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        pnameLabel = new JLabel("Product Name:");
        pnameLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        pnameLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        lpriceLabel = new JLabel("List Price:");
        lpriceLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        lpriceLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        reffeeLabel = new JLabel("Referral Fee:");
        reffeeLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        reffeeLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        refpcntgLabel = new JLabel("Referral Fee %:");
        refpcntgLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        refpcntgLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        notfoundLabel = new JLabel("Product Not Found.");
        notfoundLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        notfoundLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    }

    // EFFECTS: Sets visibility of elements.
    // MODIFIES: this.
    private void setVisibility() {

        asinLabel.setVisible(false);
        typeAsin.setVisible(false);
        asinText.setVisible(false);
        noProducts.setVisible(false);
        search.setVisible(false);
        catLabel.setVisible(false);
        pnameLabel.setVisible(false);
        lpriceLabel.setVisible(false);
        reffeeLabel.setVisible(false);
        refpcntgLabel.setVisible(false);
        notfoundLabel.setVisible(false);
    }

    // EFFECTS: Adds elements to the JPanel.
    // MODIFIES: this.
    private void addElements() {

        panel.add(heading);
        panel.add(load);
        panel.add(typeAsin);
        panel.add(asinText);
        panel.add(search);
        panel.add(asinLabel);
        panel.add(catLabel);
        panel.add(pnameLabel);
        panel.add(lpriceLabel);
        panel.add(reffeeLabel);
        panel.add(refpcntgLabel);
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
                checkProductDetails();
            }
            if (e.getSource().equals(search)) {
                search();
            }
            if (e.getSource().equals(back)) {
                new SubMenuGUI("Product Details");
                frame.dispose();
            }
        }
    };

    // EFFECTS: Searches for an asin and displays the corresponding details.
    // MODIFIES: this.
    private void search() {

        try {

            asin = asinText.getText();
            ProductDetails product = productDetails.get(manageOne.getProductIndex(asin));

            notfoundLabel.setVisible(false);

            asinLabel.setText("ASIN: " + product.getAsin());
            asinLabel.setVisible(true);
            catLabel.setText("Category: " + product.getCategory());
            catLabel.setVisible(true);
            pnameLabel.setText("Product Name: " + product.getProductName());
            pnameLabel.setVisible(true);
            lpriceLabel.setText("List Price: " + product.getListPrice());
            lpriceLabel.setVisible(true);
            product.setRefFee();
            reffeeLabel.setText("Referral Fee: " + product.getRefFee());
            reffeeLabel.setVisible(true);
            refpcntgLabel.setText("Referral Fee %: " + product.getRefFeePcntg());
            refpcntgLabel.setVisible(true);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {

            setErrorLabels();
            return;
        }
    }

    // EFFECTS: Searches for an asin and displays the corresponding details.
    // MODIFIES: this.
    private void setErrorLabels() {

        asinLabel.setVisible(false);
        catLabel.setVisible(false);
        pnameLabel.setVisible(false);
        lpriceLabel.setVisible(false);
        reffeeLabel.setVisible(false);
        refpcntgLabel.setVisible(false);
        notfoundLabel.setVisible(true);
    }

    // EFFECTS: Checks if array is empty, shows error if true or unhides labels if false.
    private void checkProductDetails() {

        if (productDetails.size() == 0) {
            noProducts.setVisible(true);
        } else {
            load.setVisible(false);
            typeAsin.setVisible(true);
            asinText.setVisible(true);
            search.setVisible(true);
        }
    }
}
