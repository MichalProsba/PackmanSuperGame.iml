package pacman.game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

/**
 * Class containing the board model
 */

public class Model extends JPanel implements ActionListener {

    private JFrame alertWindow;

    // Alert Window
    private static final int ALERT_WINDOW_X_SIZE = 500;
    private static final int ALERT_WINDOW_Y_SIZE = 200;

    // AlertWindow Ok button
    private static final int OK_BUTTON_WIDTH = 100;
    private static final int OK_BUTTON_HEIGHT = 50;
    private static final int OK_BUTTON_X_POSITION = (ALERT_WINDOW_X_SIZE-OK_BUTTON_WIDTH)/2;
    private static final int OK_BUTTON_Y_POSITION = ((ALERT_WINDOW_Y_SIZE-OK_BUTTON_HEIGHT)/2)+20;

    // Alert Label
    private static final int ALERT_LABEL_WIDTH = 120;
    private static final int ALERT_LABEL_HEIGHT = 50;
    private static final int ALERT_LABEL_X_POSITION = (ALERT_WINDOW_X_SIZE-ALERT_LABEL_WIDTH)/2;
    private static final int ALERT_LABEL_Y_POSITION = 10;

    // Colors
    private static final Color backgroundColor = new Color(0,0,0);
    private static final Color textColor = new Color(255,255,0);
    private static final Color chosenButtonBackgroundColor = new Color(0,72,251);

    // Titlebar Image
    private static final Image pacmanImage = Toolkit.getDefaultToolkit().getImage("Images/Speed/Medium.PNG");

    // Fonts
    private static final Font menuFont = new Font("Arial", Font.BOLD, 18);

    //Zycia
    private Player player;

    //Struktura zmiennych gry
    private GameVariable gameVariable;

    //Konfiguracja gry, zawierajaca role, mape, szybkosc gry
    private GameConfiguration gameConfiguration;

    //Rozmiar jako wysokosc i szerokosc w pikselach
    private Dimension d;
    //Rozmiar i rodzaj czcionki
    private final Font smallFont = new Font("Arial", Font.BOLD, 48);

    //Pozycja startowa duchow
    private final int START_POSITION_GHOST_X = 4;
    private final int START_POSITION_GHOST_Y = 4;

    //Pozycja startowa pacmana
    private final int START_POSITION_PACMAN_X = 7;
    private final int START_POSITION_PACMAN_Y = 11;

    //Szerokosc ekranu gracza
    private final int USER_SCREEN_SIZE_WIDTH = 1920;
    //Wysokosc ekranu gracza
    private final int USER_SCREEN_SIZE_HEIGHT = 1080;
    //Wysokosc i szerokosc obrazka
    private final int IMAGE_SIZE = 60;
    //Maksymalna ilosc map
    private final int MAX_MAP = 3;

    //Wysokosc i szerokosc pojedynczego bloku
    private final int BLOCK_SIZE =  IMAGE_SIZE;

    //Ilosc blokow na szerokosc
    private final int N_BLOCKS_WIDTH = USER_SCREEN_SIZE_WIDTH/BLOCK_SIZE - 1;
    //Ilosc blokow na wysokosc
    private final int N_BLOCKS_HEIGHT = USER_SCREEN_SIZE_HEIGHT/BLOCK_SIZE - 3;

    //Szerokosc ekranu
    private final int SCREEN_SIZE_WIDTH = N_BLOCKS_WIDTH * BLOCK_SIZE;
    //Wysokosc ekranu
    private final int SCREEN_SIZE_HEIGHT = N_BLOCKS_HEIGHT * BLOCK_SIZE;


    //Macierz o rozmiarze N_BLOCKS_WIDTH*N_BLOCKS_HEIGHT stanowiąca plansze
    private short[] screenData = new short[N_BLOCKS_WIDTH * N_BLOCKS_HEIGHT];

    //Predkosc Pacmana
    private final int PACMAN_SPEED = 6;

