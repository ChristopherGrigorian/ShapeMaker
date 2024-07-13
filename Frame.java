import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;

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
        app.setSize(1200, 600);
        app.setTitle("My Paint App");
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setResizable(false);
        app.setVisible(true);
    }

    public Frame() {
        JPanel drawPanel = new DrawPanel();
        Overseer overseer = Overseer.getInstance();
        overseer.setDrawPanel(drawPanel);
        MouListener ml = new MouListener();
        overseer.getDrawPanel().addMouseListener(ml);
        overseer.getDrawPanel().addMouseMotionListener(ml);
        TextPanel textPanel = new TextPanel();
        overseer.addPropertyChangeListener(textPanel);


        setLayout(new BorderLayout());
        add(drawPanel, BorderLayout.CENTER);
        add(textPanel, BorderLayout.WEST);

        createMenuBar();

        // Add KeyboardListener to the frame
        KeyboardListener kl = new KeyboardListener();
        addKeyListener(kl);
        setFocusable(true);
        requestFocusInWindow();
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        // Create File Menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        JMenuItem loadMenuItem = new JMenuItem("Load");
        Overseer overseer = Overseer.getInstance();
        newMenuItem.addActionListener(e -> overseer.newFile());
        saveMenuItem.addActionListener(e -> overseer.saveFile());
        loadMenuItem.addActionListener(e -> overseer.loadFile());
        fileMenu.add(newMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(loadMenuItem);
        menuBar.add(fileMenu);

        menuBar.add(createEditMenu());
        menuBar.add(createShapesMenu());
        menuBar.add(createColorsMenu());

        // Create About Menu
        JMenu aboutMenu = new JMenu("Help");
        JMenuItem creditsMenuItem = new JMenuItem("About");
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

        Overseer overseer = Overseer.getInstance();
        rectangleTool.addActionListener(e -> overseer.setShape("Rectangle"));
        circleTool.addActionListener(e -> overseer.setShape("Circle"));
        arcTool.addActionListener(e -> overseer.setShape("Arc"));
        lineTool.addActionListener(e -> overseer.setShape("Line"));

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

        Overseer overseer = Overseer.getInstance();
        blackColor.addActionListener(e -> overseer.setColor(Color.BLACK));
        redColor.addActionListener(e -> overseer.setColor(Color.RED));
        yellowColor.addActionListener(e -> overseer.setColor(Color.YELLOW));
        greenColor.addActionListener(e -> overseer.setColor(Color.GREEN));
        blueColor.addActionListener(e -> overseer.setColor(Color.BLUE));

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
        JMenuItem copyAction = new JMenuItem("Copy");
        JMenuItem pasteAction = new JMenuItem("Paste");

        Overseer overseer = Overseer.getInstance();
        undoAction.addActionListener(e -> {
            overseer.undoFromStack();
            overseer.doSomething();
        });
        redoAction.addActionListener(e -> {
            overseer.redoToStack();
            overseer.doSomething();
        });
        eraseAction.addActionListener(e -> {
            overseer.eraseStack();
            overseer.doSomething();
        });

        copyAction.addActionListener(e -> overseer.copyShape());
        pasteAction.addActionListener(e -> overseer.pasteShape());

        editMenu.add(undoAction);
        editMenu.add(redoAction);
        editMenu.add(eraseAction);
        editMenu.add(copyAction);
        editMenu.add(pasteAction);

        return editMenu;
    }

    private void dialogBox() {
        // create a dialog Box
        JDialog d = new JDialog(this, "About");

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