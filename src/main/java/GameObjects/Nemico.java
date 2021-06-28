package GameObjects;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Classe astratta che definisce le caratteristiche dei nemici
 */
public abstract class Nemico extends Personaggio {
    boolean existing; //Determina se il nemico esiste o meno
    int standardSpeedX; //Velocità standard del nemico
    Random rnd = new Random(); //Variabile casuale per determinare le azioni del nemico

    /**
     * Costruttore che setta i parametri e i valori di default
     */
    public Nemico(JPanel parent, ArrayList<GameObject> lgo, int x, int y, String r, String l) {

        this.parent = parent;
        this.lgo = lgo;
        this.x = x;
        this.y = y;
        this.l = new ImageIcon(l);
        this.r = new ImageIcon(r);
        icon = this.r;
        this.existing = true;
        inWater = false;

    }

    /**
     * Aggiorna lo stato del nemico e, ne fa eseguire le azioni se si trova in prossimità della GameObjects.Ball
     */
    public void update() {
        gravity();
        jumpUpdate();
        floorCollisions();
        isColliding();
        shootCollision();
        if (Math.abs(this.x - lgo.get(0).getX()) < 500)
            movimento();
        newPositions();
    }

    /**
     * Disegna l'icona del nemico
     * @param g
     */
    public void paint(Graphics g) {
        icon.paintIcon(parent, g, (int) x, (int) y);
    }

    /**
     * Determina casualmente i movimenti del nemico
     */
    public void movimento() {
        speedX = standardSpeedX;
        int jmp = rnd.nextInt(100);
        if (jmp == 1) {
            this.jump(false);
        }
        if (jmp == 2) {
            standardSpeedX = -standardSpeedX;
            if (icon == l) {
                icon = r;
            } else icon = l;
        }
    }

    public abstract void ballCollision();

    public abstract void shoot();

    public void setExisting(boolean existing) {
        this.existing = existing;
    }

    /**
     * Quando il nemico è colpito dal fuoco perde 3 punti vita e se la vita = 0 setta existing a false.
     */
    public void shootCollision() {
        for (GameObject go : lgo) {
            if (go != this) {
                if (go instanceof Fuoco) {
                    if (go.getBounds().intersects(this.getBounds())) {
                        vita -= 3;
                        ((Fuoco) go).setExisting(false);
                        if (vita == 0)
                            setExisting(false);

                    }
                }
            }
        }
    }

    public boolean isExisting() {
        return existing;
    }
}

