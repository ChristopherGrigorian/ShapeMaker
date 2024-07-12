import java.awt.*;

/**
 * The ShapeDecorator class is an abstract class implementing the Shape interface.
 * It allows for extending the functionality of a Shape object dynamically.
 *
 * @autor Christopher Grigorian
 * @autor Eric Canihuante
 */
public abstract class ShapeDecorator implements Shape {
    protected Shape decoratedShape;

    public ShapeDecorator(Shape decoratedShape) {
        this.decoratedShape = decoratedShape;
    }

    @Override
    public void draw(Graphics g) {
        decoratedShape.draw(g);
    }

    @Override
    public void move(int dx, int dy) {
        decoratedShape.move(dx, dy);
    }

    @Override
    public boolean contains(int x, int y) {
        return decoratedShape.contains(x, y);
    }

    @Override
    public void setSelected(boolean selected) {
        decoratedShape.setSelected(selected);
    }

    @Override
    public boolean getSelected() {
        return decoratedShape.getSelected();
    }

    @Override
    public void click() {
        decoratedShape.click();
    }

    @Override
    public int getX() {
        return decoratedShape.getX();
    }

    @Override
    public int getY() {
        return decoratedShape.getY();
    }

    @Override
    public int getW() {
        return decoratedShape.getW();
    }

    @Override
    public int getH() {
        return decoratedShape.getH();
    }

    @Override
    public Color getColor() {
        return decoratedShape.getColor();
    }

    @Override
    public Shape clone() {
        return decoratedShape.clone();
    }
}