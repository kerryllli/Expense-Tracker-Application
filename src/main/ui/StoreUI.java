package ui;

import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

// Represents a user interface for selecting options to store the transaction history
public class StoreUI extends DialogUI {

    private JLabel store;
    private JsonWriter jsonWriter;
    private String destination;
    private JTextField path;
    private JButton save;
    private JLabel l1;
    private JPanel panel;

    // EFFECTS: Constructs a new StoreUI object with the specified JFrame, title, and user.
    public StoreUI(JFrame j, String title, User user) {
        super(j, title, user);
        this.l1 = new JLabel();
        this.store = new JLabel("Store to");
        this.path = new JTextField(20);
        path.addActionListener(new PathAction());
        this.save = new JButton("Save");
        save.addActionListener(new SaveAction());
        this.panel = new JPanel();
        destination = "";

        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.add(store);
        panel.add(path);
        panel.add(save);
        panel.add(l1);

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        super.visible();
    }

    // Represents the action performed when entering the path in the path text field
    private class PathAction implements ActionListener {

        // MODIFIES: this
        // EFFECTS: Sets the destination path when the user enters text into the path text field
        @Override
        public void actionPerformed(ActionEvent e) {
            destination = path.getText();
        }
    }

    // Represents the action performed when entering save
    private class SaveAction implements ActionListener {

        // MODIFIES: this, user
        // EFFECTS: Saves the transaction history to the specified destination when the save button is clicked.
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean b = save.isEnabled();
            if (b) {
                jsonWriter = new JsonWriter("./data/" + destination + ".json");
                try {
                    jsonWriter.open();
                    jsonWriter.write(user);
                    jsonWriter.close();
                    l1.setText("Saved transaction to " + destination);
                } catch (FileNotFoundException ex) {
                    l1.setText("Unable to write to file: " + destination);
                }
            }
        }
    }
}
