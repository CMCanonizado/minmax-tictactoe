// Arquilita, Jasper Ian Z. | Canonizado, Carlos Miguel E.
// U-10L | Week 11 - Min-Max Trees

public class Main {
    Board board;

    Main(){
        // Initialize board
        board = new Board();
        while(board.checkRunning() == 5){
            if(board.move == 1) // Player's move
            else if(board.move == 2){
                // MinMax
                // Get best move, let's say: x = 1, y = 1
                // board.buttons[1][1].doClick();
            }
        }

        board.unclick();
        if(board.checkRunning() == 1) System.out.println("Player 1 won!");
        else if(board.checkRunning() == -1) System.out.println("Player 2 won!");        
        else if(board.checkRunning() == 0) System.out.println("It's a draw!");            
    }


    public static void main(String[] args){
        // Start program
        Main main = new Main();
    }
}