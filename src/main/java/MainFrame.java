import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainFrame extends JFrame implements ActionListener {
    JButton newGame;
    JButton next;
    JButton quit;
    JButton options;
    JButton lanITA;
    JButton lanENG;
    JButton audY;
    JButton audN;
    JButton back;
    BallPanel p1;
    JLabel audioLabel;
    JLabel languageLabel;
    JPanel GameMenu;
    JPanel settingPanel;
    boolean audio;
    boolean language;

    public MainFrame() {
        super("Scratch!");
        this.
        gameMenu();
        setSize(1280, 720);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setFocusable(true);
        audio = true;
        language = true;

    }

    public void gameMenu() {
        GameMenu = new JPanel();
        if (!language) {
            newGame = new JButton("New Game");
            next = new JButton("Continue");
            quit = new JButton("Quit Game");
            options = new JButton("Settings");
        } else {
            newGame = new JButton("Nuova Partita");
            next = new JButton("Continua");
            quit = new JButton("Esci dal gioco");
            options = new JButton("Impostazioni");
        }

        newGame.setFont(new Font("Arial", Font.ITALIC, 50));
        next.setFont(new Font("Arial", Font.ITALIC, 50));
        quit.setFont(new Font("Arial", Font.ITALIC, 50));
        options.setFont(new Font("Arial", Font.ITALIC, 50));
        GameMenu.add(BorderLayout.PAGE_START, newGame);
        newGame.addActionListener(this);
        GameMenu.add(BorderLayout.CENTER, next);
        next.addActionListener(this);
        GameMenu.add(BorderLayout.LINE_END, options);
        GameMenu.add(BorderLayout.PAGE_END, quit);
        options.addActionListener(this);

        quit.addActionListener(this);
        this.setContentPane(GameMenu);
        setVisible(true);

    }

    public void settings() {
        settingPanel = new JPanel(new GridLayout(3, 3));
        if (!language) {
            audioLabel = new JLabel("      AUDIO");
            languageLabel = new JLabel("      LANGUAGE");
            audY = new JButton("ON");
            audN = new JButton("OFF");
            lanENG = new JButton("ENGLISH");
            lanITA = new JButton("ITALIANO");
            back = new JButton("MAIN MENU");
        } else {
            audioLabel = new JLabel("      AUDIO:");
            languageLabel = new JLabel("      LINGUA:");
            audY = new JButton("ON");
            audN = new JButton("OFF");
            lanENG = new JButton("ENGLISH");
            lanITA = new JButton("ITALIANO");
            back = new JButton("MENU INIZIALE");

        }
        audioLabel.setFont(new Font("Arial", Font.ITALIC, 50));
        languageLabel.setFont(new Font("Arial", Font.ITALIC, 50));
        audY.setFont(new Font("Arial", Font.ITALIC, 50));
        audN.setFont(new Font("Arial", Font.ITALIC, 50));
        lanENG.setFont(new Font("Arial", Font.ITALIC, 50));
        lanITA.setFont(new Font("Arial", Font.ITALIC, 50));
        back.setFont(new Font("Arial", Font.ITALIC, 50));

        settingPanel.add(audioLabel);
        settingPanel.add(audN);
        settingPanel.add(audY);
        settingPanel.add(languageLabel);
        settingPanel.add(lanITA);
        settingPanel.add(lanENG);
        audN.addActionListener(this);
        audY.addActionListener(this);
        lanENG.addActionListener(this);
        lanITA.addActionListener(this);
        settingPanel.add(back);
        back.addActionListener(this);
        this.setContentPane(settingPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newGame) {

            remove(GameMenu);
            p1 = new BallPanel();
            add(BorderLayout.CENTER, p1);
            p1.init(1);
            setContentPane(p1);
            addKeyListener(p1);
            setVisible(true);

        }
        if (e.getSource() == quit) {
            this.dispose();
        }
        if (e.getSource() == options) {
            remove(GameMenu);
            settings();

        }
        if (e.getSource() == audN) {
            audio = false;
        }

        if (e.getSource() == audY) {
            audio = true;
        }

        if (e.getSource() == lanENG) {
            language = false;
        }
        if (e.getSource() == lanITA) {
            language = true;
        }
        if (e.getSource() == back) {
            remove(settingPanel);
            gameMenu();
        }

    }
}
