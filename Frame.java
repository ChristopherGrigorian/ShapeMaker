import javax.swing.*;
import java.awt.*;

/**
 * @author christophergrigorian (Christopher Grigorian)
 * @author [put your github] (Name)
 * @author [put your github] (Name)
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
        DragListener dl = new DragListener();
        Overseer.getDrawPanel().addMouseMotionListener(dl);

        JPanel southPanel = new SouthPanel();
        JPanel westPanel = new WestPanel();

        setLayout(new BorderLayout());
        add(drawPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
        add(westPanel, BorderLayout.WEST);


    }


}
