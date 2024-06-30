import javax.swing.*;

public class SouthPanel extends JPanel {
    public SouthPanel() {
        JButton undo = new JButton("Undo");
        JButton redo = new JButton("Redo");
        add(undo);
        add(redo);

        ActListener al = new ActListener();
        undo.addActionListener(al);
        redo.addActionListener(al);
    }
}
