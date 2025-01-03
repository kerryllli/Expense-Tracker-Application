package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a user interface for setting budget amounts and percentages for various categories.
public class SetBudgetUI extends UI {

    private User user;
    private JPanel panel;
    private JLabel l0;
    private JLabel s0;
    private JLabel l1;
    private JLabel s1;
    private JLabel l2;
    private JLabel s2;
    private JLabel l3;
    private JLabel s3;
    private JLabel l4;
    private JLabel s4;
    private JLabel l5;
    private JLabel s5;
    private JLabel l6;
    private JLabel s6;
    private JLabel l7;
    private JLabel s7;
    private JLabel l8;
    private JLabel s8;
    private JLabel l9;
    private JLabel s9;
    private JLabel l10;
    private JLabel s10;
    private JLabel l11;
    private JLabel s11;
    private JTextField t0;
    private JTextField t1;
    private JTextField t2;
    private JTextField t3;
    private JTextField t4;
    private JTextField t5;
    private JTextField t6;
    private JTextField t7;
    private JTextField t8;
    private JTextField t9;
    private JTextField t10;
    private JTextField t11;
    private JButton save;

    // EFFECTS: Constructs a new SetBudgetUI object with the specified user.
    public SetBudgetUI(User user) {
        super("Set Budget");
        this.user = user;
        instantiateShowing();
        amountSetting();
        percentageSetting();

        panel = new JPanel();

        save = new JButton("Save");
        panel = new JPanel(new GridLayout(0, 2, 5, 5));
        layoutSettingHelper1();
        layoutSettingHelper2();
        layoutSettingHelper3();

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        super.visible();

        save.addActionListener(new SaveAction());
    }


    // EFFECTS: Initializes labels for displaying input values.
    public void instantiateShowing() {
        s0 = new JLabel();
        s1 = new JLabel();
        s2 = new JLabel();
        s3 = new JLabel();
        s4 = new JLabel();
        s5 = new JLabel();
        s6 = new JLabel();
        s7 = new JLabel();
        s8 = new JLabel();
        s9 = new JLabel();
        s10 = new JLabel();
        s11 = new JLabel();
    }


    // EFFECTS: Helper method to add components related to budget amounts to the panel.
    public void layoutSettingHelper1() {
        panel.add(l0);
        panel.add(t0);
        panel.add(new JLabel());
        panel.add(s0);
        panel.add(l1);
        panel.add(t1);
        panel.add(new JLabel());
        panel.add(s1);
        panel.add(l2);
        panel.add(t2);
        panel.add(new JLabel());
        panel.add(s2);
        panel.add(l3);
        panel.add(t3);
        panel.add(new JLabel());
        panel.add(s3);
        panel.add(l4);
        panel.add(t4);
        panel.add(new JLabel());
        panel.add(s4);
    }

    // EFFECTS: Helper method to add components related to budget amounts to the panel.
    public void layoutSettingHelper2() {
        panel.add(l5);
        panel.add(t5);
        panel.add(new JLabel());
        panel.add(s5);
        panel.add(l6);
        panel.add(t6);
        panel.add(new JLabel());
        panel.add(s6);
        panel.add(l7);
        panel.add(t7);
        panel.add(new JLabel());
        panel.add(s7);
        panel.add(l8);
        panel.add(t8);
        panel.add(new JLabel());
        panel.add(s8);
        panel.add(l9);
        panel.add(t9);
        panel.add(new JLabel());
        panel.add(s9);
    }

    // EFFECTS: Helper method to add components related to budget amounts to the panel.
    public void layoutSettingHelper3() {
        panel.add(l10);
        panel.add(t10);
        panel.add(new JLabel());
        panel.add(s10);
        panel.add(l11);
        panel.add(t11);
        panel.add(new JLabel());
        panel.add(s11);
        panel.add(save);
    }


    // EFFECTS: Sets up the layout and components for input of budget amounts.
    public void amountSetting() {
        l0 = new JLabel("Education Budget Amount");
        t0 = new JTextField(10);
        t0.addActionListener(new EducationAmount());
        l1 = new JLabel("Entertainment Budget Amount: ");
        t1 = new JTextField(10);
        t1.addActionListener(new EntertainmentAmount());
        l2 = new JLabel("Food Budget Amount: ");
        t2 = new JTextField(10);
        t2.addActionListener(new FoodAmount());
        l3 = new JLabel("Healthcare Budget Amount: ");
        t3 = new JTextField(10);
        t3.addActionListener(new HealthcareAmount());
        l4 = new JLabel("Housing Budget Amount: ");
        t4 = new JTextField(10);
        t4.addActionListener(new HousingAmount());
        l5 = new JLabel("Other Budget Amount: ");
        t5 = new JTextField(10);
        t5.addActionListener(new OtherAmount());

        textFieldSizeSetting();
    }

    // EFFECTS: Sets the size of the text fields
    public void textFieldSizeSetting() {

        t1.setPreferredSize(new Dimension(t1.getPreferredSize().width, 20));
        t2.setPreferredSize(new Dimension(t2.getPreferredSize().width, 20));
        t3.setPreferredSize(new Dimension(t3.getPreferredSize().width, 20));
        t4.setPreferredSize(new Dimension(t4.getPreferredSize().width, 20));
        t5.setPreferredSize(new Dimension(t5.getPreferredSize().width, 20));
    }

