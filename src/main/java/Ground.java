import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Ground extends AbstractGameObject{
    Color color;
    public Ground(JPanel parent, ArrayList<GameObject> lgo, int w, int h, int x, int y, int speedX, int speedY, Color color) {
        this.parent = parent;
        this.lgo = lgo;
        this.w = w;
        this.h = h;
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
        this.color=color;
    }

    @Override
    public void update() {
        newPositions();
    }

    @Override
    public void paint(Graphics g) {
        ImageIcon icon = new ImageIcon("images/Ground.png");
        icon.paintIcon(parent,g,x,y);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,w,h);
    }
}
