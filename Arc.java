import java.awt.*;

public class Arc extends Shape {
    public Arc(Color color, int x, int y, int w, int h) {
        super(color, x, y, w, h);
    }

    @Override
    public void drawShape(Graphics g) {
        g.setColor(color);
        g.fillArc(x, y, w, h, 0, 180);
    }
}
