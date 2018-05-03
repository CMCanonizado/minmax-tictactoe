import javafx.application.Application;
import javafx.scene.media.MediaPlayer;
import java.util.concurrent.TimeUnit;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.net.URL;
import java.awt.*;

public class Board {
    public JFrame frame;
    public char[][] config;
    public Button[][] buttons;
    final JFXPanel fxPanel = new JFXPanel();

    // To know which player is currently moving - either 1 or 2
    public int move;

    public Board(int move){
        this.move = move;
        config = new char[3][3];
        buttons = new Button[3][3];
        if(move == 1) frame = new JFrame("Tic-Tac-Toe | Arquilita & Canonizado | First Turn: USER");
        else frame = new JFrame("Tic-Tac-Toe | Arquilita & Canonizado | First Turn: AI");

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
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Returns a new copy of the configuration after applying an action
    public char[][] applyAction(char[][] oldConfig, String action, char player){
        char[][] newConfig = new char[3][3];
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                newConfig[i][j] = oldConfig[i][j];
            }
        }

        switch(action){
            case "ul": newConfig[0][0] = player; break;
            case "um": newConfig[0][1] = player; break;
            case "ur": newConfig[0][2] = player; break;
            case "ml": newConfig[1][0] = player; break;
            case "mm": newConfig[1][1] = player; break;
            case "mr": newConfig[1][2] = player; break;
            case "ll": newConfig[2][0] = player; break;
            case "lm": newConfig[2][1] = player; break;
            case "lr": newConfig[2][2] = player; break;                         
            default: break;
        }

        return newConfig;
    }

    // For changing the actual configuration of the board
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
            if(row.contains("xxx") || col.contains("xxx")) return 1; // USER won
            else if(row.contains("ooo") || col.contains("ooo")) return 2; // AI won
        }

        // Check both diagonals
        String dia1 = "" + config[0][0] + config[1][1] + config[2][2];
        String dia2 = "" + config[0][2] + config[1][1] + config[2][0];

        if(dia1.contains("xxx") || dia2.contains("xxx")) return 1; // USER won
        else if(dia1.contains("ooo") || dia2.contains("ooo")) return 2; // AI won
        
        if(!board.contains("e")) return 0; // Draw

        return 3; // This value means that the game is still running
    }

    // For making the buttons unclickable (when game is done)
    public void unclickButtons(){
        for(int i=0; i<3; i++)
            for(int j=0; j<3; j++)
                buttons[i][j].clicked = true;
    }

    private void loadBoard(){
        for(int i=0; i<3; i++)
            for(int j=0; j<3; j++){

                // Initialize config board with 'e' (empty)
                config[i][j] = 'e';

                // Create button that is initially an empty tile
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

                    // USER
                    if(move == 1){
                        button.changeImage("images/x.png");  
                        config[button.x][button.y] = 'x';
                        move = 2;
                    } 
                    
                    // AI
                    else if(move == 2){
                        while(playMedia("./music/ge_talon.mp3") != 1);
                        try { TimeUnit.SECONDS.sleep(1); }
                        catch(Exception event){ }
                        button.changeImage("images/o.png");  
                        config[button.x][button.y] = 'o';
                        move = 1;
                    }
                    
                    // Set clicked to true
                    button.clicked = true;
                }
            }  
        });      
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