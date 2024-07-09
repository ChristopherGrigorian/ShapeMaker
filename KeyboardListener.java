import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The KeyBoardListener class implements key listeners to handle keybaord events
 * for copying and pasting shapes in the application.
 */

public class KeyboardListener implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if ((e.getKeyCode() == KeyEvent.VK_C) && ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0)) {
            Overseer.copyShape();
        }
        if ((e.getKeyCode() == KeyEvent.VK_V) && ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0)) {
            Overseer.pasteShape();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
