package pacman.game;

import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

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

    //Zmienna mowiaca czy jestesmy w grze

    //Pacman
    Pacman pacman = new Pacman();

    short[] screenData;

    protected GameVariable gameVariable;

    public Ghost(){

    }

    public Ghost(int BLOCK_SIZE, int N_BLOCKS_WIDTH, short[] screenData, Pacman pacman){
        this.BLOCK_SIZE = BLOCK_SIZE;
        this.N_BLOCKS_WIDTH = N_BLOCKS_WIDTH;
        this.screenData = screenData;
        this.pacman = pacman;
        this.gameVariable = pacman.getGameVariable();
    }



}
