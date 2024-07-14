import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Stack;

public class TextPanel extends JPanel implements PropertyChangeListener {

    private final JTextArea textArea;

    public TextPanel() {
        setPreferredSize(new Dimension(250, 600));
        setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        this.textArea = textArea;

        // Scroll Bar is invisible at the start, and will appear once text
        // is too much to handle in one screen.
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JLabel label = new JLabel("Shape Data", JLabel.CENTER);

        add(label, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Stack<Component> shapes = ((Overseer) evt.getSource()).getStack();
        clearText();

        for (Component shape : shapes) {
            while ((shape instanceof ShapeDecorator)) {
                shape = ((ShapeDecorator) shape).getComponent();
            }
            appendText(shape.toString());
        }
    }

    private void clearText() {
        textArea.setText("");
    }

    private void appendText(String text) {
        textArea.append(text);
    }


}