package ui;

import model.ProductDetails;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PDRemove extends MainMenu {

    private JPanel panel;
    private JFrame frame;
    private JLabel heading;
    private JLabel typeAsin;
    private JLabel noProducts;
    private JLabel notfoundLabel;
    private JButton save;
    private JButton remove;
    private JButton back;
    private JTextField asinText;

    // EFFECTS: Creates JFrame, JPanel, and Elements.
    // MODIFIES: this.
    public PDRemove() {

        setBase();
        setButtons();

        asinText = new JTextField();
        asinText.setFont(new Font("Calibri", Font.PLAIN, 20));
        asinText.setAlignmentX(JTextField.CENTER_ALIGNMENT);
        asinText.setMinimumSize(new Dimension(200, 25));
        asinText.setPreferredSize(new Dimension(200, 25));
        asinText.setMaximumSize(new Dimension(200, 25));
        asinText.setVisible(false);

        frame.add(panel);
        panel.add(Box.createVerticalGlue());
        panel.add(heading);
        panel.add(typeAsin);
        panel.add(asinText);
        panel.add(load);
        panel.add(save);
        panel.add(remove);
        panel.add(back);
        panel.add(noProducts);
        panel.add(notfoundLabel);

        panel.add(Box.createVerticalStrut(600));
        frame.setBounds(100,100,700,700);
        frame.setVisible(true);
    }

    // EFFECTS: Sets base values for JFrame, JPanel, and Elements.
    // MODIFIES: this.
    private void setBase() {

        panel = new JPanel();
        frame = new JFrame();
        panel.setBorder(BorderFactory.createEmptyBorder());
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        save = new JButton("Save");
        remove = new JButton("Remove");
        back = new JButton("Back");
        heading = new JLabel("Remove");
        heading.setFont(new Font("Calibri", Font.BOLD, 35));
        heading.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        typeAsin = new JLabel("Type ASIN:");
        typeAsin.setFont(new Font("Calibri", Font.PLAIN, 20));
        typeAsin.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        typeAsin.setVisible(false);
        noProducts = new JLabel("No products have been added.");
        noProducts.setFont(new Font("Calibri", Font.PLAIN, 20));
        noProducts.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        noProducts.setVisible(false);
        notfoundLabel = new JLabel("Product Not Found.");
        notfoundLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        notfoundLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        notfoundLabel.setVisible(false);
    }

    // EFFECTS: Creates buttons.
    // MODIFIES: this.
    private void setButtons() {

        save.setAlignmentX(JButton.CENTER_ALIGNMENT);
        save.setMinimumSize(new Dimension(200, 25));
        save.setPreferredSize(new Dimension(200, 25));
        save.setMaximumSize(new Dimension(200, 25));
        save.setVisible(false);
        save.addActionListener(chooseAction);
        load.addActionListener(chooseAction);

        remove.setAlignmentX(JButton.CENTER_ALIGNMENT);
        remove.setMinimumSize(new Dimension(200, 25));
        remove.setPreferredSize(new Dimension(200, 25));
        remove.setMaximumSize(new Dimension(200, 25));
        remove.setVisible(false);
        remove.addActionListener(chooseAction);

        back.setAlignmentX(JButton.CENTER_ALIGNMENT);
        back.setMinimumSize(new Dimension(200, 25));
        back.setPreferredSize(new Dimension(200, 25));
        back.setMaximumSize(new Dimension(200, 25));
        back.addActionListener(chooseAction);
    }

    // EFFECTS: Handles button input.
    ActionListener chooseAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(back)) {
                new SubMenuGUI("Product Details");
                frame.dispose();
            }
            if (e.getSource().equals(load)) {
                load();
                checkProductDetails();
            }
            if (e.getSource().equals(save)) {
                save();
            }
            if (e.getSource().equals(remove)) {
                remove();
            }
        }
    };

    // EFFECTS: Removes selected item from array.
    private void remove() {

        try {

            asin = asinText.getText();
            ProductDetails product = productDetails.get(manageOne.getProductIndex(asin));
            manageOne.remove(product.getAsin());
        } catch (IndexOutOfBoundsException e) {

            notfoundLabel.setVisible(true);
            return;
        }
    }

    // EFFECTS: Checks if array is empty, shows error if true or unhides labels if false.
    private void checkProductDetails() {

        if (productDetails.size() == 0) {
            noProducts.setVisible(true);
        } else {
            load.setVisible(false);
            typeAsin.setVisible(true);
            asinText.setVisible(true);
            remove.setVisible(true);
            save.setVisible(true);
        }
    }
}
