import java.awt.Graphics;
import java.io.Serializable;

/**
 * The ShapeDecorator class is an abstract class that implements the Component interface
 * and provides a base for creating decorators that add functionality to Shape objects.
 * This class handles the common functionality for all shape decorators.
 *
 * @author Ethan Ellis (Structure and coding)
 * @author Christopher Grigorian (Base class and serializing)
 * @author Eric Canihuante (Comments and debugging)
 */


public abstract class ShapeDecorator implements Component, Serializable {
    protected Component component;

    public abstract Component clone();

    public void setComponent(Component component) {
        this.component = component;
    }

    public Component getComponent() {
        return component;
    }

    @Override
    public void drawShape(Graphics g) {
        if (component != null) {
            component.drawShape(g);
        }
    }

    @Override
    public int getX() {
        return component.getX();
    }

    @Override
    public int getY() {
        return component.getY();
    }

    @Override
    public int getW() {
        return component.getW();
    }

    @Override
    public int getH() {
        return component.getH();
    }

    @Override
    public void move(int dx, int dy) {
        if (component != null) {
            component.move(dx, dy);
        }
    }

}
