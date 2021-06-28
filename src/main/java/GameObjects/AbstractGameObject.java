package GameObjects;

import Engine.BallPanel;
import GameObjects.GameObject;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Classe astratta che definisce alcuni metodi e attributi che avranno tutti gli oggetti del gioco
 *
 */
public abstract class AbstractGameObject implements GameObject {
    JPanel parent; //Pannello a cui farà riferimento
    ArrayList<GameObject> lgo; //Lista di gameObject a cui apparterrà
    int w, h; //Larghezza e altezza
    double x, y; //Posizione x e y
    double speedX, speedY; //Velocità x e y

    //Getter e setter
    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getSpeedX() {
        return speedX;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    //Metodo che calcola la posizione che avrà l'oggetto nel frame successivo
    public void newPositions() {
        BallPanel par = (BallPanel) parent;
        x += speedX + par.getSceneSpeedX();
        y += speedY + par.getSceneSpeedY();
    }

    public JPanel getParent(){
        return parent;
    }
}
