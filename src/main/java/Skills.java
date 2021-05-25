import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Skills implements GameObject {
    int x;
    int y;
    JPanel parent;
    Ball mainBall;
    ImageIcon ammoIcon;
    ImageIcon heartIcon;
    Skills(Ball mainBall, JPanel parent){
        this.mainBall = mainBall;
        ammoIcon = new ImageIcon("images/Fuoco.png");
        this.heartIcon = new ImageIcon("images/Cuore.png");
        this.parent = parent;
    }

    @Override
    public void update() {

    }

    @Override
    public void paint(Graphics g) {
        int base = 30;
        //g.setColor(Color.black);
        //g.fillRoundRect(x,y,400,200,50,50);
        g.setColor(Color.RED);
        for(int i=0;i< mainBall.vita;++i){
            heartIcon.paintIcon(parent,g,base + 30 * i, 30);
        }
        g.setFont(new Font("Arial", Font.ITALIC, 30));
        ammoIcon.paintIcon(parent, g, 50, 80);
        g.setColor(Color.WHITE);
        g.drawString(": "+ mainBall.munizioni,90,100);

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
