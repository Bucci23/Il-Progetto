package GameObjects;

import javax.swing.*;
import java.util.ArrayList;
/**
 * Classe che identifica un nemico standard, essendo in acqua salta più spesso
 */
public class Squalo extends Nemico {

    public Squalo(JPanel parent, ArrayList<GameObject> lgo, int x, int y, String r, String l) {
        super(parent, lgo, x, y, r, l);
        this.w = 200;
        this.h = 100;
        standardSpeedX = -8;
        vita = 3;
        inWater = true;
    }

    @Override
    public void ballCollision() {

    }

    @Override
    public void movimento() {
        speedX = standardSpeedX;
        int jmp = rnd.nextInt(100);
        if (jmp <= 6) {
            if(inWater)
                this.jump(false);
        }
        if (jmp == 11) {
            standardSpeedX = -standardSpeedX;
            if (icon == l) {
                icon = r;
            } else icon = l;
        }
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
