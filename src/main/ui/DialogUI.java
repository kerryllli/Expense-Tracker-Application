package ui;

import javax.swing.*;
import java.awt.*;

// Represents an abstract user interface for dialog windows.
public abstract class DialogUI {

    protected JFrame mainFrame;
    protected JDialog frame;
    protected User user;

    // EFFECTS: Constructs a new DialogUI object with the specified JFrame, title, and user.
    public DialogUI(JFrame frame, String title, User user) {
        mainFrame = frame;
        this.user = user;
        this.frame = new JDialog(frame, title,true);
        this.frame.setPreferredSize(new Dimension(500, 500));
        this.frame.setResizable(false);
    }

    // MODIFIES: this
    // EFFECTS: Makes the dialog window visible.
    public void visible() {
        this.frame.pack();
        this.frame.setVisible(true);
    }
}
