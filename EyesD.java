import java.awt.Graphics;

public class EyesD extends ShapeDecorator {

    @Override
    public void drawShape(Graphics g) {
        super.drawShape(g);
        addEyes(g);
    }

    private void addEyes(Graphics g) {
        // Add eye logic
        System.out.println("eyes drawn");
    }
}
