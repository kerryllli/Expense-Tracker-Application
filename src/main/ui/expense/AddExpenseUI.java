package ui.expense;

import model.EventLog;
import model.transcation.Date;
import model.transcation.Expense;
import model.transcation.TransactionType;
import ui.DialogUI;
import ui.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a user interface for adding expenses to the expense list.
public class AddExpenseUI extends DialogUI {

    private JLabel l1;
    private JLabel year;
    private JLabel month;
    private JLabel day;
    private JLabel amount;
    private JLabel category;
    private JTextField yearInput;
    private JLabel yearShowing;
    private JTextField monthInput;
    private JLabel monthShowing;
    private JTextField dayInput;
    private JLabel dayShowing;
    private JTextField amountInput;
    private JLabel amountShowing;
    private JComboBox box;
    private JPanel panel;
    private JButton b1;
    private JButton b2;
    private JPanel textPanel;
    private JTable jt;

    private String givenYear;
    private String givenMonth;
    private String givenDay;
    private String givenAmount;
    private boolean clickOnce;


    // EFFECTS: Constructs a new AddExpenseUI object with the specified JFrame, title, and user.
    public AddExpenseUI() {
        super(j, title, user);

        createLabel();
        createText();
        frameSetting();

        super.visible();
    }

    // EFFECTS: Initiate the JLabel, JButton, JComboBox, JPanel.
    public void createLabel() {
        l1 = new JLabel("You are allowed to add one expense to your expense list");
        year = new JLabel("Year");
        month = new JLabel("Month");
        day = new JLabel("Day");
        amount = new JLabel("Amount");
        category = new JLabel("Category");
        frame.getContentPane().setLayout(new FlowLayout());
        String[] option = { "Education","Entertainment","Food","Healthcare","Housing", "Other"};
        box = new JComboBox(option);
        b1 = new JButton("Save");
        b1.addActionListener(new SaveAction());
        b2 = new JButton("Click to View Expense List");
        b2.addActionListener(new ViewAction());
        clickOnce = true;

        textPanel = new JPanel();
        panel = new JPanel();
        panel.add(box);
    }

    // EFFECTS: Initiate the JTextField and add ActionListener to every input
    public void createText() {
        yearInput = new JTextField();
        yearInput.addActionListener(new YearAction());
        yearShowing = new JLabel();
        monthInput = new JTextField();
        monthInput.addActionListener(new MonthAction());
        monthShowing = new JLabel();
        dayInput = new JTextField();
        dayInput.addActionListener(new DayAction());
        dayShowing = new JLabel();
        amountInput = new JTextField();
        amountInput.addActionListener(new AmountAction());
        amountShowing = new JLabel();
    }

    //EFFECTS: Set the layout of the frame
    public void frameSetting() {
        textPanel.add(l1);
        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().add(textPanel);
        frame.getContentPane().add(panel);

        panel.setLayout(new GridLayout(12, 2));
        panelSetting();
    }

    //EFFECTS: Add the components to the panel
    public void panelSetting() {
        panel.add(year);
        panel.add(yearInput);
        panel.add(new JLabel());
        panel.add(yearShowing);
        panel.add(month);
        panel.add(monthInput);
        panel.add(new JLabel());
        panel.add(monthShowing);
        panel.add(day);
        panel.add(dayInput);
        panel.add(new JLabel());
        panel.add(dayShowing);
        panel.add(amount);
        panel.add(amountInput);
        panel.add(new JLabel());
        panel.add(amountShowing);
        panel.add(category);
        panel.add(box);

        panel.add(b1);
        panel.add(b2);
    }

    // Represents the action performed when entering the year of the expense
    private class YearAction implements ActionListener {

        // MODIFIES: this
        // EFFECTS: Updates the givenYear and sets text for givenYear to display the input year.
        @Override
        public void actionPerformed(ActionEvent e) {
            givenYear = yearInput.getText();
            if (givenYear.matches("[0-9]+")) {
                yearShowing.setText("Year: " + givenYear);
            } else {
                yearShowing.setText("Please enter a valid year");
            }
        }
    }

    // Represents the action performed when entering the month of the expense
    private class MonthAction implements ActionListener {

        // MODIFIES: this
        // EFFECTS: Updates the givenMonth and sets text for givenMonth to display the input month.
        @Override
        public void actionPerformed(ActionEvent e) {
            givenMonth = monthInput.getText();
            if (givenMonth.matches("[1-9]|(1[0-2])")) {
                monthShowing.setText("Month: " + givenMonth);
            } else {
                monthShowing.setText("Please enter a valid month");
            }
        }
    }