    //Ilosc duchow
    private int N_GHOSTS = 6;

    //Animacje serca ducha,pacmana
    private Image heart, ghost;

    /*
    //Tablica dopuszczalnych predkosci
    private final int validSpeeds[] = {1, 2, 3, 4, 5, 6};

     */
    //Maksymalna predkosc
    private final int maxSpeed = 6;

    //Aktualna predkosc
    private int currentSpeed = 4;


    //Timer
    private Timer timer;

    //Tworzenie postaci
    private Pacman pacman;
    private Clyde clyde;
    private Blinky blinky;
    private Pinky pinky;
    private Inky inky;

    //Tworzenie watkow

    Thread blinkyThread;
    Thread pinkyThread;
    Thread clydeThread;
    Thread inkyThread;

    //Plansza 15x15 - 225 elementow
    //0 - przeszkoda
    //1 - lewa krawedz
    //2 - gorna krawedz
    //4 - prawa krawedz
    //8 - dolna krawedz
    //16 - zeton do zjedzenia
    //Aby obliczyc wartosc pola dodajemy, przykladowo lewy gorny rog
    //lewy gorny rog = lewa krawedz + gorna krawedz + zeton do zjedzenia
    //19 = 1 + 2 + 16

    private final short levelData1[] = {
            19, 26, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 26, 22,
            21,  0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,  0, 21,
            21,  0, 25, 24, 24, 24, 24, 24, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 24, 24, 24, 24, 24, 28,  0, 21,
            21,  0,  0,  0,  0,  0,  0,  0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,  0,  0,  0,  0,  0,  0,  0, 21,
            21,  0, 19, 18, 18, 18, 18, 18, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 18, 18, 18, 18, 18, 22,  0, 21,
            21,  0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 24, 24, 24, 24, 24, 24, 24, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,  0, 21,
            21,  0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 20,  0,  0,  0,  0,  0,  0,  0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 20,  0, 21,
            21,  0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 20,  0,  0,  0,  0,  0,  0,  0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 20,  0, 21,
            21,  0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 20,  0,  0,  0,  0,  0,  0,  0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 20,  0, 21,
            21,  0, 25, 24, 24, 24, 24, 24, 16, 16, 16, 20,  0,  0,  0,  0,  0,  0,  0, 17, 16, 16, 16, 24, 24, 24, 24, 24, 28,  0, 21,
            21,  0,  0,  0,  0,  0,  0,  0, 17, 16, 16, 16, 18, 18, 18, 18, 18, 18, 18, 16, 16, 16, 20,  0,  0,  0,  0,  0,  0,  0, 21,
            21,  0, 19, 18,  18, 18, 18, 18, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 18, 18, 18, 18, 18, 22, 0, 21,
            21,  0, 25, 24,  24, 24, 24, 24, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 24, 24, 24, 24, 24, 28, 0, 21,
            21,  0,  0,  0,   0,  0,  0,  0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20, 0,  0,  0,  0,  0,  0, 0, 21,
            25, 26, 26, 26, 26, 26, 26, 26, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 26, 26, 26, 26, 26, 26, 26, 28
    };

