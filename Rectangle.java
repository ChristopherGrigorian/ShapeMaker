import java.awt.*;
import java.io.*;

/**
 * The Rectangle class represents a drawable rectangle shape.
 * It extends the Shape class and includes functionality to draw a filled rectangle.
 * Can be serialized for save and load functionality
 *
 * @author CharlieRay668 (Charlie Ray) (Wrote Contains methods for all shapes)
 * @author Christopher Grigorian (Base class and serializing)
 * @author Eric Canihuante (toString fixes)
 *
 */

public class Rectangle extends Shape implements Serializable {

    public Rectangle(Color color, int x, int y, int w, int h) {
        super(color, x, y, w, h);
    }

    @Override
    public void drawShape(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(color);
        g.fillRect(x, y, w, h);
        if (Overseer.getInstance().getBaseShapeComponent() != null && Overseer.getInstance().getBaseShapeComponent().equals(this)) {
            g2d.setColor(Color.MAGENTA);
            g2d.setStroke(new BasicStroke(1));
            g2d.drawRect(x, y, w, h);
        }
    }

    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException, ClassNotFoundException {
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
        return x >= this.x && x <= this.x + w && y >= this.y && y <= this.y + h;
    }

    @Override
    public Component clone() {
        return new Rectangle(this.color, this.x, this.y, this.w, this.h);
    }

    @Override
    public String toString() {
        return String.format("Shape: Rectangle\nColor: R = %d | G = %d | B = %d\nPosition: X = %d | Y = %d\nSize: Width " +
                "= %d | " + "Height = %d\n\n", color.getRed(), color.getGreen(), color.getBlue(), x, y, w, h);
    }
}
