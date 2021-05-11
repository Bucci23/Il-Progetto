import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class NemicoLV1 extends Nemico {

    public NemicoLV1(JPanel parent, ArrayList<GameObject> lgo, int x, int y,String r,String l) {
        vita=3;
        this.parent = parent;
        this.lgo = lgo;
        this.x=x;
        this.y=y;
        this.w = 60;
        this.h = 100;
        this.l = new ImageIcon(l);
        this.r = new ImageIcon(r);
        icon = this.r;
    }

    @Override
    public void update() {
        gravity();
        jumpUpdate();
        wallCollisions();
        floorCollisions();
        isColliding();
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

    }

    @Override
    public void shoot() {

    }
}
