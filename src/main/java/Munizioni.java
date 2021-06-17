import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Munizioni extends PowerUp {

    public Munizioni(JPanel parent, ArrayList<GameObject> lgo, int x, int y, String icon) {
        super(parent, lgo, x, y, icon);
        this.w = 50;
        this.h = 40;
    }

    @Override
    public void specialAbility(Ball ball) {
        ball.setMunizioni(ball.getMunizioni() + 5);
    }

    @Override
    public void update() {
        newPositions();
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, w, h);
    }

}
