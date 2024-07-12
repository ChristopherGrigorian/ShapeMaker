import java.awt.*;
/**
 * The MouthDecorator class extends the ShapeDecorator class.
 * It adds mouth drawing functionality to a Shape object.
 *
 * @author Eric Canihuante (Mouth class template)
 */
public class MouthDecorator extends ShapeDecorator {
    public MouthDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        addMouth(g);
    }

    private void addMouth(Graphics g) {
        // Add mouth drawing logic here
    }

    @Override
    public void click() {
        super.click();
    }
}
