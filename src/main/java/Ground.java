import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Classe che definisce le piattaforme presenti nel gioco.
 */
public class Ground extends AbstractGameObject {
    ImageIcon[] icons; //vettore che contiene i 2 tipi di icone che lo compongono
    int nGrass; //Numero di tiles che compongono la parte superiore
    int nSimple; //numero di tiles che compongono la parte interna

    /**
     * Costruttore che setta i parametri e valori di default
     * @param parent Pannello a cui fa riferimento
     * @param lgo lista di gameObject che la contiene
     * @param x posizione asse X
     * @param y posizione asse Y
     * @param w larghezza
     * @param h altezza
     * @param speedX velocità X
     * @param speedY velocità Y
     * @param pathG nome del file dell'icona superiore
     * @param pathS nome del file dell'icona interna
     */
    public Ground(JPanel parent, ArrayList<GameObject> lgo, int w, int h, int x, int y, double speedX, double speedY, String pathG, String pathS) {
        this.parent = parent;
        this.lgo = lgo;
        this.w = w;
        this.h = h;
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
        icons = new ImageIcon[2];
        icons[0] = new ImageIcon(pathG);
        icons[1] = new ImageIcon(pathS);
        imagecalc();
    }

    /**
     * Aggiorna la posizione sullo schermo
     */
    @Override
    public void update() {
        newPositions();
    }

    /**
     * Calcola i numeri di nGrass e nSimple in base alle dimensioni
     */
    void imagecalc() {
        nGrass = w / 50;
        nSimple = (w * h) / (50 * 50) - nGrass;
    }

    /**
     * Disegna il giusto numero di icone nGrass ed nSimple per dare l'effetto desiderato
     * @param g graphics
     */
    @Override
    public void paint(Graphics g) {
        for (double i = x; i < x + w - 1; i += 50) {
            icons[0].paintIcon(parent, g, (int) i, (int) y);
        }
        for (double i = x; i < x + w - 1; i += 50) {
            for (double j = y + 50; j < y + h; j += 50) {
                icons[1].paintIcon(parent, g, (int) i, (int) j);
            }
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, w, h);
    }
}
