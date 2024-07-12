import java.awt.*;

public class MouthD extends ShapeDecorator {
    @Override
    public void drawShape(Graphics g) {
        super.drawShape(g);
        addMouth(g);
    }

    private void addMouth(Graphics g) {
        // Add eye logic
    }
}
