package GameObjects;

import Engine.BallPanel;
import FileManagement.AudioPlayer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Oggetto coin, una moneta collezionabile
 */
public class Coin extends PowerUp {

    AudioPlayer audioPlayer; //per suono alla raccolta

    /**
     * Costruttore che setta i parametri
     * @param parent Pannello a cui fa riferimento
     * @param lgo lista di gameObject che la contiene
     * @param x posizione asse X
     * @param y posizione asse Y
     * @param icon icona della moneta
     */
    public Coin(JPanel parent, ArrayList<GameObject> lgo, int x, int y, String icon) {
        super(parent, lgo, x, y, icon);
        audioPlayer = new AudioPlayer();
    }

    /**
     * Aumenta di 1 le monete della GameObjects.Ball
     * @param ball Oggetto a cui si applica la specialAbility
     */
    @Override
    public void specialAbility(Ball ball) {
        ball.setMonete(ball.getMonete() + 1);
        if(((BallPanel) parent).getParent().isAudio()) {
            audioPlayer.play("audio/coin.wav");
        }

    }

    /**
     * Aggiorna la posizione del GameObjects.Coin sulla scena
     */
    @Override
    public void update() {
        newPositions();
    }

    /**
     *
     * @return Rettangolo che occupa sullo schermo.
     */
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, w, h);
    }

}
