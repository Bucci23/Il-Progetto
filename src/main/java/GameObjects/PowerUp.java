package GameObjects;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Classe astratta che definisce le caratteristiche di un powerUp
 */
public abstract class PowerUp extends AbstractGameObject {
    ImageIcon icon; //icona del powerUp
    Boolean existing;//Setta la sua esistenza
    /**
     * Costruttore che setta i parametri e i valori di default
     */
    public PowerUp(JPanel parent, ArrayList<GameObject> lgo, int x, int y, String icon) {
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.lgo = lgo;
        this.icon = new ImageIcon(icon);
        w = 30;
        h = 40;
        existing = true;
    }

    public Boolean getExisting() {
        return existing;
    }

    /**
     * Disegna l'icona sulla scena
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        icon.paintIcon(parent, g, (int)x,(int) y);
    }

    /**
     * Setter per l'attributo existing
     * @param existing valore da assegnare
     */
    public void setExisting(boolean existing) {
        this.existing = existing;
    }

    /**
     * Aggiorna l'esistenza del powerUp
     */
    public void existenceUpdate() {
        if (!existing) {
            lgo.remove(this);
        }
    }

    public abstract void specialAbility(Ball ball);
}
