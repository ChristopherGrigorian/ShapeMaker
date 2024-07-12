import java.awt.*;
/**
 * The HatDecorator class extends the ShapeDecorator class.
 * It adds hat drawing functionality to a Shape object.
 *
 * @author Eric Canihuante (Hat drawing template)
 */
import java.awt.Graphics;

/**
 * The HatDecorator class extends the ShapeDecorator class.
 * It adds hat drawing functionality to a Shape object.
 *
 * @autor Christopher Grigorian
 * @autor Eric Canihuante
 */
public class HatDecorator extends ShapeDecorator {
    public HatDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        addHat(g);
    }

    private void addHat(Graphics g) {
        // Add hat drawing logic here
    }

    @Override
    public void click() {
        super.click();
    }
}
