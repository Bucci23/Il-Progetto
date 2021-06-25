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
    JButton note;
    JButton ret;
    BallPanel p1;
    JLabel audioLabel;
    JLabel languageLabel;
    JLabel infos9;
    JLabel infos8;
    JLabel infos7;
    JLabel infos6;
    JLabel infos5;
    JLabel infos4;
    JLabel infos3;
    JLabel infos2;
    JLabel infos;
    JPanel GameMenu;
    JPanel settingPanel;
    JPanel info;
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
        BoxLayout b = new BoxLayout(GameMenu, BoxLayout.Y_AXIS);
        GameMenu.setLayout(b);
        if (!language) {
            newGame = new JButton("NEW GAME");
            next = new JButton("CONTINUE");
            quit = new JButton("QUIT GAME");
            options = new JButton("SETTINGS");
            note = new JButton("INFO");
        } else {
            newGame = new JButton("NUOVA PARTITA");
            next = new JButton("CONTINUA");
            quit = new JButton("ESCI DAL GIOCO");
            options = new JButton("IMPOSTAZIONI");
            note = new JButton("INFORMAZIONI");
        }

        newGame.setFont(new Font("Mario Kart DS", Font.PLAIN, 125));
        next.setFont(new Font("Mario Kart DS", Font.PLAIN, 125));
        quit.setFont(new Font("Mario Kart DS", Font.PLAIN, 125));
        options.setFont(new Font("Mario Kart DS", Font.PLAIN, 125));
        note.setFont(new Font("Mario Kart DS", Font.PLAIN, 125));
        GameMenu.add(newGame);
        newGame.addActionListener(this);
        GameMenu.add(next);
        next.addActionListener(this);
        GameMenu.add(options);
        GameMenu.add(note);
        GameMenu.add(quit);
        note.addActionListener(this);
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
            audioLabel = new JLabel("      AUDIO");
            languageLabel = new JLabel("      LINGUA");
            audY = new JButton("ON");
            audN = new JButton("OFF");
            lanENG = new JButton("ENGLISH");
            lanITA = new JButton("ITALIANO");
            back = new JButton("MENU");

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
        setContentPane(settingPanel);
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
    public void showInfo() {
        info = new JPanel(new FlowLayout());
        BoxLayout b = new BoxLayout(info, BoxLayout.Y_AXIS);
        info.setLayout(b);
        if(language){



            infos = new JLabel("Benvenuti in SCRATCH!                                                 ");
            infos2 = new JLabel("In questo gioco dovrete riuscire a raggiungere il traguardo,                             ");
            infos3 = new JLabel("superando molti ostacoli e raccogliendo monete nascoste.                               ");
            infos4 = new JLabel("Informazioni utili:                                     ");
            infos5 = new JLabel("-Le pozioni vi faranno recuperare vita                  ");
            infos6 = new JLabel("-I forzieri contengono utili munizioni                           ");
            infos7 = new JLabel("-All'avanzare dei livelli, aumentera' la resistenza dei nemici               ");
            infos8 = new JLabel("-Per consultare la guida nel gioco, clicca il punto di domanda in alto a destra         " );
            infos9 = new JLabel("Gioco creato da Davide Bucciarelli & Gianluca De Angelis              ");
            ret = new JButton("MENU INIZIALE");


        } else{
            ret = new JButton("MAIN MENU");
            infos = new JLabel("Welcome to SCRATCH!                                     ");
            infos2 = new JLabel("In this game you will have to get to the end of every level,                ");
            infos3 = new JLabel("dealing with many obstacles and collecting hidden coins.                  ");
            infos4 = new JLabel("Useful information:                                       ");
            infos5 = new JLabel("-Potions will make you gain life points                   ");
            infos6 = new JLabel("-Chests contain useful ammunition                        ");
            infos7 = new JLabel("-In later levels, enemies will be stronger                 ");
            infos8 = new JLabel("-To read the in-game guide, click on the question mark in the top-right corner");
            infos9 = new JLabel("Created by Davide Bucciarelli & Gianluca De Angelis");
        }
        infos.setFont(new Font("Arial", Font.PLAIN, 35));
        infos2.setFont(new Font("Arial", Font.PLAIN, 35));
        infos3.setFont(new Font("Arial", Font.PLAIN, 35));
        infos4.setFont(new Font("Arial", Font.PLAIN, 35));
        infos5.setFont(new Font("Arial", Font.PLAIN, 35));
        infos6.setFont(new Font("Arial", Font.PLAIN, 35));
        infos7.setFont(new Font("Arial", Font.PLAIN, 35));
        infos8.setFont(new Font("Arial", Font.PLAIN, 35));
        infos9.setFont(new Font("Arial", Font.PLAIN, 35));
        ret.setFont(new Font("Arial", Font.PLAIN, 100));
        info.add(infos);
        info.add(infos2);
        info.add(infos3);
        info.add(infos4);
        info.add(infos5);
        info.add(infos6);
        info.add(infos7);
        info.add(infos8);
        info.add(infos9);
        info.add(ret);
        ret.addActionListener(this);
        setContentPane(info);
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
        if(e.getSource() == note){
            remove(GameMenu);
            showInfo();
        }
        if (e.getSource() == ret) {
            remove(info);
            gameMenu();
        }
    }

}
