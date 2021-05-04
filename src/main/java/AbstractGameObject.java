import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class AbstractGameObject implements GameObject {
    JPanel parent;
    ArrayList<GameObject> lgo;
    int w, h;
    int x, y;
    int speedX, speedY;

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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public void newPositions() {
        BallPanel par=(BallPanel) parent;
        x = x += speedX + par.sceneSpeedX;
        y = y += speedY + par.sceneSpeedY;
    }


}
