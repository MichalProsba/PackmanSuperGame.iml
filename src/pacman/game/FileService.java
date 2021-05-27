package pacman.game;

import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class FileService {
    private static final int statsLimit = 10;
    public static void saveToFile(List<Player> players, String path){
        File f = new File(path);
        if(!f.exists()) {
            try {
                f.createNewFile();
            } catch(Exception e){
                System.out.println(e.getMessage());
            }
        }

        if(f.canWrite()) {
            try {
                FileWriter fw = new FileWriter(f, false);
                Formatter fm = new Formatter(fw);
                for (int j = 0; j < statsLimit && j < players.size(); j++) {
                    fm.format("%d.:%s:%d\n", players.get(j).getRankingPosition(), players.get(j).getNickname(), players.get(j).getScore());
                }
                fm.close();
                fw.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public static String[] getFileContent(String path){
        File f = new File(path);
        String[] content = new String[statsLimit];
        for (int j = 0; j < statsLimit; j++) {
            content[j] = "";
        }
        if(!f.exists()) {
            return content;
        }
        if(f.canRead()) {
            try {
                Scanner sf = new Scanner(f);
                int i = 0;
                while(sf.hasNextLine()){
                    content[i] = sf.nextLine();
                    i++;
                }
                sf.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return content;
    }
}
