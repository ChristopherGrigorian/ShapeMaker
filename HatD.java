import java.awt.*;

public class HatD extends ShapeDecorator {

    @Override
    public void drawShape(Graphics g) {
        super.drawShape(g);
        addHat(g);
    }

    private void addHat(Graphics g) {
        // Add eye logic
    }
}
