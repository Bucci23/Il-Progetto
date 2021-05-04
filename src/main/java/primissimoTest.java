import org.w3c.dom.Text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class primissimoTest extends JFrame implements KeyListener {
    private TextArea t;
    public primissimoTest() {

        JPanel p1=new JPanel();
        t =new TextArea();
        t.addKeyListener(this);
        p1.add(t);
        setSize(500, 400);
        setContentPane(p1);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        t.setText("Hai cagato?");
        System.out.println(e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        t.setText("Hai cagato?");
        System.out.println(e.getKeyChar());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        t.setText("Hai cagato?");
        System.out.println(e.getKeyChar());
    }


}
