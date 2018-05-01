import java.io.File;
import javax.imageio.*;
import javax.swing.*;
import java.awt.*;

public class Button extends JButton{
	private Image image;
	
	// Board x and y values
	public int x; 
	public int y;

	// To ensure that the image can only be clicked once
	public Boolean clicked;

	public Button(int x, int y){
		this.changeImage("images/e.png");
		this.x = x;
		this.y = y;
		this.clicked = false;

		// Necessary settings
		this.setBorder(null);
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
		this.setFocusPainted(false);
	}

	public void changeImage(String filename){
		try{
			this.image = ImageIO.read(new File(filename));
			this.setIcon(new ImageIcon(image));
		} catch (Exception e){ System.out.println("Failed to load image: "+filename); }
	}
}