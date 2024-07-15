import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
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

public class Line extends Shape implements Serializable {

    public Line(Color color, int x, int y, int w, int h) {
        super(color, x, y, w, h);
    }

    @Override
    public void drawShape(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(7));
        g2d.setColor(color);
        g2d.drawLine(x, y, w, h);
        if (Overseer.getInstance().getBaseShapeComponent() != null && Overseer.getInstance().getBaseShapeComponent().equals(this)) {
            g2d.setColor(Color.MAGENTA);
            g2d.setStroke(new BasicStroke(1));
            g2d.drawLine(x, y, w, h);
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
    public Shape clone() {
        return new Line(this.color, this.x, this.y, this.w, this.h);
    }

    @Override
    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
        this.w += dx;
        this.h += dy;
    }

    @Override
    public Component nextDecorator() {
        return this;
    }

    @Override
    public String toString() {
        return "<Line\n" +
                "\tcolor=" + color + "\n" +
                "\tx = " + x + "\n" +
                "\ty = " + y + "\n" +
                "\twidth = " + w + "\n" +
                "\theight = " + h + "\n" +
                ">\n";
    }
}
