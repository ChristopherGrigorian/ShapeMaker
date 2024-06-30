import java.awt.*;

public class Circle extends Shape {

    public Circle(Color color, int x, int y, int w, int h) {
        super(color, x, y, w, h);
    }

    @Override
    public void drawShape(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, w, h);
    }
}


