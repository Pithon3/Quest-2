package quest2;

//The Main Class

//Import Stuff
import javax.imageio.ImageIO;  
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WindowFrame extends JFrame{
	
	static JFrame frame;  //For reopening and creating new windows
	static Window window;  //Field for the content of the frame
	public boolean doExit;  //If the window is not on its own, make sure it doesn't close every thing when it closes
	
	private String icon = "src/quest2/Logo.png";  //Path of the logo picture
	private static BufferedImage BufImage;  //The buffered image for the logo picture
	Image image;  //The image for the icon of the frame
	
	private static final long serialVersionUID = 1L;  //Code so I don't get a warning

	public WindowFrame(boolean doExit) {		
		this.doExit = doExit;  //Assign the argument doExit to the Field
		
		window = new Window(this);  //Initialize the Window class
	    add(window);  //Add the window (the content of the program) to the screen
		
		//Get a Image for the Icon
	    try {
			BufImage = ImageIO.read(new File(icon));  //Get a buffered image with the path (hopefully)
		} catch (IOException e) {
			e.printStackTrace();  //If the above doesn't happen, give a big error to the user
		}
		ImageIcon ii = new ImageIcon(BufImage);  //get a ImageIcon from the buffered image
		image = ii.getImage();  //Get the final image for the icon of the window
		
		if (doExit == true) {  //Do this only if it is the root window
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //End the program with the close of the window
		} else {
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  //Just close the window with the pushing of the X button
		}
		
		//Set stuff for the window
		setSize(720, 480);  //Set the size of the window
		setTitle("Quest 2");  //Set the title of the window so people know what they are playing
		setVisible(true);  //Make the window visible so the goblins don't sneek up on them
		setResizable(false);  //Make sure that people can't distort the content
		setIconImage(image);  //Set the hard earned image as the icon of the frame
		setLocationRelativeTo(null);
	}
	
	public static void main(String args[]) {  //The main function that get called at the begginning of the program
		frame = new WindowFrame(true);  //Initialize WindowFrame and start this off
	}
	
	public static void play() {  //Function to start the game without main
		frame = new WindowFrame(false);  //Create new WindowFrame
	}
	
	public static void replay(boolean doExit) {  //To replay the game, and close other window, and make sure it has the right doExit value
		frame.dispose();  //Close the old game
		frame = new WindowFrame(doExit);  // Create new game
	}
	
	public static void exit() {  //Exit the program
		frame.dispose();  //Close the window
		System.exit(0);  //Terminate the program
	}
	
}
