package pacman.game;

/**
 * Represents the game variables such as lives, score, etc.
 */
public class GameVariable {
    //Zycia
    private int lives;
    //Wynik
    private int score;
    //Zmienna mowiaca czy jestesmy w grze
    private boolean inGame;
    //Zmienna mowiaca czy pacman zyje
    private boolean dying;

    /**
     * Constructor which sets default values to the game variables
     */
    public GameVariable(){
        this.lives = 3;
        this.score = 0;
        this.inGame = false;
        this.dying = false;
    }

    /**
     * Parametric contructor
     * @param lives Left lives
     * @param score Current score
     * @param inGame In game
     * @param dying Loosing lives
     */
    public GameVariable(int lives, int score, boolean inGame, boolean dying){
        this.lives = lives;
        this.score = score;
        this.inGame = inGame;
        this.dying = dying;
    }

    /**
     * Gets lives
     * @return Number of lives
     */
    public int getLives() {
        return lives;
    }

    /**
     * Sets lives
     * @param lives Number of lives
     */
    public void setLives(int lives) {
        this.lives = lives;
    }

    /**
     * Gets score
     * @return Score
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets score
     * @param score Score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Gets if player is in game
     * @return If the player is in game
     */
    public boolean isInGame() {
        return inGame;
    }

    /**
     * Sets if the player is in game
     * @param inGame Is player in game
     */
    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    /**
     * Gets if player is dying
     * @return Is player dying
     */
    public boolean isDying() {
        return dying;
    }

    /**
     * Sets if player is dying
     * @param dying Is player dying
     */
    public void setDying(boolean dying) {
        this.dying = dying;
    }
}
