package pacman.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * StatisticsService - handles the statistics of the game
 */
public class StatisticsService {
    private static final int statsLimit = 10;

    /**
     * Gets the current game stats
     * @return current Stats in a ready to display version
     */
    public static String[] getStats(){
        String[] tmpStats = FileService.getFileContent("Statistics/ranking.txt");
        String[] returnStats = new String[statsLimit];
        for (int i = 0; i < statsLimit; i++) {
            returnStats[i] = tmpStats[i].replace(':',' ');
        }
        return returnStats;
    }

    /**
     * Updates game statistics
     * @param currentPlayer represents the current player
     */
    public static void updateStatistics(Player currentPlayer){
        String[] stats = FileService.getFileContent("Statistics/ranking.txt");
        int howManyPlayers = stats.length;
        List<Player> players = new ArrayList<>();
        boolean isAlreadyThere = false;
        for(int i = 0; i < howManyPlayers; i++){
            if (stats[i].length() > 0) {
                Integer tmpRankingPosition = Integer.parseInt(stats[i].substring(0, stats[i].indexOf('.')));
                String tmpNickname = stats[i].substring(stats[i].indexOf(':')+1, stats[i].lastIndexOf(':'));
                Integer tmpScore = Integer.parseInt(stats[i].substring(stats[i].lastIndexOf(':')+1));
                Player tmpPlayer;
                tmpPlayer = new Player(tmpNickname, tmpScore, tmpRankingPosition);
                if (currentPlayer.getNickname().equals(tmpPlayer.getNickname())){
                    if (currentPlayer.getScore() >= tmpPlayer.getScore()){
                        tmpPlayer = currentPlayer;
                    }
                    isAlreadyThere = true;
                }
                players.add(tmpPlayer);
            }
        }
        if (!isAlreadyThere){
            players.add(currentPlayer);
        }
        Collections.sort(players);
        Collections.reverse(players);
        for(int i = 0; i < players.size(); i++){
            players.get(i).updateRankingPosition(i+1);
        }
        FileService.saveToFile(players, "Statistics/ranking.txt");
    }
}
