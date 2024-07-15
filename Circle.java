import java.awt.Color;
import java.awt.Graphics;
import java.io.*;

/**
 * The Circle class represents a drawable circle shape. It extends the Shape class
 * and includes functionality to draw a filled circle.
 * Can be serialized for save and load functionality
 *
 *  @author CharlieRay668 (Charlie Ray) (Wrote Contains methods for all shapes)
 *  @author Christopher Grigorian (Base class and serializing)
 *  @author Eric Canihuante (Clone)
 *
 */

public class Circle extends Shape implements Serializable {

    public Circle(Color color, int x, int y, int w, int h) {
        super(color, x, y, w, h);
    }

    @Override
    public void drawShape(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, w, h);
        if (Overseer.getInstance().getBaseShapeComponent() != null && Overseer.getInstance().getBaseShapeComponent().equals(this)) {
            g.setColor(Color.MAGENTA);
            g.drawOval(x, y, w, h);
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
        int centerX = this.x + this.w / 2;
        int centerY = this.y + this.h / 2;
        int radius = this.w / 2;

        return Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2) <= Math.pow(radius, 2);
    }

    @Override
    public Component clone() {
        int targetIndex = Overseer.getInstance().getShapeIndex(this);
        Component target = Overseer.getInstance().getStackIndex(targetIndex);
        Circle newCirc = new Circle(this.color, this.x, this.y, this.w, this.h);
        if (target instanceof EyesD) {
            EyesD newEyes = new EyesD();
            newEyes.setComponent(newCirc);
            return newEyes;
        } else if (target instanceof MouthD) {
            MouthD newMouth = new MouthD();
            EyesD newEyes = new EyesD();
            newMouth.setComponent(newEyes);
            newEyes.setComponent(newCirc);
            return newMouth;
        } else {
            HatD newHat = new HatD();
            MouthD newMouth = new MouthD();
            EyesD newEyes = new EyesD();
            newHat.setComponent(newMouth);
            newMouth.setComponent(newEyes);
            newEyes.setComponent(newCirc);
            return newHat;
        }
    }

    @Override
    public String toString() {
        return "<Circle\n" +
                "\tcolor=" + color + "\n" +
                "\tx = " + x + "\n" +
                "\ty = " + y + "\n" +
                "\twidth = " + w + "\n" +
                "\theight = " + h + "\n" +
                ">\n";
    }
}


