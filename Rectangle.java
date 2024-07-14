import java.awt.Color;
import java.awt.Graphics;
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

public class Rectangle extends Shape implements Serializable {

    public Rectangle(Color color, int x, int y, int w, int h) {
        super(color, x, y, w, h);
    }

    @Override
    public void drawShape(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, w, h);
        if (Overseer.getInstance().getSelectedShape() != null && Overseer.getInstance().getSelectedShape().equals(this)) {
            g.setColor(Color.MAGENTA);
            g.drawRect(x, y, w, h);
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
    public Shape clone() {
        return new Rectangle(this.color, this.x, this.y, this.w, this.h);
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
