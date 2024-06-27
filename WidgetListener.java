import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WidgetListener implements ActionListener {
    public WidgetListener() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Undo")) {
            System.out.println("Undo has been pressed!");
        } else {
            System.out.println("Erase has been pressed!");
        }
    }
}
