import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class NemicoLV1 extends Nemico {
    Random rnd = new Random();
    int standardSpeedX;
    public NemicoLV1(JPanel parent, ArrayList<GameObject> lgo, int x, int y, String r, String l) {
        vita = 3;
        this.parent = parent;
        this.lgo = lgo;
        this.x = x;
        this.y = y;
        this.w = 60;
        this.h = 100;
        this.l = new ImageIcon(l);
        this.r = new ImageIcon(r);
        icon = this.r;
        standardSpeedX = -5;

    }

    @Override
    public void update() {
        gravity();
        jumpUpdate();
        floorCollisions();
        isColliding();
        if(Math.abs(this.x-lgo.get(0).getX()) < 800)
            movimento();
        newPositions();
    }

    @Override
    public void paint(Graphics g) {
        icon.paintIcon(parent, g, x, y);
    }

    @Override
    public void ballCollision() {

    }

    @Override
    public void movimento() {
        speedX=standardSpeedX;
        int jmp = rnd.nextInt(100);
        if (jmp == 1) {
            this.jump(false);
        }
        if(jmp==2){
            standardSpeedX = -standardSpeedX;
        }
    }

    @Override
    public void shoot() {

    }
}
