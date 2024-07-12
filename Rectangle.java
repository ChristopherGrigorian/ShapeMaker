import java.awt.*;
import java.io.*;

/**
 * The Rectangle class represents a drawable rectangle shape.
 * It extends the Shape class and includes functionality to draw a filled rectangle.
 * Can be serialized for save and load functionality
 *
 * @author CharlieRay668 (Charlie Ray) (Wrote Contains methods for all shapes)
 * @author Christopher Grigorian (Base class and serializing)
 * @author Eric Canihuante (Clone)
 *
 */

import java.awt.*;
import java.io.Serializable;

/**
 * The Rectangle class represents a rectangle shape.
 * It includes properties for color, position, and dimensions.
 * It implements the Shape interface to ensure it can be drawn and moved.
 *
 * @author Christopher Grigorian
 */
public class Rectangle implements Shape, Serializable {
    private static final long serialVersionUID = 1L;
    private Color color;
    private int x, y, w, h;
    private boolean selected;

    public Rectangle(Color color, int x, int y, int w, int h) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, w, h);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, w, h); // Draw the border of the rectangle

        if (selected) {
            g.setColor(Color.MAGENTA);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke(new BasicStroke(2)); // Set the stroke for the selection border
            g2d.drawRect(x - 1, y - 1, w + 2, h + 2); // Draw selection border
        }
    }

    @Override
    public void click() {
        // Handle click
    }

    @Override
    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    @Override
    public boolean contains(int x, int y) {
        return (x >= this.x && x <= this.x + w) && (y >= this.y && y <= this.y + h);
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public boolean getSelected() {
        return selected;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getW() {
        return w;
    }

    @Override
    public int getH() {
        return h;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public Shape clone() {
        try {
            return (Shape) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // Can't happen
        }
    }

    @Override
    public String toString() {
        return "<Rectangle\n" +
                "\tcolor=" + color + "\n" +
                "\tx = " + x + "\n" +
                "\ty = " + y + "\n" +
                "\twidth = " + w + "\n" +
                "\theight = " + h + "\n" +
                ">\n";
    }
}
