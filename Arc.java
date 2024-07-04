import java.awt.*;

public class Arc extends Shape {
    private final int startAngle;
    private final int arcAngle;

    public Arc(Color color, int x, int y, int w, int h, boolean flip) {
        super(color, x, y, w, h);
        if (flip) {
            this.startAngle = 0;
            this.arcAngle = -180;
        } else {
            this.startAngle = 0;
            this.arcAngle = 180;
        }
    }

    @Override
    public void drawShape(Graphics g) {
        g.setColor(color);
        g.fillArc(x, y, w, h, startAngle, arcAngle);
    }
}
