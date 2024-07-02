import java.awt.*;

public class Circle extends Shape {

    public Circle(Color color, int x, int y, int w, int h) {
        super(color, x, y, w, h);
    }

    @Override
    public void drawShape(Graphics g) {
        g.setColor(color);
        if (w < 0) {
            g.fillOval(x + w, y, -w, h);
        }
        if (h < 0) {
            g.fillOval(x, y + h, w, -h);
        }
        if (w < 0 && h < 0) {
            g.fillOval(x + w, y + h, -w, -h);
        }
        g.fillOval(x, y, w, h);
    }
}


