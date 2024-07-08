import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * The MouListener class implements mouse listeners to handle mouse events
 * for drawing shapes in the application.
 */

public class MouListener implements MouseListener, MouseMotionListener {
    private int x1;
    private int y1;
    private int xDragStart;
    private int yDragStart;

    @Override
    public void mouseClicked(MouseEvent e) {
        int selectedX = e.getX();
        int selectedY = e.getY();
        for (int i = 0; i < Overseer.getStack().size(); i++) {
            Shape s = Overseer.getStack().get(i);
            if (s.contains(selectedX, selectedY)) {
                s.setSelected(!s.getSelected());
                Overseer.doSomething();
                break;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();
        xDragStart = x1;
        yDragStart = y1;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        shapeCalculation(e, false);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        shapeCalculation(e, true);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    private void shapeCalculation(MouseEvent e, boolean isDragging) {
        int x = Math.min(x1, e.getX());
        int y = Math.min(y1, e.getY());
        int w = Math.abs(e.getX() - x1);
        int h = Math.abs(e.getY() - y1);
        boolean flip = e.getY() > y1; // Determine if arc should flip
        String currentShape = Overseer.getShape();

        if (isDragging) {
            switch (currentShape) {
                case "Rectangle" -> Overseer.setBox(new Rectangle(Overseer.getColor(), x, y, w, h));
                case "Circle" -> Overseer.setBox(new Circle(Overseer.getColor(), x, y, w, h));
                case "Arc" -> Overseer.setBox(new Arc(Overseer.getColor(), x, y, w, h, flip));
                case "Line" -> Overseer.setBox(new Line(Overseer.getColor(), x1, y1, e.getX(), e.getY()));
            }
        } else {
            switch (currentShape) {
                case "Rectangle" -> Overseer.pushToStack(new Rectangle(Overseer.getColor(), x, y, w, h));
                case "Circle" -> Overseer.pushToStack(new Circle(Overseer.getColor(), x, y, w, h));
                case "Arc" -> Overseer.pushToStack(new Arc(Overseer.getColor(), x, y, w, h, flip));
                case "Line" -> Overseer.pushToStack(new Line(Overseer.getColor(), x1, y1, e.getX(), e.getY()));
            }
            Overseer.setBox(null);
        }
        Overseer.doSomething();
    }
}