    // EFFECTS: Sets up the layout and components for input of budget percentages.
    public void percentageSetting() {
        l6 = new JLabel("Education Budget Percentage: ");
        t6 = new JTextField(10);
        t6.addActionListener(new EducationPercentage());
        l7 = new JLabel("Entertainment Budget Percentage: ");
        t7 = new JTextField(10);
        t7.addActionListener(new EntertainmentPercentage());
        l8 = new JLabel("Food Budget Percentage: ");
        t8 = new JTextField(10);
        t8.addActionListener(new FoodPercentage());
        l9 = new JLabel("Healthcare Budget Percentage: ");
        t9 = new JTextField(10);
        t9.addActionListener(new HealthcarePercentage());
        l10 = new JLabel("Housing Budget Percentage: ");
        t10 = new JTextField(10);
        t10.addActionListener(new HousingPercentage());
        l11 = new JLabel("Other Budget Percentage: ");
        t11 = new JTextField(10);
        t11.addActionListener(new OtherPercentage());
    }


    // Represents the action listeners for setting education budget amounts
    private class EducationAmount implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (t0.getText().matches("[0-9]+")) {
                user.getEducation().setBudget(Integer.valueOf(t0.getText()));
                s0.setText("Input: " + t0.getText());
                user.getEducation().setRemainingBudget(Integer.valueOf(t0.getText()));
            } else {
                s0.setText("Please enter a valid number");
            }

        }
    }

    // Represents the action listeners for setting entertainment budget amounts
    private class EntertainmentAmount implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (t1.getText().matches("[0-9]+")) {
                user.getEntertainment().setBudget(Integer.valueOf(t1.getText()));
                s1.setText("Input: " + t1.getText());
                user.getEntertainment().setRemainingBudget(Integer.valueOf(t1.getText()));
            } else {
                s1.setText("Please enter a valid number");
            }
        }
    }

    // Represents the action listeners for setting food budget amounts
    private class FoodAmount implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (t2.getText().matches("[0-9]+")) {
                user.getFood().setBudget(Integer.valueOf(t2.getText()));
                s2.setText("Input: " + t2.getText());
                user.getFood().setRemainingBudget(Integer.valueOf(t2.getText()));
            } else {
                s2.setText("Please enter a valid number");
            }

        }
    }

    // Represents the action listeners for setting healthcare budget amounts
    private class HealthcareAmount implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (t3.getText().matches("[0-9]+")) {
                user.getHealth().setBudget(Integer.valueOf(t3.getText()));
                s3.setText("Input: " + t3.getText());
                user.getHealth().setRemainingBudget(Integer.valueOf(t3.getText()));
            } else {
                s3.setText("Please enter a valid number");
            }
        }
    }

    // Represents the action listeners for setting housing budget amounts
    private class HousingAmount implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (t4.getText().matches("[0-9]+")) {
                user.getHousing().setBudget(Integer.valueOf(t4.getText()));
                s4.setText("Input: " + t4.getText());
                user.getHousing().setRemainingBudget(Integer.valueOf(t4.getText()));
            } else {
                s4.setText("Please enter a valid number");
            }
        }
    }

    // Represents the action listeners for setting other budget amounts
    private class OtherAmount implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (t5.getText().matches("[0-9]+")) {
                user.getOther().setBudget(Integer.valueOf(t5.getText()));
                s5.setText("Input: " + t5.getText());
                user.getOther().setRemainingBudget(Integer.valueOf(t5.getText()));
            } else {
                s5.setText("Please enter a valid number");
            }
        }
    }

    // Represents the action listeners for setting education budget percentage
    private class EducationPercentage implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (t6.getText().matches("[0-9]|([1-9][0-9])")) {
                user.getEducation().setPercentage(Integer.valueOf(t6.getText()));
                s6.setText("Input: " + t6.getText());
            } else {
                s6.setText("Please enter a valid percentage");
            }
        }
    }

    // Represents the action listeners for setting entertainment budget percentage
    private class EntertainmentPercentage implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (t7.getText().matches("[0-9]|([1-9][0-9])")) {
                user.getEntertainment().setPercentage(Integer.valueOf(t7.getText()));
                s7.setText("Input: " + t7.getText());
            } else {
                s7.setText("Please enter a valid percentage");
            }
        }
    }

    // Represents the action listeners for setting food budget percentage
    private class FoodPercentage implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (t8.getText().matches("[0-9]|([1-9][0-9])")) {
                user.getFood().setPercentage(Integer.valueOf(t8.getText()));
                s8.setText("Input: " + t8.getText());
            } else {
                s8.setText("Please enter a valid percentage");
            }

        }
    }

    // Represents the action listeners for setting healthcare budget percentage
    private class HealthcarePercentage implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (t9.getText().matches("[0-9]|([1-9][0-9])")) {
                user.getHealth().setPercentage(Integer.valueOf(t9.getText()));
                s9.setText("Input: " + t9.getText());
            } else {
                s9.setText("Please enter a valid percentage");
            }
        }
    }

    // Represents the action listeners for setting housing budget percentage
    private class HousingPercentage implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (t10.getText().matches("[0-9]|([1-9][0-9])")) {
                user.getHousing().setPercentage(Integer.valueOf(t10.getText()));
                s10.setText("Input: " + t10.getText());
            } else {
                s10.setText("Please enter a valid percentage");
            }
        }
    }

    // Represents the action listeners for setting other budget percentage
    private class OtherPercentage implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (t11.getText().matches("[0-9]|([1-9][0-9])")) {
                user.getOther().setPercentage(Integer.valueOf(t11.getText()));
                s11.setText("Input: " + t11.getText());
            } else {
                s11.setText("Please enter a valid percentage");
            }
        }
    }


    // Represents the action listeners for clicking the save button
    private class SaveAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            boolean n = save.isEnabled();
            if (n) {
                frame.setVisible(false);
                OptionsUI optionsUI = new OptionsUI(user);
                optionsUI.visible();
            }
        }
    }
}
