import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Ground extends AbstractGameObject{
    ImageIcon icon;
    public Ground(JPanel parent, ArrayList<GameObject> lgo, int w, int h, int x, int y, int speedX, int speedY, String path) {
        this.parent = parent;
        this.lgo = lgo;
        this.w = w;
        this.h = h;
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
        icon= new ImageIcon(path);
    }

    @Override
    public void update() {
        newPositions();
    }

    @Override
    public void paint(Graphics g) {
        icon.paintIcon(parent,g,x,y);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,w,h);
    }
}
