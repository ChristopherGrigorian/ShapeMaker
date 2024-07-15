import java.awt.Graphics;
import java.awt.Color;

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
            int eyeWidth = w / 5;
            int eyeHeight = h / 3;
            int eyeY = y + h / 4;

            // Left eye
            int leftEyeX = x + w / 4;
            g.setColor(Color.WHITE);
            g.fillOval(leftEyeX, eyeY, eyeWidth, eyeHeight);
            g.setColor(Color.BLUE);
            g.fillOval(leftEyeX + eyeWidth / 3, eyeY + eyeHeight / 3, eyeWidth / 2, eyeHeight / 2);

            // Right eye
            int rightEyeX = x + (3 * w / 4) - eyeWidth;
            g.setColor(Color.WHITE);
            g.fillOval(rightEyeX, eyeY, eyeWidth, eyeHeight);
            g.setColor(Color.BLUE);
            g.fillOval(rightEyeX + eyeWidth / 3, eyeY + eyeHeight / 3, eyeWidth / 2, eyeHeight / 2);
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
