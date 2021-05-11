import javax.swing.*;
import java.awt.*;

public abstract class Personaggio extends AbstractGameObject{
    boolean onGround=false;
    int vita;
    ImageIcon icon;
    ImageIcon l;
    ImageIcon r;
    boolean isJumping;
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, w, h);
    }
    public void gravity() {
        if (!onGround)
            speedY = speedY + 1;

    }
    public void setJumping(boolean isJumping) {
        this.isJumping = isJumping;
    }

    public void goRight() {
        icon=r;
        if (this.getSpeedX() < 20)
            this.setSpeedX(this.getSpeedX() + 2);
    }

    public void goLeft() {
        icon=l;
        if (this.getSpeedX() > -20)
            this.setSpeedX(this.getSpeedX() - 2);
    }

    public void jump(boolean enemy) {
        if (!this.isJumping || enemy) {
            this.setSpeedY(-30);
            this.setJumping(true);
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
            floorBounce(parent.getHeight() - w);
        } else
            onGround = false;
    }


    public void isColliding() {
        for (GameObject go : lgo) {
            if (go != this) {
                if (go instanceof Ground)
                    groundCollide((Ground) go);
                if(go instanceof NemicoLV1){
                    enemyCollide((NemicoLV1) go);
                }

            }
        }
    }

    private void cornerBounce(Ground g) {
        if(this.y<g.getY()){
            floorBounce(g.getY()-h);
        }
        if(this.y + h>g.getY()+g.getH()){
            roofBounce(g.getY()+g.getH());
        }

    }
    public void groundCollide(Ground g){
        if (this.getBounds().intersects(g.getBounds())) {
            if (this.y > g.getY() && this.y + w < (g.getY() + g.getH())) {
                if (this.x < g.getX())
                    wallBounce(g.getX() - w);
                else if (this.x + w > g.getX() + g.getW())
                    wallBounce(g.getX() + g.getW());
            } else {
                if (this.x > g.getX() && this.x + w < g.getX() + g.getW()) {
                    if (this.y < g.getY()) {
                        floorBounce(g.getY() - w);
                    } else {
                        roofBounce(g.getY() + g.getH());
                    }
                } else
                    cornerBounce(g);

            }
        }
    }
    public void enemyCollide(NemicoLV1 n){
        if(this.getBounds().intersects(n.getBounds())){
            this.vita--;
            this.jump(true);
        }
    }

    public void floorBounce(int y) {
        if (speedY > 0)
            speedY = -speedY / 2;
        speedX = speedX * 4 / 5;
        this.y = y;
        onGround = true;
    }

    public void roofBounce(int y) {
        speedY = -speedY / 2;
        this.y = y;
    }

    public void wallBounce(int x) {
        speedX = -speedX;
        this.x = x;
    }
}
