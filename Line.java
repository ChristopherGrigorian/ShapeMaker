import java.awt.*;
import java.io.*;

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
        if (selected) {
            g2d.setColor(Color.RED);
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
        int slope = (this.h - this.y) / (this.w - this.x);
        int yIntercept = this.y - slope * this.x;
        int yCalc = slope * x + yIntercept;
        return y >= yCalc - 3 && y <= yCalc + 3;
//        return     x >= this.x - 3
//                && x <= this.w + 3
//                && y >= this.y - 3
//                && y <= this.h + 3;
    }

    public void setW(int w) {
        this.w = w;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

}
