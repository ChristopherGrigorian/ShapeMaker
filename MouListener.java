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
    private int xDragStart = -1;
    private int yDragStart = -1;
    private Shape draggingShape = null;

    @Override
    public void mouseClicked(MouseEvent e) {
        int selectedX = e.getX();
        int selectedY = e.getY();
        Shape selectedShape = null;

        for (Shape s : Overseer.getStack()) {
            if (s.contains(selectedX, selectedY)) {
                selectedShape = s;
                break;
            }
        }

        Overseer.setSelectedShape(selectedShape);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();
        boolean found = false;
        for (int i = Overseer.getStack().size() - 1; i >= 0; i--) {
            Shape s = Overseer.getStack().get(i);
            if (s.contains(x1, y1)) {
                xDragStart = x1;
                yDragStart = y1;
                draggingShape = s;
                Overseer.setSelectedShape(s);
                found = true;
                break;
            }
        }
        if (!found) {
            xDragStart = yDragStart = -1;
            draggingShape = null;
            Overseer.setSelectedShape(null);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (xDragStart != -1 && yDragStart != -1) {
            xDragStart = yDragStart = -1;
        } else if (e.getX() == x1 && e.getY() == y1) {
            Overseer.setSelectedShape(null);
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

            Shape selectedShape = Overseer.getSelectedShape();
            if (selectedShape != null) {
                selectedShape.move(dx, dy);
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

    private void shapeCalculation(MouseEvent e, boolean isDragging) {
        int x = Math.min(x1, e.getX());
        int y = Math.min(y1, e.getY());
        int w = Math.abs(e.getX() - x1);
        int h = Math.abs(e.getY() - y1);
        boolean flip = e.getY() > y1; // Determine if arc should flip
        String currentShape = Overseer.getShape();

        if (isDragging) {
            switch (currentShape) {
                case "Rectangle" -> Overseer.setSelectedShape(new Rectangle(Overseer.getColor(), x, y, w, h));
                case "Circle" -> Overseer.setSelectedShape(new Circle(Overseer.getColor(), x, y, w, h));
                case "Arc" -> Overseer.setSelectedShape(new Arc(Overseer.getColor(), x, y, w, h, flip));
                case "Line" -> Overseer.setSelectedShape(new Line(Overseer.getColor(), x1, y1, e.getX(), e.getY()));
            }
        } else {
            switch (currentShape) {
                case "Rectangle" -> Overseer.pushToStack(new Rectangle(Overseer.getColor(), x, y, w, h));
                case "Circle" -> Overseer.pushToStack(new Circle(Overseer.getColor(), x, y, w, h));
                case "Arc" -> Overseer.pushToStack(new Arc(Overseer.getColor(), x, y, w, h, flip));
                case "Line" -> Overseer.pushToStack(new Line(Overseer.getColor(), x1, y1, e.getX(), e.getY()));
            }
            Overseer.setSelectedShape(null);
        }
        Overseer.doSomething();
    }
}
