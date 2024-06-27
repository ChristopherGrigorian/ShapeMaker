import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ComboListener implements ActionListener {
    DrawPanel myPanel;
    public ComboListener(DrawPanel panel) {
        myPanel = panel;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JComboBox) {
            JComboBox cb = (JComboBox) e.getSource();
            myPanel.setColor(Objects.requireNonNull(cb.getSelectedItem()).toString());
        }
    }
}
