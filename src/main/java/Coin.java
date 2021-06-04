import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Coin extends AbstractGameObject {
    JPanel parent;
    ArrayList<GameObject> lgo;
    ImageIcon icon;
    boolean existing;

    public Coin(JPanel parent, ArrayList<GameObject> lgo, int x, int y, String icon) {
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
    public void update() {
        posUpdate();
    }
    public void posUpdate(){
        BallPanel par=(BallPanel) parent;
        x = x += speedX + par.sceneSpeedX;
        y = y += speedY + par.sceneSpeedY;
    }
    @Override
    public void paint(Graphics g) {
        icon.paintIcon(parent, g, x, y);
    }

    public void setExisting(boolean existing) {
        this.existing = existing;
    }

    public void existenceUpdate() {
        if (!existing) {
            lgo.remove(this);
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, w, h);
    }
}
