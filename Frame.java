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

        // Create File Menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        JMenuItem loadMenuItem = new JMenuItem("Load");
        saveMenuItem.addActionListener(e -> Overseer.saveFile());
        loadMenuItem.addActionListener(e -> Overseer.loadFile());
        fileMenu.add(saveMenuItem);
        fileMenu.add(loadMenuItem);
        menuBar.add(fileMenu);

        menuBar.add(createShapesMenu());
        menuBar.add(createColorsMenu());
        menuBar.add(createEditMenu());

        // Create About Menu
        JMenu aboutMenu = new JMenu("About");
        JMenuItem creditsMenuItem = new JMenuItem("Credits");
        creditsMenuItem.addActionListener(e -> dialogBox());
        aboutMenu.add(creditsMenuItem);
        menuBar.add(aboutMenu);

        setJMenuBar(menuBar);
    }

    private JMenu createShapesMenu() {
        JMenu shapesMenu = new JMenu("Shapes");
        JRadioButtonMenuItem rectangleTool = new JRadioButtonMenuItem("Rectangle");
        JRadioButtonMenuItem circleTool = new JRadioButtonMenuItem("Circle");
        JRadioButtonMenuItem arcTool = new JRadioButtonMenuItem("Arc");
        JRadioButtonMenuItem lineTool = new JRadioButtonMenuItem("Line");

        ButtonGroup shapeGroup = new ButtonGroup();
        shapeGroup.add(rectangleTool);
        shapeGroup.add(circleTool);
        shapeGroup.add(arcTool);
        shapeGroup.add(lineTool);

        rectangleTool.setSelected(true);

        rectangleTool.addActionListener(e -> Overseer.setShape("Rectangle"));
        circleTool.addActionListener(e -> Overseer.setShape("Circle"));
        arcTool.addActionListener(e -> Overseer.setShape("Arc"));
        lineTool.addActionListener(e -> Overseer.setShape("Line"));

        shapesMenu.add(rectangleTool);
        shapesMenu.add(circleTool);
        shapesMenu.add(arcTool);
        shapesMenu.add(lineTool);

        return shapesMenu;
    }

    private JMenu createColorsMenu() {
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

        blackColor.setSelected(true);

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

        return colorMenu;
    }

    private JMenu createEditMenu() {
        JMenu editMenu = new JMenu("Edit");
        JMenuItem undoAction = new JMenuItem("Undo");
        JMenuItem redoAction = new JMenuItem("Redo");
        JMenuItem eraseAction = new JMenuItem("Erase");

        undoAction.addActionListener(e -> {
            Overseer.popFromStack();
            Overseer.doSomething();
        });
        redoAction.addActionListener(e -> {
            Overseer.redoToStack();
            Overseer.doSomething();
        });
        eraseAction.addActionListener(e -> {
            Overseer.clearStack();
            Overseer.doSomething();
        });

        editMenu.add(undoAction);
        editMenu.add(redoAction);
        editMenu.add(eraseAction);

        return editMenu;
    }

    private void dialogBox() {
        // create a dialog Box
        JDialog d = new JDialog(this, "Credits");

        JLabel l = new JLabel();

        l.setText("<html>@author christophergrigorian (Christopher Grigorian) <br>" +
                "@author CharlieRay668 (Charlie Ray) <br>" +
                "@author manualdriver (Harold Ellis) <br>" +
                "@author ecan00 (Eric Canihuante)</html>");

        d.add(l);
        d.setSize(400, 150);
        d.setVisible(true);
    }
}
