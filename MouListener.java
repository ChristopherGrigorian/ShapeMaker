import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouListener implements MouseListener, MouseMotionListener {
    private int x1;
    private int y1;

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int x2 = e.getX();
        int y2 = e.getY();

        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);
        int w = Math.abs(x2 - x1);
        int h = Math.abs(y2 - y1);

        boolean flip = y2 > y1; // Determine if arc should flip

        if (Overseer.getShape().equals("Rectangle")) {
            Overseer.pushToStack(new Rectangle(Overseer.getColor(), x, y, w, h));
        } else if (Overseer.getShape().equals("Circle")) {
            Overseer.pushToStack(new Circle(Overseer.getColor(), x, y, w, h));
        } else {
            Overseer.pushToStack(new Arc(Overseer.getColor(), x, y, w, h, flip));
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
        int dx = Math.min(x1, e.getX());
        int dy = Math.min(y1, e.getY());
        int dw = Math.abs(e.getX() - x1);
        int dh = Math.abs(e.getY() - y1);

        boolean flip = e.getY() > y1; // Determine if arc should flip

        switch (Overseer.getShape()) {
            case "Rectangle" -> Overseer.setBox(new Rectangle(Overseer.getColor(), dx, dy, dw, dh));
            case "Circle" -> Overseer.setBox(new Circle(Overseer.getColor(), dx, dy, dw, dh));
            case "Arc" -> Overseer.setBox(new Arc(Overseer.getColor(), dx, dy, dw, dh, flip));
        }
        Overseer.doSomething();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
