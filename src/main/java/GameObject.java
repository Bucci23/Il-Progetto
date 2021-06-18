import java.awt.*;

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
