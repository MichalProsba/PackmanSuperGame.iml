package pacman.game;

public class GameConfiguration {
    private int Level = 1;
    //Predkosc gry
    private int GameSpeed = 2;
    //Rola pacmana
    private int PacmanRole = 3;

    public GameConfiguration(){
        this.PacmanRole = 1;
        this.Level = 1;
        this.GameSpeed = 2;
    }

    public GameConfiguration(int Level, int GameSpeed, int PacmanRole){
        this.PacmanRole = PacmanRole;
        this.Level = Level;
        this.GameSpeed = GameSpeed;
    }

    public int getLevel() {
        return Level;
    }

    public void setLevel(int level) {
        Level = level;
    }

    public int getGameSpeed() {
        return GameSpeed;
    }

    public void setGameSpeed(int gameSpeed) {
        GameSpeed = gameSpeed;
    }

    public int getPacmanRole() {
        return PacmanRole;
    }

    public void setPacmanRole(int pacmanRole) {
        PacmanRole = pacmanRole;
    }
}
