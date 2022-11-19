package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PDAdd extends MainMenu {

    private JPanel panel;
    private JFrame frame;
    private JLabel heading;
    private JButton back;
    private JButton submit;
    private JTextField asinIn;
    private JTextField catIn;
    private JTextField pnameIn;
    private JTextField lpriceIn;
    private JTextField refpcntgIn;

    public PDAdd() {

        panel = new JPanel();
        frame = new JFrame();
        panel.setBorder(BorderFactory.createEmptyBorder());
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        back = new JButton("Back");
        submit = new JButton("Submit");
        heading = new JLabel("Add");
        heading.setFont(new Font("Calibri", Font.BOLD, 35));
        heading.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        back.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        back.setMinimumSize(new Dimension(200, 25));
        back.setPreferredSize(new Dimension(200, 25));
        back.setMaximumSize(new Dimension(200, 25));
        back.addActionListener(chooseAction);

        asinIn = new JTextField("ASIN");
        asinIn.setFont(new Font("Calibri", Font.PLAIN, 20));
        asinIn.setAlignmentX(JTextField.CENTER_ALIGNMENT);
        asinIn.setMinimumSize(new Dimension(200, 25));
        asinIn.setPreferredSize(new Dimension(200, 25));
        asinIn.setMaximumSize(new Dimension(200, 25));

        catIn = new JTextField("Category");
        catIn.setFont(new Font("Calibri", Font.PLAIN, 20));
        catIn.setAlignmentX(JTextField.CENTER_ALIGNMENT);
        catIn.setMinimumSize(new Dimension(200, 25));
        catIn.setPreferredSize(new Dimension(200, 25));
        catIn.setMaximumSize(new Dimension(200, 25));

        pnameIn = new JTextField("Product Name");
        pnameIn.setFont(new Font("Calibri", Font.PLAIN, 20));
        pnameIn.setAlignmentX(JTextField.CENTER_ALIGNMENT);
        pnameIn.setMinimumSize(new Dimension(200, 25));
        pnameIn.setPreferredSize(new Dimension(200, 25));
        pnameIn.setMaximumSize(new Dimension(200, 25));

        lpriceIn = new JTextField("List Price");
        lpriceIn.setFont(new Font("Calibri", Font.PLAIN, 20));
        lpriceIn.setAlignmentX(JTextField.CENTER_ALIGNMENT);
        lpriceIn.setMinimumSize(new Dimension(200, 25));
        lpriceIn.setPreferredSize(new Dimension(200, 25));
        lpriceIn.setMaximumSize(new Dimension(200, 25));

        refpcntgIn = new JTextField("Referral Fee %");
        refpcntgIn.setFont(new Font("Calibri", Font.PLAIN, 20));
        refpcntgIn.setAlignmentX(JTextField.CENTER_ALIGNMENT);
        refpcntgIn.setMinimumSize(new Dimension(200, 25));
        refpcntgIn.setPreferredSize(new Dimension(200, 25));
        refpcntgIn.setMaximumSize(new Dimension(200, 25));

        frame.add(panel);
        panel.add(Box.createVerticalGlue());
        panel.add(heading);
        panel.add(asinIn);
        panel.add(catIn);
        panel.add(pnameIn);
        panel.add(lpriceIn);
        panel.add(refpcntgIn);
        panel.add(back);

        panel.add(Box.createVerticalStrut(600));
        frame.setBounds(100,100,700,700);
        frame.setVisible(true);
    }

    ActionListener chooseAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(back)) {
                new ProductDetailsGUI();
                frame.dispose();
            }
            if (e.getSource().equals(submit)) {
                submit();
            }
        }
    };

    private void submit() {
    }
}
