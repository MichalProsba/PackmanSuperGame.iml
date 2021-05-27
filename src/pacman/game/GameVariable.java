package pacman.game;

public class GameVariable {
    //Zycia
    private int lives;
    //Wynik
    private int score;
    //Zmienna mowiaca czy jestesmy w grze
    private boolean inGame;
    //Zmienna mowiaca czy pacman zyje
    private boolean dying;

    public GameVariable(){
        this.lives = 3;
        this.score = 0;
        this.inGame = false;
        this.dying = false;
    }

    public GameVariable(int lives, int score, boolean inGame, boolean dying){
        this.lives = lives;
        this.score = score;
        this.inGame = inGame;
        this.dying = dying;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public boolean isDying() {
        return dying;
    }

    public void setDying(boolean dying) {
        this.dying = dying;
    }
}
