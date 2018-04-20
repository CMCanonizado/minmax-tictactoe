import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Board {
    public char[][] config;
    public JFrame frame;

    // To know which player is currently moving - either 1 or 2
    public int move;

    public Board(int move){
        this.move = move;
        config = new char[3][3];
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

    private void loadBoard(){
        for(int i=0; i<3; i++)
            for(int j=0; j<3; j++){

                // Initialize config board with 'e' (empty)
                config[i][j] = 'e';

                // Create button that is initially empty
                Button button = new Button("images/e.png", j, i);
                frame.add(button); 

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
                        printConfig();
                    }  
                });      
            }
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