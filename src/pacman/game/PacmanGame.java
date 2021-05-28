package pacman.game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class PacmanGame extends JFrame{

    public PacmanGame(){

    }

    public PacmanGame(int level, int gameSpeed, int pacmanRole, Player currentPlayer) {
        add(new Model(new GameConfiguration(level,gameSpeed,pacmanRole),currentPlayer));
    }

    public static void main(String[] args) {
        StartWindow.RunGame();
    }
}
