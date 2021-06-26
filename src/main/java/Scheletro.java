import javax.swing.*;
import java.util.ArrayList;
/**
 * Classe che identifica un nemico standard
 */
public class Scheletro extends Nemico{
    public Scheletro(JPanel parent, ArrayList<GameObject> lgo, int x, int y, String r, String l) {
        super(parent, lgo, x, y, r, l);
        vita = 3;
        this.w = 50;
        this.h = 100;
        standardSpeedX = -8;
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
