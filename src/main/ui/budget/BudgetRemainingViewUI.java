package ui.budget;

import ui.DialogUI;
import ui.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// Represents a user interface for displaying the remaining budget for various categories.
public class BudgetRemainingViewUI extends DialogUI {

    private GraphUI graphUI;
    private JLabel l1;
    private JLabel info;
    private JPopupMenu popupmenu;
    private JMenuItem education;
    private JMenuItem entertainment;
    private JMenuItem food;
    private JMenuItem healthcare;
    private JMenuItem housing;
    private JMenuItem other;
    private JButton viewGraph;

    // EFFECTS: Constructs a new BudgetRemainingViewUI object with the specified JFrame, title, and user.
    public BudgetRemainingViewUI(JFrame j, String title, User user) {
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

        processActionListener();

        viewGraph = new JButton("View Graph");
        viewGraph.addActionListener(new ViewGraphAction());
        viewGraph.setPreferredSize(new Dimension(100, 50));
        frame.add(viewGraph);
        frame.setLayout(new FlowLayout(FlowLayout.LEFT));

        super.visible();
    }

    // EFFECTS: Processes action listeners for every component in the popupmenu
    public void processActionListener() {
        education.addActionListener(new EduAction());
        entertainment.addActionListener(new EntertainmentAction());
        food.addActionListener(new FoodAction());
        healthcare.addActionListener(new HealthcareAction());
        housing.addActionListener(new HousingAction());
        other.addActionListener(new OtherAction());
    }

    // EFFECTS: Builds the popup menu with different budget categories
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

    // Represents the action performed when the "Education" menu item is clicked
    private class EduAction implements ActionListener {

        // EFFECTS: Updates the info label with the remaining budget for education
        @Override
        public void actionPerformed(ActionEvent e) {
            info.setText("The Remaining Budget of Education is: " + user.getEducation().getRemainingBudget());
        }
    }

    // Represents the action performed when the "Entertainment" menu item is clicked
    private class EntertainmentAction implements ActionListener {

        // EFFECTS: Updates the info label with the remaining budget for entertainment
        @Override
        public void actionPerformed(ActionEvent e) {
            info.setText("The Remaining Budget of Entertainment is: " + user.getEntertainment().getRemainingBudget());
        }
    }

    // Represents the action performed when the "Food" menu item is clicked
    private class FoodAction implements ActionListener {

        // EFFECTS: Updates the info label with the remaining budget for food
        @Override
        public void actionPerformed(ActionEvent e) {
            info.setText("The Remaining Budget of Food is: " + user.getFood().getRemainingBudget());
        }
    }

    // Represents the action performed when the "Healthcare" menu item is clicked
    private class HealthcareAction implements ActionListener {

        // EFFECTS: Updates the info label with the remaining budget for healthcare
        @Override
        public void actionPerformed(ActionEvent e) {
            info.setText("The Remaining Budget of Healthcare is: " + user.getHealth().getRemainingBudget());
        }
    }

    // Represents the action performed when the "Housing" menu item is clicked
    private class HousingAction implements ActionListener {

        // EFFECTS: Updates the info label with the remaining budget for housing
        @Override
        public void actionPerformed(ActionEvent e) {
            info.setText("The Remaining Budget of Housing is: " + user.getHousing().getRemainingBudget());
        }
    }

    // Represents the action performed when the "Other" menu item is clicked
    private class OtherAction implements ActionListener {

        // EFFECTS: Updates the info label with the remaining budget for other expenses
        @Override
        public void actionPerformed(ActionEvent e) {
            info.setText("The Remaining Budget of Other is: " + user.getOther().getRemainingBudget());
        }
    }

    // Represents the action performed when the "View Graph" button is clicked
    private class ViewGraphAction implements ActionListener {

        private int[] arr;

        public ViewGraphAction() {
            arr = new int[6];
            arr[0] = (int) user.getEducation().getRemainingBudget();
            arr[1] = (int) user.getEntertainment().getRemainingBudget();
            arr[2] = (int) user.getFood().getRemainingBudget();
            arr[3] = (int) user.getHealth().getRemainingBudget();
            arr[4] = (int) user.getHousing().getRemainingBudget();
            arr[5] = (int) user.getOther().getRemainingBudget();
        }

        // EFFECTS: Create a new graphUI with given array of remaining budget
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean b = viewGraph.isEnabled();
            if (b) {
                new ViewGraphAction();
                graphUI = new GraphUI(getMax());
                graphUI.process(arr);
                graphUI.setPreferredSize(new Dimension(400, 400));
                JPanel graphPanel = new JPanel();
                graphPanel.add(graphUI);

                frame.getContentPane().add(graphPanel, BorderLayout.SOUTH);
                frame.revalidate();
            }

        }

        // EFFECTS: Returns the maximum value from the array
        public int getMax() {
            int max = arr[0]; // Initialize max with the first element of the array
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] > max) {
                    max = arr[i];
                }
            }
            return max;
        }
    }

    // Represents a mouse adapter for displaying the popup menu
    private class MouseAdapter implements MouseListener {

        // EFFECTS: Displays the popup menu when the mouse is clicked
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
