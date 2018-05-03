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

    public char[][] applyAction(char[][] configLol, String action, char player){
        char[][] config1 = new char[3][3];
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                config1[i][j] = configLol[i][j];
            }
        }

        switch(action){
            case "ul": config1[0][0] = player; break;
            case "um": config1[0][1] = player; break;
            case "ur": config1[0][2] = player; break;
            case "ml": config1[1][0] = player; break;
            case "mm": config1[1][1] = player; break;
            case "mr": config1[1][2] = player; break;
            case "ll": config1[2][0] = player; break;
            case "lm": config1[2][1] = player; break;
            case "lr": config1[2][2] = player; break;                         
            default: break;
        }
        return config1;
    }

    public void applyAction(String action){
        switch(action){
            case "ul": this.config[0][0] = 'o'; this.buttons[0][0].doClick(); break;
            case "um": this.config[0][1] = 'o'; this.buttons[0][1].doClick(); break;
            case "ur": this.config[0][2] = 'o'; this.buttons[0][2].doClick(); break;
            case "ml": this.config[1][0] = 'o'; this.buttons[1][0].doClick(); break;
            case "mm": this.config[1][1] = 'o'; this.buttons[1][1].doClick(); break;
            case "mr": this.config[1][2] = 'o'; this.buttons[1][2].doClick(); break;
            case "ll": this.config[2][0] = 'o'; this.buttons[2][0].doClick(); break;
            case "lm": this.config[2][1] = 'o'; this.buttons[2][1].doClick(); break;
            case "lr": this.config[2][2] = 'o'; this.buttons[2][2].doClick(); break;                         
            default: break;
        }
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
                Button button = new Button(i, j);
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
                        config[button.x][button.y] = 'x';
                        move = 2;
                    } 
                    
                    // Player 2
                    else if(move == 2){
                        button.changeImage("images/o.png");  
                        config[button.x][button.y] = 'o';
                        move = 1;
                    }
                    
                    // Set clicked to true
                    button.clicked = true;
                }
                printConfig();
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