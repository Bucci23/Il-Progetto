import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.*;

public class Ball extends Personaggio implements ActionListener {
    Color color;
    boolean isShooting;
    Timer shootDelay;
    Timer hitDelay;
    int munizioni;
    int monete;

    public Ball(JPanel parent, ArrayList<GameObject> lgo, int w, int h, int x, int y, int speedX, int speedY, Color color) {
        this.parent = parent;
        this.lgo = lgo;
        this.w = w;
        this.h = h;
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
        this.color = color;
        this.isJumping = false;
        this.onGround = false;
        this.r = new ImageIcon("images/mostro.png");
        this.l = new ImageIcon("images/mostroSpecchiato.png");
        icon = r;
        this.vita = 10;
        isShooting = false;
        shootDelay = new Timer(500, this);
        munizioni = 10;
        isHittingEnemy = false;
        hitDelay = new Timer(1000, this);
        monete = 0;
    }

    void shoot() {
        if (!isShooting && munizioni > 0) {
            isShooting = true;
            shootDelay.start();
            munizioni--;
            if (icon == r)
                lgo.add(new Fuoco(parent, lgo, 30, "images/fuoco.png", "images/fuocoSpecchiato.png", x + w, y + h / 3));
            if (icon == l)
                lgo.add(new Fuoco(parent, lgo, -30, "images/fuoco.png", "images/fuocoSpecchiato.png", x - w, y + h / 3));
        }
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
    public void powerUpCollect(PowerUp c) {
        c.specialAbility(this);
        c.setExisting(false);

    }

    @Override
    public void enemyCollide(NemicoLV1 n) {
        if (this.getBounds().intersects(n.getBounds())) {
            if (!isHittingEnemy) {
                this.vita--;
                this.jump(true);
                isHittingEnemy = true;
                hitDelay.start();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        icon.paintIcon(parent, g, x, y);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == shootDelay) {
            isShooting = false;
            shootDelay.stop();
        }
        if (e.getSource() == hitDelay) {
            isHittingEnemy = false;
            hitDelay.stop();
        }
    }

    public int getMunizioni() {
        return munizioni;
    }

    public void setMunizioni(int munizioni) {
        this.munizioni = munizioni;
    }

    public int getMonete() {
        return monete;
    }

    public void setMonete(int monete) {
        this.monete = monete;
    }
}
