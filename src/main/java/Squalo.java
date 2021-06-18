import javax.swing.*;
import java.util.ArrayList;

public class Squalo extends Nemico{

    public Squalo(JPanel parent, ArrayList<GameObject> lgo, int x, int y, String r, String l) {
        super(parent, lgo, x, y, r, l);
        this.w = 100;
        this.h = 50;
        standardSpeedX = -8;
        vita=3;
        inWater = true;
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
    public void enemyCollide(Dinosauro n) {

    }
}
