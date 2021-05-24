import javax.swing.*;
import java.awt.*;

/**
 * COMMENTO!
 * visto
 */
public class MainFrame extends JFrame {
    public MainFrame() {
        super("Scratch!");
        BallPanel p1 = new BallPanel();
        JPanel p2 = new JPanel(new BorderLayout());
        p1.init();
        add(BorderLayout.CENTER, p1);
        add(BorderLayout.PAGE_START, p2);
        setSize(1440, 900);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
