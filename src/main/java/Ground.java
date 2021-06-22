import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Ground extends AbstractGameObject {
    ImageIcon[] icons;
    int nGrass;
    int nSimple;

    public Ground(JPanel parent, ArrayList<GameObject> lgo, int w, int h, int x, int y, double speedX, double speedY, String pathG, String pathS) {
        this.parent = parent;
        this.lgo = lgo;
        this.w = w;
        this.h = h;
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
        icons = new ImageIcon[2];
        icons[0] = new ImageIcon(pathG);
        icons[1] = new ImageIcon(pathS);
        imagecalc();
    }

    @Override
    public void update() {
        newPositions();
    }

    void imagecalc() {
        nGrass = w / 50;
        nSimple = (w * h) / (50 * 50) - nGrass;
    }

    @Override
    public void paint(Graphics g) {
        for (double i = x; i < x + w - 1; i += 50) {
            icons[0].paintIcon(parent, g, (int) i, (int) y);
        }
        for (double i = x; i < x + w - 1; i += 50) {
            for (double j = y + 50; j < y + h; j += 50) {
                icons[1].paintIcon(parent, g, (int) i, (int) j);
            }
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, w, h);
    }
}
