package pacman.game;

/**
 * PLayer class represent a player
 */
public class Player implements Comparable<Player>{
    private String nickname;
    private Integer score = 0;
    private Integer rankingPosition = 10000;

    /**
     * Gets the nickname
     * @return The nickname
     */
    String getNickname(){
        return this.nickname;
    }

    /**
     * Gets the score
     * @return The score
     */
    public Integer getScore(){
        return this.score;
    }

    /**
     * Gets the ranking position
     * @return The ranking position
     */
    public Integer getRankingPosition(){return this.rankingPosition;}

    /**
     * Contructor
     * @param currentPlayer Nickname of the player
     */
    Player(String currentPlayer){
        nickname = currentPlayer;
    }

    /**
     * Constructor 2
     * @param currentPlayer Nickname of the player
     * @param currentScore Score of the Player
     * @param currentRankingPosition Ranking position of the player
     */
    Player(String currentPlayer, Integer currentScore, Integer currentRankingPosition){
        nickname = currentPlayer;
        score = currentScore;
        rankingPosition = currentRankingPosition;
    }

    /**
     * Updates score
     * @param newScore New score
     */
    void updateScore(int newScore){
        this.score = newScore;
    }

    /**
     * Updates ranking position
     * @param newPosition New ranking position
     */
    void updateRankingPosition(int newPosition){
        this.rankingPosition = newPosition;
    }

    /**
     * Overrides the compareTo method
     * @param o Player to compare
     * @return Compared score
     */
    @Override
    public int compareTo(Player o) {
        if (getScore() == -1 || o.getScore() == -1) {
            return 0;
        }
        return getScore().compareTo(o.getScore());
    }
}
