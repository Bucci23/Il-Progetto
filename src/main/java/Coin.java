import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Coin extends PowerUp {

    AudioPlayer audioPlayer;
    public Coin(JPanel parent, ArrayList<GameObject> lgo, int x, int y, String icon) {
        super(parent, lgo, x, y, icon);
        audioPlayer = new AudioPlayer();
    }

    @Override
    public void specialAbility(Ball ball) {
        ball.setMonete(ball.getMonete() + 1);
        if(((BallPanel) parent).parent.audio) {
            audioPlayer.play("audio/coin.wav");
        }

    }

    @Override
    public void update() {
        newPositions();
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, w, h);
    }

}
