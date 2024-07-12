import java.awt.*;
import java.io.*;

/**
 * The Arc class represents a drawable arc shape. It extends the Shape class and includes
 * functionality to draw an arc either normally or flipped upside down.
 * Can be serialized for save and load functionality
 *
 *  @author CharlieRay668 (Charlie Ray) (Wrote Contains methods for all shapes)
 *  @author Christopher Grigorian (Base class and serializing)
 *
 */

public class Arc implements Shape, Serializable {
    private static final long serialVersionUID = 1L;
    private Color color;
    private int x, y, w, h;
    private boolean flip;
    private boolean selected;

    public Arc(Color color, int x, int y, int w, int h, boolean flip) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.flip = flip;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        if (flip) {
            g.fillArc(x, y, w, h, 0, 180);
        } else {
            g.fillArc(x, y, w, h, 180, 180);
        }
        g.setColor(Color.BLACK);
        if (flip) {
            g.drawArc(x, y, w, h, 0, 180); // Draw the border of the arc
        } else {
            g.drawArc(x, y, w, h, 180, 180); // Draw the border of the arc
        }

        if (selected) {
            g.setColor(Color.MAGENTA);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke(new BasicStroke(3)); // Set the stroke for the selection border
            if (flip) {
                g2d.drawArc(x - 1, y - 1, w + 2, h + 2, 0, 180); // Draw selection border
            } else {
                g2d.drawArc(x - 1, y - 1, w + 2, h + 2, 180, 180); // Draw selection border
            }
            if (selected) {
                g2d.setColor(Color.MAGENTA);
                g2d.setStroke(new BasicStroke(1)); // Set the stroke for the selection border
                g2d.drawLine(x, y, x + (w - x) / 2, y + (h - y) / 2); // Draw selection border
            }
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
        return "<Arc\n" +
                "\tcolor=" + color + "\n" +
                "\tx = " + x + "\n" +
                "\ty = " + y + "\n" +
                "\twidth = " + w + "\n" +
                "\theight = " + h + "\n" +
                ">\n";
    }


}