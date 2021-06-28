package GameObjects;

import Engine.BallPanel;
import FileManagement.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Oggetto GameObjects.Fuoco, che viene generato quando la GameObjects.Ball o un nemico spara
 */
public class Fuoco extends AbstractGameObject {
    int standardSpeed; //velocità del fuoco
    ImageIcon r; //Immagine verso destra
    ImageIcon l; //Immagine verso sinistra
    ImageIcon icon; //icona del fuoco
    boolean existing; //Determina l'esistenza o meno
    boolean nemico; //Determina se il fuoco è sparato dalla GameObjects.Ball o da un nemico
    AudioPlayer audioPlayer; //Per riprodurre audio ad ogni colpo

    /**
     * Costruttore che setta i parametri e valori di default.
     * @param parent Pannello a cui fa riferimento
     * @param lgo lista di gameObject che la contiene
     * @param x posizione asse X
     * @param y posizione asse Y
     * @param r icona di destra
     * @param l icona di sinistra
     * @param nemico se spara un nemico o no
     */
    public Fuoco(JPanel parent, ArrayList<GameObject> lgo, int standardSpeed, String r, String l, int x, int y, boolean nemico) {
        this.parent = parent;
        this.lgo = lgo;
        this.standardSpeed = standardSpeed;
        this.r = new ImageIcon(r);
        this.l = new ImageIcon(l);
        this.x = x;
        this.y = y;
        w = 30;
        h = 20;
        this.existing = true;
        this.nemico = nemico;
        audioPlayer = new AudioPlayer();
    }

    /**
     * Aggiorna lo stato del fuoco per il frame successivo
     */
    @Override
    public void update() {
        this.speedX = standardSpeed;
        this.speedY = 0;
        isHitting();
        isOnScreen();
        newPositions();

    }

    /**
     * Disegna l'icona o di destra o di sinistra sullo schermo
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        if (speedX > 0) {
            this.icon = r;
        } else
            this.icon = l;
        icon.paintIcon(parent, g, (int) x, (int) y);

    }
    /**
     * Se il fuoco esce di scena lo rende inesistente
     */
    void isOnScreen() {
        if (this.x > parent.getWidth() || this.x < 0) {
            this.setExisting(false);
        }
    }

    public boolean isExisting() {
        return existing;
    }

    /**
     * Determina se il fuoco sta colpendo un altro gameObject.
     */
    public void isHitting() {
        for (GameObject go : lgo) {
            if ((go != this && !(go instanceof Ball)) && ((!(go instanceof PowerUp) && (!(go instanceof Nemico)))) && !(go instanceof Water)) {
                if (this.getBounds().intersects(go.getBounds()))
                    setExisting(false);
            }
            if(go instanceof Ball && nemico){
                if (this.getBounds().intersects(go.getBounds())) {
                    setExisting(false);
                    ((Ball) go).setVita(((Ball) go).getVita() - 1);
                    if(((BallPanel) parent).getParent().isAudio()) {
                        audioPlayer.play("audio/hit.wav");
                    }
                }
            }
        }
    }

    /**
     * Setter per l'attributo Existing
     * @param existing valore di existing
     */
    public void setExisting(boolean existing) {
        this.existing = existing;
    }


    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, w, h);
    }
}
