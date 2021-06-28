package GameObjects;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Oggetto cassa di munizioni, alla raccolta aumenta di 5 le munizioni disponibili alla GameObjects.Ball
 */
public class Munizioni extends PowerUp {
    /**
     *Costruttore che setta i parametri e i valori di default
     */
    public Munizioni(JPanel parent, ArrayList<GameObject> lgo, int x, int y, String icon) {
        super(parent, lgo, x, y, icon);
        this.w = 50;
        this.h = 40;
    }

    /**
     * Alla raccolta, aumenta di 5 le munizioni della GameObjects.Ball
     * @param ball GameObjects.Ball che ha raccolto l'oggetto
     */
    @Override
    public void specialAbility(Ball ball) {
        ball.setMunizioni(ball.getMunizioni() + 5);
    }

    /**
     * Aggiorna la posizione
     */
    @Override
    public void update() {
        newPositions();
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, w, h);
    }

}
