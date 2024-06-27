import javax.swing.*;

public class SouthPanel extends JPanel {
    public SouthPanel() {
        JButton undo = new JButton("Undo");
        JButton erase = new JButton("Erase");
        //setLayout(new GridLayout(1,2));
        add(undo);
        add(erase);

    }
}
