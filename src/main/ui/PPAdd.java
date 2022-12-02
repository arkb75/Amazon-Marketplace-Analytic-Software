package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PPAdd extends MainMenu {

    private JPanel panel;
    private JFrame frame;
    private JLabel heading;
    private JLabel error;
    private JButton submit;
    private JButton save;
    private JButton back;
    JTextField orderIDIn;
    JTextField asinIn;
    JTextField qtysoldIn;
    JTextField nrevIn;
    JTextField cpuIn;

    // EFFECTS: Creates JFrame, JPanel, and Elements.
    // MODIFIES: this.
    public PPAdd() {

        setBase();
        setButtons();

        setAsinIn("ASIN");
        setOrderIDIn("Order ID");
        setQtysoldIn("Quantity Sold");
        setNrevIn("Net Revenue");
        setCpuIn("Cost Per Unit");

        frame.add(panel);
        panel.add(Box.createVerticalGlue());
        panel.add(heading);
        panel.add(orderIDIn);
        panel.add(asinIn);
        panel.add(qtysoldIn);
        panel.add(nrevIn);
        panel.add(cpuIn);
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

    // EFFECTS: Creates QTY Sold textbox.
    // MODIFIES: this.
    private void setQtysoldIn(String name) {

        qtysoldIn = new JTextField(name);
        qtysoldIn.setFont(new Font("Calibri", Font.PLAIN, 20));
        qtysoldIn.setAlignmentX(JTextField.CENTER_ALIGNMENT);
        qtysoldIn.setMinimumSize(new Dimension(200, 25));
        qtysoldIn.setPreferredSize(new Dimension(200, 25));
        qtysoldIn.setMaximumSize(new Dimension(200, 25));
    }

    // EFFECTS: Creates Net Revenue textbox.
    // MODIFIES: this.
    private void setNrevIn(String name) {

        nrevIn = new JTextField(name);
        nrevIn.setFont(new Font("Calibri", Font.PLAIN, 20));
        nrevIn.setAlignmentX(JTextField.CENTER_ALIGNMENT);
        nrevIn.setMinimumSize(new Dimension(200, 25));
        nrevIn.setPreferredSize(new Dimension(200, 25));
        nrevIn.setMaximumSize(new Dimension(200, 25));
    }

    // EFFECTS: Creates Cost Per Unit textbox.
    // MODIFIES: this.
    private void setCpuIn(String name) {

        cpuIn = new JTextField(name);
        cpuIn.setFont(new Font("Calibri", Font.PLAIN, 20));
        cpuIn.setAlignmentX(JTextField.CENTER_ALIGNMENT);
        cpuIn.setMinimumSize(new Dimension(200, 25));
        cpuIn.setPreferredSize(new Dimension(200, 25));
        cpuIn.setMaximumSize(new Dimension(200, 25));
    }

    // EFFECTS: Handles button input.
    ActionListener chooseAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(back)) {
                new SubMenuGUI("Product Performance");
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

            String asinThree = asinIn.getText();
            orderIDTwo = Integer.parseInt(orderIDIn.getText());
            int qtySold = Integer.parseInt(qtysoldIn.getText());
            int netRev = Integer.parseInt(nrevIn.getText());
            int cpu = Integer.parseInt(cpuIn.getText());

            manageThree.add(asinThree, orderIDTwo, qtySold, netRev, cpu, false);
            error.setVisible(false);
        } catch (NumberFormatException e) {

            error.setVisible(true);
            return;
        }
    }
}
