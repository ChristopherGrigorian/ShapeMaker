import javax.swing.*;
import java.awt.*;

/**
 * The MenuBar class represents the menu bar of the application.
 * It includes menus for selecting shapes, colors, and editing actions.
 */

public class MenuBar extends JMenuBar {
    public MenuBar() {
        // Shape Menu
        JMenu shapesMenu = new JMenu("Shapes");
        JRadioButtonMenuItem rectangleTool = new JRadioButtonMenuItem("Rectangle");
        JRadioButtonMenuItem circleTool = new JRadioButtonMenuItem("Circle");
        JRadioButtonMenuItem arcTool = new JRadioButtonMenuItem("Arc");

        ButtonGroup shapeGroup = new ButtonGroup();
        shapeGroup.add(rectangleTool);
        shapeGroup.add(circleTool);
        shapeGroup.add(arcTool);

        // Set default shape to Rectangle
        rectangleTool.setSelected(true);
        Overseer.setShape("Rectangle");

        // Action listeners to set the tool in Overseer
        rectangleTool.addActionListener(e -> Overseer.setShape("Rectangle"));
        circleTool.addActionListener(e -> Overseer.setShape("Circle"));
        arcTool.addActionListener(e -> Overseer.setShape("Arc"));

        shapesMenu.add(rectangleTool);
        shapesMenu.add(circleTool);
        shapesMenu.add(arcTool);
        add(shapesMenu);

        // Color Menu
        JMenu colorMenu = new JMenu("Color");
        JRadioButtonMenuItem blackColor = new JRadioButtonMenuItem("Black");
        JRadioButtonMenuItem redColor = new JRadioButtonMenuItem("Red");
        JRadioButtonMenuItem yellowColor = new JRadioButtonMenuItem("Yellow");
        JRadioButtonMenuItem greenColor = new JRadioButtonMenuItem("Green");
        JRadioButtonMenuItem blueColor = new JRadioButtonMenuItem("Blue");

        ButtonGroup colorGroup = new ButtonGroup();
        colorGroup.add(blackColor);
        colorGroup.add(redColor);
        colorGroup.add(yellowColor);
        colorGroup.add(greenColor);
        colorGroup.add(blueColor);

        // Set default color to Black
        blackColor.setSelected(true);
        Overseer.setColor(Color.BLACK);

        blackColor.addActionListener(e -> Overseer.setColor(Color.BLACK));
        redColor.addActionListener(e -> Overseer.setColor(Color.RED));
        yellowColor.addActionListener(e -> Overseer.setColor(Color.YELLOW));
        greenColor.addActionListener(e -> Overseer.setColor(Color.GREEN));
        blueColor.addActionListener(e -> Overseer.setColor(Color.BLUE));

        colorMenu.add(blackColor);
        colorMenu.add(redColor);
        colorMenu.add(yellowColor);
        colorMenu.add(greenColor);
        colorMenu.add(blueColor);
        add(colorMenu);

        // Add Edit Menu
        add(createEditMenu());
    }

    private JMenu createEditMenu() {
        // Edit Menu for Undo/Redo
        JMenu editMenu = new JMenu("Edit");
        JMenuItem undoAction = new JMenuItem("Undo");
        JMenuItem redoAction = new JMenuItem("Redo");

        // Action listeners for undo/redo actions
        undoAction.addActionListener(e -> {
            Overseer.popFromStack();
            Overseer.doSomething();
        });
        redoAction.addActionListener(e -> {
            Overseer.redoToStack();
            Overseer.doSomething();
        });

        editMenu.add(undoAction);
        editMenu.add(redoAction);

        return editMenu;
    }
}
