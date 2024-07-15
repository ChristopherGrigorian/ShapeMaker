import java.awt.Graphics;
import java.awt.Color;

/**
 * The MouthD class is a decorator that adds a mouth to a shape.
 * It extends the ShapeDecorator class and includes functionality to draw a mouth on a shape.
 *
 * @author Eric Canihuante (Structure, comments, and debugging)
 * @author Harold Ellis (Drawing logic and coding)
 */


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
            int mouthWidth = w / 3;
            int mouthHeight = h / 4;
            int mouthX = x + (w - mouthWidth) / 2;
            int mouthY = y + (2 * h / 3);

            g.setColor(Color.DARK_GRAY);
            g.fillOval(mouthX, mouthY, mouthWidth, mouthHeight);
        }
    }

    public Component nextDecorator() {
        HatD hat = new HatD();
        hat.setComponent(this);
        return hat;
    }

    @Override
    public Component clone() {
        MouthD newMouth = new MouthD();
        newMouth.setComponent(this.getComponent().clone());
        return newMouth;
    }
}
