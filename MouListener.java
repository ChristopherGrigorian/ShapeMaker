import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * The MouListener class implements mouse listeners to handle mouse events
 * for drawing shapes in the application.
 * @author CharlieRay668 (Charlie Ray) (Wrote selection, drag, drop, and click functionality)
 * @author christophergrigorian (Christopher Grigorian) (Shape calculation, pushing to stack and box, clean-up)
 */

public class MouListener implements MouseListener, MouseMotionListener {
    private int x1;
    private int y1;
    private int xDragStart = -1;
    private int yDragStart = -1;
    private Shape currentShape;

    @Override
    public void mouseClicked(MouseEvent e) {
        int selectedX = e.getX();
        int selectedY = e.getY();
        Shape s = findShape(selectedX, selectedY);
        if (currentShape != null && currentShape != s) {
            currentShape.setSelected(false);
            Overseer.doSomething();
        }
        currentShape = s;
        if (s != null) {
            s.setSelected(!s.getSelected());
            Overseer.doSomething();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();
        xDragStart = yDragStart = -1;
        Shape s = findShape(x1, y1);
        if (s != null) {
            xDragStart = x1;
            yDragStart = y1;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (xDragStart != -1 && yDragStart != -1) {
            xDragStart = yDragStart = -1;
        } else {
            shapeCalculation(e, false);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (xDragStart != -1 && yDragStart != -1) {
            int dx = e.getX() - xDragStart;
            int dy = e.getY() - yDragStart;

            if (currentShape != null && currentShape.getSelected()) {
                if (currentShape instanceof Line l) {
                    l.setX(l.getX() + dx);
                    l.setY(l.getY() + dy);
                    l.setW(l.getW() + dx);
                    l.setH(l.getH() + dy);
                } else {
                    currentShape.setX(currentShape.getX() + dx);
                    currentShape.setY(currentShape.getY() + dy);
                }
            }

            xDragStart = e.getX();
            yDragStart = e.getY();
            Overseer.doSomething();
        } else {
            shapeCalculation(e, true);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    private Shape findShape(int x, int y) {
        for (int i = Overseer.getStack().size() - 1; i >= 0; i--) {
            Shape s = Overseer.getStack().get(i);
            if (s.contains(x, y)) {
                return s;
            }
        }
        return null;
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
