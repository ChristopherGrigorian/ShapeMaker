import java.awt.*;

public class EyesD extends ShapeDecorator {

    @Override
    public void drawShape(Graphics g) {
        super.drawShape(g);
        addEyes(g);
    }

    private void addEyes(Graphics g) {
        // Add eye logic
    }
}
