package quest2;

//Import stuff
import java.awt.event.MouseEvent;

public class Draggable extends Sprite {

	int mx;  //the X value of the mouse
	int my;  //the Y value of the mouse
	String name;  //name of the defence that will be placed
	boolean mousein = false;  //boolean variable that represents if the mouse is in the area of the sprite
	boolean clicked = false;
	boolean placed = false;
	
	Good1 master;  //the master of this sprite
	public String filepath;  //For accesing the path once the drggable is being converted
	
	public Draggable(String filepath, int x, int y, int xlong, int ylong, String n, Good1 good1) {
		super(filepath, x, y, xlong, ylong);
		
		//Assign stuff
		master = good1;
		name = n;
		this.filepath = filepath;
	}
	
	public void mouseUpdate(int mx, int my) {
		x = mx;
		y = my;
		
		if ((y + yl) < 440) {
			if (placed == false) {
				master.createNewDraggable(this);
			}
			
			placed = true;
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {  //Update if the mouse is dragged
		mx = e.getX();
		my = e.getY();
		
		if (checkCollision(mx, my, 1, 1)) {
			if (master.getDraggee() == null) {
				master.setDraggee(this);
				clicked = true;
			}
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		master.setDraggee(null);
	}

}
