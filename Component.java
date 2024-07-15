import java.awt.Graphics;

/**
 * The Component interface defines the methods that must be implemented by any drawable shape
 * or shape decorator in the application. This interface ensures that all components can be
 * drawn, cloned, and manipulated consistently.
 *
 * @author Ethan Ellis (Base class with method signatures)
 * @author Christopher Grigorian (Added more method signatures)
 * @author Eric Canihuante (Comments)
 */

public interface Component {
    void drawShape(Graphics g);
    int getX();
    int getY();
    int getW();
    int getH();
    Component nextDecorator();
    Component clone();
    void move(int dx, int dy);
}
