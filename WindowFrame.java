package quest2;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WindowFrame extends JFrame{
	
	static JFrame frame;
	static Window window;
	private String icon = "src/quest2/Logo.png";
	
	public boolean doExit;
	Image image;
	
	private static final long serialVersionUID = 1L;
	private static BufferedImage BufImage;

	public WindowFrame(boolean doExit) {
		this.doExit = doExit;
		
		window = new Window(this);
	       add(window);  
		
		try {
			BufImage = ImageIO.read(new File(icon));
		} catch (IOException e) {
			//e.printStackTrace();
		}
		
		ImageIcon ii = new ImageIcon(BufImage);
		image = ii.getImage();
		
		if (doExit == true) {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		} else {
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
		setSize(720, 480);
		setLocationRelativeTo(null);
		setTitle("Quest 2");
		setVisible(true);
		setResizable(false);
		setIconImage(image);
	}
	
	public static void main(String args[]) {
		frame = new WindowFrame(true);
	}
	
	public static void play() {
		frame = new WindowFrame(false);
	}
	
	public static void replay(boolean doExit) {
		frame.dispose();
		frame = new WindowFrame(doExit);
	}
	
	public static void exit() {
		frame.dispose();
	}
	
}
