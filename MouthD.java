import java.awt.Graphics;
import java.awt.Color;

public class MouthD extends ShapeDecorator {
    private int x, y, w, h;

    @Override
    public void drawShape(Graphics g) {
        if (super.getComponent() != null) {
            x = component.getX();
            y = component.getY();
            w = component.getW();
            h = component.getH();
        }
        super.drawShape(g);
        addMouth(g);
    }

    private void addMouth(Graphics g) {
        if (component != null) {
            int mouthWidth = w / 2;
            int mouthHeight = h / 5;
            int mouthX = x + w / 4;
            int mouthY = y + (3 * h / 4);

            g.setColor(Color.RED);
            g.fillArc(mouthX, mouthY, mouthWidth, mouthHeight, 0, -180);
        }
    }

    public Component nextDecorator() {
        HatD hat = new HatD();
        hat.setComponent(this);
        return hat;
    }
}
