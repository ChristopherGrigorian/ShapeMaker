import java.awt.*;
import java.io.*;

/**
 * The Line class represents a drawable Line shape. It extends the Shape class
 * and includes functionality to draw a Line.
 * Can be serialized for save and load functionality
 *
 * @author CharlieRay668 (Charlie Ray) (Wrote Contains methods for all shapes)
 * @author Christopher Grigorian (Base class and serializing)
 * @author Eric Canihuante (Clone and Move)
 *
 */

public class Line implements Shape, Serializable {
    private static final long serialVersionUID = 1L;
    private Color color;
    private int x, y, w, h;
    private boolean selected;

    public Line(Color color, int x, int y, int w, int h) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(color);
        g2d.drawLine(x, y, w, h);
        if (selected) {
            g2d.setColor(Color.MAGENTA);
            g2d.setStroke(new BasicStroke(1));
            g2d.drawLine(x, y, w, h);
        }
    }

    @Override
    public void click() {
        // Handle click
    }

    @Override
    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
        this.w += dx;
        this.h += dy;
    }

    @Override
    public boolean contains(int x, int y) {
        double distance = ptSegDist(this.x, this.y, this.w, this.h, x, y);
        return distance <= 3;
    }

    private double ptSegDist(int x1, int y1, int x2, int y2, int px, int py) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        double px1 = px - x1;
        double py1 = py - y1;
        double lengthSquared = dx * dx + dy * dy;
        double projection = (px1 * dx + py1 * dy) / lengthSquared;
        if (projection < 0) {
            dx = px1;
            dy = py1;
        } else if (projection > 1) {
            dx = px - x2;
            dy = py - y2;
        } else {
            dx = px1 - projection * dx;
            dy = py1 - projection * dy;
        }
        return Math.sqrt(dx * dx + dy * dy);
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

    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeInt(color.getRGB());
        out.writeInt(x);
        out.writeInt(y);
        out.writeInt(w);
        out.writeInt(h);
    }

    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        color = new Color(in.readInt());
        x = in.readInt();
        y = in.readInt();
        w = in.readInt();
        h = in.readInt();
    }

}