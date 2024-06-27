import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RadioListener implements ActionListener {
    DrawPanel myPanel;
    public RadioListener(DrawPanel panel) {
        myPanel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        myPanel.setShape(e.getActionCommand());
    }
}
