import javax.swing.*;
import java.awt.*;
import java.util.Stack;

/**
 * The Overseer class manages the state and operations related to drawing shapes in the application.
 * This class is essentially the "table" from lecture.
 */

public class Overseer {
    private static final Stack<Shape> shapes = new Stack<>();
    private static final Stack<Shape> redoShapes = new Stack<>();
    private static JPanel drawPanel;
    private static Color color;
    private static String shape;
    private static Shape box;
    private static boolean clearPerformed = false;

    public static JPanel getDrawPanel() {
        return drawPanel;
    }

    public static void setDrawPanel(JPanel drawPanel) {
        Overseer.drawPanel = drawPanel;
    }

    public static Color getColor() {
        return color==null?Color.black:color;
    }

    public static void setColor(Color color) {
        Overseer.color = color;
    }

    public static String getShape() {
        return shape==null?"Rectangle":shape;
    }

    public static void setShape(String shape) {
        Overseer.shape = shape;
    }

    public static void doSomething() {
        drawPanel.repaint();
    }

    public static Stack<Shape> getStack() {
        return shapes;
    }

    public static void pushToStack(Shape shape) {
        Overseer.shapes.add(shape);
        Overseer.clearPerformed = false;
        Overseer.redoShapes.clear();
    }

    public static void popFromStack() {
        if (!shapes.isEmpty()) {
            redoShapes.add(shapes.pop());
        }
    }

    public static void redoToStack() {
        if (!redoShapes.isEmpty()) {
            if (!clearPerformed) {
                Overseer.shapes.add(redoShapes.pop());
            } else {
                clearPerformed = false;
                shapes.addAll(redoShapes);
                redoShapes.clear();
            }
        }
    }

    public static void clearStack() {
        if (!shapes.isEmpty()) {
            redoShapes.addAll(shapes);
            clearPerformed = true;
            shapes.clear();
        }
    }

    public static Shape getBox() {
        return box;
    }

    public static void setBox(Shape box) {
        Overseer.box = box;
    }
}
