import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Vite extends PowerUp {
    public Vite(JPanel parent, ArrayList<GameObject> lgo, int x, int y, String icon) {
        super(parent, lgo, x, y, icon);
        w = 40;
        h = 80;
    }

    @Override
    public void update() {
        newPositions();
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int) y, w, h);
    }

    @Override
    public void specialAbility(Ball ball) {
        ball.setVita(10);
    }
}
