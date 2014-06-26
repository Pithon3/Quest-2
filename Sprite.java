package quest2;

//Import Stuff
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

//A handy class for displaying things on the screen
public class Sprite extends MouseAdapter{
	
	int x;  //X position of the sprite
	int y;  //Y position of the sprite
	int dx;  //The X speed of the sprite
	int dy;  //The Y speed of the sprite
	int x2;  //X position of the opposite corner of the sprite
	int y2;  //Y position of the opposite corner of the sprite
	int xl;  //The Length of the sprite
	int yl;  //The height of the sprite
	
	Image image;  //The image for the sprite
	BufferedImage buffimage;  //The buffered image for the sprite
	
	public Sprite(String path, int x, int y, int xlong, int ylong) {
		try {
			buffimage = ImageIO.read(new File(getClass().getResource(path));  //Get a buffered Image from the path given
		} catch (IOException e) {
			e.printStackTrace();  //Give a error if it doesn't work
		};
		ImageIcon ii = new ImageIcon(buffimage);  //Get a image icon from the buffered image
		image = ii.getImage();  //Get a image from the image icon
		
		this.x = x;  //set given x to the field X
		this.y = y;  //set given y to the field y
		x2 = this.x + xlong;  //calculate x2 from xlong and x
		y2 = this.y + ylong;  //calculate y2 from ylong and y
		
		xl = xlong;  //set given length to the field
		yl = ylong;  //set given height to the field
		
	}
	
	public void Update() {
		//To get overridden
	}
	
	boolean checkCollision(int drx, int dry, int xlong, int ylong) {  //Check for a collision with a sprite
		Rectangle rect1 = new Rectangle(x, y, xl, yl);
		Rectangle rect2 = new Rectangle(drx, dry, xlong, ylong);
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
	
	public int getX() {  //Getter for X
		return x;
	}
	
	public int getY() {  //getter for Y
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
	
}
