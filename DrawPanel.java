import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel {

    public DrawPanel() {
        setBackground(Color.CYAN);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shape s : Overseer.getStack()) {
            s.drawShape(g);
        }
        if (Overseer.getBox() != null) {
            Overseer.getBox().drawShape(g);
        }
    }
}
