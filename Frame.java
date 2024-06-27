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
        JPanel panel = new DrawPanel();
        JPanel panel2 = new SouthPanel();
        JPanel panel3 = new WestPanel();
        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        add(panel2, BorderLayout.SOUTH);
        add(panel3, BorderLayout.WEST);


    }


}