    private final short levelData2[] = {
            19, 18, 18, 18, 18, 26, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 26, 18, 18, 18, 18, 22,
            17, 16, 16, 16, 20,  0,  17, 16, 16, 16, 16, 16, 24, 24, 24, 24, 24, 24, 24, 16, 16, 16, 16, 16, 20, 0, 17, 16, 16, 16, 20,
            17, 24, 24, 24, 28,  0,  17, 16, 16, 16, 16, 20,  0,  0,  0,  0,  0,  0,  0, 17, 16, 16, 16, 16, 20, 0, 25, 24, 24, 24, 20,
            21,  0,  0,  0,  0,  0,  17, 16, 16, 16, 16, 20,  0,  0,  0,  0,  0,  0,  0, 17, 16, 16, 16, 16, 20, 0,  0,  0,  0,  0, 21,
            17, 18, 18, 18, 18, 18, 16, 16, 16, 16, 16,  20,  0,  0,  0,  0,  0,  0,  0, 17, 16, 16, 16, 16, 16, 18, 18, 18, 18, 18, 20,
            17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16,  20,  0,  0,  0,  0,  0,  0,  0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
            17, 16, 16, 16, 24, 16, 16, 16, 16, 16, 16,  20,  0,  0,  0,  0,  0,  0,  0, 17, 16, 16, 16, 16, 16, 16, 24, 16, 16, 16, 20,
            17, 16, 16, 20,  0, 17, 16, 16, 16, 16, 16,  20,  0,  0,  0,  0,  0,  0,  0, 17, 16, 16, 16, 16, 16, 20, 0, 17, 16,  16, 20,
            17, 16, 16, 20,  0,  17, 16, 16, 16, 16, 16, 16, 18, 18, 18, 18, 18, 18, 18, 16, 16, 16, 16, 16, 16, 20, 0, 17, 16, 16, 20,
            17, 24, 24, 28,  0,  25, 24, 24, 24, 24, 24, 24, 24, 24, 16, 16, 16, 16, 24, 24, 24, 24, 24, 24, 24, 28, 0, 25, 24, 24, 20,
            21,  0,  0,  0,  0,   0,  0,  0,  0,  0,  0,  0,  0,  0, 17, 16, 16,  20,  0,  0,  0,  0,  0,  0,  0,  0, 0,  0,  0,  0, 21,
            17, 18, 18, 22,  0,  19, 18, 18, 18, 18, 18, 18, 18, 18, 16, 16, 16, 16, 18, 18, 18, 18, 18, 18, 18, 22, 0, 19, 18, 18, 20,
            17, 16, 16, 20,  0,  17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20, 0, 17, 16, 16, 20,
            17, 16, 16, 20,  0,  17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20, 0, 17, 16, 16, 20,
            25, 24, 24, 24, 26, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 26, 24, 24, 24, 28
    };

    private final short levelData3[] = {
            19,  18, 18, 18, 18, 18, 18, 18, 18, 18, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 18, 18, 18, 18, 18, 18, 18, 18, 18, 22,
            17,  16, 16, 16, 16, 16, 16, 16, 16, 20,  0,  0,  0,  0,  0,  0,  0,  0,  0, 0, 0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 20,
            17,  24, 24, 24, 24, 24, 24, 24, 16, 16, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 16, 16, 24, 24, 24, 24, 24, 24, 24, 20,
            21,  0,  0,  0,  0,  0,  0,  0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,  0,  0,  0,  0,  0,  0,  0, 21,
            21,  0, 19, 18, 18, 18, 18, 18, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 18, 18, 18, 18, 18, 22,  0, 21,
            21,  0, 25, 24, 24, 24, 24, 24, 16, 16, 16, 16, 24, 24, 16, 16, 16, 24, 24, 16, 16, 16, 16, 24, 24, 24, 24, 24, 28,  0, 21,
            21,  0,  0,  0,  0,  0,  0,  0, 17, 16, 16, 20,  0,  0, 17, 16, 20,  0,  0, 17, 16, 16, 20,  0,  0,  0,  0,  0,  0,  0, 21,
            21,  0, 19, 18, 18, 18, 18, 18, 16, 16, 16, 20,  0,  0, 17, 16, 20,  0,  0, 17, 16, 16, 16, 18, 18, 18, 18, 18, 22,  0, 21,
            21,  0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 20,  0,  0, 17, 16, 20,  0,  0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 20,  0, 21,
            21,  0, 25, 24, 24, 24, 24, 24, 16, 16, 16, 20,  0,  0, 17, 16, 20,  0,  0, 17, 16, 16, 16, 24, 24, 24, 24, 24, 28,  0, 21,
            21,  0,  0,  0,  0,  0,  0,  0, 17, 16, 16, 16, 18, 18, 16, 16, 16, 18, 18, 16, 16, 16, 20,  0,  0,  0,  0,  0,  0,  0, 21,
            21,  0, 19, 18,  18, 18, 18, 18, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 18, 18, 18, 18, 18, 22, 0, 21,
            21,  0, 25, 24,  24, 24, 24, 24, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 24, 24, 24, 24, 24, 28, 0, 21,
            21,  0,  0,  0,   0,  0,  0,  0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20, 0,  0,  0,  0,  0,  0, 0, 21,
            25, 26, 26, 26, 26, 26, 26, 26, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 26, 26, 26, 26, 26, 26, 26, 28
    };

