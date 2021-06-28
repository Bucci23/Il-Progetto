package GameObjects;

import java.awt.*;

/**
 * Interfaccia che definisce i metodi che devono avere gli oggetti di gioco
 */
public interface GameObject {
    void update();
    void paint(Graphics g);
    Rectangle getBounds();
    double getX();
    void setX(double x);
    double getY();
    void setY(double y);
    double getSpeedX();
    void setSpeedX(double sx);
    double getSpeedY();
    void setSpeedY(double sy);
}
