import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouListener implements MouseListener, MouseMotionListener {
    int x1, x2, y1, y2, x, y, w, h;

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

        x = Math.min(x1, x2);
        y = Math.min(y1, y2);
        w = Math.abs(x2 - x1);
        h = Math.abs(y2 - y1);

        if (Overseer.getShape().equals("Rectangle")) {
            Overseer.pushToStack(new Rectangle(Overseer.getColor(), x, y, w, h));
        } else if (Overseer.getShape().equals("Circle")) {
            Overseer.pushToStack(new Circle(Overseer.getColor(), x, y, w, h));
        } else {
            Overseer.pushToStack(new Arc(Overseer.getColor(), x, y, w, h));
        }

        Overseer.setBox(null);
        Overseer.doSomething();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //System.out.println("hello");
        int dx = Math.min(x1, e.getX());
        int dy = Math.min(y1, e.getY());
        int dw = Math.abs(e.getX() - x1);
        int dh = Math.abs(e.getY() - y1);

        switch (Overseer.getShape()) {
            case "Rectangle" -> Overseer.setBox(new Rectangle(Overseer.getColor(), dx, dy, dw, dh));
            case "Circle" -> Overseer.setBox(new Circle(Overseer.getColor(), dx, dy, dw, dh));
            case "Arc" -> Overseer.setBox(new Arc(Overseer.getColor(), dx, dy, dw, dh));
        }
        Overseer.doSomething();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
