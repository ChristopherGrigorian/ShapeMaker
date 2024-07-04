import java.awt.*;

/**
 * The Shape class is an abstract base class representing a drawable shape.
 * It includes common properties like color and dimensions, and defines an abstract method
 * for drawing the shape on a Graphics object.
 */

public abstract class Shape {
    protected Color color;
    protected int x, y, w, h;

    public Shape(Color color, int x, int y, int w, int h) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public abstract void drawShape(Graphics g);

}
