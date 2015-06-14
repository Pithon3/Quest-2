package quest2;

//Import Stuff
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

//A handy class for displaying things on the screen
public class Sprite extends MouseAdapter{
	
	double x;  //X position of the sprite
	double y;  //Y position of the sprite
	double lx; //Last X position of the sprite
	double ly; //Last Y position of the sprite
	double dx;  //The X speed of the sprite
	double dy;  //The Y speed of the sprite
	int x2;  //X position of the opposite corner of the sprite
	int y2;  //Y position of the opposite corner of the sprite
	int xl;  //The Length of the sprite
	int yl;  //The height of the sprite
	int num; 
	boolean appear;
	boolean moving;
	boolean directed;
	
	String name;
	
	Image image;  //The image for the sprite
	BufferedImage buffimage;  //The buffered image for the sprite
	
	public Sprite(String path, double x, double y, int xlong, int ylong) {
		try {
			buffimage = ImageIO.read(getClass().getResource(path));  //Get a buffered Image from the path given
		} catch (IOException e) {
			e.printStackTrace();  //Give a error if it doesn't work
		};
		ImageIcon ii = new ImageIcon(buffimage);  //Get a image icon from the buffered image
		image = ii.getImage();  //Get a image from the image icon
		
		this.x = x;  //set given x to the field X
		this.y = y;  //set given y to the field y
		x2 = (int) (this.x + xlong);  //calculate x2 from xlong and x
		y2 = (int) (this.y + ylong);  //calculate y2 from ylong and y
		
		xl = xlong;  //set given length to the field
		yl = ylong;  //set given height to the field
		
	}
	
	public Sprite(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void Update() {
		//To get overridden
	}
	
	boolean checkCollision(double drx, double dry, int xlong, int ylong) {  //Check for a collision with a sprite
		Rectangle rect1 = new Rectangle((int) x, (int) y, xl, yl);
		Rectangle rect2 = new Rectangle((int) drx, (int) dry, xlong, ylong);
		if (rect1.intersects(rect2)) {
			return true;
		} else {
			return false;
		}
		
	}
	
	static boolean checkCollision(double x, double y, int xl, int yl, double drx, double dry, int xlong, int ylong) {  //Check for a collision with a sprite
		Rectangle rect1 = new Rectangle((int) x, (int) y, xl, yl);
		Rectangle rect2 = new Rectangle((int) drx, (int) dry, xlong, ylong);
		if (rect1.intersects(rect2)) {
			return true;
		} else {
			return false;
		}
		
	}

	
	protected boolean checkOutOfRange() {  //Check if the sprite is out if range, and if so, move it back in range
		int xra = 720 - xl;
		int yra = 480 - yl;
		
		boolean ret = false;
		
		if (x < 0) {
			x = 0;
			ret = true;
		}
			
		if (x > xra) {
			x = xra;
			ret = true;
		}
		
		if (y < 0) {
			y = 0;
			ret = true;
		}
		
		if (y > yra) {
			y = yra;
			ret = true;
		}
		
		return ret;
	}
	
	public double getX() {  //Getter for X
		return x;
	}
	
	public double getY() {  //getter for Y
		return y;
	}
	
	public Image getImage() {  //getter for the Image of the sprite
		return image;
	}
	
	public void Update2() {  //Another update for automatically moving and checking if the sprite is out of range
		x += dx;
		y += dy;
		checkOutOfRange();
		Update();
	}
	
	public void setImage(String path) {
		try {
			buffimage = ImageIO.read(getClass().getResource(path));  //Get a buffered Image from the path given
		} catch (IOException e) {
			e.printStackTrace();  //Give a error if it doesn't work
		};
		ImageIcon ii = new ImageIcon(buffimage);  //Get a image icon from the buffered image
		image = ii.getImage();  //Get a image from the image icon
	}
	
	
	
}
