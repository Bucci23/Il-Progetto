import javax.swing.*;
import java.awt.*;

public class Guida extends AbstractGameObject{
    ImageIcon icon;
    ImageIcon guide;
    ImageIcon guidaX;
    boolean showing;
    public Guida(BallPanel parent, String icon, String guide, String guidaX) {
        this.parent = parent;
        this.icon = new ImageIcon(icon);
        this.guide = new ImageIcon(guide);
        this.guidaX = new ImageIcon(guidaX);
        w = 100;
        h = 100;
        x = parent.parent.getWidth() - w;
        y = 40;
        showing = false;
    }

    @Override
    public void update() {
    }

    @Override
    public void paint(Graphics g) {

        if(showing){
            showGuide(g);
        }
        else{
            icon.paintIcon(parent, g,(int) x, (int) y);
        }
    }
    public void showGuide(Graphics g) {
        guidaX.paintIcon(parent, g,(int) x, (int) y);
        guide.paintIcon(parent, g, 0, 0);

    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}

