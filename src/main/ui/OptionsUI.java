package ui;

import model.EventLog;
import ui.budget.BudgetOptionsUI;
import ui.expense.AddExpenseUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

// Represents a user interface for displaying different options to the user.
public class OptionsUI extends UI {

    private User user;
    private JLabel l1;
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JButton b5;
    private JButton b6;
    private JPanel labelsPanel;
    private JPanel containerPanel;
    private EventLog eventLog;

    // EFFECTS: Constructs a new OptionsUI object with the specified user.
    public OptionsUI(User user) {
        super("Welcome");

        this.user = user;
        this.eventLog = EventLog.getInstance();
        addObserverForEveryType();
        createButtons();

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 3, 10, 10));
        buttonPanel.add(b1);
        buttonPanel.add(b2);
        buttonPanel.add(b3);
        buttonPanel.add(b4);
        buttonPanel.add(b5);
        buttonPanel.add(b6);

        labelsPanel = new JPanel();
        labelsPanel.add(l1);

        containerPanel = new JPanel();
        containerPanel.setLayout(new BorderLayout());
        containerPanel.add(labelsPanel, BorderLayout.NORTH);
        containerPanel.add(buttonPanel, BorderLayout.CENTER);

        frame.add(containerPanel);
        frame.setLocationRelativeTo(null);
        frame.addWindowListener(new IrregularQuitAction());
        super.visible();
    }

    // EFFECTS: Add the observers for every type of transaction
    public void addObserverForEveryType() {
        user.getEducation().addObserver(eventLog);
        user.getEntertainment().addObserver(eventLog);
        user.getHealth().addObserver(eventLog);
        user.getFood().addObserver(eventLog);
        user.getHousing().addObserver(eventLog);
        user.getOther().addObserver(eventLog);
    }

    // EFFECTS: Creates the buttons for different options and sets up action listeners.
    public void createButtons() {
        l1 = new JLabel("Select one of the following options");
        b1 = new JButton("Expense");
        b2 = new JButton("Income");
        b3 = new JButton("Balance");
        b4 = new JButton("Budget");
        b5 = new JButton("Store");
        b6 = new JButton("Quit");

        b1.addActionListener(new ExpenseAction());
        b4.addActionListener(new BudgetAction());
        b5.addActionListener(new StoreAction());
        b6.addActionListener(new QuitAction());
    }

    // Represents the action performed when the "Expense" button is clicked.
    private class ExpenseAction implements ActionListener {

        //EFFECTS: Create a new BudgetOptionsUI with given frame, title and user
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean b = b1.isEnabled();
            if (b) {
                new AddExpenseUI(frame, "Add Expense", user);
            }
        }
    }

    // Represents the action performed when the "Budget" button is clicked.
    private class BudgetAction implements ActionListener {

        //EFFECTS: Create a new BudgetOptionsUI with given frame, title and user
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean b = b4.isEnabled();
            if (b) {
                new BudgetOptionsUI(frame, "Budget Options", user);
            }
        }
    }

    // Represents the action performed when the "Store" button is clicked.
    private class StoreAction implements ActionListener {

        //EFFECTS: Create a new StoreUI with given frame, title and user
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean storeBoolean = b5.isEnabled();
            if (storeBoolean) {
                new StoreUI(frame, "Store", user);
            }
        }
    }

    // Represents the action performed when the "Quit" button is clicked.
    private class QuitAction implements ActionListener {

        //EFFECTS: Close the optionUI window and quit the application
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean quitBoolean = b6.isEnabled();
            if (quitBoolean) {
                eventLog.printOnConsole();
                System.exit(0);
            }
        }
    }

    // Represents the action performed when the window is closed by clicking "exit"
    private class IrregularQuitAction implements WindowListener {


        @Override
        public void windowOpened(WindowEvent e) {

        }

        //EFFECTS: Print the EventLog on console when the window is closed
        @Override
        public void windowClosing(WindowEvent e) {
            eventLog.printOnConsole();
        }

        @Override
        public void windowClosed(WindowEvent e) {

        }

        @Override
        public void windowIconified(WindowEvent e) {

        }

        @Override
        public void windowDeiconified(WindowEvent e) {

        }

        @Override
        public void windowActivated(WindowEvent e) {

        }

        @Override
        public void windowDeactivated(WindowEvent e) {

        }
    }


}
