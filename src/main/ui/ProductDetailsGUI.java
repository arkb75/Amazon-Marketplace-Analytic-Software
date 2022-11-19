package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductDetailsGUI {

    private JPanel panel;
    private JFrame frame;
    private JButton view;
    private JButton add;
    private JButton remove;
    private JButton back;
    private JLabel heading;

    public ProductDetailsGUI() {

        panel = new JPanel();
        frame = new JFrame();
        panel.setBorder(BorderFactory.createEmptyBorder());
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        view = new JButton("View");
        add = new JButton("Add");
        remove = new JButton("Remove");
        back = new JButton("Back");
        view.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        view.setMinimumSize(new Dimension(200, 25));
        view.setPreferredSize(new Dimension(200, 25));
        view.setMaximumSize(new Dimension(200, 25));
        add.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        add.setMinimumSize(new Dimension(200, 25));
        add.setPreferredSize(new Dimension(200, 25));
        add.setMaximumSize(new Dimension(200, 25));
        remove.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        remove.setMinimumSize(new Dimension(200, 25));
        remove.setPreferredSize(new Dimension(200, 25));
        remove.setMaximumSize(new Dimension(200, 25));
        back.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        back.setMinimumSize(new Dimension(200, 25));
        back.setPreferredSize(new Dimension(200, 25));
        back.setMaximumSize(new Dimension(200, 25));
        heading = new JLabel("Product Details");
        heading.setFont(new Font("Calibri", Font.BOLD, 35));
        heading.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        view.addActionListener(chooseAction);
        add.addActionListener(chooseAction);
        back.addActionListener(chooseAction);

        frame.add(panel);
        panel.add(Box.createVerticalGlue());
        panel.add(heading);
        panel.add(view);
        panel.add(add);
        panel.add(remove);
        panel.add(back);
        panel.add(Box.createVerticalStrut(600));
        frame.setBounds(100,100,700,700);
        frame.setVisible(true);
    }

    ActionListener chooseAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(view)) {
                new PDView();
                frame.dispose();
            }
            if (e.getSource().equals(add)) {
                new PDAdd();
                frame.dispose();
            }
            if (e.getSource().equals(back)) {
                new MainMenu();
                frame.dispose();
            }
        }
    };
}
