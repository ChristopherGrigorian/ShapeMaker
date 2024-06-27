import javax.swing.*;
import java.awt.*;

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
        DrawPanel panel = new DrawPanel();
        SouthPanel panel2 = new SouthPanel();
        WestPanel panel3 = new WestPanel(panel);
        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        add(panel2, BorderLayout.SOUTH);
        add(panel3, BorderLayout.WEST);


    }


}
