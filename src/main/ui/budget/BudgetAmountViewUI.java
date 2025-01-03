package ui.budget;

import ui.DialogUI;
import ui.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


// Represents a user interface for viewing budget amounts for different categories.
public class BudgetAmountViewUI extends DialogUI {

    private JLabel l1;
    private JLabel info;
    private JPopupMenu popupmenu;
    private JMenuItem education;
    private JMenuItem entertainment;
    private JMenuItem food;
    private JMenuItem healthcare;
    private JMenuItem housing;
    private JMenuItem other;


    // EFFECTS: Constructs a new BudgetAmountViewUI object with the specified JFrame, title, and user.
    public BudgetAmountViewUI(JFrame j, String title, User user) {
        super(j, title, user);

        l1 = new JLabel("Click the type of budget for which you want to view");
        popupmenu = new JPopupMenu("Options");

        info = new JLabel();
        info.setHorizontalAlignment(JLabel.CENTER);
        info.setSize(400,100);

        buildMenu();
        frame.add(popupmenu);
        frame.add(info);
        frame.addMouseListener(new MouseAdapter());

        frame.setLayout(new BorderLayout());
        frame.add(l1, BorderLayout.NORTH);
        education.addActionListener(new EduAction());
        entertainment.addActionListener(new EntertainmentAction());
        food.addActionListener(new FoodAction());
        healthcare.addActionListener(new HealthcareAction());
        housing.addActionListener(new HousingAction());
        other.addActionListener(new OtherAction());

        super.visible();
    }

    // EFFECTS: Builds the popup menu with budget categories
    public void buildMenu() {
        education = new JMenuItem("Education");
        entertainment = new JMenuItem("Entertainment");
        food = new JMenuItem("Food");
        healthcare = new JMenuItem("Healthcare");
        housing = new JMenuItem("Housing");
        other = new JMenuItem("Other");

        popupmenu.add(education);
        popupmenu.add(entertainment);
        popupmenu.add(food);
        popupmenu.add(healthcare);
        popupmenu.add(housing);
        popupmenu.add(other);
    }

    // Represents the action performed when the "Education" menu item is selected
    private class EduAction implements ActionListener {

        // EFFECTS: Displays the budget amount for education category
        @Override
        public void actionPerformed(ActionEvent e) {
            info.setText("The Education Budget Amount is " + user.getEducation().getBudget());
        }
    }

    // Represents the action performed when the "Entertainment" menu item is selected
    private class EntertainmentAction implements ActionListener {

        // EFFECTS: Displays the budget amount for entertainment category
        @Override
        public void actionPerformed(ActionEvent e) {
            info.setText("The Entertainment Budget Amount is " + user.getEntertainment().getBudget());
        }
    }

    // Represents the action performed when the "Food" menu item is selected
    private class FoodAction implements ActionListener {

        // EFFECTS: Displays the budget amount for food category
        @Override
        public void actionPerformed(ActionEvent e) {
            info.setText("The Food Budget Amount is " + user.getFood().getBudget());
        }
    }

    // Represents the action performed when the "Healthcare" menu item is selected
    private class HealthcareAction implements ActionListener {

        // EFFECTS: Displays the budget amount for healthcare category
        @Override
        public void actionPerformed(ActionEvent e) {
            info.setText("The Healthcare Budget Amount is " + user.getHealth().getBudget());
        }
    }

    // Represent the action performed when the "Housing" menu item is selected
    private class HousingAction implements ActionListener {

        // EFFECTS: Displays the budget amount for housing category
        @Override
        public void actionPerformed(ActionEvent e) {
            info.setText("The Housing Budget Amount is " + user.getHousing().getBudget());
        }
    }

    // Represent the action performed when the "Other" menu item is selected
    private class OtherAction implements ActionListener {

        // EFFECTS: Displays the budget amount for other category
        @Override
        public void actionPerformed(ActionEvent e) {
            info.setText("The Other Budget Amount is " + user.getOther().getBudget());
        }
    }

    // Represent the action when the mouse is clicked
    private class MouseAdapter implements MouseListener {

        // EFFECTS: Shows the popup menu at the mouse coordinates
        @Override
        public void mouseClicked(MouseEvent e) {
            popupmenu.show(frame, e.getX(), e.getY());
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

}
