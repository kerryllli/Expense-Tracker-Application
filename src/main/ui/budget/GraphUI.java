package ui.budget;

import java.awt.*;

// Represents a graphical user interface for displaying data in a bar graph format.
public class GraphUI extends Canvas {

    private int y0;
    private int y1;
    private int x0;
    private int x1;
    private int space;
    private int width;

    private int[] data;
    private double factor;
    private String[] label;


    // EFFECTS: Constructs a new GraphUI object with the specified maximum value.
    public GraphUI(int max) {
        this.x0 = 60;
        this.y0 = 60;
        this.x1 = 380;
        this.y1 = 300;
        this.space = 20;
        this.width = 30;

        data = new int[] {0,0,0,0,0,0};
        label = new String[] {"Edu", "Ent", "Food", "Health", "House", "Other"};
        factor = (double) (y1 - y0) / (double) max;
    }

    // EFFECTS: Processes the data values and scales them to fit within the graph.
    public void process(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            data[i] = (int) (arr[i] * factor);
        }
    }


    // EFFECTS: Overrides the paint method to draw the graph
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(x0, y0, x0, y1);
        g.drawLine(x0, y1, x1, y1);
        setBackground(Color.WHITE);
        setForeground(Color.BLUE);
        for (int i = 0; i < data.length; i++) {
            g.fillRect(x0 + ((i + 1) * space) + (i * width), y1 - data[i], width, data[i]);
            g.drawString(label[i], x0 + ((i + 1) * space) + (i * width), y1 + 25);
        }
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.rotate(-Math.PI / 2);
        graphics2D.drawString("Remaining Amount", (-y1 / 2) - 50, x0 - 25);
    }

}
