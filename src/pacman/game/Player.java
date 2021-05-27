package pacman.game;

public class Player implements Comparable<Player>{
    private String nickname;
    private Integer score = 0;
    private Integer rankingPosition = 10000;

    String getNickname(){
        return this.nickname;
    }

    public Integer getScore(){
        return this.score;
    }

    public Integer getRankingPosition(){return this.rankingPosition;}

    Player(String currentPlayer){
        nickname = currentPlayer;
    }

    Player(String currentPlayer, Integer currentScore, Integer currentRankingPosition){
        nickname = currentPlayer;
        score = currentScore;
        rankingPosition = currentRankingPosition;
    }

    void updateScore(int newScore){
        this.score = newScore;
    }

    void updateRankingPosition(int newPosition){
        this.rankingPosition = newPosition;
    }

    @Override
    public int compareTo(Player o) {
        if (getScore() == -1 || o.getScore() == -1) {
            return 0;
        }
        return getScore().compareTo(o.getScore());
    }
}
