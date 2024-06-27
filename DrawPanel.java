import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

public class DrawPanel extends JPanel {
    int a, b, c, d;

    public DrawPanel() {
        setBackground(Color.CYAN);
        MouListener ml = new MouListener(this);
        addMouseListener(ml);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(a, b, c, d);

    }

    public void set(int x, int y, int w, int z) {
        a = x;
        b = y;
        c = w;
        d = z;
    }

}
