import javax.swing.*;
import java.util.ArrayList;

public class Granchio extends Nemico {

    public Granchio(JPanel parent, ArrayList<GameObject> lgo, int x, int y, String r, String l) {
        super(parent, lgo, x, y, r, l);
        vita = 6;
        this.w = 60;
        this.h = 50;
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
