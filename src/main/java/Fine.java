import javax.swing.*;
import java.awt.*;

public class Fine extends AbstractGameObject{
    ImageIcon icon;
    public Fine(BallPanel parent, String icon){
        this.icon = new ImageIcon(icon);
        this.parent = parent;
        x =0;
        y = 0;
    }
    @Override
    public void update() {

    }

    @Override
    public void paint(Graphics g) {
        icon.paintIcon(parent, g, (int) x, (int) y);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
