import javax.swing.*;
import java.util.ArrayList;

public class Mummia extends Nemico{
    public Mummia(JPanel parent, ArrayList<GameObject> lgo, int x, int y, String r, String l) {
        super(parent, lgo, x, y, r, l);
        vita = 30;
        this.w = 400;
        this.h = 400;
        standardSpeedX = 0;
    }

    @Override
    public void ballCollision() {

    }

    @Override
    public void shoot() {

    }

    @Override
    public void powerUpCollect(PowerUp c) {

    }

    @Override
    public void enemyCollide(Nemico n) {

    }
}
