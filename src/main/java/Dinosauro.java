import javax.swing.*;
import java.util.ArrayList;

public class Dinosauro extends Nemico {

    public Dinosauro(JPanel parent, ArrayList<GameObject> lgo, int x, int y, String r, String l) {
        super(parent, lgo, x, y, r, l);
        vita = 3;
        this.w = 60;
        this.h = 100;
        standardSpeedX = -5;

    }


    @Override
    public void ballCollision() {

    }

    @Override
    public void powerUpCollect(PowerUp c) {

    }

    @Override
    public void enemyCollide(Nemico n) {
    }

    @Override
    public void shoot() {

    }
}
