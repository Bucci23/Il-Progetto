import javax.swing.*;
import java.awt.*;

public abstract class Personaggio extends AbstractGameObject {
    boolean onGround = false;
    boolean inWater;
    int vita;
    ImageIcon icon;
    ImageIcon l;
    ImageIcon r;
    boolean isJumping;
    boolean isHittingEnemy;

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, w, h);
    }

    public void gravity() {
        if (!inWater) {
            if (!onGround)
                speedY = speedY + 1;
        } else {
            if (!onGround)
                speedY = speedY + 0.3;
        }

    }

    public void setJumping(boolean isJumping) {
        this.isJumping = isJumping;
    }

    public void goRight() {
        icon = r;
        if (this.getSpeedX() < 10)
            this.setSpeedX(this.getSpeedX() + 2);
    }

    public void goLeft() {
        icon = l;
        if (this.getSpeedX() > -10)
            this.setSpeedX(this.getSpeedX() - 2);
    }

    public void jump(boolean enemy) {
        if (!this.isJumping || enemy) {
            if (!inWater) {
                this.setSpeedY(-25);
                this.setJumping(true);
            } else {
                this.setSpeedY(-10);
            }
        }
    }

    public void jumpUpdate() {
        if (onGround)
            setJumping(false);
    }

    public void wallCollisions() {
        if (x >= parent.getWidth() - w) {
            wallBounce(parent.getWidth() - w);
        }
        if (x < 0) {
            wallBounce(0);
        }
    }

    public void floorCollisions() {
        if (y >= parent.getHeight() - w && !onGround) {
            if (this instanceof Ball)
                floorBounce(parent.getHeight() - h);
        } else
            onGround = false;
    }


    public void isColliding() {
        for (GameObject go : lgo) {
            if (go != this) {
                if (go instanceof Ground){
                    if(go instanceof InvisibleWall)
                        ((InvisibleWall) go).kill(this);
                    groundCollide((Ground) go);
                }
                if (go instanceof Nemico) {
                    enemyCollide((Nemico) go);
                }
                if (go instanceof PowerUp) {
                    powerUpCollide((PowerUp) go);
                }
                if (go instanceof Salvadanaio && this instanceof Ball) {
                    ((Salvadanaio) go).isColliding((Ball) this);
                }
                if(go instanceof Water){
                    ((Water) go).isColliding(this);
                }


            }
        }
    }

    public void powerUpCollide(PowerUp c) {
        if (this.getBounds().intersects(c.getBounds())) {
            powerUpCollect(c);
        }
    }

    public abstract void powerUpCollect(PowerUp c);

    private void cornerBounce(Ground g) {
        if (this.y < g.getY()) {
            floorBounce(g.getY() - h);
        }
        if (this.y + h > g.getY() + g.getH()) {
            roofBounce(g.getY() + g.getH());
        }

    }

    public void groundCollide(Ground g) {
        if (this.getBounds().intersects(g.getBounds())) {
            if (this.y > g.getY() && this.y + h < (g.getY() + g.getH())) {
                if (this.x < g.getX())
                    wallBounce(g.getX() - w);
                else if (this.x + w > g.getX() + g.getW())
                    wallBounce(g.getX() + g.getW());
            } else {
                if (this.x > g.getX() && this.x + h < g.getX() + g.getW()) {
                    if (this.y < g.getY()) {
                        floorBounce(g.getY() - h);
                    } else {
                        roofBounce(g.getY() + g.getH());
                    }
                } else
                    cornerBounce(g);

            }
        }
    }

    public abstract void enemyCollide(Nemico n);

    public void floorBounce(double y) {
        if (speedY > 0)
            speedY = -speedY / 2;
        if(((BallPanel) parent).livello == 3 && this instanceof Ball){
            speedX = speedX * 9/10;
        }else
        speedX = speedX * 4 / 5;

        this.y = y;
        onGround = true;
    }

    public void roofBounce(double y) {
        speedY = -speedY / 2;
        this.y = y;
    }

    public void wallBounce(double x) {
        speedX = -speedX / 2;
        this.x = x;
    }

    public void setVita(int vita) {
        this.vita = vita;
    }

    public int getVita() {
        return vita;
    }


}
