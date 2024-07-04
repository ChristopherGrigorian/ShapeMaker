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

        JMenuBar menuBar = new MenuBar();
        setJMenuBar(menuBar);


    }


}
