package GameObjects;

import Engine.BallPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Oggetto da caricare nella schermata di congratulazioni.
 * Una semplice immagine che compare in primo piano.
 */
public class Fine extends AbstractGameObject {
    ImageIcon icon;
    public Fine(BallPanel parent, String icon){
        this.icon = new ImageIcon(icon);
        this.parent = parent;
        x =0;
        y = 0;
    }
    @Override
    public void update() {

    }

    @Override
    public void paint(Graphics g) {
        icon.paintIcon(parent, g, (int) x, (int) y);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
