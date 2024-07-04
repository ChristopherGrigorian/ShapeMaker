import java.awt.*;

/**
 * The Rectangle class represents a drawable rectangle shape.
 * It extends the Shape class and includes functionality to draw a filled rectangle.
 */

public class Rectangle extends Shape {

    public Rectangle(Color color, int x, int y, int w, int h) {
        super(color, x, y, w, h);
    }

    @Override
    public void drawShape(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, w, h);
    }
}
