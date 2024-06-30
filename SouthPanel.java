import javax.swing.*;

public class SouthPanel extends JPanel {
    public SouthPanel() {
        JButton undo = new JButton("Undo");
        JButton erase = new JButton("Erase");
        add(undo);
        add(erase);

        ActListener al = new ActListener();
        undo.addActionListener(al);
        erase.addActionListener(al);
    }
}
