import javax.swing.*;
import java.awt.*;

/**
 * Semplice immagine che simula l'effetto di un fondale marino
 * Quando un personaggio la percorre, si setta l'attributo inWater a true.
 */
public class Water extends AbstractGameObject{
    ImageIcon wIcon;

    public Water(String wIcon, int x, int y,int w,int h, BallPanel parent) {
        this.wIcon = new ImageIcon(wIcon);
        this.parent = parent;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    @Override

    public void update() {
        newPositions();
    }

    @Override
    public void paint(Graphics g) {
        wIcon.paintIcon(parent,g,(int) x,(int) y);
    }
    public void isColliding(Personaggio p){
        if(getBounds().intersects(p.getBounds())){
            p.inWater = true;
            p.isJumping = false;
        }
        else{
            p.inWater = false;
        }
    }
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int) y, w, h);
    }
}
