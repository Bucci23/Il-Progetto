import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Coin extends PowerUp {

    public Coin(JPanel parent, ArrayList<GameObject> lgo, int x, int y, String icon) {
        super(parent, lgo, x, y, icon);
    }

    @Override
    public void specialAbility(Ball ball) {
        ball.setMonete(ball.getMonete() + 1);

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
