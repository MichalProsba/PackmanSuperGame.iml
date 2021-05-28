package pacman.game;

/**
 * Represents the game configuration
 */
public class GameConfiguration {
    private int Level = 1;
    //Predkosc gry
    private int GameSpeed = 2;
    //Rola pacmana
    private int PacmanRole = 3;

    /**
     * Contructor
     */
    public GameConfiguration(){
        this.PacmanRole = 1;
        this.Level = 1;
        this.GameSpeed = 2;
    }

    /**
     * Parametric constructor
     * @param Level Current level
     * @param GameSpeed Current game speed
     * @param PacmanRole Pacman role
     */
    public GameConfiguration(int Level, int GameSpeed, int PacmanRole){
        this.PacmanRole = PacmanRole;
        this.Level = Level;
        this.GameSpeed = GameSpeed;
    }

    /**
     * Gets current level
     * @return Current level
     */
    public int getLevel() {
        return Level;
    }

    /**
     * Sets level
     * @param level Current level
     */
    public void setLevel(int level) {
        Level = level;
    }

    /**
     * Gets game speed
     * @return Current game speed
     */
    public int getGameSpeed() {
        return GameSpeed;
    }

    /**
     * Sets game speed
     * @param gameSpeed Current game speed
     */
    public void setGameSpeed(int gameSpeed) {
        GameSpeed = gameSpeed;
    }

    /**
     * Gets pacman role
     * @return Current pacman role
     */
    public int getPacmanRole() {
        return PacmanRole;
    }

    /**
     * Sets pacman role
     * @param pacmanRole Current pacman role
     */
    public void setPacmanRole(int pacmanRole) {
        PacmanRole = pacmanRole;
    }
}
