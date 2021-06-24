import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public abstract class Nemico extends Personaggio {
    boolean existing;
    int standardSpeedX;
    Random rnd = new Random();

    public Nemico(JPanel parent, ArrayList<GameObject> lgo, int x, int y, String r, String l) {

        this.parent = parent;
        this.lgo = lgo;
        this.x = x;
        this.y = y;
        this.l = new ImageIcon(l);
        this.r = new ImageIcon(r);
        icon = this.r;
        this.existing = true;
        inWater = false;

    }

    public void update() {
        gravity();
        jumpUpdate();
        floorCollisions();
        isColliding();
        shootCollision();
        if (Math.abs(this.x - lgo.get(0).getX()) < 500)
            movimento();
        newPositions();
    }

    public void paint(Graphics g) {
        icon.paintIcon(parent, g, (int) x, (int) y);
    }

    public void movimento() {
        speedX = standardSpeedX;
        int jmp = rnd.nextInt(100);
        if (jmp == 1) {
            this.jump(false);
        }
        if (jmp == 2) {
            standardSpeedX = -standardSpeedX;
            if (icon == l) {
                icon = r;
            } else icon = l;
        }
    }

    public abstract void ballCollision();

    public abstract void shoot();

    public void setExisting(boolean existing) {
        this.existing = existing;
    }

    public void shootCollision() {
        for (GameObject go : lgo) {
            if (go != this) {
                if (go instanceof Fuoco) {
                    if (go.getBounds().intersects(this.getBounds())) {
                        vita -= 3;
                        ((Fuoco) go).setExisting(false);
                        if (vita == 0)
                            setExisting(false);

                    }
                }
            }
        }
    }
}

