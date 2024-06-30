import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class Overseer {
    private static final Stack<Shape> shapes = new Stack<>();
    private static JPanel drawPanel;
    private static Color color;
    private static int XCord;
    private static int YCord;
    private static int width;
    private static int height;
    private static String shape;

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

    public static int getXCord() {
        return XCord;
    }

    public static void setXCord(int XCord) {
        Overseer.XCord = XCord;
    }

    public static int getYCord() {
        return YCord;
    }

    public static void setYCord(int YCord) {
        Overseer.YCord = YCord;
    }

    public static int getWidth() {
        return width;
    }

    public static void setWidth(int width) {
        Overseer.width = width;
    }

    public static int getHeight() {
        return height;
    }

    public static void setHeight(int height) {
        Overseer.height = height;
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
    }

    public static void popFromStack() {
        shapes.pop();
    }

    public static void clearStack() {
        shapes.clear();
    }
}
