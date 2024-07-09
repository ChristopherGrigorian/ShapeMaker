import java.awt.*;

/**
 * The Shape class is an abstract base class representing a drawable shape.
 * It includes common properties like color and dimensions, and defines an abstract method
 * for drawing the shape on a Graphics object.
 */

public abstract class Shape {
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public abstract boolean contains(int x, int y);

    public abstract void drawShape(Graphics g);

    public void drawSelectionHighlight(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.CYAN);
        g2d.setStroke(new BasicStroke(3)); // Use a thicker stroke for highlighting
    }

    @Override
    public abstract Shape clone();

    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

}
