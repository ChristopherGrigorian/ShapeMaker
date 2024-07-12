import java.awt.*;
/**
 * The EyeDecorator class extends the ShapeDecorator class.
 * It adds eye drawing functionality to a Shape object.
 *
 * @author Eric Canihuante (Eye class template)
 */
public class EyeDecorator extends ShapeDecorator {
    public EyeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        addEyes(g);
    }

    private void addEyes(Graphics g) {
        // Add eye drawing logic here
    }

    @Override
    public void click() {
        super.click();
    }
}
