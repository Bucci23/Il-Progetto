import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Fuoco extends AbstractGameObject {
    int standardSpeed;
    ImageIcon r;
    ImageIcon l;
    ImageIcon icon;
    boolean existing;

    public Fuoco(JPanel parent, ArrayList<GameObject> lgo, int standardSpeed, String r, String l, int x, int y) {
        this.parent = parent;
        this.lgo = lgo;
        this.standardSpeed = standardSpeed;
        this.r = new ImageIcon(r);
        this.l = new ImageIcon(l);
        this.x = x;
        this.y = y;
        w = 30;
        h = 20;
        this.existing = true;
    }

    @Override
    public void update() {
        this.speedX = standardSpeed;
        this.speedY = 0;
        isHitting();
        existenceUpdate();
        newPositions();
    }

    @Override
    public void paint(Graphics g) {
        if (speedX > 0) {
            this.icon = r;
        } else
            this.icon = l;
        icon.paintIcon(parent, g, x, y);

    }

    public void isHitting() {
        for (GameObject go : lgo) {
            if (go != this && !(go instanceof Ball )) {
                if (this.getBounds().intersects(go.getBounds()))
                    setExisting(false);
            }
        }
    }

    void setExisting(boolean existing) {
        this.existing = existing;
    }

    void existenceUpdate() {
        if (!existing) {
            lgo.remove(this);
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, w, h);
    }
}