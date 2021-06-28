package GameObjects;

import Engine.BallPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Mostra una immagine di guida sullo schermo
 */
public class Guida extends AbstractGameObject {
    ImageIcon icon; //icona da cliccare per mostrare la guida
    ImageIcon guide; //immagine che contiene la guida
    ImageIcon guidaX; //icona da cliccare per chiudere la guida
    boolean showing; //determina se si sta mostrando o meno la guida

    /**
     * Costruttopre che setta i parametri e i valori di default
     * @param parent pannello a cui fa riferimento
     * @param icon immagine icon
     * @param guide immagine della guida
     * @param guidaX immagine chiusura
     */
    public Guida(BallPanel parent, String icon, String guide, String guidaX) {
        this.parent = parent;
        this.icon = new ImageIcon(icon);
        this.guide = new ImageIcon(guide);
        this.guidaX = new ImageIcon(guidaX);
        w = 100;
        h = 100;
        x = parent.getParent().getWidth() - w;
        y = 40;
        showing = false;
    }

    /**
     * Non si aggiorna, deve stare ferma
     */
    @Override
    public void update() {
    }

    /**
     * Disegna le icone oppure l'intera guida
     * @param g
     */
    @Override
    public void paint(Graphics g) {

        if(showing){
            showGuide(g);
        }
        else{
            icon.paintIcon(parent, g,(int) x, (int) y);
        }
    }

    /**
     * Mostra la guida e l'icona per la chiusura.
     * @param g
     */
    public void showGuide(Graphics g) {
        guidaX.paintIcon(parent, g,(int) x, (int) y);
        guide.paintIcon(parent, g, 0, 0);

    }

    /**
     * La guida non ha bounds
     * @return
     */
    @Override
    public Rectangle getBounds() {
        return null;
    }

    public boolean isShowing() {
        return showing;
    }

    public void setShowing(boolean showing) {
        this.showing = showing;
    }
}

