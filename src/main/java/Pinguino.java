import javax.swing.*;
import java.util.ArrayList;

public class Pinguino extends Nemico{

    public Pinguino(JPanel parent, ArrayList<GameObject> lgo, int x, int y, String r, String l) {
        super(parent, lgo, x, y, r, l);
        vita = 9;
        this.w = 50;
        this.h = 100;
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
