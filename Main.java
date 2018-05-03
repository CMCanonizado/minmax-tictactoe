// Arquilita, Jasper Ian Z. | Canonizado, Carlos Miguel E.
// U-10L | Week 11 - Min-Max Trees

import javafx.application.Application;
import javafx.scene.media.MediaPlayer;
import java.util.concurrent.TimeUnit;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.Random;
import java.io.File;
import java.net.URL;

public class Main {
    Board board;
    Random rand = new Random();
    boolean first = true;
    final JFXPanel fxPanel = new JFXPanel();

    // Note: X is always the USER and O is always the AI
    /*

        !!! Important !!!
        If you feel that there is a slight delay, that is because of the sound effects
        we included. Just simply comment out these lines (will include where):

        try { TimeUnit.SECONDS.sleep(1); }
        catch(Exception event){ }

        @ Line 43-44 - Main.java
        @ Line 154-155 - Board.java

    */

    Main(){
        // Initialize board
        board = new Board(rand.nextInt(2)+1);

        while(this.playMedia("./music/dasal_muna.mp3") != 1);
        try { TimeUnit.SECONDS.sleep(1); }
        catch(Exception event){ }

        while(board.checkRunning() == 3){
            if(board.move == 1){ if(first) first = false; }
            else if(board.move == 2){
                // If AI moves first - its move will automatically be at the center (to make it faster)
                if(first){
                    board.buttons[1][1].doClick();
                    first = false;
                } // Get action of the AI - Alpha-Beta pruning is applied here
                else board.applyAction(minmax(board.config," ","MAX",-30000,+30000).action); 
            }
        }

        // Game is done - make sure buttons are now unclickable
        board.unclickButtons();

        // Prompt for users to exit
        if(board.checkRunning() == 1){
            while(this.playMedia("./music/talon_din_ako.mp3") != 1);
            int result = JOptionPane.showConfirmDialog(null, "You won! Exit?", "Result", 
                JOptionPane.DEFAULT_OPTION);
            if (result == 0) System.exit(0);
        }
        else if(board.checkRunning() == 2){
            while(this.playMedia("./music/una_ulo.mp3") != 1);
            int result = JOptionPane.showConfirmDialog(null, "The AI won! Exit?", "Result", 
                JOptionPane.DEFAULT_OPTION);
            if (result == 0) System.exit(0);            
        }        
        else if(board.checkRunning() == 0){
            while(this.playMedia("./music/par_asan.mp3") != 1);
            int result = JOptionPane.showConfirmDialog(null, "It's a draw! Exit?", "Result", 
                JOptionPane.DEFAULT_OPTION);
            if (result == 0) System.exit(0);            
        }         
    }
    
// =================================== START OF MIN-MAX ALGO ===================================

    public State minmax(char[][] config, String action, String node, int alpha, int beta){
        if(isTerminal(config) != 5) return (new State(action,isTerminal(config)));
        if(node.equals("MAX")) return getMax(config, node, alpha, beta);
        if(node.equals("MIN")) return getMin(config, node, alpha, beta);
        return null;
    }

    public State getMax(char[][] config, String node, int alpha, int beta){
        State m = new State(" ",-30000);
        for(String action : getActions(config)){
            State v = minmax(board.applyAction(config, action, 'o'), action, "MIN", alpha, beta);
            m.action = ( (v.value>m.value) ? action : m.action );
            m.value = ( (v.value>m.value) ? v.value : m.value );
            if(m.value >= beta) return m;
            alpha = ( (alpha>m.value) ? alpha : m.value );
        }
        return m;
    }

    public State getMin(char[][] config, String node, int alpha, int beta){
        State m = new State(" ", +30000);
        for(String action : getActions(config)){
            State v = minmax(board.applyAction(config, action, 'x'), action, "MAX", alpha, beta);
            m.action = ( (v.value<m.value) ? action : m.action );
            m.value = ( (v.value<m.value) ? v.value : m.value );
            if(m.value <= alpha) return m;
            beta = ( (beta<m.value) ? beta : m.value);
        }
        return m;
    }

// =================================== END OF MIN-MAX ALGO ===================================    

    public int isTerminal(char[][] config){
        String row, col, board = "";

        // Check horizontally and vertically
        for(int i=0; i<3; i++){
            row = ""; col = "";
            for(int j=0; j<3; j++){
                row += config[i][j];
                col += config[j][i];
                board += config[i][j];
            }
            if(row.contains("xxx") || col.contains("xxx")) return -1; // USER won
            else if(row.contains("ooo") || col.contains("ooo")) return 1; // AI won
        }

        // Check both diagonals
        String dia1 = "" + config[0][0] + config[1][1] + config[2][2];
        String dia2 = "" + config[0][2] + config[1][1] + config[2][0];

        if(dia1.contains("xxx") || dia2.contains("xxx")) return -1; // USER won
        else if(dia1.contains("ooo") || dia2.contains("ooo")) return 1; // AI won
        
        if(!board.contains("e")) return 0; // Draw

        return 5; // This value means that the state is still not the terminal state
    }

    // Gets all possible moves given the board configuration
    public ArrayList<String> getActions(char[][] config){
        ArrayList<String> actions = new ArrayList<String>();
        if(config[0][0] == 'e') actions.add("ul"); // Upper Left
        if(config[0][1] == 'e') actions.add("um"); // Upper Middle
        if(config[0][2] == 'e') actions.add("ur"); // Upper Right
        if(config[1][0] == 'e') actions.add("ml"); // Middle Left
        if(config[1][1] == 'e') actions.add("mm"); // Middle Middle
        if(config[1][2] == 'e') actions.add("mr"); // Middle Right
        if(config[2][0] == 'e') actions.add("ll"); // Lower Left
        if(config[2][1] == 'e') actions.add("lm"); // Lower Middle
        if(config[2][2] == 'e') actions.add("lr"); // Lower Right
        return actions;
    }

    public int playMedia(String mp3){
        try {
            URL resource = getClass().getResource(mp3);
            Media media = new Media(resource.toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
        } catch (Exception err) { return 0;
        } finally { return 1; }
    }

    public static void main(String[] args){
        // Start program
        Main main = new Main();
    }
}