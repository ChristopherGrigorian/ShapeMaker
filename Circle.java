import java.awt.*;

/**
 * The Circle class represents a drawable circle shape. It extends the Shape class
 * and includes functionality to draw a filled circle.
 */

public class Circle extends Shape {

    public Circle(Color color, int x, int y, int w, int h) {
        super(color, x, y, w, h);
    }

    @Override
    public void drawShape(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, w, h);
    }
}


