import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
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

    public static void saveFile() {
        String fileName = JOptionPane.showInputDialog(drawPanel, "Enter file name (without extension):", "Save File", JOptionPane.PLAIN_MESSAGE);
        if (fileName != null) {
            if (!fileName.endsWith(".ser")) {
                fileName += ".ser";
            }
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
                oos.writeObject(shapes);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(drawPanel, "Error saving file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Load File");

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Serialized Files", "ser");
        fileChooser.setFileFilter(filter);

        int userSelection = fileChooser.showOpenDialog(drawPanel);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileChooser.getSelectedFile()))) {
                shapes.clear();
                redoShapes.clear();
                // I create the save file, so it is (at least I think so) okay to suppress this warning.
                Stack<Shape> loadedShapes = (Stack<Shape>) ois.readObject();
                shapes.addAll(loadedShapes);
                doSomething();
            } catch (IOException | ClassNotFoundException e) {
                JOptionPane.showMessageDialog(drawPanel, "Error loading file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (userSelection == JFileChooser.CANCEL_OPTION) {
            System.out.println("Load operation canceled by user.");
        } else if (userSelection == JFileChooser.ERROR_OPTION) {
            System.err.println("Error in file chooser dialog.");
        }
    }

}
