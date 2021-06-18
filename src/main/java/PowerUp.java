import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class PowerUp extends AbstractGameObject {
    ImageIcon icon;
    Boolean existing;

    public PowerUp(JPanel parent, ArrayList<GameObject> lgo, int x, int y, String icon) {
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.lgo = lgo;
        this.icon = new ImageIcon(icon);
        w = 30;
        h = 40;
        existing = true;
    }

    @Override
    public void paint(Graphics g) {
        icon.paintIcon(parent, g, (int)x,(int) y);
    }


    public void setExisting(boolean existing) {
        this.existing = existing;
    }

    public void existenceUpdate() {
        if (!existing) {
            lgo.remove(this);
        }
    }

    public abstract void specialAbility(Ball ball);
}
