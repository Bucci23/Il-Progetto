import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InvisibleWall extends Ground{
    boolean kills;
    public InvisibleWall(JPanel parent, ArrayList<GameObject> lgo, int w, int h, int x, int y, double speedX, double speedY, String pathG, String pathS, boolean kills) {
        super(parent, lgo, w, h, x, y, speedX, speedY, pathG, pathS);
        this.kills = kills;

    }

    void kill(Personaggio ball){
        if(getBounds().intersects(ball.getBounds()) && kills){
            ball.setVita(0);
        }
    }

    @Override
    public void paint(Graphics g) {

    }
}
