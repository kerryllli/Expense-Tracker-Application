package ui.budget;

import ui.DialogUI;
import ui.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a user interface for selecting options related to budget management.
public class BudgetOptionsUI extends DialogUI {

    private JLabel l1;
    private JButton b1;
    private JButton b2;
    private JPanel panel;

    // EFFECTS: Constructs a new BudgetOptionsUI object with the specified JFrame, title, and user.
    public BudgetOptionsUI(JFrame j, String title, User user) {
        super(j, title, user);

        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        l1 = new JLabel("Select one of the following options");
        b1 = new JButton("View");
        b2 = new JButton("Quit");

        panel = new JPanel();
        panel.add(l1);
        panel.add(b1);
        panel.add(b2);

        b1.addActionListener(new ViewAction());
        b2.addActionListener(new QuitAction());
        frame.add(panel);

        super.visible();
    }

    // Represents the action performed when the "View" button is clicked
    private class ViewAction implements ActionListener {

        // EFFECTS: Displays the BudgetViewOptionsUI when the button is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean viewBoolean = b1.isEnabled();
            if (viewBoolean) {
                frame.setVisible(false);
                new BudgetViewOptionsUI(mainFrame, "View Budget", user);
            }
        }
    }

    // Represents the action performed when the "Quit" button is clicked
    private class QuitAction implements ActionListener {

        // EFFECTS: Exits the application when the button is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean quitBoolean = b2.isEnabled();
            if (quitBoolean) {
                System.exit(0);
            }
        }
    }

}
