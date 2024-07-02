import java.awt.*;

public class Arc extends Shape {
    public Arc(Color color, int x, int y, int w, int h) {
        super(color, x, y, w, h);
    }

    @Override
    public void drawShape(Graphics g) {
        g.setColor(color);
        if (w < 0) {
            g.fillArc(x + w, y, -w, h, 0, 180);
        }
        if (h < 0) {
            g.fillArc(x, y + h, w, -h, 0, 180);
        }
        if (w < 0 && h < 0) {
            g.fillArc(x + w, y + h, -w, -h, 0, 180);
        }
        g.fillArc(x, y, w, h, 0, 180);
    }
}
