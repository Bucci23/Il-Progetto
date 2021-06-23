import javax.swing.*;
import java.awt.*;
import java.awt.font.ImageGraphicAttribute;

public class Salvadanaio extends AbstractGameObject {
    ImageIcon icon;
    ImageIcon vignetta;
    ImageIcon vignettaSuperato;
    int livello;
    int moneteRichieste;
    boolean lvSuperato;
    boolean showAnimation;

    DataBase save;
    BallPanel ballPanel;


    public Salvadanaio(BallPanel ballPanel, double x, double y, int livello, String vignetta, String vignettaSuperato) {
        this.ballPanel = ballPanel;
        parent = ballPanel;
        this.x = x;
        this.y = y;
        w = 200;
        h = 200;
        this.livello = livello;
        switch (livello) {
            case 1 -> {
                moneteRichieste = 3;
                icon = new ImageIcon("images/Salvadanaio1.png");
            }
            case 2 -> {
                moneteRichieste = 5;
                icon = new ImageIcon("images/Salvadanaio2.png");
            }
            case 3 -> {
                moneteRichieste = 7;
                icon = new ImageIcon("images/Salvadanaio3.png");
            }
            case 4 -> {
                moneteRichieste = 10;
                icon = new ImageIcon("images/Salvadanaio4.png");
            }
            case 5 -> {
                moneteRichieste = 1;
                icon = new ImageIcon("images/Salvadanaio5.png");
            }
        }
        lvSuperato = false;
        showAnimation = false;
        this.vignetta = new ImageIcon(vignetta);
        this.vignettaSuperato = new ImageIcon(vignettaSuperato);
        save = new DataBase();
    }

    @Override
    public void update() {
        newPositions();
    }

    @Override
    public void paint(Graphics g) {
        icon.paintIcon(parent, g, (int) x, (int) y);
        if(showAnimation){
            vignetta.paintIcon(parent, g, (int) x - 150, (int) y - 200);
        }
        if(lvSuperato){
            vignettaSuperato.paintIcon(parent, g,(int) x - 150, (int) y - 200);
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, w, h);
    }

    public void isColliding(Ball ball) {
        if (this.getBounds().intersects(ball.getBounds())) {
            if (ball.monete >= moneteRichieste) {
                lvSuperato = true;
                ballPanel.lvSuperato = true;
                saveGame();
            } else
                showAnimation = true;
            lvSuperato = false;
        } else
            showAnimation = false;
    }

    public boolean isLvSuperato() {
        return lvSuperato;
    }

    public void saveGame(){
        save.save(livello + 1);
    }
}