    private int GameSpeed1 = 30;
    private int GameSpeed2 = 15;
    private int GameSpeed3 = 10;

    /**
     * Model constructor
     * @param gameConfiguration - parameter containing game parameters
     * @param player - parameter containing player data
     */
    public Model(GameConfiguration gameConfiguration, Player player){
        this.gameVariable = new GameVariable();
        this.gameConfiguration = gameConfiguration;
        this.player = player;
        this.pacman = new Pacman(gameConfiguration.getPacmanRole(), PACMAN_SPEED, BLOCK_SIZE, N_BLOCKS_WIDTH, screenData, gameVariable);
        this.clyde = new Clyde(BLOCK_SIZE, N_BLOCKS_WIDTH, N_BLOCKS_HEIGHT, screenData, pacman);
        this.blinky = new Blinky(BLOCK_SIZE, N_BLOCKS_WIDTH, N_BLOCKS_HEIGHT, screenData, pacman);
        this.pinky = new Pinky(BLOCK_SIZE, N_BLOCKS_WIDTH, N_BLOCKS_HEIGHT, screenData, pacman);
        this.inky = new Inky(BLOCK_SIZE, N_BLOCKS_WIDTH, N_BLOCKS_HEIGHT, screenData, pacman);
        //Zaladowanie zdjec
        loadImages();
        //Inicjalizacja obrazkow
        initVariables();
        //Funkcja pozwolajaca na sterowanie pacmanem
        addKeyListener(new TAdapter());
        //Ustawienie widocznosci
        setFocusable(true);
        //Zainicjowanie gry
        initGame();
    }

    /**
     * Method that initialize game
     */
    //Inicjalizacja gry
    private void initGame() {
        //Ilosc zyc
        gameVariable.setLives(3);
        //Wynik
        gameVariable.setScore(0);
        //Inicjalizacja mapy
        initLevel();
        //Aktualna predkosc
        currentSpeed = 3;
        setGhostSpeed();
    }

    /**
     * Method that simulate the game, calculate position of ghost and pacman and check maze
     * @param g2d - graphics holder
     */
    //Funkcja
    private void playGame(Graphics2D g2d) {
        if (gameVariable.isDying()) {
            death();
        } else {
            pacman.movePacman();
            pacman.drawPacman(g2d);
            calculateGhostsPosition(g2d);
            checkMaze();
        }
    }

