import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouListener implements MouseListener {
    int x1, x2, y1, y2;
    DrawPanel myPanel;
    public MouListener(DrawPanel panel) {
        myPanel = panel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Mou Pressed");
        x1 = e.getX();
        y1 = e.getY();

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Mou Released");
        x2 = e.getX();
        y2 = e.getY();
        //System.out.println(x1 + ", " + y1 + ", " + x2 + ", " + y2);
        myPanel.set(Math.min(x1, x2),
                    Math.min(y1, y2),
                    Math.abs(x2 - x1),
                    Math.abs(y2 - y1));

        myPanel.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
