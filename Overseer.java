import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.util.Stack;

/**
 * The Overseer class manages the state and operations related to drawing shapes in the application.
 * This class is essentially the "table" from lecture.
 *
 * @author Christopher Grigorian (Save and Load)
 * @author Eric Canihuante (Copy and Paste)
 */

public class Overseer {
    private static final Stack<Shape> shapes = new Stack<>();
    private static final Stack<Shape> redoShapes = new Stack<>();
    private static final Stack<Shape> clearedShapes = new Stack<>();
    private static JPanel drawPanel;
    private static Color color;
    private static String shape;
    private static Shape box;
    private static boolean savePerformed = false;


    private static Shape copiedShape;
    private static Shape selectedShape;
    private static int pasteOffsetX = 10;
    private static int pasteOffsetY = 10;

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
        for (Shape s : shapes) {
            if (s.getSelected()) {
                s.color = color;
            }
        }
        doSomething();
    }

    public static String getShape() {
        return shape==null?"Rectangle":shape;
    }

    public static void setShape(String shape) {
        Overseer.shape = shape;
    }

    public static void doSomething() {
        savePerformed = false;
        drawPanel.repaint();
    }

    public static Stack<Shape> getStack() {
        return shapes;
    }

    public static void pushToStack(Shape shape) {
        Overseer.shapes.add(shape);
        Overseer.redoShapes.clear();
    }

    public static void undoFromStack() {
        if (!clearedShapes.isEmpty()) {
            shapes.addAll(clearedShapes);
            clearedShapes.clear();
        } else {
            if (!shapes.isEmpty()) {
                redoShapes.add(shapes.pop());
            }
        }
    }

    public static void redoToStack() {
        if (!redoShapes.isEmpty()) {
            Overseer.shapes.add(redoShapes.pop());
        }
    }

    public static void eraseStack() {
        if (!shapes.isEmpty()) {
            clearedShapes.addAll(shapes);
            System.out.println(clearedShapes);
            shapes.clear();
        }
    }

    public static Shape getBox() {
        return box;
    }

    public static void setBox(Shape box) {
        Overseer.box = box;
    }



    public static void newFile() {
        if (!shapes.isEmpty()) {
            if (!savePerformed) {
                int wantSave = JOptionPane.showConfirmDialog(drawPanel, "You have not saved this file. Would you like to save?", null, JOptionPane.YES_NO_OPTION);
                if (wantSave == JOptionPane.YES_OPTION) {
                    saveFile();
                } else if (wantSave == JOptionPane.NO_OPTION) {
                    shapes.clear();
                    redoShapes.clear();
                    doSomething();
                }
            } else {
                shapes.clear();
                redoShapes.clear();
                doSomething();
            }
        }
    }

    public static void saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save File");

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Serialized Files", "ser");
        fileChooser.setFileFilter(filter);

        int returnValue = fileChooser.showSaveDialog(drawPanel);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath() + ".ser";
            File fileName = new File(filePath);
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
                oos.writeObject(shapes);
                savePerformed = true;
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
            File fileName = fileChooser.getSelectedFile();
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
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

    public static void copyShape() {
        copiedShape = selectedShape;
    }

    public static void pasteShape() {
        if (copiedShape != null) {
            Shape newShape = copiedShape.clone();
            int newX, newY;
            newX = selectedShape.getX() + pasteOffsetX;
            newY = selectedShape.getY() + pasteOffsetY;

            newShape.move(newX - newShape.getX(), newY - newShape.getY());
            pushToStack(newShape);
            setSelectedShape(newShape);

            if (pasteOffsetX > 800 || pasteOffsetY > 600) {
                resetPasteOffset();
            } else {
                pasteOffsetX += 10;
                pasteOffsetY += 10;
            }
            doSomething();
        }
    }

    private static void resetPasteOffset() {
        pasteOffsetX = 10;
        pasteOffsetY = 10;
    }

    public static Shape getSelectedShape() {
        return selectedShape;
    }

    public static void setSelectedShape(Shape selectedShape) {
        Overseer.selectedShape = selectedShape;
        resetPasteOffset();
    }
}
