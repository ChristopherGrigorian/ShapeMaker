import java.awt.Color;
import java.awt.Graphics;
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

public class Arc extends Shape implements Serializable {
    private int startAngle;
    private boolean flip;

    public Arc(Color color, int x, int y, int w, int h, boolean flip) {
        super(color, x, y, w, h);
        this.flip = flip;
        startAngle = flip ? 180 : 0;

    }

    @Override
    public void drawShape(Graphics g) {
        g.setColor(color);
        int adjustedY = flip ? y - h : y;
        int arcAngle = 180;
        g.fillArc(x, adjustedY, w, h * 2, startAngle, arcAngle);
        if (Overseer.getInstance().getSelectedShape() != null && Overseer.getInstance().getSelectedShape().equals(this)) {
            g.setColor(Color.MAGENTA);
            g.drawArc(x, adjustedY, w, h * 2, startAngle, arcAngle);
        }
    }

    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeInt(color.getRGB());
        out.writeInt(x);
        out.writeInt(y);
        out.writeInt(w);
        out.writeInt(h);
        out.writeInt(startAngle);
        out.writeBoolean(flip);
    }

    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        color = new Color(in.readInt());
        x = in.readInt();
        y = in.readInt();
        w = in.readInt();
        h = in.readInt();
        startAngle = in.readInt();
        flip = in.readBoolean();
    }

    @Override
    public boolean contains(int x, int y) {
        // This covers both states (flip and not flipped)
        return x >= this.x && x <= this.x + w && y >= this.y && y <= this.y + h;
    }

    @Override
    public Component clone() {
        int targetIndex = Overseer.getInstance().getShapeIndex(this);
        Component target = Overseer.getInstance().getStackIndex(targetIndex);
        Arc newArc = new Arc(this.color, this.x, this.y, this.w, this.h, this.flip);
        if (target instanceof EyesD) {
            EyesD newEyes = new EyesD();
            newEyes.setComponent(newArc);
            return newEyes;
        } else {
            HatD newHat = new HatD();
            EyesD newEyes = new EyesD();
            newHat.setComponent(newEyes);
            newEyes.setComponent(newArc);
            return newHat;
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
