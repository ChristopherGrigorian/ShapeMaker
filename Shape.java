import java.awt.*;
import java.io.Serializable;

/**
 * The Shape interface represents a drawable shape. It includes methods for
 * drawing, clicking, moving, and managing selection.
 *
 * @author Christopher Grigorian (Base interface)
 * @author Eric Canihuante (Selection and movement)
 *
 */

public interface Shape extends Serializable, Cloneable {
    void draw(Graphics g);
    void move(int dx, int dy);
    boolean contains(int x, int y);
    void setSelected(boolean selected);
    boolean getSelected();
    void click();
    int getX();
    int getY();
    int getW();
    int getH();
    Color getColor();
    Shape clone();
}