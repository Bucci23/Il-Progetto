package GameObjects;

import Engine.BallPanel;
import FileManagement.AudioPlayer;

import javax.swing.*;
import java.awt.*;

/**
 * Classe Astratta che determina le caratteristiche dei personaggi che compaiono nel gioco
 */
public abstract class Personaggio extends AbstractGameObject {
    boolean onGround = false; //Se il personaggio si trova su un ground o meno
    boolean inWater; //Se si trova in acqua o meno
    int vita; //Numero di punti vita che ha
    ImageIcon icon; //Icona del personaggio da disegnare
    ImageIcon l; //Icona di sinstra
    ImageIcon r; //Icona di destra
    boolean isJumping; //Se sta saltando o meno
    boolean isHittingEnemy; //Se sta colpendo un nemico o meno
    AudioPlayer audioPlayer = new AudioPlayer(); //Per riprodurre degli audio

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, w, h);
    }

    /**
     * Per far subire l'effettoi di gravità ai personaggi, esso differisce se siamo in acqua
     */
    public void gravity() {
        if (!inWater) {
            if (!onGround)
                speedY = speedY + 1;
        } else {
            if (!onGround)
                speedY = speedY + 0.3;
        }

    }

    /**
     * Setta il valore di isJumping
     * @param isJumping valore da assegnare
     */
    public void setJumping(boolean isJumping) {
        this.isJumping = isJumping;
    }

    /**
     * Per far andare il personaggio verso destra
     */
    public void goRight() {
        icon = r;
        if (this.getSpeedX() < 10)
            this.setSpeedX(this.getSpeedX() + 2);
    }
    /**
     * Per far andare il personaggio verso sinistra
     */
    public void goLeft() {
        icon = l;
        if (this.getSpeedX() > -10)
            this.setSpeedX(this.getSpeedX() - 2);
    }
    /**
     * Per far saltare il personaggio
     * @param enemy determina se il salto è causato da un nemico o meno
     */
    public void jump(boolean enemy) {
        if (!this.isJumping || enemy) {
            if (!inWater) {
                this.setSpeedY(-25);
                if(!enemy && this instanceof Ball && ((BallPanel) parent).getParent().isAudio()){
                    audioPlayer.play("audio/Salto.wav");
                }
                this.setJumping(true);
            } else {
                this.setSpeedY(-10);
            }
        }
    }

    /**
     * Aggiorna il valore di isJumping in base ad onGround
     */
    public void jumpUpdate() {
        if (onGround)
            setJumping(false);
    }

    /**
     * Determina le collisioni con i limiti laterali dello schermo (Non dovrebbe mai accadere)
     */
    public void wallCollisions() {
        if (x >= parent.getWidth() - w) {
            wallBounce(parent.getWidth() - w);
        }
        if (x < 0) {
            wallBounce(0);
        }
    }
    /**
     * Determina le collisioni con i limiti (superiore o inferiore) dello schermo (Non dovrebbe mai accadere)
     */
    public void floorCollisions() {
        if (y >= parent.getHeight() - w && !onGround) {
            if (this instanceof Ball)
                floorBounce(parent.getHeight() - h);
        } else
            onGround = false;
    }

    /**
     * Determina le collisioni con i vari GameObjects.GameObject presenti in lgo
     */
    public void isColliding() {
        for (GameObject go : lgo) {
            if (go != this) {
                if (go instanceof Ground){
                    if(go instanceof InvisibleWall)
                        ((InvisibleWall) go).kill(this);
                    groundCollide((Ground) go);
                }
                if (go instanceof Nemico) {
                    enemyCollide((Nemico) go);
                }
                if (go instanceof PowerUp) {
                    powerUpCollide((PowerUp) go);
                }
                if (go instanceof Salvadanaio && this instanceof Ball) {
                    ((Salvadanaio) go).isColliding((Ball) this);
                }
                if(go instanceof Water){
                    ((Water) go).isColliding(this);
                }


            }
        }
    }

    /**
     * Determina cosa succede in caso di collisione con un GameObjects.PowerUp
     * @param c powerUp con cui si collide
     */
    public void powerUpCollide(PowerUp c) {
        if (this.getBounds().intersects(c.getBounds())) {
            powerUpCollect(c);
        }
    }

    public abstract void powerUpCollect(PowerUp c);

    /**
     * Rimbalzo dei personaggi agli angoli dei ground
     * @param g ground con cui si collide
     */
    private void cornerBounce(Ground g) {
        if (this.y < g.getY()) {
            floorBounce(g.getY() - h);
        }
        if (this.y + h > g.getY() + g.getH()) {
            roofBounce(g.getY() + g.getH());
        }

    }

    /**
     * Determina la collisione con i ground
     * @param g
     */
    public void groundCollide(Ground g) {
        if (this.getBounds().intersects(g.getBounds())) {
            if (this.y > g.getY() && this.y + h < (g.getY() + g.getH())) {
                if (this.x < g.getX())
                    wallBounce(g.getX() - w);
                else if (this.x + w > g.getX() + g.getW())
                    wallBounce(g.getX() + g.getW());
            } else {
                if (this.x > g.getX() && this.x + h < g.getX() + g.getW()) {
                    if (this.y < g.getY()) {
                        floorBounce(g.getY() - h);
                    } else {
                        roofBounce(g.getY() + g.getH());
                    }
                } else
                    cornerBounce(g);

            }
        }
    }

    public abstract void enemyCollide(Nemico n);

    /**
     * Rimbalzo dei personaggi sulle superfici piane
     * @param y valore Y della superfice su cui si rimbalza
     */
    public void floorBounce(double y) {
        if (speedY > 0)
            speedY = -speedY / 2;
        if(((BallPanel) parent).getLivello() == 3 && this instanceof Ball){
            speedX = speedX * 9/10;
        }else
        speedX = speedX * 4 / 5;

        this.y = y;
        onGround = true;
    }

    /**
     *      * Rimbalzo dei personaggi sulle superfici piane poste in alto rispetto al personaggio
     *      * @param y valore Y della superfice su cui si rimbalza
     * @param y valore Y della superfice su cui si rimbalza
     */
    public void roofBounce(double y) {
        speedY = -speedY / 2;
        this.y = y;
    }
    /**
     * Rimbalzo dei personaggi sulle superfici verticali
     * @param x valore X della superfice su cui si rimbalza
     */
    public void wallBounce(double x) {
        speedX = -speedX / 2;
        this.x = x;
    }

    /**
     * Setter per la vita
     * @param vita valore da assegnare
     */
    public void setVita(int vita) {
        this.vita = vita;
    }
    /**
     * Getter per la vita
     */
    public int getVita() {
        return vita;
    }


}
