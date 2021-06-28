package GameObjects;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Speciale ground invisibile che uccide la GameObjects.Ball al tocco. Usato come limite inferiore dei livelli
 */
public class InvisibleWall extends Ground{

    boolean kills; //Determina se deve uccidere la GameObjects.Ball o meno
    /**
     * Costruttore che setta i parametri
     */
    public InvisibleWall(JPanel parent, ArrayList<GameObject> lgo, int w, int h, int x, int y, double speedX, double speedY, String pathG, String pathS, boolean kills) {
        super(parent, lgo, w, h, x, y, speedX, speedY, pathG, pathS);
        this.kills = kills;

    }

    /**
     * Setta la vita della GameObjects.Ball che lo tocca a 0.
     * @param ball GameObjects.Ball che lo tocca
     */
    void kill(Personaggio ball){
        if(getBounds().intersects(ball.getBounds()) && kills){
            ball.setVita(0);
        }
    }

    /**
     * Non disegna niente
     * @param g graphics
     */
    @Override
    public void paint(Graphics g) {

    }
}
