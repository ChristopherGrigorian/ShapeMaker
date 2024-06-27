import javax.swing.*;

public class SouthPanel extends JPanel {
    public SouthPanel() {
        WidgetListener wl = new WidgetListener();
        JButton undo = new JButton("Undo");
        JButton erase = new JButton("Erase");
        add(undo);
        add(erase);
        undo.addActionListener(wl);
        erase.addActionListener(wl);
    }
}
