package pacman.game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Model extends JPanel implements ActionListener {
    //Zycia
    private Player player;

    private GameVariable gameVariable;

    //Konfiguracja gry, zawierajaca role, mape, szybkosc gry
    private GameConfiguration gameConfiguration;

    //Rozmiar jako wysokosc i szerokosc w pikselach
    private Dimension d;
    //Rozmiar i rodzaj czcionki
    private final Font smallFont = new Font("Arial", Font.BOLD, 48);

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

    //Tablica dopuszczalnych predkosci
    private final int validSpeeds[] = {1, 2, 3, 4, 6, 8};
    //Maksymalna predkosc
    private final int maxSpeed = 6;
    //Aktualna predkosc
    private int currentSpeed = 3;
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

    //Inicjalizacja gry
    private void initGame() {
        //Ilosc zyc
        gameVariable.setLives(3);
        //Wynik
        gameVariable.setScore(0);
        //Inicjalizacja mapy
        initLevel();
        //Aktualna ilosc duchow
        N_GHOSTS = 6;
        //Aktualna predkosc
        currentSpeed = 3;
    }

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

    private void calculateGhostsPosition(Graphics2D g2d) {
        blinkyThread = new Thread(blinky);
        blinkyThread.start();
        blinky.drawBlinky(g2d);

        clydeThread = new Thread(clyde);
        clydeThread.start();
        clyde.drawClyde(g2d);

        inkyThread = new Thread(inky);
        inkyThread.start();
        inky.drawInky(g2d);

        pinkyThread = new Thread(pinky);
        pinkyThread.start();
        pinky.drawPinky(g2d);

        try {
            blinkyThread.join();
            clydeThread.join();
            inkyThread.join();
            pinkyThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Zaladowanie zdjec
    private void loadImages() {
        heart = new ImageIcon("Images/Lives/heart.png").getImage();
    }

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

    //Wyswietlenie informacji o starcie gry
    private void showIntroScreen(Graphics2D g2d) {
        String start = "Press SPACE to start";
        g2d.setColor(Color.orange);
        g2d.drawString(start, (SCREEN_SIZE_WIDTH)/ 2.73F, (SCREEN_SIZE_HEIGHT)/2);
    }

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

            /*
            if (N_GHOSTS < MAX_GHOSTS) {
                N_GHOSTS++;
            }
             */

            if (currentSpeed < maxSpeed) {
                currentSpeed++;
                if(gameConfiguration.getLevel() >= MAX_MAP)
                {
                    gameConfiguration.setLevel(1);
                }
                else
                {
                    gameConfiguration.setLevel((gameConfiguration.getLevel() + 1));
                }
            }
            initLevel();
        }
    }

    //Smierc pacmana
    private void death() {
        gameVariable.setLives((gameVariable.getLives() - 1));
        if (gameVariable.getLives() == 0) {
            gameVariable.setInGame(false);
            player.updateScore(gameVariable.getScore());
            StatisticsService.updateStatistics(player, "Statistics/ranking.txt");
            StartWindow.RunGame();


        }
        continueLevel();
    }

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

    //Funkcja ustawiajaca duchy i pacmana w pozycji poczatkowej
    private void continueLevel() {

        int random;

        //Pozycja startowa duchow
        clyde.ghost_y = 4 * BLOCK_SIZE; //start position
        clyde.ghost_x = 4 * BLOCK_SIZE;
        clyde.ghost_dy = 0;
        clyde.ghost_dx = -1;
        //Randomowe predkosci duchow
        random = (int) (Math.random() * (currentSpeed + 1));
        if (random > currentSpeed) {
            random = currentSpeed;
        }
        clyde.ghostSpeed = validSpeeds[random];

        //Pozycja startowa duchow
        blinky.ghost_y = 4 * BLOCK_SIZE; //start position
        blinky.ghost_x = 4 * BLOCK_SIZE;
        blinky.ghost_dy = 0;
        blinky.ghost_dx = -1;
        //Randomowe predkosci duchow
        random = (int) (Math.random() * (currentSpeed + 1));
        if (random > currentSpeed) {
            random = currentSpeed;
        }
        blinky.ghostSpeed = validSpeeds[random];

        //Pozycja startowa duchow
        pinky.ghost_y = 4 * BLOCK_SIZE; //start position
        pinky.ghost_x = 4 * BLOCK_SIZE;
        pinky.ghost_dy = 0;
        pinky.ghost_dx = -1;
        //Randomowe predkosci duchow
        random = (int) (Math.random() * (currentSpeed + 1));
        if (random > currentSpeed) {
            random = currentSpeed;
        }
        pinky.ghostSpeed = validSpeeds[random];

        //Pozycja startowa duchow
        inky.ghost_y = 4 * BLOCK_SIZE; //start position
        inky.ghost_x = 4 * BLOCK_SIZE;
        inky.ghost_dy = 0;
        inky.ghost_dx = -1;
        //Randomowe predkosci duchow
        random = (int) (Math.random() * (currentSpeed + 1));
        if (random > currentSpeed) {
            random = currentSpeed;
        }
        inky.ghostSpeed = validSpeeds[random];

        //Pozycja startowa pacmana
        pacman.pacman_x = 7 * BLOCK_SIZE;
        pacman.pacman_y = 11 * BLOCK_SIZE;
        //Reset kierunku ruchu
        pacman.pacman_dx = 0;
        pacman.pacman_dy = 0;
        //Reset kierunku sterowania
        pacman.req_dx = 0;
        pacman.req_dy = 0;
        gameVariable.setDying(false);
    }

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

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

}
