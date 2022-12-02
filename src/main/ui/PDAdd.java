package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PDAdd extends MainMenu {

    private JPanel panel;
    private JFrame frame;
    private JLabel heading;
    private JLabel error;
    private JButton submit;
    private JButton save;
    private JButton back;
    JTextField asinIn;
    JTextField catIn;
    JTextField pnameIn;
    JTextField lpriceIn;
    JTextField refpcntgIn;

    // EFFECTS: Creates JFrame, JPanel, and Elements.
    // MODIFIES: this.
    public PDAdd() {

        setBase();
        setButtons();

        setAsinIn("ASIN");
        setcatIn("Category");
        setPnameIn("Product Name");
        setLpriceIn("List Price");
        setRefpcntgIn("Referral Fee %");

        frame.add(panel);
        panel.add(Box.createVerticalGlue());
        panel.add(heading);
        panel.add(asinIn);
        panel.add(catIn);
        panel.add(pnameIn);
        panel.add(lpriceIn);
        panel.add(refpcntgIn);
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

    // EFFECTS: Creates Category textbox.
    // MODIFIES: this.
    private void setcatIn(String name) {

        catIn = new JTextField(name);
        catIn.setFont(new Font("Calibri", Font.PLAIN, 20));
        catIn.setAlignmentX(JTextField.CENTER_ALIGNMENT);
        catIn.setMinimumSize(new Dimension(200, 25));
        catIn.setPreferredSize(new Dimension(200, 25));
        catIn.setMaximumSize(new Dimension(200, 25));
    }

    // EFFECTS: Creates Product Name textbox.
    // MODIFIES: this.
    private void setPnameIn(String name) {

        pnameIn = new JTextField(name);
        pnameIn.setFont(new Font("Calibri", Font.PLAIN, 20));
        pnameIn.setAlignmentX(JTextField.CENTER_ALIGNMENT);
        pnameIn.setMinimumSize(new Dimension(200, 25));
        pnameIn.setPreferredSize(new Dimension(200, 25));
        pnameIn.setMaximumSize(new Dimension(200, 25));
    }

    // EFFECTS: Creates List Price textbox.
    // MODIFIES: this.
    private void setLpriceIn(String name) {

        lpriceIn = new JTextField(name);
        lpriceIn.setFont(new Font("Calibri", Font.PLAIN, 20));
        lpriceIn.setAlignmentX(JTextField.CENTER_ALIGNMENT);
        lpriceIn.setMinimumSize(new Dimension(200, 25));
        lpriceIn.setPreferredSize(new Dimension(200, 25));
        lpriceIn.setMaximumSize(new Dimension(200, 25));
    }

    // EFFECTS: Creates Referral Fee % textbox.
    // MODIFIES: this.
    private void setRefpcntgIn(String name) {

        refpcntgIn = new JTextField(name);
        refpcntgIn.setFont(new Font("Calibri", Font.PLAIN, 20));
        refpcntgIn.setAlignmentX(JTextField.CENTER_ALIGNMENT);
        refpcntgIn.setMinimumSize(new Dimension(200, 25));
        refpcntgIn.setPreferredSize(new Dimension(200, 25));
        refpcntgIn.setMaximumSize(new Dimension(200, 25));
    }

    // EFFECTS: Handles button input.
    ActionListener chooseAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(back)) {
                new SubMenuGUI("Product Details");
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

            asin = asinIn.getText();
            String category = catIn.getText();
            String productName = pnameIn.getText();
            int listPrice = Integer.parseInt(lpriceIn.getText());
            int refFeePcntg = Integer.parseInt(refpcntgIn.getText());

            manageOne.add(asin, category, productName, listPrice, refFeePcntg, false);
            error.setVisible(false);
        } catch (NumberFormatException e) {

            error.setVisible(true);
            return;
        }
    }
}
