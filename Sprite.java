package quest2;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public class Sprite extends MouseAdapter{
	
	int x;
	int y;
	int dx;
	int dy;
	
	int x2;
	int y2;
	
	int xl;
	int yl;
	
	Image image; 
	BufferedImage buffimage;
	
	String filepath;
	
	public Sprite(String path, int x, int y, int xlong, int ylong) {
		filepath = path;
		try {
			buffimage = ImageIO.read(new File(this.filepath));
		} catch (IOException e) {
			e.printStackTrace();
		};
		ImageIcon ii = new ImageIcon(buffimage);
		image = ii.getImage();
		
		this.x = x;
		this.y = y;
		x2 = this.x + xlong;
		y2 = this.y + ylong;
		
		xl = xlong;
		yl = ylong;
		
	}
	
	public void Update() {
		//To get overridden
	}
	
	boolean checkCollision(int drx, int dry, int xlong, int ylong) {		
		Rectangle rect1 = new Rectangle(x, y, xl, yl);
		Rectangle rect2 = new Rectangle(drx, dry, xlong, ylong);
		if (rect1.intersects(rect2)) {
			return true;
		} else {
			return false;
		}
		
	}
	
	protected boolean checkOutOfRange() {
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
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Image getImage() {
		return image;
	}
	
	public void Update2() {
		x += dx;
		y += dy;
		checkOutOfRange();
		Update();
	}
	
	public void mousePressed(MouseEvent e) { 
		
	}
	
	public void mouseReleased(MouseEvent e) { 
		
	}
	
	public void mouseEntered(MouseEvent e) { 
		
	}
    
    public void mouseExited(MouseEvent e) { 
    	
    }
    
    public void mouseClicked(MouseEvent e) { 
    	
    }
    
    public void mouseMoved(MouseEvent e) { 
    	
    }
    
    public void mouseDragged(MouseEvent e) { 
    	
    }
	
}
