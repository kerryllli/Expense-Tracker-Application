package ui;

import javax.swing.*;
import java.awt.*;

// Represents a base class for user interface components in the application.
public abstract class UI {

    protected JFrame frame;

    // EFFECTS: Constructs a new UI object with the specified title.
    protected UI(String title) {
        frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }

    // MODIFIES: this
    // EFFECTS: Makes the UI visible by packing its contents and setting the visibility of the frame to true.
    protected void visible() {
        frame.pack();
        frame.setVisible(true);
    }

}
