import java.awt.*;
import java.util.ArrayList;

public class Skills implements GameObject {
    int x;
    int y;
    Ball mainBall;
    Skills(Ball mainBall){
        this.mainBall = mainBall;
    }

    @Override
    public void update() {

    }

    @Override
    public void paint(Graphics g) {
        int base = 30;
        g.setColor(Color.black);
        g.fillRoundRect(x,y,400,200,50,50);
        g.setColor(Color.RED);
        for(int i=0;i< mainBall.vita;++i){
            g.fillOval(base + 30*i,30,20,20);
        }

    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {

    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {

    }

    @Override
    public int getSpeedX() {
        return 0;
    }

    @Override
    public void setSpeedX(int sx) {

    }

    @Override
    public int getSpeedY() {
        return 0;
    }

    @Override
    public void setSpeedY(int sy) {

    }
}
