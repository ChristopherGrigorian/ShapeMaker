import java.awt.*;

public class Arc extends Shape {
    private final int startAngle;
    private final int arcAngle;
    private final boolean flip;

    public Arc(Color color, int x, int y, int w, int h, boolean flip) {
        super(color, x, y, w, h);
        this.flip = flip;
        startAngle = flip ? 180 : 0;
        arcAngle = 180;
    }

    @Override
    public void drawShape(Graphics g) {
        g.setColor(color);
        int adjustedY = flip ? y - h : y;
        g.fillArc(x, adjustedY, w, h * 2, startAngle, arcAngle);
        //System.out.println(x);
        //System.out.println(y);
    }
}
