import javax.management.timer.TimerNotification;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serial;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.Random;

public class BallPanel extends JPanel implements KeyListener, ActionListener {
    @Serial
    private static final long serialVersionUID = 1L;
    JFrame parent;
    Timer timer;
    ArrayList<GameObject> lgo;
    int bX;
    int bY;
    Random rnd;
    ImageIcon background;
    boolean scrollingR = false;
    boolean scrollingL = false;
    boolean scrollingU = false;
    boolean scrollingD = false;
    int sceneSpeedX;
    int sceneSpeedY;
    int ground;
    Skills skills;

    public BallPanel() {
        rnd = new Random();
        setBackground(Color.CYAN);
        addKeyListener(this);
        setFocusable(true);
    }

    public void init() {

        lgo = new ArrayList<GameObject>();
        background = new ImageIcon("images/SfondoStrano.png");
        lgo.add(new Ball(this, lgo, 60, 60, getWidth(), getHeight(), 1, 1, Color.BLUE));
        lgo.add(1, new Ground(this, lgo, 300, 400, 200, 1000, 0, 0, "images/groundgrass.png", "images/groundsimple.png"));
        lgo.add(2, new Ground(this, lgo, 600, 400, 1000, 700, 0, 0, "images/groundgrass.png", "images/groundsimple.png"));
        lgo.add(3, new Ground(this, lgo, 600, 200, 1800, 900, 0, 0, "images/groundgrass.png", "images/groundsimple.png"));
        lgo.add(4, new Ground(this, lgo, 300, 400, 2500, 600, 0, 0, "images/groundgrass.png", "images/groundsimple.png"));
        lgo.add(5, new Ground(this, lgo, 300, 400, 3000, 500, 0, 0, "images/groundgrass.png", "images/groundsimple.png"));
        lgo.add(6, new Ground(this, lgo, 300, 400, 200, 400, 0, 0, "images/groundgrass.png", "images/groundsimple.png"));
        lgo.add(7, new Ground(this, lgo, 700, 50, 4000, 900, 0, 0, "images/groundgrass.png", "images/groundsimple.png"));
        lgo.add(new Ground(this, lgo, 300, 100, 1200, 400, 0, 0, "images/groundgrass.png", "images/groundsimple.png"));
        lgo.add(new Ground(this, lgo, 350, 150, 1500, 200, 0, 0, "images/groundgrass.png", "images/groundsimple.png"));
        lgo.add(new Ground(this, lgo, 10000, 100, 0, 1300, 0, 0, "images/groundgrass.png", "images/groundsimple.png"));
        lgo.add(new NemicoLV1(this, lgo, 1100, 500, "images/dinoL.png", "images/dinoR.png"));
        lgo.add(new NemicoLV1(this, lgo, 1500, 500, "images/dinoL.png", "images/dinoR.png"));
        lgo.add(new NemicoLV1(this, lgo, 2000, 900, "images/dinoL.png", "images/dinoR.png"));
        lgo.add(new NemicoLV1(this, lgo, 2500, 500, "images/dinoL.png", "images/dinoR.png"));
        lgo.add(new NemicoLV1(this, lgo, 2800, 500, "images/dinoL.png", "images/dinoR.png"));
        lgo.add(new NemicoLV1(this, lgo, 2900, 500, "images/dinoL.png", "images/dinoR.png"));
        lgo.add(new NemicoLV1(this, lgo, 3000, 500, "images/dinoL.png", "images/dinoR.png"));
        lgo.add(new Coin(this, lgo,700 , 1050, "images/moneta.png"));
        lgo.add(new Coin(this, lgo, 200, 500, "images/moneta.png"));
        lgo.add(new Coin(this, lgo, 500, 900, "images/moneta.png"));
        lgo.add(new Vite(this, lgo, 900, 1100, "images/Pozione.png"));
        lgo.add(new Munizioni(this, lgo, 700, 1100, "images/Munizioni.png"));
        lgo.add(new Vite(this, lgo, 500, 1100, "images/Pozione.png"));





        skills = new Skills((Ball) lgo.get(0), this);
        bX = 0;
        bY = -2000;
        ground = 0;
        sceneSpeedX = 0;
        sceneSpeedY = 0;

        timer = new Timer(20, this);
        timer.start();

    }

    public void moveSceneRight() {
        Ball b = (Ball) lgo.get(0);
        if (b.getSpeedX() + sceneSpeedX < 0) {
            sceneSpeedX = -b.getSpeedX();
        }


    }