    /**
     * Method that calculate ghost position and draw a ghost
     * @param g2d - graphics holder
     */
    private void calculateGhostsPosition(Graphics2D g2d) {
        blinkyThread = new Thread(blinky);
        blinkyThread.start();

        clydeThread = new Thread(clyde);
        clydeThread.start();

        inkyThread = new Thread(inky);
        inkyThread.start();

        pinkyThread = new Thread(pinky);
        pinkyThread.start();

        try {
            blinkyThread.join();
            clydeThread.join();
            inkyThread.join();
            pinkyThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        blinky.drawBlinky(g2d);
        clyde.drawClyde(g2d);
        inky.drawInky(g2d);
        pinky.drawPinky(g2d);
    }

    /**
     * Method that load images
     */
    //Zaladowanie zdjec
    private void loadImages() {
        heart = new ImageIcon("Images/Lives/heart.png").getImage();
    }

    /**
     * Method that inicial variavles
     */
    //Zainicjalizowanie zmiennych
    private void initVariables() {
        //Tu byl screenData
        //Rozmiar okna
        d = new Dimension(1920, 1080);
        //Odswierzanie co 40 ms
        switch(gameConfiguration.getGameSpeed()){
            case 1:
                timer = new Timer(GameSpeed1, this);
                break;
            case 2:
                timer = new Timer(GameSpeed2, this);
                break;
            case 3:
                timer = new Timer(GameSpeed3, this);
                break;
        }
        //Start timera
        timer.start();
    }

    /**
     * Method that draw intro screen
     * @param g2d - graphics holder
     */
    //Wyswietlenie informacji o starcie gry
    private void showIntroScreen(Graphics2D g2d) {
        String start = "Press SPACE to start";
        g2d.setColor(Color.orange);
        g2d.drawString(start, (SCREEN_SIZE_WIDTH)/ 2.73F, (SCREEN_SIZE_HEIGHT)/2);
    }

    /**
     * Method that draw score
     * @param g - graphics holder
     */
    //Wyswietlenie wyniku
    private void drawScore(Graphics2D g) {
        g.setFont(smallFont);
        g.setColor(new Color(5, 181, 79));
        String s = "Score: " + gameVariable.getScore();
        g.drawString(s, (SCREEN_SIZE_WIDTH) / 1.22F, (SCREEN_SIZE_HEIGHT) + 50);

        for (int i = 0; i < gameVariable.getLives(); i++) {
            g.drawImage(heart, i * 38 + 100, (SCREEN_SIZE_HEIGHT) + 5, this);
        }
    }

    /**
     * Method that checks if there are any point to eat by packman on the maze
     */
    //Sprawdzenie czy wszystkie zetony zostaly zjedzone
    private void checkMaze() {

        int i = 0;
        boolean finished = true;

        while (i < N_BLOCKS_WIDTH * N_BLOCKS_HEIGHT && finished) {

            if ((screenData[i] & 16) != 0) {
                finished = false;
            }

            i++;
        }

        if (finished) {
            gameVariable.setScore((gameVariable.getScore() + 100));
            if(gameConfiguration.getLevel() >= MAX_MAP)
            {
                gameConfiguration.setLevel(1);
            }
            else
            {
                gameConfiguration.setLevel((gameConfiguration.getLevel() + 1));
            }
            if (currentSpeed < maxSpeed) {
                currentSpeed++;
                setGhostSpeed();
            }
            initLevel();
        }
    }

    /**
     * Method that substract pacman lives and show window when pacman doesn't have any lives
     */
    //Smierc pacmana
    private void death() {
        gameVariable.setLives((gameVariable.getLives() - 1));
        if (gameVariable.getLives() == 0) {
            gameVariable.setInGame(false);
            player.updateScore(gameVariable.getScore());
            StatisticsService.updateStatistics(player);

            this.setEnabled(false);
            alertWindow = new JFrame("Alert");
            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
            int x = (int) ((dimension.getWidth() - ALERT_WINDOW_X_SIZE) / 2);
            int y = (int) ((dimension.getHeight() - ALERT_WINDOW_Y_SIZE) / 2);
            alertWindow.setBounds(x, y,ALERT_WINDOW_X_SIZE, ALERT_WINDOW_Y_SIZE);
            alertWindow.getContentPane().setBackground(backgroundColor);
            alertWindow.setUndecorated (true);
            alertWindow.getContentPane().setLayout(null);
            alertWindow.setIconImage(pacmanImage);
            alertWindow.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, chosenButtonBackgroundColor));

            // Create alert label
            Label statsTitleLabel = new Label("GAME OVER");
            statsTitleLabel.setBounds(ALERT_LABEL_X_POSITION,ALERT_LABEL_Y_POSITION,ALERT_LABEL_WIDTH,ALERT_LABEL_HEIGHT);
            statsTitleLabel.setFont(menuFont);
            statsTitleLabel.setForeground(textColor);
            alertWindow.getContentPane().add(statsTitleLabel);

            // Create ok button
            JButton okButton = new JButton("Ok");
            okButton.setBounds(OK_BUTTON_X_POSITION,OK_BUTTON_Y_POSITION,OK_BUTTON_WIDTH,OK_BUTTON_HEIGHT);
            okButton.setBackground(backgroundColor);
            okButton.setForeground(textColor);
            okButton.setBorderPainted(true);
            okButton.setFont(menuFont);
            okButton.addActionListener(this);
            alertWindow.getContentPane().add(okButton);

            alertWindow.setVisible(true);

            StartWindow.disablePacWindow(false);
            alertWindow.toFront();
            timer.stop();
        }else{
            continueLevel();
        }
    }

    /**
     * Method that draw maze
     * @param g2d - graphics holder
     */
    //Rysowanie planszy
    private void drawMaze(Graphics2D g2d) {

        short i = 0;
        int x, y;

        for (y = 0; y < SCREEN_SIZE_HEIGHT; y += BLOCK_SIZE) {
            for (x = 0; x <SCREEN_SIZE_WIDTH; x += BLOCK_SIZE) {

                g2d.setColor(new Color(0,72,251));

                g2d.setStroke(new BasicStroke(5));

                switch(gameConfiguration.getLevel()){
                    case 1:
                        if ((levelData1[i] == 0)) {
                            g2d.setColor(new Color(0,0,0));
                            g2d.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
                        }
                        break;

                    case 2:
                        if ((levelData2[i] == 0)) {
                            g2d.setColor(new Color(0,0,0));
                            g2d.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
                        }
                        break;

                    case 3:
                        if ((levelData3[i] == 0)) {
                            g2d.setColor(new Color(0,0,0));
                            g2d.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
                        }
                        break;
                }

                if ((screenData[i] & 1) != 0) {
                    g2d.drawLine(x, y, x, y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 2) != 0) {
                    g2d.drawLine(x, y, x + BLOCK_SIZE - 1, y);
                }

                if ((screenData[i] & 4) != 0) {
                    g2d.drawLine(x + BLOCK_SIZE - 1, y, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 8) != 0) {
                    g2d.drawLine(x, y + BLOCK_SIZE - 1, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 16) != 0) {
                    g2d.setColor(new Color(255,255,255));
                    g2d.fillOval(x + BLOCK_SIZE/2 - 6, y + BLOCK_SIZE/2 - 6, 12, 12);
                }

                i++;
            }
        }
    }

    /**
     * Method that loading a map
     */
    //Przepisujemy mape do mapy, ktora bedzie wyswietlana
    private void initLevel() {
        switch(gameConfiguration.getLevel()){
            case 1:
                for (int i = 0; i < N_BLOCKS_WIDTH * N_BLOCKS_HEIGHT; i++) {
                    screenData[i] = levelData1[i];
                }
                break;
            case 2:
                for (int i = 0; i < N_BLOCKS_WIDTH * N_BLOCKS_HEIGHT; i++) {
                    screenData[i] = levelData2[i];
                }
                break;
            case 3:
                for (int i = 0; i < N_BLOCKS_WIDTH * N_BLOCKS_HEIGHT; i++) {
                    screenData[i] = levelData3[i];
                }
                break;
            default:
                System.out.println("Bład: nieprawidłowy level");
                break;
        }
        continueLevel();
    }

    /**
     * Method that set start position ghosts and pacman
     */
    //Funkcja ustawiajaca duchy i pacmana w pozycji poczatkowej
    private void continueLevel() {

        int random;

        //Pozycja startowa duchow
        clyde.ghost_y = START_POSITION_GHOST_X * BLOCK_SIZE; //start position
        clyde.ghost_x = START_POSITION_GHOST_Y * BLOCK_SIZE;
        clyde.ghost_dy = 0;
        clyde.ghost_dx = -1;

        //Pozycja startowa duchow
        blinky.ghost_y = START_POSITION_GHOST_X * BLOCK_SIZE; //start position
        blinky.ghost_x = START_POSITION_GHOST_Y * BLOCK_SIZE;
        blinky.ghost_dy = 0;
        blinky.ghost_dx = -1;

        //Pozycja startowa duchow
        pinky.ghost_y = START_POSITION_GHOST_X * BLOCK_SIZE; //start position
        pinky.ghost_x = START_POSITION_GHOST_Y * BLOCK_SIZE;
        pinky.ghost_dy = 0;
        pinky.ghost_dx = -1;

        //Pozycja startowa duchow
        inky.ghost_y = START_POSITION_GHOST_X * BLOCK_SIZE; //start position
        inky.ghost_x = START_POSITION_GHOST_Y * BLOCK_SIZE;
        inky.ghost_dy = 0;
        inky.ghost_dx = -1;

        //Pozycja startowa pacmana
        pacman.pacman_x = START_POSITION_PACMAN_X * BLOCK_SIZE;
        pacman.pacman_y = START_POSITION_PACMAN_Y * BLOCK_SIZE;
        //Reset kierunku ruchu
        pacman.pacman_dx = 0;
        pacman.pacman_dy = 0;
        //Reset kierunku sterowania
        pacman.req_dx = 0;
        pacman.req_dy = 0;
        gameVariable.setDying(false);
    }

    /**
     * Method that draw a components
     * @param g - graphics holder
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        //Kolor planszy
        g2d.setColor(Color.black);
        //Rozmiar planszy
        g2d.fillRect(0, 0, d.width, d.height);
        //Rysuj plansze
        drawMaze(g2d);
        //Rysuj wynik
        drawScore(g2d);
        if (gameVariable.isInGame()) {
            playGame(g2d);
        } else {
            showIntroScreen(g2d);
        }
        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    }

    /**
     * Methot that have interaction with player and allow control a pacman
     */
    //controls
    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();
            //Jesli jestesmy w grze to
            if (gameVariable.isInGame()) {
                //Nacisniecie lewej strzalki ruch w lewo
                if (key == KeyEvent.VK_LEFT) {
                    pacman.req_dx = -1;
                    pacman.req_dy = 0;
                    //Nacisniecie prawej strzalki ruch w prawo
                } else if (key == KeyEvent.VK_RIGHT) {
                    pacman.req_dx = 1;
                    pacman.req_dy = 0;
                    //Nacisniecie górnej strzalki ruch w góre
                } else if (key == KeyEvent.VK_UP) {
                    pacman.req_dx = 0;
                    pacman.req_dy = -1;
                    //Nacisniecie górnej strzalki ruch w dolnej
                } else if (key == KeyEvent.VK_DOWN) {
                    pacman.req_dx = 0;
                    pacman.req_dy = 1;
                    //Jesli gra jest uruchomiona i nacisniete zostanie ESC to wychodzimy z gry
                } else if (key == KeyEvent.VK_ESCAPE && timer.isRunning()) {
                    gameVariable.setInGame(false);
                }
            }//Jesli nie jestesmy w grze nacisniecie spacji powoduje rozpoczecie gry
            else {
                if (key == KeyEvent.VK_SPACE) {
                    gameVariable.setInGame(true);
                    initGame();
                }
            }
        }
    }

    /**
     * Methot that set ghost speed as random from 1 to currentSpeed
     */
    public void setGhostSpeed(){
        blinky.setGhostRandomSpeed(currentSpeed);
        pinky.setGhostRandomSpeed(currentSpeed);
        inky.setGhostRandomSpeed(currentSpeed);
        clyde.setGhostRandomSpeed(currentSpeed);
    }

    /**
     * Override the actionPerformed method
     * @param e - given action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        if (e.getActionCommand() != null){
            if (e.getActionCommand().equals("Ok")) {
                alertWindow.dispose();
                this.setEnabled(true);
                StartWindow.RunGame();
                StartWindow.disablePacWindow(true);
                StartWindow.windowToFront();
            }
        }
    }

}
