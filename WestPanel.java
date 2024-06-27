import javax.swing.*;
import java.awt.*;

public class WestPanel extends JPanel {
    public WestPanel() {
        String[] colors = {"Blue", "Red", "Green", "Yellow"};
        JComboBox colorBox = new JComboBox(colors);
        JRadioButton recRadio = new JRadioButton("Rectangle");
        JRadioButton cirRadio = new JRadioButton("Circle");
        JRadioButton arcRadio = new JRadioButton("Arc");
        ButtonGroup bg = new ButtonGroup();
        bg.add(recRadio);
        bg.add(cirRadio);
        bg.add(arcRadio);
        setLayout(new GridLayout(7,1));
        add(colorBox);
        add(recRadio);
        add(cirRadio);
        add(arcRadio);
    }
}
