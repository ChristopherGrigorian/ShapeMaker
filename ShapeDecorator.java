import java.awt.*;

public class ShapeDecorator implements Component {

    protected Component component;

    public void setComponent(Component component) {
        this.component = component;
    }

    @Override
    public void drawShape(Graphics g) {
        if (component != null) {
            component.drawShape(g);
        }
    }
}
