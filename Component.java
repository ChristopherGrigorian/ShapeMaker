import java.awt.Graphics;

public interface Component {
    void drawShape(Graphics g);
    int getX();
    int getY();
    int getW();
    int getH();
    Component nextDecorator();
}
