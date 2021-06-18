import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InvisibleWall extends Ground{
    public InvisibleWall(JPanel parent, ArrayList<GameObject> lgo, int w, int h, int x, int y, double speedX, double speedY, String pathG, String pathS) {
        super(parent, lgo, w, h, x, y, speedX, speedY, pathG, pathS);
    }
    @Override
    public void paint(Graphics g) {
    }
}
