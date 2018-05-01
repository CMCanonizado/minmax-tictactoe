// Arquilita, Jasper Ian Z. | Canonizado, Carlos Miguel E.
// U-10L | Week 11 - Min-Max Trees

import java.util.ArrayList;

public class Main {
    Board board;
    // ul um ur | ml mm mr | ll lm lr

    Main(){
        // Initialize board
        board = new Board();

        while(board.checkRunning() == 5){
            if(board.move == 1){ }
            else if(board.move == 2){
                for(String action : getActions(board.config))
                    if(getMax(board.config) == 1) board.applyAction(action);
                    else if(getMax(board.config) == 0) board.applyAction(action);                    
            }
        }

        board.unclick();
        if(board.checkRunning() == 1) System.out.println("Player 1 won!");
        else if(board.checkRunning() == -1) System.out.println("Player 2 won!");        
        else if(board.checkRunning() == 0) System.out.println("It's a draw!");            
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

    public int isTerminal(char[][] config1){
        String row, col, board = "";

        // Check horizontally and vertically
        for(int i=0; i<3; i++){
            row = ""; col = "";
            for(int j=0; j<3; j++){
                row += config1[i][j];
                col += config1[j][i];
                board += config1[i][j];
            }
            if(row.contains("xxx") || col.contains("xxx")) return 1; // Player 1 won
            else if(row.contains("ooo") || col.contains("ooo")) return -1; // Player 2 won
        }

        String dia1 = "" + config1[0][0] + config1[1][1] + config1[2][2];
        String dia2 = "" + config1[0][2] + config1[1][1] + config1[2][0];

        if(dia1.contains("xxx") || dia2.contains("xxx")) return 1; // Player 1 won
        else if(dia1.contains("ooo") || dia2.contains("xxx")) return -1; // Player 2 won
        
        if(!board.contains("e")) return 0; // Draw

        return 5; // Placeholder for G
    }

    public int value(char[][] config1, String node){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                System.out.print(config1[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        if(isTerminal(config1) != 5) return isTerminal(config1);
        if(node == "MIN") return getMax(config1);
        if(node == "MAX") return getMin(config1);   
        return 0;
    }

    public int getMax(char[][] config1){
        int m = -10;
        for(String action : getActions(config1)){
            int v = value(board.applyAction(config1,action,'o'),"MAX");
            if(v > m) m = v; 
        }
        return m;
    }

    public int getMin(char[][] config1){
        int m = +10;
        for(String action : getActions(config1)){
            int v = value(board.applyAction(config1,action,'x'),"MIN");
            if(v < m) m = v; 
        }
        return m;
    }

    public static void main(String[] args){
        // Start program
        Main main = new Main();
    }
}