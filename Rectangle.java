import java.awt.*;

public class Rectangle extends Shape {

    public Rectangle(Color color, int x, int y, int w, int h) {
        super(color, x, y, w, h);
    }

    @Override
    public void drawShape(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, w, h);
    }
}
