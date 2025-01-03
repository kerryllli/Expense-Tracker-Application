package ui.budget;

import ui.DialogUI;
import ui.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a user interface for selecting options to view budget-related information.
public class BudgetViewOptionsUI extends DialogUI {

    private JLabel l1;
    private JButton b1;
    private JButton b2;

    // EFFECTS: Constructs a new BudgetViewOptionsUI object with the specified JFrame, title, and user.
    public BudgetViewOptionsUI(JFrame j, String title, User user) {
        super(j, title, user);

        l1 = new JLabel("Click one of the following options");
        b1 = new JButton("View Budget Amount");
        b2 = new JButton("View Remaining Budget");

        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        frame.add(Box.createVerticalGlue());
        frame.add(l1);
        l1.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(Box.createRigidArea(new Dimension(0, 10)));
        frame.add(b1);
        b1.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(Box.createRigidArea(new Dimension(0, 5)));
        frame.add(b2);
        b2.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(Box.createVerticalGlue());
        b1.addActionListener(new AmountAction());
        b2.addActionListener(new RemainingAction());

        super.visible();
    }

    // Represents the action performed when the "View Budget Amount" button is clicked
    private class AmountAction implements ActionListener {

        // EFFECTS: Displays the BudgetAmountViewUI when the button is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean b = b1.isEnabled();
            if (b) {
                frame.setVisible(false);
                new BudgetAmountViewUI(mainFrame, "View Budget Amount", user);
            }
        }
    }

    // Represents the action performed when the "View Remaining Budget" button is clicked
    private class RemainingAction implements ActionListener {

        // EFFECTS: Displays the BudgetRemainingViewUI when the button is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean b = b2.isEnabled();
            if (b) {
                frame.setVisible(false);
                new BudgetRemainingViewUI(mainFrame, "View Remaining Budget", user);
            }
        }
    }
}
