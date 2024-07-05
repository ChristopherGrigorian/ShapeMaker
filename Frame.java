import javax.swing.*;
import java.awt.*;

/**
 * The Frame class represents the main application window.
 * It extends JFrame and sets up the user interface, including the drawing panel and menu bar.
 *
 * @author christophergrigorian (Christopher Grigorian)
 * @author CharlieRay668 (Charlie Ray)
 * @author manualdriver (Harold Ellis)
 * @author ecan00 (Eric Canihuante)
 *
 */

public class Frame extends JFrame {
    public static void main(String[] args) {
        Frame app = new Frame();
        app.setSize(800, 600);
        app.setTitle("My Paint App");
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setResizable(false);
        app.setVisible(true);
    }

    public Frame() {
        JPanel drawPanel = new DrawPanel();
        Overseer.setDrawPanel(drawPanel);
        MouListener ml = new MouListener();
        Overseer.getDrawPanel().addMouseListener(ml);
        Overseer.getDrawPanel().addMouseMotionListener(ml);

        setLayout(new BorderLayout());
        add(drawPanel, BorderLayout.CENTER);

        createMenuBar();
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

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

        // Add to menuBar
        menuBar.add(shapesMenu);
        menuBar.add(colorMenu);
        menuBar.add(createEditMenu());

        //Create about menu
        menuBar.add(createAboutButton());

        setJMenuBar(menuBar);
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

    private JButton createAboutButton() {
        JButton button = new JButton("About");
        button.setOpaque(true);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusable(false);
        button.addActionListener(e -> dialogBox());
        return button;
    }

    private void dialogBox() {
        // create a dialog Box
        JDialog d = new JDialog(this, "About us");

        JLabel l = new JLabel();

        l.setText("<html>@author christophergrigorian (Christopher Grigorian) <br>" +
                "<html>@author CharlieRay668 (Charlie Ray) <br>" +
                "<html>@author manualdriver (Harold Ellis) <br>" +
                "@author ecan00 (Eric Canihuante)</html>");

        d.add(l);
        d.setSize(300, 150);
        d.setVisible(true);
    }
}
