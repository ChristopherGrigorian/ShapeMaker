import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouListener implements MouseListener {
    int x1, x2, y1, y2;

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
       // System.out.println("Mou Pressed");
        x1 = e.getX();
        y1 = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       // System.out.println("Mou Released");
        x2 = e.getX();
        y2 = e.getY();
        if (Overseer.getShape().equals("Rectangle")) {
            Overseer.pushToStack(new Rectangle(Overseer.getColor(), x1, y1, x2-x1, y2-y1));
        } else if (Overseer.getShape().equals("Circle")) {
            Overseer.pushToStack(new Circle(Overseer.getColor(), x1, y1, x2-x1, y2-y1));
        } else {
            Overseer.pushToStack(new Arc(Overseer.getColor(), x1, y1, x2-x1, y2-y1));
        }
        Overseer.doSomething();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
