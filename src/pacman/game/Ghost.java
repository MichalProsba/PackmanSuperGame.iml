package pacman.game;

import javax.swing.JPanel;
import java.util.Random;

public class Ghost extends JPanel {
    //Polozenie duchow
    public int ghost_x;
    public int ghost_y;
    public int ghost_dx;
    public int ghost_dy;
    public int ghostSpeed;

    //Polozenie duchow
    protected int[] dx = new int[4];
    protected int [] dy = new int[4];

    //Wysokosc i szerokosc pojedynczego bloku
    protected int BLOCK_SIZE;

    //Ilosc blokow na szerokosc
    protected int N_BLOCKS_WIDTH;
    //Ilosc blokow na szerokosc
    protected int N_BLOCKS_HEIGHT;

    //Tablica dopuszczalnych predkosci
    private final int validSpeeds[] = {1, 2, 3, 4, 5, 6};

    //Zmienna mowiaca czy jestesmy w grze

    //Pacman
    Pacman pacman = new Pacman();

    short[] screenData;

    protected GameVariable gameVariable;

    public Ghost(){

    }

    /**
     * Ghost constructor
     * @param BLOCK_SIZE - block size
     * @param N_BLOCKS_WIDTH - numbers of blocks in width
     * @param N_BLOCKS_HEIGHT - number of blocks in height
     * @param screenData - includes map model
     * @param pacman - include pacman object
     */
    public Ghost(int BLOCK_SIZE, int N_BLOCKS_WIDTH, int N_BLOCKS_HEIGHT, short[] screenData, Pacman pacman){
        this.BLOCK_SIZE = BLOCK_SIZE;
        this.N_BLOCKS_WIDTH = N_BLOCKS_WIDTH;
        this.N_BLOCKS_HEIGHT = N_BLOCKS_HEIGHT;
        this.screenData = screenData;
        this.pacman = pacman;
        this.gameVariable = pacman.getGameVariable();
    }

    /**
     * Method that set ghost random speed from 1 to currentSpeed
     * @param currentSpeed - current speed ghost depending on level
     */
    public void setGhostRandomSpeed(int currentSpeed){
        //Randomowe predkosci duchow
        int random = (int) (Math.random() * (currentSpeed + 1));
        if (random > currentSpeed) {
            random = currentSpeed;
        }
        ghostSpeed = validSpeeds[random];
    }

    /**
     * Method that check colision ghost with pacman
     */
    public void checkColision() {
        if (pacman.pacman_x > (ghost_x - 12) && pacman.pacman_x < (ghost_x + 12)
                && pacman.pacman_y > (ghost_y - 12) && pacman.pacman_y < (ghost_y + 12)
                && gameVariable.isInGame()) {
            gameVariable.setDying(true);
        }
    }
}
