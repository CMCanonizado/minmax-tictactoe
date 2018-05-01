// Arquilita, Jasper Ian Z. | Canonizado, Carlos Miguel E.
// U-10L | Week 11 - Min-Max Trees

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

public class Main {
    Board board;
    // ul um ur | ml mm mr | ll lm lr
    char[][] newConfig;

    Main(){
        // Initialize board
        board = new Board();

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
                    System.out.println(action + " " + actionValue.get(action));
                }
                board.applyAction(maxAction);
            }
        }

        board.unclick();
        if(board.checkRunning() == 1) JOptionPane.showMessageDialog(null, "Player 1 won!", "TIC-TAC-TOE", JOptionPane.INFORMATION_MESSAGE);
        else if(board.checkRunning() == -1) JOptionPane.showMessageDialog(null, "Player 2 won!", "TIC-TAC-TOE", JOptionPane.INFORMATION_MESSAGE);        
        else if(board.checkRunning() == 0) JOptionPane.showMessageDialog(null, "It's a draw!", "TIC-TAC-TOE", JOptionPane.INFORMATION_MESSAGE);         
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

    public String isTerminal(char[][] config1, int utility){
        String row, col, board = "";

        // Check horizontally and vertically
        for(int i=0; i<3; i++){
            row = ""; col = "";
            for(int j=0; j<3; j++){
                row += config1[i][j];
                col += config1[j][i];
                board += config1[i][j];
            }
            if(row.contains("ooo") || col.contains("ooo")) return "WIN"; // Player 2 won
            else if(row.contains("xxx") || col.contains("xxx")) return "FAIL"; // Player 1 won
        }

        String dia1 = "" + config1[0][0] + config1[1][1] + config1[2][2];
        String dia2 = "" + config1[0][2] + config1[1][1] + config1[2][0];

        if(dia1.contains("ooo") || dia2.contains("ooo")) return "WIN"; // Player 2 won
        else if(dia1.contains("xxx") || dia2.contains("xxx")) return "FAIL"; // Player 1 won
        
        if(!board.contains("e")) return "DRAW"; // Draw

        return "NONE"; // Placeholder for G
    }

    public int value(char[][] configLol, String node, int utility){
        char[][] config1 = new char[3][3];
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                config1[i][j] = configLol[i][j];
            }
        }
        int is_terminal = 0;

        if (isTerminal(config1, utility).equals("WIN")) is_terminal = 1;
        else if (isTerminal(config1, utility).equals("PRIORITY")) is_terminal = 32000;
        else if (isTerminal(config1, utility).equals("FAIL")) is_terminal = -1;
        else if (isTerminal(config1, utility).equals("DRAW")) is_terminal = 0;

        if(isTerminal(config1, utility).equals("NONE")) return utility + is_terminal;
        if(node.equals("MIN")) return getMax(config1, utility);
        if(node.equals("MAX")) return getMin(config1, utility);  
        return 0;
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
        System.out.println(m);
        return m;
    }

    public int getMin(char[][] configLol, int utility){
        int m = 30000;
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
        System.out.println(m);
        return m;
    }

    public static void main(String[] args){
        // Start program
        Main main = new Main();
    }
}