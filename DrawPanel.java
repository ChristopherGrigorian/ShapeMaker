import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel {

    public DrawPanel() {
        setBackground(Color.CYAN);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Shape currShape = Overseer.getCurrShape();
        if (currShape != null) {
            currShape.drawShape(g);
        }
        for (Shape s : Overseer.getStack()) {
            s.drawShape(g);
        }
    }
}
