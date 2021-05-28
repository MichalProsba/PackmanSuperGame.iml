package pacman.game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

/**
 * Represents the pacman game
 */
public class PacmanGame extends JFrame{

    /**
     * Constructor
     */
    public PacmanGame(){

    }

    /**
     * Parametric constructor
     * @param level Chosen level
     * @param gameSpeed Chosen speed
     * @param pacmanRole Chosen role
     * @param currentPlayer Current player info
     */
    public PacmanGame(int level, int gameSpeed, int pacmanRole, Player currentPlayer) {
        add(new Model(new GameConfiguration(level,gameSpeed,pacmanRole),currentPlayer));
    }

    /**
     * Main function
     * @param args Given args
     */
    public static void main(String[] args) {
        StartWindow.RunGame();
    }
}
