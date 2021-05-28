package pacman.game;

import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Represent pacman
 */
public class Pacman extends JPanel{

    //Polozenie, zmiana polozenia predkosc pacmana
    public int pacman_x;
    public int pacman_y;
    public int pacman_dx;
    public int pacman_dy;

    //Ruchy pacmana w zaleznosci od klawiszy
    public int req_dx;
    public int req_dy;

    //Predkosc Pacmana
    public int PACMAN_SPEED;

    //Zdjecia Pacmana
    public Image up, down, left, right;

    //Wysokosc i szerokosc pojedynczego bloku
    private int BLOCK_SIZE;

    //Ilosc blokow na szerokosc
    private int N_BLOCKS_WIDTH;

    //Wynik
    private GameVariable gameVariable;

    //Plansza
    private short[] screenData;

    /**
     * Pacman constructor
     */
    public Pacman(){

    }

    /**
     * Pacman constructor
     * @param pacmanRole - pacman role
     * @param PACMAN_SPEED - pacman speed
     * @param BLOCK_SIZE - block size
     * @param N_BLOCKS_WIDTH - numbers of blocks in width
     * @param screenData - includes map model
     * @param gameVariable - structure that contains a game variables
     */
    public Pacman(int pacmanRole, int PACMAN_SPEED, int BLOCK_SIZE, int N_BLOCKS_WIDTH, short[] screenData, GameVariable gameVariable) {
        switch (pacmanRole) {
            case 1:
                down = new ImageIcon("Images/PacmanClassic/down.gif").getImage();
                up = new ImageIcon("Images/PacmanClassic/up.gif").getImage();
                left = new ImageIcon("Images/PacmanClassic/left.gif").getImage();
                right = new ImageIcon("Images/PacmanClassic/right.gif").getImage();
                break;
            case 2:
                down = new ImageIcon("Images/PacmanGirl/down.gif").getImage();
                up = new ImageIcon("Images/PacmanGirl/up.gif").getImage();
                left = new ImageIcon("Images/PacmanGirl/left.gif").getImage();
                right = new ImageIcon("Images/PacmanGirl/right.gif").getImage();
                break;
            case 3:
                down = new ImageIcon("Images/PacmanBoy/down.gif").getImage();
                up = new ImageIcon("Images/PacmanBoy/up.gif").getImage();
                left = new ImageIcon("Images/PacmanBoy/left.gif").getImage();
                right = new ImageIcon("Images/PacmanBoy/right.gif").getImage();
                break;
            default:
                System.out.println("Błąd: nieprawidłowa rola");
                break;
        }
        this.PACMAN_SPEED = PACMAN_SPEED;
        this.BLOCK_SIZE = BLOCK_SIZE;
        this.N_BLOCKS_WIDTH = N_BLOCKS_WIDTH;
        this.screenData = screenData;
        this.gameVariable = gameVariable;
    }

    /**
     * Method that draw pacman
     * @param g2d - graphics holder
     */
    //Narysuj pacmana
    public void drawPacman(Graphics2D g2d) {
        if (req_dx == -1) {
            g2d.drawImage(left, pacman_x + 1, pacman_y + 1, this);
        } else if (req_dx == 1) {
            g2d.drawImage(right, pacman_x + 1, pacman_y + 1, this);
        } else if (req_dy == -1) {
            g2d.drawImage(up, pacman_x + 1, pacman_y + 1, this);
        } else {
            g2d.drawImage(down, pacman_x + 1, pacman_y + 1, this);
        }
    }

    /**
     * Methot tha move pacman
     */
    //Ruchy Pacmana
    public void movePacman() {
        int pos;
        short ch;
        if (pacman_x % BLOCK_SIZE == 0 && pacman_y % BLOCK_SIZE == 0) {
            pos = pacman_x / BLOCK_SIZE + N_BLOCKS_WIDTH * (int) (pacman_y / BLOCK_SIZE);
            ch = screenData[pos];
            //Jesli znajduje sie w polu z kropka
            if ((ch & 16) != 0) {
                //Zjada kropke
                // 10011
                //&01111
                // 00011
                screenData[pos] = (short) (ch & 15);
                //Zwiekszenie wyniku
                gameVariable.setScore((gameVariable.getScore()+1));
            }
            //Jesli req_dx lub req_dy sa rozne od 0 to Pacman jest kontrolowany
            if (req_dx != 0 || req_dy != 0) {
                //Jesli pacman nie jest na krawedzi to mozna nim ruszyc
                //Ruch w lewo i nie jestesmy w lewej krawedzi to zrob ruch itd
                if (!((req_dx == -1 && req_dy == 0 && (ch & 1) != 0)
                        || (req_dx == 1 && req_dy == 0 && (ch & 4) != 0)
                        || (req_dx == 0 && req_dy == -1 && (ch & 2) != 0)
                        || (req_dx == 0 && req_dy == 1 && (ch & 8) != 0))) {
                    pacman_dx = req_dx;
                    pacman_dy = req_dy;
                }
            }
            //Jesli natrafi sie przeszkoda zatrzymaj pacmana
            if ((pacman_dx == -1 && pacman_dy == 0 && (ch & 1) != 0)
                    || (pacman_dx == 1 && pacman_dy == 0 && (ch & 4) != 0)
                    || (pacman_dx == 0 && pacman_dy == -1 && (ch & 2) != 0)
                    || (pacman_dx == 0 && pacman_dy == 1 && (ch & 8) != 0)) {
                pacman_dx = 0;
                pacman_dy = 0;
            }
        }
        //Zmien pozycje pacmana
        pacman_x = pacman_x + PACMAN_SPEED * pacman_dx;
        pacman_y = pacman_y + PACMAN_SPEED * pacman_dy;
    }

    /**
     * Getter gameVariable
     * @return - gameVariable
     */
    public GameVariable getGameVariable() {
        return gameVariable;
    }

    /**
     * Setter gameVariable
     * @param gameVariable - game variable
     */
    public void setGameVariable(GameVariable gameVariable) {
        this.gameVariable = gameVariable;
    }
}
