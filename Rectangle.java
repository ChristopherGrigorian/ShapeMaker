import java.awt.*;

public class Rectangle extends Shape {

    public Rectangle(Color color, int x, int y, int w, int h) {
        super(color, x, y, w, h);
    }

    @Override
    public void drawShape(Graphics g) {
        g.setColor(color);
        if (w < 0) {
            g.fillRect(x + w, y, -w, h);
        }
        if (h < 0) {
            g.fillRect(x, y + h, w, -h);
        }
        if (w < 0 && h < 0) {
            g.fillRect(x + w, y + h, -w, -h);
        }
        g.fillRect(x, y, w, h);
    }
}
