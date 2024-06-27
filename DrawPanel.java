import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel {
    int a, b, c, d;
    String shape = "Rectangle";
    String color = "Blue";

    public DrawPanel() {
        setBackground(Color.CYAN);
        MouListener ml = new MouListener(this);
        addMouseListener(ml);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        switch (color) {
            case "Blue" -> g.setColor(Color.BLUE);
            case "Red" -> g.setColor(Color.RED);
            case "Green" -> g.setColor(Color.GREEN);
            case "Yellow" -> g.setColor(Color.YELLOW);

        }

        switch (shape) {
            case "Rectangle" -> g.fillRect(a, b, c, d);
            case "Circle" -> g.fillOval(a, b, c, d);
            case "Arc" -> g.fillArc(a, b, c, d, 0, 180);
        }
    }

    public void set(int x, int y, int w, int z) {
        a = x;
        b = y;
        c = w;
        d = z;
    }

    public void setShape(String s) {
        shape = s;
    }

    public void setColor(String s) {
        color = s;
    }
}
