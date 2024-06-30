import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ActListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JComboBox<?> cb) {
            String chosenColor = (String) cb.getSelectedItem();
            switch (Objects.requireNonNull(chosenColor)) {
                case "Black" -> Overseer.setColor(Color.BLACK);
                case "Red" -> Overseer.setColor(Color.RED);
                case "Yellow" -> Overseer.setColor(Color.YELLOW);
                case "Green" -> Overseer.setColor(Color.GREEN);
                case "Blue" -> Overseer.setColor(Color.BLUE);
            }
        } else if (e.getSource() instanceof JRadioButton) {
            Overseer.setShape(e.getActionCommand());
        } else {
            if (e.getActionCommand().equals("Undo")) {
                Overseer.popFromStack();
                Overseer.doSomething();
            } else if (e.getActionCommand().equals("Erase")) {
                Overseer.clearStack();
                Overseer.doSomething();
            }
        }
    }
}
