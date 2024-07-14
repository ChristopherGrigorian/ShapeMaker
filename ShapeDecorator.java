import java.awt.Graphics;
import java.io.Serializable;

public abstract class ShapeDecorator implements Component, Serializable {
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

    @Override
    public int getX() {
        return component.getX();
    }

    @Override
    public int getY() {
        return component.getY();
    }

    @Override
    public int getW() {
        return component.getW();
    }

    @Override
    public int getH() {
        return component.getH();
    }
}
