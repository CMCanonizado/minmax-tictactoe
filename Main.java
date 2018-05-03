// Arquilita, Jasper Ian Z. | Canonizado, Carlos Miguel E.
// U-10L | Week 11 - Min-Max Trees

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import java.io.File;
import java.net.URL;
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.embed.swing.JFXPanel;

public class Main {
    Board board;
    // ul um ur | ml mm mr | ll lm lr
    char[][] newConfig;
    final JFXPanel fxPanel = new JFXPanel();

    Main(){
        // Initialize board
        board = new Board();
        while(this.playMedia("./music/dasal_muna.mp3") != null);

        while(board.checkRunning() == 5){
            if(board.move == 1){ }
            else if(board.move == 2){
                HashMap<String, Integer> actionValue = new HashMap<String, Integer>();
                for(String action : getActions(board.config)){
                    newConfig = new char[3][3];
                    for(int i=0; i<3; i++){
                        for(int j=0; j<3; j++){
                            newConfig[i][j] = board.config[i][j];
                        }
                    }
                    actionValue.put(action, getMax(newConfig,0));
                }  

                // get the highest utility
                int max = -30000;
                String maxAction = "";
                for (String action: actionValue.keySet()) {
                    if (actionValue.get(action) > max) {
                        max = actionValue.get(action);
                        maxAction = action;
                    }
                }
                board.applyAction(maxAction);
            }
        }

        board.unclick();
        if(board.checkRunning() == 1) JOptionPane.showMessageDialog(null, "Player 1 won!", "TIC-TAC-TOE", JOptionPane.INFORMATION_MESSAGE);
        else if(board.checkRunning() == -1) JOptionPane.showMessageDialog(null, "Player 2 won!", "TIC-TAC-TOE", JOptionPane.INFORMATION_MESSAGE);        
        else if(board.checkRunning() == 0) JOptionPane.showMessageDialog(null, "It's a draw!", "TIC-TAC-TOE", JOptionPane.INFORMATION_MESSAGE);         
    }
    
    public void playMedia(String mp3) {
        URL resource = getClass().getResource(mp3);
        Media media = new Media(resource.toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    public ArrayList<String> getActions(char[][] config1){
        ArrayList<String> actions = new ArrayList<String>();
        if(config1[0][0] == 'e') actions.add("ul");
        if(config1[0][1] == 'e') actions.add("um");
        if(config1[0][2] == 'e') actions.add("ur");
        if(config1[1][0] == 'e') actions.add("ml");
        if(config1[1][1] == 'e') actions.add("mm");
        if(config1[1][2] == 'e') actions.add("mr");
        if(config1[2][0] == 'e') actions.add("ll");
        if(config1[2][1] == 'e') actions.add("lm");
        if(config1[2][2] == 'e') actions.add("lr");
        return actions;
    }

    public int isTerminal(char[][] config1, int utility){
        String row, col, board = "";

        // Check horizontally and vertically
        for(int i=0; i<3; i++){
            row = ""; col = "";
            for(int j=0; j<3; j++){
                row += config1[i][j];
                col += config1[j][i];
                board += config1[i][j];
            }
            if(row.contains("xxx") || col.contains("xxx")) return 5; // Player 1 won
            else if(    row.contains("xxo") || col.contains("xxo") ||
                        row.contains("xox") || col.contains("xox") ||
                        row.contains("oxx") || col.contains("oxx")
            ) return 3;
            else if(row.contains("ooo") || col.contains("ooo")) return -1; // Player 2 won
        }

        String dia1 = "" + config1[0][0] + config1[1][1] + config1[2][2];
        String dia2 = "" + config1[0][2] + config1[1][1] + config1[2][0];

        if(dia1.contains("xxx") || dia2.contains("xxx")) return 5; // Player 1 won
        else if(    dia1.contains("xxo") || dia2.contains("xxo") ||
                    dia1.contains("xox") || dia2.contains("xox") ||
                    dia1.contains("oxx") || dia2.contains("oxx")
        ) return 3;
        else if(dia1.contains("ooo") || dia2.contains("ooo")) return -1; // Player 2 won
        
        if(!board.contains("e")) return 0; // Draw

        return 2; // Placeholder for G
    }

    public int value(char[][] configLol, String node, int utility){
        char[][] config1 = new char[3][3];
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                config1[i][j] = configLol[i][j];
            }
        }
        int sum = 0;
        if(isTerminal(config1, utility) != 2) return isTerminal(config1, utility);
        if(node == "MIN") return getMax(config1, utility);
        if(node == "MAX") return getMin(config1, utility);   
        return sum;
    }

    public int getMax(char[][] configLol, int utility){
        int m = -30000;
        char[][] config1 = new char[3][3];
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                config1[i][j] = configLol[i][j];
            }
        }

        for(String action : getActions(config1)){    
            int v = value(board.applyAction(config1,action,'o'),"MAX", utility);
            if(v > m) m = v; 
        }
        System.out.println(utility + m);
        return utility + m;
    }

    public int getMin(char[][] configLol, int utility){
        int m = +30000;
        char[][] config1 = new char[3][3];
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                config1[i][j] = configLol[i][j];
            }
        }

        for(String action : getActions(config1)){
            int v = value(board.applyAction(config1,action,'x'),"MIN", utility);
            if(v < m) m = v; 
        }
        System.out.println(utility + m);
        return utility + m;
    }

    public static void main(String[] args){
        // Start program
        Main main = new Main();
    }
}