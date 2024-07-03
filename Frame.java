import javax.swing.*;
import java.awt.*;

/**
 * @author christophergrigorian (Christopher Grigorian)
 * @author CharlieRay668 (Charlie Ray
 * @author manualdriver (Harold Ellis)
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

        JPanel southPanel = new SouthPanel();
        JPanel westPanel = new WestPanel();

        setLayout(new BorderLayout());
        add(drawPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
        add(westPanel, BorderLayout.WEST);


    }


}
