import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Undo")) {
            System.out.println("Undo has been pressed!");
        } else if (e.getActionCommand().equals("Redo")) {
            System.out.println("Erase has been pressed!");
        }
    }
}
