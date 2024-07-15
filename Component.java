import java.awt.Graphics;

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
