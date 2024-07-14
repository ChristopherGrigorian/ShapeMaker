import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.awt.Color;
import java.util.Stack;

/**
 * The Overseer class manages the state and operations related to drawing shapes in the application.
 * This class is essentially the "table" from lecture.
 *
 * @author Christopher Grigorian (Save and Load)
 * @author Eric Canihuante (Copy and Paste)
 * @author Charlie Ray (Singleton)
 */

public class Overseer {//extends PropertyChangeSupport {
    private static Overseer instance;
    private final PropertyChangeSupport pcs;

    private static final Stack<Component> shapes = new Stack<>();
    private static final Stack<Component> redoShapes = new Stack<>();
    private static final Stack<Component> clearedShapes = new Stack<>();
    private static JPanel drawPanel;
    private static Color color;
    private static String shape;
    private static Shape box;
    private static boolean savePerformed = false;

    private static Shape copiedShape;
    private static Shape selectedShape;
    private static Component selectedComponent;
    private static int pasteOffsetX = 10;
    private static int pasteOffsetY = 10;

    private Overseer() {
        //super(new Object());

        pcs = new PropertyChangeSupport(this);
    }

    public static Overseer getInstance() {
        if (instance == null) {
            instance = new Overseer();
        }
        return instance;
    }

    public JPanel getDrawPanel() {
        return drawPanel;
    }

    public void setDrawPanel(JPanel drawPanel) {
        Overseer.drawPanel = drawPanel;
    }

    public Color getColor() {
        return color == null ? Color.black : color;
    }

    public void setColor(Color color) {
        Overseer.color = color;
        if (selectedShape != null) {
            selectedShape.color = color;
        }
        doSomething();
    }

    public String getShape() {
        return shape == null ? "Rectangle" : shape;
    }

    public Component getStackIndex(int i) {
        return shapes.get(i);
    }

    public void setShape(String shape) {
        Overseer.shape = shape;
    }

    public void doSomething() {
        savePerformed = false;
        pcs.firePropertyChange("shapeChanges", null, shapes);
        drawPanel.repaint();
    }

    public Stack<Component> getStack() {
        return shapes;
    }

    public void pushToStack(Component shape) {
        if (!((shape.getW() == 0) && (shape.getH() == 0))) {
            Overseer.shapes.add(shape);
            Overseer.redoShapes.clear();
        }
    }

    public void undoFromStack() {
        if (!clearedShapes.isEmpty()) {
            shapes.addAll(clearedShapes);
            clearedShapes.clear();
        } else {
            if (!shapes.isEmpty()) {
                redoShapes.add(shapes.pop());
            }
        }
    }

    public void redoToStack() {
        if (!redoShapes.isEmpty()) {
            Overseer.shapes.add(redoShapes.pop());
        }
    }

    public void eraseStack() {
        if (!shapes.isEmpty()) {
            clearedShapes.addAll(shapes);
            shapes.clear();
        }
    }

    public Shape getBox() {
        return box;
    }

    public void setBox(Shape box) {
        Overseer.box = box;
    }

    public void newFile() {
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

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    public void saveFile() {
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
    public void loadFile() {
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

    public int getShapeIndex(Component target) {
        for (int i = 0; i <= shapes.size(); i++) {
            Component currChain = shapes.get(i);
            while (!(currChain instanceof Shape)) {
                currChain = ((ShapeDecorator) currChain).getComponent();
            }
            if (currChain == target) {
                return i;
            }
        }
        return -1;
    }

    public void copyShape() {
        copiedShape = selectedShape;
        resetPasteOffset();
    }

    public void pasteShape() {
        if (copiedShape != null) {
            Component newShape = copiedShape.clone();
            int newX, newY;
            newX = copiedShape.getX() + pasteOffsetX;
            newY = copiedShape.getY() + pasteOffsetY;

            newShape.move(newX - newShape.getX(), newY - newShape.getY());
            pushToStack(newShape);

            if (pasteOffsetX > 800 || pasteOffsetY > 600) {
                resetPasteOffset();
            } else {
                pasteOffsetX += 10;
                pasteOffsetY += 10;
            }
            doSomething();
        }
    }

    private void resetPasteOffset() {
        pasteOffsetX = 10;
        pasteOffsetY = 10;
    }

    public Shape getSelectedShape() {
        return selectedShape;
    }

    public void setSelectedShape(Shape selectedShape) {
        Overseer.selectedShape = selectedShape;
    }

    public void setSelectedComponent(Component setComponent) {
        Overseer.selectedComponent = setComponent;
        Component s = selectedComponent;

        while ((s instanceof ShapeDecorator)) {
            s = ((ShapeDecorator) s).getComponent();
        }
        setSelectedShape((Shape) s);

        getStack().remove(selectedComponent);
        getStack().push(selectedComponent.nextDecorator());

        doSomething();
    }

}