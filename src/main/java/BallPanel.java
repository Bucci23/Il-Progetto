import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serial;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.Random;

public class BallPanel extends JPanel implements KeyListener, ActionListener, MouseListener {
    @Serial
    private static final long serialVersionUID = 1L;
    MainFrame parent;
    Timer timer;
    ArrayList<GameObject> lgo;
    Guida guida;
    int bX;
    int bY;
    int offset;
    Random rnd;
    ImageIcon background;
    ImageIcon backgroundS;
    ImageIcon gameOverImage;
    ImageIcon gameOverRetry;
    ImageIcon pauseImage;
    ImageIcon pauseResume;
    ImageIcon fondale;
    ImageIcon lvSuperatoIMG;
    boolean scrollingR;
    boolean scrollingL;
    boolean scrollingU;
    boolean scrollingD;
    boolean gameOver;
    boolean animation;
    boolean lvSuperato;
    boolean language;
    double sceneSpeedX;
    double sceneSpeedY;
    int ground;
    Skills skills;
    int livello;
    boolean pause;
    Timer GOTimer;
    Timer PTimer;
    Timer LTimer;

    public BallPanel() {
        rnd = new Random();
        setBackground(Color.CYAN);
        addKeyListener(this);
        addMouseListener(this);
        setFocusable(true);
        scrollingR = false;
        scrollingL = false;
        scrollingU = false;
        scrollingD = false;
        gameOver = false;
        animation = false;

    }

    public void init(int livello, boolean language, MainFrame parent) {
        lgo = new ArrayList<GameObject>();
        this.parent = parent;
        this.language = language;
        this.livello = livello;
        addKeyListener(this);
        offset = 6;
        bX = -500;
        bY = -150;
        if (livello == 1)
            initLV1();
        if (livello == 2) {
            initLV2();
        }
        if (!language) {
            gameOverImage = new ImageIcon("images/gameOver.png");
            gameOverRetry = new ImageIcon("images/gameOverRetry.png");
            pauseImage = new ImageIcon("images/pause.png");
            pauseResume = new ImageIcon("images/pauseResume.png");
            lvSuperatoIMG = new ImageIcon("images/VignettaLVSuperatoENG.png");
        } else {
            gameOverImage = new ImageIcon("images/gameOver.png");
            gameOverRetry = new ImageIcon("images/gameOverRetryITA.png");
            pauseImage = new ImageIcon("images/pauseITA.png");
            pauseResume = new ImageIcon("images/pauseResumeITA.png");
            lvSuperatoIMG = new ImageIcon("images/VignettaLVSuperatoITA.png");
        }
        skills = new Skills((Ball) lgo.get(0), this);

        sceneSpeedX = 0;
        sceneSpeedY = 0;
        lvSuperato = false;
        timer = new Timer(20, this);
        GOTimer = new Timer(700, this);
        PTimer = new Timer(700, this);
        LTimer = new Timer(3000, this);
        guida = new Guida(this, "images/guida.png", "images/showGuide.png", "images/guidaX.png");
        timer.start();
        pause = false;

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
        if ((b.getY() > this.getHeight() - 100 && b.speedY > 0) && ground < this.getHeight()) {
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
        bY += (sceneSpeedY / offset);
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
            checkDeath();
            checkLVSuperato();
            repaint();
        }
        if (e.getSource() == GOTimer) {
            animation = !animation;
            repaint();
        }
        if (e.getSource() == PTimer) {
            if (!gameOver) {
                animation = !animation;
                repaint();
            }
        }
        if (e.getSource() == LTimer) {
            init(livello + 1, language, parent);
        }

    }


    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        paintBackground(g);
        for (GameObject go : lgo) {
            if (!pause)
                go.update();
            go.paint(g);
        }
        skills.update();
        skills.paint(g);
        guida.update();
        guida.paint(g);