    // Represents the action performed when entering the day of the expense
    private class DayAction implements ActionListener {

        // MODIFIES: this
        // EFFECTS: Updates the givenDay and sets text for givenDay to display the input day.
        @Override
        public void actionPerformed(ActionEvent e) {
            givenDay = dayInput.getText();
            if (givenDay.matches("[1-9]|([1-2][0-9])|(3[0-1])")) {
                dayShowing.setText("Day: " + givenDay);
            } else {
                dayShowing.setText("Please enter a valid day");
            }
        }
    }

    // Represents the action performed when entering the amount of the expense
    private class AmountAction implements ActionListener {

        // MODIFIES: this
        // EFFECTS: Updates the givenAmount and sets text for givenAmount to display the input amount.
        @Override
        public void actionPerformed(ActionEvent e) {
            givenAmount = amountInput.getText();
            if (givenAmount.matches("[0-9]+")) {
                amountShowing.setText("Amount: " + givenAmount);
            } else {
                amountShowing.setText("Please enter a valid number");
            }
        }
    }

    // Represents the action performed when the user clicks the save button to add the expense.
    private class SaveAction implements ActionListener {

        // EFFECTS: Adds a new expense based on user input and updates the remaining budget.
        @Override
        public void actionPerformed(ActionEvent e) {

            Expense expense = new Expense(createDateFromInputs(), Integer.valueOf(givenAmount),
                    switchCategory());
            expense.addObserver(EventLog.getInstance());
            user.addExpense(expense);
            updateRemainingBudget(expense);

            frameSetting();
        }

        // EFFECTS: Create a new expense by given year, month, day and amount
        private TransactionType switchCategory() {
            String x = String.valueOf(box.getSelectedItem());
            switch (x) {
                case "Education":
                    return TransactionType.EDUCATION;
                case "Entertainment":
                    return TransactionType.ENTERTAINMENT;
                case "Food":
                    return TransactionType.FOOD;
                case "Healthcare":
                    return TransactionType.HEALTHCARE;
                case "Housing":
                    return TransactionType.HOUSING;
                case "Other":
                    return TransactionType.OTHER;
            }
            return null;
        }

        // MODIFIES: this
        // EFFECTS: Updates the remaining budget based on the expense category.
        private void updateRemainingBudget(Expense e) {
            String x = String.valueOf(box.getSelectedItem());
            switch (x) {
                case "Education":
                    user.getEducation().remainingBudget(e);
                    break;
                case "Entertainment":
                    user.getEntertainment().remainingBudget(e);
                    break;
                case "Food":
                    user.getFood().remainingBudget(e);
                    break;
                case "Healthcare":
                    user.getHealth().remainingBudget(e);
                    break;
                case "Housing":
                    user.getHousing().remainingBudget(e);
                    break;
                case "Other":
                    user.getOther().remainingBudget(e);
                    break;
            }
        }

        // EFFECTS: Returns a new Date object based on user input.
        private Date createDateFromInputs() {
            return (new Date(Integer.valueOf(givenYear), Integer.valueOf(givenMonth), Integer.valueOf(givenDay)));
        }
    }

//    // Represents the action performed when the user clicks the view button to see the expense list.
//    private class ViewAction implements ActionListener {
//
//        // EFFECTS: Initiates viewing the expense list in a JTable.
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            boolean b = b2.isEnabled();
//            if (b && clickOnce) {
//                String [] column = {"Date","Expense Amount"};
//                jt = new JTable(user.printExpenseListTry(), column);
//                JScrollPane sp = new JScrollPane(jt);
//                panel.removeAll();
//                panel.add(sp, BorderLayout.CENTER);
//                frame.revalidate();
//                frame.repaint();
//                clickOnce = false;
//            }
//        }
//    }

    // Represents the action performed when the user clicks the view button to see the expense list.
    private class ViewAction implements ActionListener {

        // EFFECTS: Initiates viewing the expense list in a JTable.
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean b = b2.isEnabled();
            if (b && clickOnce) {
                String [] column = {"Date","Expense Amount"};
                jt = new JTable(user.printExpenseListTry(), column);
                JScrollPane sp = new JScrollPane(jt);
                panel.removeAll();
                panel.add(sp, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                clickOnce = false;
            }
        }
    }
}
