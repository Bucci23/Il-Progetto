import javax.swing.*;
import java.util.ArrayList;

public class Gorilla extends Nemico{
    public Gorilla(JPanel parent, ArrayList<GameObject> lgo, int x, int y, String r, String l) {
        super(parent, lgo, x, y, r, l);
        vita = 12;
        this.w = 300;
        this.h = 300;
        standardSpeedX = -5;
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
