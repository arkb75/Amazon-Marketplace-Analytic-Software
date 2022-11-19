package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubMenuGUI {

    private JPanel panel;
    private JFrame frame;
    private JButton view;
    private JButton add;
    private JButton remove;
    private JButton back;
    private JLabel heading;

    private String submenu;

    // EFFECTS: Creates JFrame, JPanel, and Elements.
    public SubMenuGUI(String submenu) {

        this.submenu = submenu;

        panel = new JPanel();
        frame = new JFrame();
        panel.setBorder(BorderFactory.createEmptyBorder());
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        view = new JButton("View");
        add = new JButton("Add");
        remove = new JButton("Remove");
        back = new JButton("Back");
        setElements();

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

    // EFFECTS: Sets elements.
    // MODIFIES: this.
    private void setElements() {

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
        heading = new JLabel(submenu);
        heading.setFont(new Font("Calibri", Font.BOLD, 35));
        heading.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        view.addActionListener(chooseAction);
        add.addActionListener(chooseAction);
        remove.addActionListener(chooseAction);
        back.addActionListener(chooseAction);
    }

    // EFFECTS: Handles button input.
    ActionListener chooseAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(view)) {
                view();
                frame.dispose();
            }
            if (e.getSource().equals(add)) {
                add();
                frame.dispose();
            }
            if (e.getSource().equals(remove)) {
                remove();
                frame.dispose();
            }
            if (e.getSource().equals(back)) {
                new MainMenu();
                frame.dispose();
            }
        }
    };

    // EFFECTS: Opens the appropriate view class.
    private void view() {
        if (submenu.equals("Product Details")) {
            new PDView();
        }
        if (submenu.equals("Product Performance")) {
            new PPView();
        }
        if (submenu.equals("Purchase Orders")) {
            new POView();
        }
    }

    // EFFECTS: Opens the appropriate add class.
    private void add() {

        if (submenu.equals("Product Details")) {
            new PDAdd();
        }
        if (submenu.equals("Product Performance")) {
            new PPAdd();
        }
        if (submenu.equals("Purchase Orders")) {
            new POAdd();
        }
    }

    // EFFECTS: Opens the appropriate remove class.
    private void remove() {

        if (submenu.equals("Product Details")) {
            new PDRemove();
        }
        if (submenu.equals("Product Performance")) {
            new PPRemove();
        }
        if (submenu.equals("Purchase Orders")) {
            new PORemove();
        }
    }
}
