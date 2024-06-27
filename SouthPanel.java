import javax.swing.*;

public class SouthPanel extends JPanel {
    public SouthPanel() {
        ButtonListener wl = new ButtonListener();
        JButton undo = new JButton("Undo");
        JButton erase = new JButton("Erase");
        add(undo);
        add(erase);
        undo.addActionListener(wl);
        erase.addActionListener(wl);
    }
}
