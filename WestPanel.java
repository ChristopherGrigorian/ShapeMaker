import javax.swing.*;
import java.awt.*;


public class WestPanel extends JPanel {
    public WestPanel(DrawPanel panel) {
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

        recRadio.setSelected(true);

        ComboListener cl = new ComboListener(panel);
        colorBox.addActionListener(cl);

        RadioListener rl = new RadioListener(panel);
        recRadio.addActionListener(rl);
        cirRadio.addActionListener(rl);
        arcRadio.addActionListener(rl);
    }
}
