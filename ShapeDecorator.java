import java.awt.Graphics;

public class ShapeDecorator implements Component {

    protected Component component;

    public void setComponent(Component component) {
        this.component = component;
    }

    public Component getComponent() {
        return component;
    }

    @Override
    public void drawShape(Graphics g) {
        if (component != null) {
            component.drawShape(g);
        }
    }
}
