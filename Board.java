import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Board {
    public char[][] config;
    public Button[][] buttons;
    public JFrame frame;

    // To know which player is currently moving - either 1 or 2
    public int move;

    public Board(){
        move = 1;
        config = new char[3][3];
        buttons = new Button[3][3];
        frame = new JFrame("Tic-Tac-Toe | Arquilita & Canonizado");
        
        // Necessary settings for the frame
        frame.setPreferredSize(new Dimension(600,600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        // Initialize board
        this.loadBoard();

        // Necessary settings for the frame
        frame.setLayout(new GridLayout(3,3));
        frame.setFocusable(true);
        frame.pack();
        frame.setVisible(true);
    }

    public int checkRunning(){
        String row, col, board = "";

        // Check horizontally and vertically
        for(int i=0; i<3; i++){
            row = ""; col = "";
            for(int j=0; j<3; j++){
                row += config[i][j];
                col += config[j][i];
                board += config[i][j];
            }
            if(row.contains("xxx") || col.contains("xxx")) return 1; // Player 1 won
            else if(row.contains("ooo") || col.contains("ooo")) return -1; // Player 2 won
        }

        String dia1 = "" + config[0][0] + config[1][1] + config[2][2];
        String dia2 = "" + config[0][2] + config[1][1] + config[2][0];

        if(dia1.contains("xxx") || dia2.contains("xxx")) return 1; // Player 1 won
        else if(dia1.contains("ooo") || dia2.contains("xxx")) return -1; // Player 2 won
        
        if(!board.contains("e")) return 0; // Draw

        return 5; // Placeholder for G
    }

    public void unclick(){
        for(int i=0; i<3; i++)
            for(int j=0; j<3; j++)
                buttons[i][j].clicked = true;
    }

    private void loadBoard(){
        for(int i=0; i<3; i++)
            for(int j=0; j<3; j++){

                // Initialize config board with 'e' (empty)
                config[i][j] = 'e';

                // Create button that is initially empty
                Button button = new Button(j, i);
                addButtonListener(button);

                // Store button to buttons array and add to frame
                buttons[i][j] = button;
                frame.add(button); 
            }
    }

    private void addButtonListener(Button button){
        button.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
                
                // Only move if current button has not been clicked yet
                if(!button.clicked){

                    // Player 1
                    if(move == 1){
                        button.changeImage("images/x.png");  
                        config[button.y][button.x] = 'x';
                        move = 2;
                    } 
                    
                    // Player 2
                    else if(move == 2){
                        button.changeImage("images/o.png");  
                        config[button.y][button.x] = 'o';
                        move = 1;
                    }
                    
                    // Set clicked to true
                    button.clicked = true;
                }
                checkRunning();
            }  
        });      
    }

    // For checking current configuration
    public void printConfig(){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                System.out.print(config[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

}