    public void moveSceneLeft() {
        Ball b = (Ball) lgo.get(0);
        if (b.getSpeedX() + sceneSpeedX > 0) {
            sceneSpeedX = -b.getSpeedX();

        }
    }

    public void stopSceneX() {
        this.sceneSpeedX = 0;
    }

    public void stopSceneY() {
        this.sceneSpeedY = 0;
    }

    public void leftBound() {
        Ball b = (Ball) lgo.get(0);
        if (b.getX() <= 300 && b.speedX < 0) {
            scrollingL = true;
            moveSceneRight();
        } else if (b.getX() > 300 + b.getW() || b.getSpeedX() > 0)
            scrollingL = false;
        if (!scrollingL && !scrollingR) {
            stopSceneX();
        }
    }

    public void rightBound() {
        Ball b = (Ball) lgo.get(0);
        if (b.getX() + b.getW() >= this.getWidth() - 300 && b.speedX > 0) {
            scrollingR = true;
            moveSceneLeft();
        } else if (b.getX() + b.getW() < this.getWidth() - 300 - b.getW() || b.getSpeedX() < 0)
            scrollingR = false;
        if (!scrollingR && !scrollingL) {
            stopSceneX();
        }
    }

    public void moveSceneUp() {
        Ball b = (Ball) lgo.get(0);
        if (b.getSpeedY() + sceneSpeedY < 0) {
            sceneSpeedY = -b.getSpeedY();

        }
    }

    public void upperBound() {
        Ball b = (Ball) lgo.get(0);
        if (b.getY() < 300 && b.speedY < 0) {
            scrollingU = true;
            moveSceneUp();
        } else if (b.getY() > 300 || b.getSpeedY() > 0)
            scrollingU = false;
        if (!scrollingU && !scrollingD) {
            stopSceneY();
        }
    }

    public void moveSceneDown() {
        Ball b = (Ball) lgo.get(0);
        if (b.getSpeedY() + sceneSpeedY > 0) {
            sceneSpeedY = -b.getSpeedY();
        }
    }

    public void lowerBound() {
        Ball b = (Ball) lgo.get(0);
        groundUpdate();
        if ((b.getY() > this.getHeight() - 300 && b.speedY > 0) && ground < this.getHeight()) {
            scrollingD = true;
            moveSceneDown();
        } else if (b.getY() + b.getH() < this.getHeight() - 300 - b.getH() || b.getSpeedY() < 0 || ground >= this.getHeight())
            scrollingD = false;
        if (!scrollingU && !scrollingD) {
            stopSceneY();
        }
    }

    public void bUpdate() {
        bX += (sceneSpeedX / 3);
        bY += (sceneSpeedY / 6);
    }

    private void shootUpdate() {
        Iterator<GameObject> i = lgo.iterator();
        while (i.hasNext()) {
            GameObject g = i.next();
            if (g instanceof Fuoco) {
                Fuoco f = (Fuoco) g;
                if (!f.existing) {
                    i.remove();
                }
            }
        }
    }

    private void enemyUpdate() {
        Iterator<GameObject> i = lgo.iterator();
        while (i.hasNext()) {
            GameObject g = i.next();
            if (g instanceof Nemico) {
                Nemico n = (Nemico) g;
                if (!n.existing) {
                    i.remove();
                }
            }
        }
    }

    void powerUpUpdate() {
        Iterator<GameObject> i = lgo.iterator();
        while (i.hasNext()) {
            GameObject g = i.next();
            if (g instanceof PowerUp) {
                PowerUp n = (PowerUp) g;
                if (!n.existing) {
                    i.remove();
                }
            }
        }
    }

    public void groundUpdate() {
        ground -= sceneSpeedY;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            rightBound();
            leftBound();
            upperBound();
            lowerBound();
            bUpdate();
            shootUpdate();
            enemyUpdate();
            powerUpUpdate();
            repaint();
        }
    }


    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        background.paintIcon(this, g, bX, bY);
        for (GameObject go : lgo) {
            go.update();
            go.paint(g);
        }
        skills.update();
        skills.paint(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!(lgo.get(0) instanceof Ball)) {
            return;
        }
        Ball mainBall = ((Ball) lgo.get(0));
        if (e.getKeyChar() == 'd' || e.getKeyChar() == 'D') {
            mainBall.goRight();
        }
        if (e.getKeyChar() == 'a' || e.getKeyChar() == 'A') {
            mainBall.goLeft();
        }
        if (e.getKeyChar() == ' ') {
            mainBall.jump(false);
        }
        if (e.getKeyChar() == 'f' || e.getKeyChar() == 'F') {
            mainBall.shoot();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


}
