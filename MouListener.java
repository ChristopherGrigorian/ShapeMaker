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
 */

public class MouListener implements MouseListener, MouseMotionListener {
    private int x1;
    private int y1;
    private int xDragStart = -1;
    private int yDragStart = -1;
    private Shape currentComponentBaseShape;
    private Component currentComponent;
    private boolean firstSelection = true;

    @Override
    public void mouseClicked(MouseEvent e) {
        // Force reset selections
        int selectedX = e.getX();
        int selectedY = e.getY();
        Component c = findComponent(selectedX, selectedY);
        if (c != null) {
            // If I switch components, set first selection to true
            if (Overseer.getInstance().getBaseShapeComponent() != currentComponentBaseShape) {
                firstSelection = true;
            }

            Overseer.getInstance().setSelectedComponent(c);
            Overseer.getInstance().setBaseShapeComponent(currentComponentBaseShape);

            if (!firstSelection) {
                Overseer.getInstance().pushDecorator();
            } else {
                firstSelection = false;
            }
            currentComponent = c;
        } else {
            Overseer.getInstance().setSelectedComponent(null);
            Overseer.getInstance().setBaseShapeComponent(null);
        }

        Overseer.getInstance().doSomething();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();
        xDragStart = yDragStart = -1;
        currentComponent = findComponent(x1, y1);
        if (currentComponent != null) {
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

            if (currentComponent != null) {
                currentComponent.move(dx, dy);
            }

            xDragStart = e.getX();
            yDragStart = e.getY();
            Overseer.getInstance().doSomething();
        } else if (currentComponent == null) {
            shapeCalculation(e, true);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    private Component findComponent(int x, int y) {
        for (int i = Overseer.getInstance().getStack().size() - 1; i >= 0; i--) {
            Component s = Overseer.getInstance().getStack().get(i);
            while ((s instanceof ShapeDecorator)) {
                s = ((ShapeDecorator) s).getComponent();
            }
            if (s instanceof Shape) {
                if (((Shape) s).contains(x, y)) {
                    currentComponentBaseShape = (Shape) s;
                    return Overseer.getInstance().getStack().get(i);
                }
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