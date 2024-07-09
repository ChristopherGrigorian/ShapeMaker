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
 */

public class Frame extends JFrame {
    private JMenuItem pasteMenuItem;  // Reference to the paste menu item
    private JMenuItem undoMenuItem;   // Reference to the undo menu item
    private JMenuItem redoMenuItem;   // Reference to the redo menu item
    private JMenuItem copyMenuItem;   // Reference to the copy menu item

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

        // Add listeners to Overseer
        Overseer.addCopyListener(this::enablePasteMenuItem);
        Overseer.addUndoRedoListener(this::updateUndoRedoMenuItems);
        Overseer.addSelectionListener(this::updateCopyMenuItem);

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
        JMenuItem eraseAllMenuItem = new JMenuItem("Erase All");  // Move and rename the erase option
        newMenuItem.addActionListener(e -> Overseer.newFile());
        saveMenuItem.addActionListener(e -> Overseer.saveFile());
        loadMenuItem.addActionListener(e -> Overseer.loadFile());
        eraseAllMenuItem.addActionListener(e -> {
            Overseer.clearStack();
            Overseer.doSomething();
        });
        fileMenu.add(newMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(loadMenuItem);
        fileMenu.add(eraseAllMenuItem);  // Add the erase option to the file menu
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
        undoMenuItem = new JMenuItem("Undo");
        redoMenuItem = new JMenuItem("Redo");
        copyMenuItem = new JMenuItem("Copy");
        pasteMenuItem = new JMenuItem("Paste");  // Initialize paste menu item

        undoMenuItem.addActionListener(e -> {
            Overseer.popFromStack();
            Overseer.doSomething();
        });
        redoMenuItem.addActionListener(e -> {
            Overseer.redoToStack();
            Overseer.doSomething();
        });
        copyMenuItem.addActionListener(e -> Overseer.copyShape());
        pasteMenuItem.addActionListener(e -> Overseer.pasteShape());

        pasteMenuItem.setEnabled(false);  // Initially disable the paste menu item

        editMenu.add(undoMenuItem);
        editMenu.add(redoMenuItem);
        editMenu.add(copyMenuItem);
        editMenu.add(pasteMenuItem);

        updateUndoRedoMenuItems();  // Set initial state for undo and redo buttons
        updateCopyMenuItem();       // Set initial state for copy button

        return editMenu;
    }

    private void enablePasteMenuItem() {
        pasteMenuItem.setEnabled(Overseer.hasCopiedShape());
    }

    private void updateUndoRedoMenuItems() {
        undoMenuItem.setEnabled(!Overseer.getStack().isEmpty() || !Overseer.getClearedShapes().isEmpty());
        redoMenuItem.setEnabled(!Overseer.getRedoStack().isEmpty() && !Overseer.wasLastActionEraseAll());
    }

    private void updateCopyMenuItem() {
        copyMenuItem.setEnabled(Overseer.hasSelectedShape());
    }

    private void dialogBox() {
        // create a dialog Box
        JDialog d = new JDialog(this, "About");

        JLabel l = new JLabel("<html><div style='text-align: center;'>"
                + "@author christophergrigorian (Christopher Grigorian) <br>"
                + "@author CharlieRay668 (Charlie Ray) <br>"
                + "@author manualdriver (Harold Ellis) <br>"
                + "@author ecan00 (Eric Canihuante)</div></html>", SwingConstants.CENTER);

        d.setLayout(new BorderLayout());
        d.add(l, BorderLayout.CENTER);
        d.setSize(400, 150);
        d.setLocationRelativeTo(this); // Center the dialog relative to the frame
        d.setVisible(true);
    }
}
