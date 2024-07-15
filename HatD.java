import java.awt.Graphics;
import java.awt.Color;

/**
 * The HatD class is a decorator that adds a hat to a shape.
 * It extends the ShapeDecorator class and includes functionality to draw a hat on a shape.
 *
 * @author Eric Canihuante (Structure, comments, and debugging)
 * @author Harold Ellis (Drawing logic and coding)
 */

public class HatD extends ShapeDecorator {
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
        addHat(g);
    }

    private void addHat(Graphics g) {
        if (component != null) {
            int hatWidth = w;
            int hatHeight = h / 3;
            int hatX = x;
            int hatY = y - hatHeight / 3;

            g.setColor(Color.DARK_GRAY);
            g.fillRect(hatX, hatY, hatWidth, hatHeight);

            int brimHeight = hatHeight / 3;
            int brimY = hatY + hatHeight - brimHeight;
            g.fillRect(hatX - hatWidth / 4, brimY, hatWidth + hatWidth / 2, brimHeight);
        }
    }

    public Component nextDecorator() {
        return Overseer.getInstance().getBaseShapeComponent();
    }

    @Override
    public Component clone() {
        HatD newHat = new HatD();
        newHat.setComponent(this.getComponent().clone());
        return newHat;
    }

}
