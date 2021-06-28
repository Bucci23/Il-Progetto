package GameObjects;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * GameObjects.Nemico finale, non si muove e spara.
 */
public class Mummia extends Nemico implements ActionListener {
    Timer sTimer;
    public Mummia(JPanel parent, ArrayList<GameObject> lgo, int x, int y, String r, String l) {
        super(parent, lgo, x, y, r, l);
        vita = 30;
        this.w = 400;
        this.h = 400;
        standardSpeedX = 0;
        sTimer = new Timer(2000, this );


    }

    @Override
    public void movimento() {
        speedX = standardSpeedX;
        sTimer.start();
    }
    @Override
    public void ballCollision() {

    }

    @Override
    public void shoot() {
        lgo.add(new Fuoco(parent, lgo, -20, "images/fuoco.png", "images/fuocoSpecchiato.png", (int) x - 31, (int) (y + h -30), true));
        sTimer.stop();
    }

    @Override
    public void powerUpCollect(PowerUp c) {

    }

    @Override
    public void enemyCollide(Nemico n) {

    }

    /**
     * Quando scatta il timer (ogni 2 secondi) la mummia spara.
     * @param e evento da gestire
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == sTimer){
            shoot();
        }
    }
}
