package ui;

import persistence.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// Represents a user interface for loading transaction history.
public class LoadHistoryUI {

    private JFrame frame;
    private JLabel l1;
    private JLabel l2;
    private JLabel l3;
    private JLabel l4;
    private JLabel l5;
    private JButton b1;
    private JButton b2;
    private JPanel textPanel;
    private JPanel buttonPanel;
    private User user;
    private JsonReader jsonReader;
    private static final String STORE = "./data/user.json";

    // EFFECTS: Constructs a new LoadHistoryUI object and initializes the user interface components.
    public LoadHistoryUI() {

        frame = new JFrame("Load History");
        l1 = new JLabel("Welcome! Let's embark on the Billionaire Journey: ");
        l5 = new JLabel("Money Management");
        l1.setBounds(10, 10, 280, 50);
        l2 = new JLabel("Do you want to load your transaction history?");
        l1.setBounds(10, 70, 280, 30);
        b1 = new JButton("Yes");
        b2 = new JButton("No");
        b1.setBounds(50, 110, 95, 20);
        b2.setBounds(50, 140, 95, 20);
        b1.addActionListener(new YesAction());
        b2.addActionListener(new NoAction());

        jsonReader = new JsonReader(STORE);
        user = new User();

        panelSetting();
        frameSetting();
    }

    // EFFECTS: Set the components of two panel
    public void panelSetting() {
        textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(3, 1));
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        textPanel.add(l1);
        textPanel.add(l5);
        textPanel.add(l2);
        buttonPanel.add(b1);
        buttonPanel.add(b2);
    }

    // EFFECTS: Set the frame layout
    public void frameSetting() {
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(textPanel, BorderLayout.CENTER);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        frame.setPreferredSize(new Dimension(350, 250));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    // Represents the action performed when the user chooses to load transaction history.
    private class YesAction implements ActionListener {

        // EFFECTS: Load transaction history and displays the OptionsUI when the yse button is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean yes = b1.isEnabled();
            if (yes) {
                frame.setVisible(false);
                loadFunction();
                new OptionsUI(user);
            }
        }

        //EFFECTS: Load data from the data history
        private void loadFunction() {
            try {
                user = jsonReader.read();
                l3 = new JLabel("Loaded transaction history from " + STORE);
                frame.add(l3);
            } catch (IOException e) {
                l4 = new JLabel("Unable to read the transaction history from: " + STORE);
                frame.add(l4);
            }
        }
    }

    // Represents the action performed when the user chooses not to load transaction history
    private class NoAction implements ActionListener {

        // EFFECTS: Displays the SetBudgetUI when the no button is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean no = b2.isEnabled();
            if (no) {
                frame.setVisible(false);
                new SetBudgetUI(user);
            }
        }
    }

}