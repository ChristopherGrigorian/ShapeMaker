import javax.swing.*;
import java.awt.*;

/**
 * The DrawPanel class is a custom JPanel that handles drawing shapes onto the panel.
 *
 * @author Christopher Grigorian
 */

public class DrawPanel extends JPanel {

    public DrawPanel() {
        setBackground(new Color(176, 250, 192));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shape s : Overseer.getInstance().getStack()) {
            s.drawShape(g);
        }
        if (Overseer.getInstance().getBox() != null) {
            Overseer.getInstance().getBox().drawShape(g);
        }
    }
}