        paintPause(g);
    }

    public void paintBackground(Graphics g) {
        int count = 0;
        for (int i = bX - background.getIconWidth(); i < 10000 + bX; i += background.getIconWidth()) {
            count++;
            if (count % 2 == 0) {
                background.paintIcon(this, g, i + getX(), bY);
            } else
                backgroundS.paintIcon(this, g, i + getX(), bY);
        }
    }

    public void paintPause(Graphics g) {
        if (pause) {
            timer.stop();
            PTimer.start();
            if (!gameOver && !lvSuperato && !guida.showing) {
                if (animation) {
                    pauseImage.paintIcon(this, g, 0, 0);
                } else {
                    pauseResume.paintIcon(this, g, 0, 0);
                }
            } else if (gameOver && !guida.showing) {
                paintGameOver(g);
            } else if (!guida.showing) {
                lvSuperatoIMG.paintIcon(this, g, getWidth() / 2 + 150, getHeight() / 2 - 100);
            }
        }
    }

    void paintGameOver(Graphics g) {
        if (animation) {
            gameOverImage.paintIcon(this, g, 0, 0);
        } else {
            gameOverRetry.paintIcon(this, g, 0, 0);
        }
    }

    void checkDeath() {
        Ball mainBall = (Ball) lgo.get(0);
        if (mainBall.getVita() <= 0) {
            gameOver = true;
            pause = true;
            GOTimer.start();
        }
    }

    void checkLVSuperato() {
        if (lvSuperato) {
            pause = true;
            LTimer.start();
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
        if (e.getKeyChar() == 27) {
            if (!gameOver) {
                if (!pause) {
                    pause = true;
                } else {
                    pause = false;
                    PTimer.stop();
                    timer.start();
                }
            }

        }
        if (e.getKeyChar() == '\n') {
            if (gameOver) {
                parent.remove(this);
                parent.gameMenu();
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    void initLV1() {
        ground = -10000;
        background = new ImageIcon("images/deserto.jpg");
        backgroundS = new ImageIcon("images/desertoS.jpg");

        lgo.add(new Ball(this, lgo, 60, 60, getWidth(), getHeight(), 1, 1, Color.BLUE));
        if (language)
            lgo.add(new Salvadanaio(this, 9800, 1100, 1, "images/vignettaITA.png", "images/vignettaLVSuperatoITA.png"));
        else
            lgo.add(new Salvadanaio(this, 9800, 1100, 1, "images/vignettaENG.png", "images/vignettaLVSuperatoENG.png"));
        lgo.add(new Ground(this, lgo, 6000, 100, -500, 1300, 0, 0, "images/SabbiaEsterno.png", "images/SabbiaInterno.png"));
        lgo.add(new Ground(this, lgo, 300, 100, 5900, 1300, 0, 0, "images/SabbiaEsterno.png", "images/SabbiaInterno.png"));
        lgo.add(new Ground(this, lgo, 300, 300, 700, 1000, 0, 0, "images/SabbiaEsterno.png", "images/SabbiaInterno.png"));
        lgo.add(new Ground(this, lgo, 300, 600, 1000, 700, 0, 0, "images/SabbiaEsterno.png", "images/SabbiaInterno.png"));
        lgo.add(new Ground(this, lgo, 300, 900, 1300, 400, 0, 0, "images/SabbiaEsterno.png", "images/SabbiaInterno.png"));
        lgo.add(new Coin(this, lgo, 1650, 1250, "images/moneta.png"));
        lgo.add(new Ground(this, lgo, 300, 200, 2200, 900, 0, 0, "images/SabbiaEsterno.png", "images/SabbiaInterno.png"));
        lgo.add(new Ground(this, lgo, 300, 600, 1900, 600, 0, 0, "images/SabbiaEsterno.png", "images/SabbiaInterno.png"));
        lgo.add(new Ground(this, lgo, 300, 800, 1600, 300, 0, 0, "images/SabbiaEsterno.png", "images/SabbiaInterno.png"));
        lgo.add(new Coin(this, lgo, 1650, 250, "images/moneta.png"));
        lgo.add(new Ground(this, lgo, 300, 300, 3000, 1000, 0, 0, "images/SabbiaEsterno.png", "images/SabbiaInterno.png"));
        lgo.add(new Munizioni(this, lgo, 3100, 950, "images/Munizioni.png"));
        lgo.add(new Ground(this, lgo, 300, 200, 6400, 1000, 0, 0, "images/SabbiaEsterno.png", "images/SabbiaInterno.png"));
        lgo.add(new Ground(this, lgo, 300, 200, 6900, 1100, 0, 0, "images/SabbiaEsterno.png", "images/SabbiaInterno.png"));
        lgo.add(new Ground(this, lgo, 300, 200, 7400, 1100, 0, 0, "images/SabbiaEsterno.png", "images/SabbiaInterno.png"));
        lgo.add(new Ground(this, lgo, 300, 200, 7400, 650, 0, 0, "images/SabbiaEsterno.png", "images/SabbiaInterno.png"));
        lgo.add(new Ground(this, lgo, 300, 200, 8000, 1100, 0, 0, "images/SabbiaEsterno.png", "images/SabbiaInterno.png"));
        lgo.add(new Ground(this, lgo, 300, 200, 8400, 1100, 0, 0, "images/SabbiaEsterno.png", "images/SabbiaInterno.png"));
        lgo.add(new Ground(this, lgo, 1000, 200, 9000, 1300, 0, 0, "images/SabbiaEsterno.png", "images/SabbiaInterno.png"));


        lgo.add(new Coin(this, lgo, 7500, 600, "images/moneta.png"));
        lgo.add(new Dinosauro(this, lgo, 3100, 900, "images/dinoL.png", "images/dinoR.png"));
        lgo.add(new Dinosauro(this, lgo, 2000, 1200, "images/dinoL.png", "images/dinoR.png"));
        lgo.add(new Dinosauro(this, lgo, 1250, 1200, "images/dinoL.png", "images/dinoR.png"));
        lgo.add(new Dinosauro(this, lgo, 3300, 1000, "images/dinoL.png", "images/dinoR.png"));
        lgo.add(new Dinosauro(this, lgo, 4500, 1100, "images/dinoL.png", "images/dinoR.png"));
        lgo.add(new Dinosauro(this, lgo, 4000, 1100, "images/dinoL.png", "images/dinoR.png"));
        lgo.add(new Dinosauro(this, lgo, 3500, 1100, "images/dinoL.png", "images/dinoR.png"));
        lgo.add(new Dinosauro(this, lgo, 4800, 1100, "images/dinoL.png", "images/dinoR.png"));
        lgo.add(new Dinosauro(this, lgo, 5000, 1100, "images/dinoL.png", "images/dinoR.png"));
        lgo.add(new Ground(this, lgo, 3000, 200, 10000, 4000, 0, 0, "images/SabbiaEsterno.png", "images/SabbiaInterno.png"));
        lgo.add(new Ground(this, lgo, 100, 10000, -100, 0, 0, 0, null, null));
        lgo.add(new Ground(this, lgo, 100, 10000, 10000, 0, 0, 0, null, null));
        lgo.add(new InvisibleWall(this, lgo, 10000, 100, 0, 1500, 0, 0, null, null, true));

    }

    void initLV2() {
        ground = -10000;
        bY = -400;
        offset = 3;
        background = new ImageIcon("images/spiaggia.jpg");
        backgroundS = new ImageIcon("images/spiaggiaS.jpg");
        fondale = new ImageIcon("images/fondale.jpg");
        lgo.add(new Ball(this, lgo, 60, 60, getWidth(), getHeight(), 1, 1, Color.BLUE));
        if (language)
            lgo.add(new Salvadanaio(this, 9800, 1100, 2, "images/vignettaITA.png", "images/vignettaLVSuperatoITA.png"));
        else
            lgo.add(new Salvadanaio(this, 9800, 1100, 2, "images/vignettaENG.png", "images/vignettaLVSuperatoENG.png"));
        lgo.add(new Munizioni(this, lgo, 400, 1250, "images/Munizioni.png"));
        lgo.add(new Ground(this, lgo, 6000, 1300, -500, 1300, 0, 0, "images/SabbiaEsterno.png", "images/SabbiaInterno.png"));
        lgo.add(new Ground(this, lgo, 300, 100, 5900, 1300, 0, 0, "images/SabbiaEsterno.png", "images/SabbiaInterno.png"));
        lgo.add(new Ground(this, lgo, 300, 200, 700, 1100, 0, 0, "images/SabbiaEsterno.png", "images/SabbiaInterno.png"));
        lgo.add(new Ground(this, lgo, 300, 300, 1000, 1000, 0, 0, "images/SabbiaEsterno.png", "images/SabbiaInterno.png"));
        lgo.add(new Ground(this, lgo, 300, 200, 1300, 1100, 0, 0, "images/SabbiaEsterno.png", "images/SabbiaInterno.png"));
        lgo.add(new Coin(this, lgo, 1650, 1250, "images/moneta.png"));
        lgo.add(new Ground(this, lgo, 300, 200, 2200, 1100, 0, 0, "images/SabbiaEsterno.png", "images/SabbiaInterno.png"));
        lgo.add(new Ground(this, lgo, 300, 300, 1900, 1000, 0, 0, "images/SabbiaEsterno.png", "images/SabbiaInterno.png"));
        lgo.add(new Ground(this, lgo, 300, 400, 1600, 900, 0, 0, "images/SabbiaEsterno.png", "images/SabbiaInterno.png"));
        lgo.add(new Coin(this, lgo, 1650, 250, "images/moneta.png"));
        lgo.add(new Ground(this, lgo, 300, 300, 3000, 1000, 0, 0, "images/SabbiaEsterno.png", "images/SabbiaInterno.png"));
        lgo.add(new Munizioni(this, lgo, 3100, 950, "images/Munizioni.png"));
        lgo.add(new Ground(this, lgo, 1000, 5000, 9000, 1300, 0, 0, "images/SabbiaEsterno.png", "images/SabbiaInterno.png"));
        lgo.add(new Coin(this, lgo, 7500, 600, "images/moneta.png"));
        lgo.add(new Ground(this, lgo, 1000, 200, 9000, 1300, 0, 0, "images/SabbiaEsterno.png", "images/SabbiaInterno.png"));
        lgo.add(new Granchio(this, lgo, 3100, 900, "images/GranchioL.png", "images/GranchioR.png"));
        lgo.add(new Granchio(this, lgo, 2000, 1200, "images/GranchioL.png", "images/GranchioR.png"));
        lgo.add(new Granchio(this, lgo, 1250, 1200, "images/GranchioL.png", "images/GranchioR.png"));
        lgo.add(new Granchio(this, lgo, 3300, 1000, "images/GranchioL.png", "images/GranchioR.png"));
        lgo.add(new Granchio(this, lgo, 4500, 1100, "images/GranchioL.png", "images/GranchioR.png"));
        lgo.add(new Granchio(this, lgo, 4000, 1100, "images/GranchioL.png", "images/GranchioR.png"));
        lgo.add(new Granchio(this, lgo, 3500, 1100, "images/GranchioL.png", "images/GranchioR.png"));
        lgo.add(new Granchio(this, lgo, 4800, 1100, "images/GranchioL.png", "images/GranchioR.png"));
        lgo.add(new Granchio(this, lgo, 5000, 1100, "images/GranchioL.png", "images/GranchioR.png"));
        lgo.add(new Water("images/fondale.png", 5500, 1300, 4000, 1000, this));

        lgo.add(new Ground(this, lgo, 3000, 200, 10000, 4000, 0, 0, "images/SabbiaEsterno.png", "images/SabbiaInterno.png"));
        lgo.add(new Ground(this, lgo, 4000, 300, 5500, 2300, 0, 0, "images/SabbiaEsterno.png", "images/SabbiaInterno.png"));
        lgo.add(new Ground(this, lgo, 100, 10000, -100, 0, 0, 0, null, null));
        lgo.add(new Ground(this, lgo, 100, 10000, 10000, 0, 0, 0, null, null));


    }

    void initLV3() {
        ground = 0;
        background = new ImageIcon("images/SfondoStrano.png");
        backgroundS = new ImageIcon("images/SfondoStrano.png");
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
        lgo.add(new Dinosauro(this, lgo, 1100, 500, "images/dinoL.png", "images/dinoR.png"));
        lgo.add(new Dinosauro(this, lgo, 1500, 500, "images/dinoL.png", "images/dinoR.png"));
        lgo.add(new Dinosauro(this, lgo, 2000, 900, "images/dinoL.png", "images/dinoR.png"));
        lgo.add(new Dinosauro(this, lgo, 2500, 500, "images/dinoL.png", "images/dinoR.png"));
        lgo.add(new Dinosauro(this, lgo, 2800, 500, "images/dinoL.png", "images/dinoR.png"));
        lgo.add(new Dinosauro(this, lgo, 2900, 500, "images/dinoL.png", "images/dinoR.png"));
        lgo.add(new Dinosauro(this, lgo, 3000, 500, "images/dinoL.png", "images/dinoR.png"));
        lgo.add(new Coin(this, lgo, 700, 1050, "images/moneta.png"));
        lgo.add(new Coin(this, lgo, 200, 500, "images/moneta.png"));
        lgo.add(new Coin(this, lgo, 500, 900, "images/moneta.png"));
        lgo.add(new Vite(this, lgo, 900, 1100, "images/Pozione.png"));
        lgo.add(new Munizioni(this, lgo, 700, 1100, "images/Munizioni.png"));
        lgo.add(new Vite(this, lgo, 500, 1100, "images/Pozione.png"));
        lgo.add(new Ground(this, lgo, 200, 3000, 10000, 1300, 0, 0, "images/groundgrass.png", "images/groundsimple.png"));
        lgo.add(new Ground(this, lgo, 3000, 200, 10000, 4000, 0, 0, "images/groundgrass.png", "images/groundsimple.png"));
        lgo.add(new Squalo(this, lgo, 3000, 500, "images/SqualoGrandeL.png", "images/SqualoGrandeR.png"));
        lgo.add(new Granchio(this, lgo, 600, 500, "images/GranchioL.png", "images/GranchioR.png"));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getX() >= getWidth() - 100 && e.getY() <= 100) {

            if (!guida.showing) {
                guida.showing = true;
                pause = true;
            } else {
                pause = false;
                guida.showing = false;
                timer.start();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
