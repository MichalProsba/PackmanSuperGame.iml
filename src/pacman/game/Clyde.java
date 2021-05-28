package pacman.game;

import java.awt.*;
import javax.swing.ImageIcon;

public class Clyde extends Ghost implements Runnable{

    //Zdjecie Clyda
    public Image clydeImage;

    public Clyde(){

    }

    public Clyde(int BLOCK_SIZE, int N_BLOCKS_WIDTH, int N_BLOCKS_HEIGHT, short[] screenData, Pacman pacman){
        super(BLOCK_SIZE, N_BLOCKS_WIDTH, N_BLOCKS_HEIGHT, screenData ,pacman);
        clydeImage = new ImageIcon("Images/Ghosts/clyde.gif").getImage();
    }

    //Ruchy duchów
    public void moveGhosts() {

        int pos;
        int count;

            if (ghost_x % BLOCK_SIZE == 0 && ghost_y % BLOCK_SIZE == 0) {
                pos = ghost_x / BLOCK_SIZE + N_BLOCKS_WIDTH * (int) (ghost_y / BLOCK_SIZE);

                count = 0;

                if ((screenData[pos] & 1) == 0 && ghost_dx != 1) {
                    dx[count] = -1;
                    dy[count] = 0;
                    count++;
                }

                if ((screenData[pos] & 2) == 0 && ghost_dy != 1) {
                    dx[count] = 0;
                    dy[count] = -1;
                    count++;
                }

                if ((screenData[pos] & 4) == 0 && ghost_dx != -1) {
                    dx[count] = 1;
                    dy[count] = 0;
                    count++;
                }

                if ((screenData[pos] & 8) == 0 && ghost_dy != -1) {
                    dx[count] = 0;
                    dy[count] = 1;
                    count++;
                }

                if (count == 0) {

                    if ((screenData[pos] & 15) == 15) {
                        ghost_dx = 0;
                        ghost_dy = 0;
                    } else {
                        ghost_dx = -ghost_dx;
                        ghost_dy = -ghost_dy;
                    }

                } else {

                    count = (int) (Math.random() * count);
                    if (count > 3) {
                        count = 3;
                    }

                    ghost_dx = dx[count];
                    ghost_dy = dy[count];
                }

            }

            ghost_x = ghost_x + (ghost_dx * ghostSpeed);
            ghost_y = ghost_y + (ghost_dy * ghostSpeed);
    }

    //Rysowanie Ducha
    public void drawGhost(Graphics2D g2d, int x, int y) {
        g2d.drawImage(clydeImage, x, y, this);
    }

    @Override
    public void run() {
        moveGhosts();
    }

    public void drawClyde(Graphics2D g2d){
        drawGhost(g2d, ghost_x + 1, ghost_y + 1);
        checkColision();
    }
}
