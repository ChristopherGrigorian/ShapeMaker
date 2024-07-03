import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class Overseer {
    private static final Stack<Shape> shapes = new Stack<>();
    private static final Stack<Shape> redoShapes = new Stack<>();
    private static JPanel drawPanel;
    private static Color color;
    private static String shape;
    private static Shape box;

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
        Overseer.redoShapes.clear();
    }

    public static void popFromStack() {
        if (!shapes.isEmpty()) {
            redoShapes.add(shapes.pop());
        }
    }

    public static void redoToStack() {
        if (!redoShapes.isEmpty()) {
            Overseer.shapes.add(redoShapes.pop());
        }
    }

    public static Shape getBox() {
        return box;
    }

    public static void setBox(Shape box) {
        Overseer.box = box;
    }
}
