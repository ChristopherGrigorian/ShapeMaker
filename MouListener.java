import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * The MouListener class implements mouse listeners to handle mouse events
 * for drawing shapes in the application.
 * <p>
 * NOTE: You must click to select something before dragging. All draws
 * other than in the area of the selected shape will be considered as a normal draw
 * and not a drag&drop so that shapes can still be drawn on top of one another
 * without moving them.
 *
 * @author Charlie Ray (Wrote selection, drag, drop, and click functionality)
 * @author Christopher Grigorian (Shape calculation, pushing to stack and box, clean-up)
 * @author Eric Canihuante (Shape decorator)
 */

public class MouListener implements MouseListener, MouseMotionListener {
    private int x1;
    private int y1;
    private int xDragStart = -1;
    private int yDragStart = -1;
    private Shape currentShape;
    private int clickCount = 0;

    @Override
    public void mouseClicked(MouseEvent e) {
        // Force reset selections
        for (Shape s : Overseer.getInstance().getStack()) {
            s.setSelected(false);
        }
        int selectedX = e.getX();
        int selectedY = e.getY();
        Shape s = findShape(selectedX, selectedY);
        if (currentShape != null && currentShape != s) {
            currentShape.setSelected(!currentShape.getSelected());
            Overseer.getInstance().doSomething();
        }
        if (s != null) {
            s.setSelected(!s.getSelected());
            currentShape = s;
        } else {
            currentShape = null;
        }
        Overseer.getInstance().setSelectedShape(currentShape);
        Overseer.getInstance().doSomething();

        // Add decorator functionality
        if (currentShape != null) {
            clickCount++;
            switch (clickCount) {
                case 1:
                    currentShape = new EyeDecorator(currentShape);
                    break;
                case 2:
                    if (!(currentShape instanceof Arc)) {
                        currentShape = new MouthDecorator(currentShape);
                    }
                    break;
                case 3:
                    currentShape = new HatDecorator(currentShape);
                    break;
                case 4:
                    resetShape();
                    clickCount = 0;
                    break;
            }
            Overseer.getInstance().setSelectedShape(currentShape);
            Overseer.getInstance().doSomething();

        }
    }

    private void resetShape() {
        Overseer overseer = Overseer.getInstance();
        String currentShapeType = overseer.getShape();
        switch (currentShapeType) {
            case "Rectangle":
                currentShape = new Rectangle(overseer.getColor(), x1, y1, 0, 0);
                break;
            case "Circle":
                currentShape = new Circle(overseer.getColor(), x1, y1, 0, 0);
                break;
            case "Arc":
                currentShape = new Arc(overseer.getColor(), x1, y1, 0, 0, false);
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();
        xDragStart = yDragStart = -1;
        currentShape = findShape(x1, y1);
        if (currentShape != null) {
            xDragStart = x1;
            yDragStart = y1;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (xDragStart != -1 && yDragStart != -1) {
            xDragStart = yDragStart = -1;
//        } else if (e.getX() == x1 && e.getY() == y1) {
//            Overseer.getInstance().setSelectedShape(null);
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

            if (currentShape != null) {
                currentShape.move(dx, dy);
            }

            xDragStart = e.getX();
            yDragStart = e.getY();
            Overseer.getInstance().doSomething();
        } else if (currentShape == null) {
            shapeCalculation(e, true);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    private Shape findShape(int x, int y) {
        for (int i = Overseer.getInstance().getStack().size() - 1; i >= 0; i--) {
            Shape s = Overseer.getInstance().getStack().get(i);
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
        Overseer overseer = Overseer.getInstance();
        String currentShapeType = overseer.getShape();
        if (isDragging) {
            switch (currentShapeType) {
                case "Rectangle" -> overseer.setBox(new Rectangle(overseer.getColor(), x, y, w, h));
                case "Circle" -> overseer.setBox(new Circle(overseer.getColor(), x, y, w, h));
                case "Arc" -> overseer.setBox(new Arc(overseer.getColor(), x, y, w, h, flip));
                case "Line" -> overseer.setBox(new Line(overseer.getColor(), x1, y1, e.getX(), e.getY()));
            }
        } else {
            switch (currentShapeType) {
                case "Rectangle" -> overseer.pushToStack(new Rectangle(overseer.getColor(), x, y, w, h));
                case "Circle" -> overseer.pushToStack(new Circle(overseer.getColor(), x, y, w, h));
                case "Arc" -> overseer.pushToStack(new Arc(overseer.getColor(), x, y, w, h, flip));
                case "Line" -> overseer.pushToStack(new Line(overseer.getColor(), x1, y1, e.getX(), e.getY()));
            }
            overseer.setBox(null);
        }
        overseer.doSomething();
    }
}