import javax.swing.*;
import java.util.ArrayList;

public abstract class AbstractGameObject implements GameObject {
    JPanel parent;
    ArrayList<GameObject> lgo;
    int w, h;
    double x, y;
    double speedX, speedY;

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

    public void newPositions() {
        BallPanel par = (BallPanel) parent;
        x += speedX + par.sceneSpeedX;
        y += speedY + par.sceneSpeedY;
    }
}
