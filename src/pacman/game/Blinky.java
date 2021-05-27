package pacman.game;

import java.awt.*;
import javax.swing.ImageIcon;

public class Blinky extends Ghost{

    //Zdjecie Clyda
    public Image blinkyImage;

    public Blinky(){

    }

    public Blinky(int BLOCK_SIZE, int N_BLOCKS_WIDTH, int N_BLOCKS_HEIGHT, short[] screenData, Pacman pacman){
        super(BLOCK_SIZE, N_BLOCKS_WIDTH, N_BLOCKS_HEIGHT, screenData ,pacman);
        blinkyImage = new ImageIcon("Images/Ghosts/blinky.gif").getImage();
    }

    //Ruchy duchów
    public void moveGhosts(Graphics2D g2d) {

        int pos;
        int count;
        double distance;
        double minDistance;
        int indexMinDistance;

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
                minDistance = BLOCK_SIZE * (N_BLOCKS_WIDTH + N_BLOCKS_HEIGHT);
                indexMinDistance = 0;
                for(int i = 0; i < count; i++){
                    distance = Math.sqrt(Math.pow(pacman.pacman_x - ghost_x - dx[i] * BLOCK_SIZE, 2) + Math.pow(pacman.pacman_y - ghost_y - dy[i] * BLOCK_SIZE, 2));
                    if(minDistance > distance){
                        minDistance = distance;
                        indexMinDistance = i;
                    }
                }
                ghost_dx = dx[indexMinDistance];
                ghost_dy = dy[indexMinDistance];
            }

        }

        ghost_x = ghost_x + (ghost_dx * ghostSpeed);
        ghost_y = ghost_y + (ghost_dy * ghostSpeed);
        drawGhost(g2d, ghost_x + 1, ghost_y + 1);

        if (pacman.pacman_x > (ghost_x - 12) && pacman.pacman_x < (ghost_x + 12)
                && pacman.pacman_y > (ghost_y - 12) && pacman.pacman_y < (ghost_y + 12)
                && gameVariable.isInGame()) {
            gameVariable.setDying(true);
        }
    }

    //Rysowanie Ducha
    public void drawGhost(Graphics2D g2d, int x, int y) {
        g2d.drawImage(blinkyImage, x, y, this);
    }

}
