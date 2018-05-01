// Arquilita, Jasper Ian Z. | Canonizado, Carlos Miguel E.
// U-10L | Week 11 - Min-Max Trees

import java.util.ArrayList;

public class Main {
    Board board;
    ArrayList<String> actions;
    // ul um ur | ml mm mr | ll lm lr

    Main(){
        // Initialize board
        board = new Board();
        actions = new ArrayList<String>();

        actions = getActions(board.config);
        for(String s : actions){
            System.out.println(s);
        }

        // while(board.checkRunning() == 5){
        //     // if(board.move == 1) // Player's move
        //     // else if(board.move == 2){
        //     //     // MinMax
        //     //     // Get best move, let's say: x = 1, y = 1
        //     //     // board.buttons[1][1].doClick();
        //     // }
        // }

        // board.unclick();
        // if(board.checkRunning() == 1) System.out.println("Player 1 won!");
        // else if(board.checkRunning() == -1) System.out.println("Player 2 won!");        
        // else if(board.checkRunning() == 0) System.out.println("It's a draw!");            
    }

    public ArrayList<String> getActions(char[][] config){
        ArrayList<String> actions = new ArrayList<String>();
        if(config[0][0] == 'e') actions.add("ul");
        if(config[0][1] == 'e') actions.add("um");
        if(config[0][2] == 'e') actions.add("ur");
        if(config[1][0] == 'e') actions.add("ml");
        if(config[1][1] == 'e') actions.add("mm");
        if(config[1][2] == 'e') actions.add("mr");
        if(config[2][0] == 'e') actions.add("ll");
        if(config[2][1] == 'e') actions.add("lm");
        if(config[2][2] == 'e') actions.add("lr");
        return actions;
    }


    public static void main(String[] args){
        // Start program
        Main main = new Main();
    }
}