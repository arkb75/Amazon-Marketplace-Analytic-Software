package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class POAdd extends MainMenu {

    private JPanel panel;
    private JFrame frame;
    private JLabel heading;
    private JLabel error;
    private JButton submit;
    private JButton save;
    private JButton back;
    JTextField orderIDIn;
    JTextField asinIn;
    JTextField qtyIn;
    JTextField ncostIn;
    JTextField detaIn;

    // EFFECTS: Creates JFrame, JPanel, and Elements.
    // MODIFIES: this.
    public POAdd() {

        setBase();
        setButtons();

        setAsinIn("ASIN");
        setOrderIDIn("Order ID");
        setQtyIn("Quantity");
        setNcostIn("Net Cost");
        setDetaIn("Delivery ETA");

        frame.add(panel);
        panel.add(Box.createVerticalGlue());
        panel.add(heading);
        panel.add(orderIDIn);
        panel.add(asinIn);
        panel.add(qtyIn);
        panel.add(ncostIn);
        panel.add(detaIn);
        panel.add(error);
        panel.add(submit);
        panel.add(save);
        panel.add(back);

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
        submit = new JButton("Submit");
        save = new JButton("Save");
        back = new JButton("Back");
        heading = new JLabel("Add");
        heading.setFont(new Font("Calibri", Font.BOLD, 35));
        heading.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        error = new JLabel("Invalid input.");
        error.setFont(new Font("Calibri", Font.PLAIN, 20));
        error.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        error.setVisible(false);
    }

    // EFFECTS: Creates buttons.
    // MODIFIES: this.
    private void setButtons() {

        submit.setAlignmentX(JButton.CENTER_ALIGNMENT);
        submit.setMinimumSize(new Dimension(200, 25));
        submit.setPreferredSize(new Dimension(200, 25));
        submit.setMaximumSize(new Dimension(200, 25));
        submit.addActionListener(chooseAction);

        save.setAlignmentX(JButton.CENTER_ALIGNMENT);
        save.setMinimumSize(new Dimension(200, 25));
        save.setPreferredSize(new Dimension(200, 25));
        save.setMaximumSize(new Dimension(200, 25));
        save.addActionListener(chooseAction);

        back.setAlignmentX(JButton.CENTER_ALIGNMENT);
        back.setMinimumSize(new Dimension(200, 25));
        back.setPreferredSize(new Dimension(200, 25));
        back.setMaximumSize(new Dimension(200, 25));
        back.addActionListener(chooseAction);
    }

    // EFFECTS: Creates Asin textbox.
    // MODIFIES: this.
    private void setAsinIn(String name) {

        asinIn = new JTextField(name);
        asinIn.setFont(new Font("Calibri", Font.PLAIN, 20));
        asinIn.setAlignmentX(JTextField.CENTER_ALIGNMENT);
        asinIn.setMinimumSize(new Dimension(200, 25));
        asinIn.setPreferredSize(new Dimension(200, 25));
        asinIn.setMaximumSize(new Dimension(200, 25));
    }

    // EFFECTS: Creates Order ID textbox.
    // MODIFIES: this.
    private void setOrderIDIn(String name) {

        orderIDIn = new JTextField(name);
        orderIDIn.setFont(new Font("Calibri", Font.PLAIN, 20));
        orderIDIn.setAlignmentX(JTextField.CENTER_ALIGNMENT);
        orderIDIn.setMinimumSize(new Dimension(200, 25));
        orderIDIn.setPreferredSize(new Dimension(200, 25));
        orderIDIn.setMaximumSize(new Dimension(200, 25));
    }

    // EFFECTS: Creates QTY textbox.
    // MODIFIES: this.
    private void setQtyIn(String name) {

        qtyIn = new JTextField(name);
        qtyIn.setFont(new Font("Calibri", Font.PLAIN, 20));
        qtyIn.setAlignmentX(JTextField.CENTER_ALIGNMENT);
        qtyIn.setMinimumSize(new Dimension(200, 25));
        qtyIn.setPreferredSize(new Dimension(200, 25));
        qtyIn.setMaximumSize(new Dimension(200, 25));
    }

    // EFFECTS: Creates Net Cost textbox.
    // MODIFIES: this.
    private void setNcostIn(String name) {

        ncostIn = new JTextField(name);
        ncostIn.setFont(new Font("Calibri", Font.PLAIN, 20));
        ncostIn.setAlignmentX(JTextField.CENTER_ALIGNMENT);
        ncostIn.setMinimumSize(new Dimension(200, 25));
        ncostIn.setPreferredSize(new Dimension(200, 25));
        ncostIn.setMaximumSize(new Dimension(200, 25));
    }

    // EFFECTS: Creates Delivery ETA textbox.
    // MODIFIES: this.
    private void setDetaIn(String name) {

        detaIn = new JTextField(name);
        detaIn.setFont(new Font("Calibri", Font.PLAIN, 20));
        detaIn.setAlignmentX(JTextField.CENTER_ALIGNMENT);
        detaIn.setMinimumSize(new Dimension(200, 25));
        detaIn.setPreferredSize(new Dimension(200, 25));
        detaIn.setMaximumSize(new Dimension(200, 25));
    }

    // EFFECTS: Handles button input.
    ActionListener chooseAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(back)) {
                new SubMenuGUI("Purchase Orders");
                frame.dispose();
            }
            if (e.getSource().equals(submit)) {
                submit();
            }
            if (e.getSource().equals(save)) {
                save();
            }
        }
    };

    // EFFECTS: Adds updated data to array (from fields).
    // MODIFIES: this.
    private void submit() {

        try {

            String asinTwo = asinIn.getText();
            orderIDOne = Integer.parseInt(orderIDIn.getText());
            int qty = Integer.parseInt(qtyIn.getText());
            int netCost = Integer.parseInt(ncostIn.getText());
            String deliveryETA = detaIn.getText();

            manageTwo.add(asinTwo, deliveryETA, orderIDOne, qty, netCost);
            error.setVisible(false);
        } catch (NumberFormatException e) {

            error.setVisible(true);
            return;
        }
    }
}
