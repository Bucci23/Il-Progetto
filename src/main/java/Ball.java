import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Classe Ball, rappresenta il personaggio protagonista, controllato dal giocatore.
 *
 */
public class Ball extends Personaggio implements ActionListener {
    Color color;
    boolean isShooting; //Determina se può sparare o no in un determinato momento
    Timer shootDelay;  //Timer per settare un delay di 0.5 secondi dopo uno sparo
    Timer hitDelay;  //Timer per dare invincibilità per 1 secondo dopo che il personaggio è stato colpito
    int munizioni;  // Numero di munizioni disponibili
    int monete;    //Numero di monete raccolte

    /**
     * Costruttore: setta i valori passati per parametro, inserisce valori di default e istanzia i timer

     * @param parent Pannello a cui fa riferimento
     * @param lgo lista di game object in cui sarà inserito
     * @param w larghezza del personaggio
     * @param h altezza del personaggio
     * @param x posizione asse X
     * @param y posizione asse Y
     * @param speedX velocità asse X
     * @param speedY velocità asse Y
     * @param color colore
     */
    public Ball(JPanel parent, ArrayList<GameObject> lgo, int w, int h, int x, int y, int speedX, int speedY, Color color) {
        this.parent = parent;
        this.lgo = lgo;
        this.w = w;
        this.h = h;
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
        this.color = color;
        this.isJumping = false;
        this.onGround = false;
        this.r = new ImageIcon("images/mostro.png");
        this.l = new ImageIcon("images/mostroSpecchiato.png");
        icon = r;
        this.vita = 5;
        isShooting = false;
        shootDelay = new Timer(500, this);
        munizioni = 5;
        isHittingEnemy = false;
        hitDelay = new Timer(1000, this);
        monete = 0;
        inWater = false;
    }

    /**
     * Metodo per sparare
     * Controllo della variabile isShooting e del numero di munizioni.
     * Quindi, a seconda dell'orientamento del personaggio, genera un oggetto Fuoco e lo aggiunge a lgo.
     */
    void shoot() {
        if (!isShooting && munizioni > 0) {
            isShooting = true;
            shootDelay.start();
            munizioni--;
            if (icon == r)
                lgo.add(new Fuoco(parent, lgo, 30, "images/fuoco.png", "images/fuocoSpecchiato.png", (int)(x + w), (int) (y + h / 3), false));
            if (icon == l)
                lgo.add(new Fuoco(parent, lgo, -30, "images/fuoco.png", "images/fuocoSpecchiato.png", (int) x - w, (int) (y + h / 3), false));
        }
    }

    /**
     * Aggiorna lo stato del personaggio per il frame successivo
     */
    @Override
    public void update() {
        gravity();
        jumpUpdate();
        wallCollisions();
        floorCollisions();
        isColliding();
        newPositions();

    }

    /**
     * Fa eseguire l'abilità speciale al PowerUp e lo fa scomparire dalla scena
     *
     * @param c PowerUp che è stato raccolto
     */
    @Override
    public void powerUpCollect(PowerUp c) {
        c.specialAbility(this);
        c.setExisting(false);

    }

    /**
     * Si controlla che il nemico e il personaggio si stiano toccando, cioè che i rettangoli determinati dalla funzione getBounds()
     * si intersechino.
     * Quindi si controlla il valore determinato dal timer di invincibilità hitDelay tramite la variabile isHittingEnemy
     * Quindi si toglie una vita al personaggio e si riproduce il suono corrispondente
     *
     * @param n Nemico con cui controllare la collisione
     */
    @Override
    public void enemyCollide(Nemico n) {
        if (this.getBounds().intersects(n.getBounds())) {
            if (!isHittingEnemy) {
                if(((BallPanel) parent).parent.audio) {
                    audioPlayer.play("audio/hit.wav");
                }
                this.vita--;
                this.jump(true);
                isHittingEnemy = true;
                hitDelay.start();
            }
        }
    }

    /**
     * Stampa l'imageIcon del personaggio
     * @param g graphics
     */
    @Override
    public void paint(Graphics g) {
        icon.paintIcon(parent, g,(int) x,(int) y);
    }

    /**
     * Aggiorna le variabili legate ai Timer per lo sparo e per i colpi
     * @param e Evento da gestire
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == shootDelay) {
            isShooting = false;
            shootDelay.stop();
        }
        if (e.getSource() == hitDelay) {
            isHittingEnemy = false;
            hitDelay.stop();
        }
    }
    //alcuni getter e setter
    public int getMunizioni() {
        return munizioni;
    }

    public void setMunizioni(int munizioni) {
        this.munizioni = munizioni;
    }

    public int getMonete() {
        return monete;
    }

    public void setMonete(int monete) {
        this.monete = monete;
    }
}
