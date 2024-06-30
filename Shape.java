import java.awt.*;

public abstract class Shape {
    protected Color color;
    protected int x, y, w, h;

    public Shape(Color color, int x, int y, int w, int h) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public abstract void drawShape(Graphics g);

}
