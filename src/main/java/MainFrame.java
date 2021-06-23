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
    DataBase b;
    int livello;
    boolean audio;
    boolean language;

    public MainFrame() {
        super("Scratch!");
        gameMenu();
        setSize(1280, 720);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setFocusable(true);
        audio = true;
        language = false;

    }

    public void gameMenu() {
        GameMenu = new JPanel();
        if (!language) {
            newGame = new JButton("NEW GAME");
            next = new JButton("CONTINUE");
            quit = new JButton("QUIT GAME");
            options = new JButton("SETTINGS");
        } else {
            newGame = new JButton("NUOVA PARTITA");
            next = new JButton("CONTINUA");
            quit = new JButton("ESCI DAL GIOCO");
            options = new JButton("IMPOSTAZIONI");
        }

        newGame.setFont(new Font("Mario Kart DS", Font.PLAIN, 50));
        next.setFont(new Font("Mario Kart DS", Font.PLAIN, 50));
        quit.setFont(new Font("Mario Kart DS", Font.PLAIN, 50));
        options.setFont(new Font("Mario Kart DS", Font.PLAIN, 50));
        GameMenu.add(newGame);
        newGame.addActionListener(this);
        GameMenu.add(next);
        next.addActionListener(this);
        GameMenu.add(options);
        GameMenu.add(quit);
        options.addActionListener(this);
        livello  = 1;
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
        audioLabel.setFont(new Font("Mario Kart DS", Font.PLAIN, 50));
        languageLabel.setFont(new Font("Mario Kart DS", Font.PLAIN, 50));
        audY.setFont(new Font("Mario Kart DS", Font.PLAIN, 50));
        audN.setFont(new Font("Mario Kart DS", Font.PLAIN, 50));
        lanENG.setFont(new Font("Mario Kart DS", Font.PLAIN, 50));
        lanITA.setFont(new Font("Mario Kart DS", Font.PLAIN, 50));
        back.setFont(new Font("Mario Kart DS", Font.PLAIN, 50));

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

    public void ballPanelLoad() {
        remove(GameMenu);
        p1 = new BallPanel();
        add(BorderLayout.CENTER, p1);
        p1.init(livello, language, this);
        setContentPane(p1);
        addKeyListener(p1);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newGame) {
            ballPanelLoad();
        }
        if (e.getSource() == next) {
            b = new DataBase();
            livello = b.read();

            ballPanelLoad();
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
