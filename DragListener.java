import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class DragListener implements MouseMotionListener {

    @Override
    public void mouseDragged(MouseEvent e) {
        if (Overseer.getCurrShape() != null) {
            Overseer.getCurrShape().setW(e.getX() - Overseer.getCurrShape().getX());
            Overseer.getCurrShape().setH(e.getY() - Overseer.getCurrShape().getY());
        }
        Overseer.doSomething();
    }

    @Override
    public void mouseMoved(MouseEvent e) {}
}
