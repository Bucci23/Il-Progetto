package GameObjects;

import Engine.BallPanel;
import FileManagement.DataBase;

import javax.swing.*;
import java.awt.*;

/**
 * Classe che identifica l'oggetto che fa concludere il livello
 */
public class Salvadanaio extends AbstractGameObject {
    ImageIcon icon; //Icona del salvadanaio
    ImageIcon vignetta; //Vignetta con messaggio mostrata se si avvicina la GameObjects.Ball
    ImageIcon vignettaSuperato; //Vignetta livello superato
    int livello;//Livello in cui compare
    int moneteRichieste; //Monete richieste per superarlo
    boolean lvSuperato; //lv superato o meno
    boolean showAnimation; //Mostrare vignette o meno

    DataBase save; //Per salvare in caso di superamento
    BallPanel ballPanel; //Pannello a cui fa riferimento

    /**
     * Costruttore che setta i parametri e i valori di default, setta l'icona in base al livello
     */
    public Salvadanaio(BallPanel ballPanel, double x, double y, int livello, String vignetta, String vignettaSuperato) {
        this.ballPanel = ballPanel;
        parent = ballPanel;
        this.x = x;
        this.y = y;
        w = 200;
        h = 200;
        this.livello = livello;
        switch (livello) {
            case 1 -> {
                moneteRichieste = 3;
                icon = new ImageIcon("images/Salvadanaio1.png");
            }
            case 2 -> {
                moneteRichieste = 5;
                icon = new ImageIcon("images/Salvadanaio2.png");
            }
            case 3 -> {
                moneteRichieste = 7;
                icon = new ImageIcon("images/Salvadanaio3.png");
            }
            case 4 -> {
                moneteRichieste = 10;
                icon = new ImageIcon("images/Salvadanaio4.png");
            }
            case 5 -> {
                moneteRichieste = 1;
                icon = new ImageIcon("images/Salvadanaio5.png");
            }
        }
        lvSuperato = false;
        showAnimation = false;
        this.vignetta = new ImageIcon(vignetta);
        this.vignettaSuperato = new ImageIcon(vignettaSuperato);
        save = new DataBase();
    }

    /**
     * Aggiorna la posizione
     */
    @Override
    public void update() {
        newPositions();
    }

    /**
     * Disegna l'icona ed eventualmente le vignette
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        icon.paintIcon(parent, g, (int) x, (int) y);
        if(showAnimation){
            vignetta.paintIcon(parent, g, (int) x - 150, (int) y - 200);
        }
        if(lvSuperato){
            vignettaSuperato.paintIcon(parent, g,(int) x - 150, (int) y - 200);
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, w, h);
    }

    /**
     * Determina la collisione con la GameObjects.Ball e che vignetta mostrare
     * @param ball
     */
    public void isColliding(Ball ball) {
        if (this.getBounds().intersects(ball.getBounds())) {
            if (ball.monete >= moneteRichieste) {
                lvSuperato = true;
                ballPanel.setLvSuperato(true);
                saveGame();
            } else
                showAnimation = true;
            lvSuperato = false;
        } else
            showAnimation = false;
    }

    public boolean isLvSuperato() {
        return lvSuperato;
    }

    /**
     * Salva il gioco sul DB
     */
    public void saveGame(){
        save.save(livello + 1);
    }
}
