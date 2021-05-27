package pacman.game;

import javax.swing.JFrame;

public class PacmanGame extends JFrame{

    public PacmanGame(int level, int gameSpeed, int pacmanRole) {
        add(new Model(new GameConfiguration(level,gameSpeed,pacmanRole)));
    }

    public static void main(String[] args) {
        StartWindow.RunGame();
    }
}
