import javax.management.timer.TimerNotification;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import java.util.Random;

public class BallPanel extends JPanel implements KeyListener, ActionListener {
    private static final long serialVersionUID = 1L;
    JFrame parent;
    Timer timer;
    ArrayList<GameObject> lgo;
    Random rnd;
    boolean scrollingR = false;
    boolean scrollingL = false;
    boolean scrollingU = false;
    boolean scrollingD = false;
    int sceneSpeedX;
    int sceneSpeedY;
    int ground;


    public BallPanel() {
        rnd = new Random();
        setBackground(Color.CYAN);
        addKeyListener(this);
        setFocusable(true);
    }

    public void init() {

        lgo = new ArrayList<GameObject>();
        lgo.add(new Ball(this, lgo, 60, 60, getWidth(), getHeight(), 1, 1, Color.BLUE));
        lgo.add(1, new Ground(this, lgo, 300, 400, 200, 600, 0, 0, Color.BLACK));
        lgo.add(2, new Ground(this, lgo, 300, 400, 1000, 300, 0, 0, Color.RED));
        lgo.add(3, new Ground(this, lgo, 300, 400, 1800, 500, 0, 0, Color.RED));
        lgo.add(4, new Ground(this, lgo, 300, 400, 2500, 200, 0, 0, Color.RED));
        lgo.add(5, new Ground(this, lgo, 300, 400, 3000, 100, 0, 0, Color.RED));
        lgo.add(6, new Ground(this, lgo, 300, 400, 200, 0, 0, 0, Color.BLACK));

        ground=900;
        sceneSpeedX = 0;
        sceneSpeedY =0;
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
        System.out.println(ground);
        if (b.getY() < 300 && b.speedY < 0) {
            System.out.println("scrollo in su!");
            scrollingU = true;
            moveSceneUp();
        } else if (b.getY() > 300 || b.getSpeedY() > 0)
            scrollingU = false;
        if (!scrollingU && !scrollingD) {
            stopSceneY();
        }
    }
    public void moveSceneDown(){
        Ball b = (Ball) lgo.get(0);
        if (b.getSpeedY() + sceneSpeedY > 0) {
            sceneSpeedY = -b.getSpeedY();

        }
    }
    public void lowerBound(){
        Ball b = (Ball) lgo.get(0);
        groundUpdate();
        if ((b.getY() > this.getHeight() - 300 && b.speedY > 0) && ground < this.getHeight()) {
            System.out.println("scrollo in giu!");
            scrollingD = true;
            moveSceneDown();
        } else if (b.getY() + b.getH() < this.getHeight() - 300 - b.getH() || b.getSpeedY() < 0 ||  ground >= this.getHeight())
            scrollingD = false;
        if (!scrollingU && !scrollingD) {
            stopSceneY();
        }
    }

    public void groundUpdate(){
        ground-=sceneSpeedY;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            rightBound();
            leftBound();
            upperBound();
            lowerBound();
            repaint();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (GameObject go : lgo) {
            go.update();
            go.paint(g);
        }
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
        if (e.getKeyChar() == 'd') {
            mainBall.goRight();
        }
        if (e.getKeyChar() == 'a') {
            mainBall.goLeft();
        }
        if (e.getKeyChar() == ' ') {
            mainBall.jump();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}