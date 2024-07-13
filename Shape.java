import java.awt.Color;
import java.awt.Graphics;

/**
 * The Shape class is an abstract base class representing a drawable shape.
 * It includes common properties like color and dimensions, and defines an abstract method
 * for drawing the shape on a Graphics object.
 * All children can be serialized for save and load functionality
 *
 * @author Christopher Grigorian (Base class and serializing)
 * @author Eric Canihuante (Clone and Move)
 *
 */

public abstract class Shape implements Component {
    protected Color color;
    protected int x, y, w, h;

    protected boolean selected = false;

    // This default constructor is necessary
    // DO NOT DELETE D:<
    public Shape() {

    }

    public Shape(Color color, int x, int y, int w, int h) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean getSelected() {
        return selected;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public abstract boolean contains(int x, int y);

    public abstract void drawShape(Graphics g);

    @Override
    public abstract Shape clone();

    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }
}
