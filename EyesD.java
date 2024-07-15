import java.awt.Graphics;
import java.awt.Color;

/**
 * The EyesD class is a decorator that adds eyes to a shape.
 * It extends the ShapeDecorator class and includes functionality to draw eyes on a shape.
 *
 * @author Eric Canihuante (Structure, comments, and debugging)
 * @author Ethan Ellis (Drawing logic and debugging)
 */

public class EyesD extends ShapeDecorator {
    private int x, y, w, h;

    @Override
    public void drawShape(Graphics g) {
        if (component != null) {
            x = component.getX();
            y = component.getY();
            w = component.getW();
            h = component.getH();
        }
        super.drawShape(g);
        addEyes(g);
    }

    private void addEyes(Graphics g) {
        if (component != null) {
            int eyeWidth = w / 4;
            int eyeHeight = h / 4;
            int eyeY = y + h / 3;

            // Left eye
            int leftEyeX = x + w / 5;
            g.setColor(Color.WHITE);
            g.fillOval(leftEyeX, eyeY, eyeWidth, eyeHeight);
            g.setColor(Color.GRAY);
            g.fillOval(leftEyeX + (3 * eyeWidth / 6), eyeY + eyeHeight / 3, eyeWidth / 3, eyeHeight / 3);

            // Right eye
            int rightEyeX = x + (3 * w / 5) - eyeWidth / 2;
            g.setColor(Color.WHITE);
            g.fillOval(rightEyeX, eyeY, eyeWidth, eyeHeight);
            g.setColor(Color.GRAY);
            g.fillOval(rightEyeX + (eyeWidth / 5), eyeY + eyeHeight / 3, eyeWidth / 3, eyeHeight / 3);
        }
    }

    public Component nextDecorator() {
        if (Overseer.getInstance().getBaseShapeComponent() instanceof Arc) {
            HatD hat = new HatD();
            hat.setComponent(this);
            return hat;
        } else {
            MouthD mouth = new MouthD();
            mouth.setComponent(this);
            return mouth;
        }
    }

    @Override
    public Component clone() {
        EyesD newEyes = new EyesD();
        newEyes.setComponent(this.getComponent().clone());
        return newEyes;
    }
}
