package quest2;

//Import stuff
import java.awt.event.MouseEvent;

public class Draggable extends Sprite{

	int mx;  //the X value of the mouse
	int my;  //the Y value of the mouse
	String name;  //name of the defence that will be placed
	boolean mousein = false;  //boolean variable that represents if the mouse is in the area of the sprite
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
	
	@Override
	public void Update() {
		checkMouse();
	}
	
	public void mouseupdate() {  //Update the place of the draggable to the place of the mouse if the mouse is dragging the sprite
		if (master.getInPlacement() == this) {
			this.x = mx;
			this.y = my;
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {  //Update if the mouse is dragged
		mx = e.getX();
		my = e.getY();
		
		if (mousein) {  //If the mouse is in the sprite...
			if (master.getInPlacement() == this) {  //...and Good1's inplacement is this sprite...
				mouseupdate();  //...Update x and y
			}
		}
	}
	
	public void mouseClicked(MouseEvent e) {  //Update if the mouse is clicked
		if (master.getInPlacement() == this) {  //If the sprite is in placement...
			//...Do this:
			master.minusCount(name);		
			master.changeInPlacement(null);
		}
		mousein = false;
	}
	
	public void checkMouse() {  //Check if the mouse is inside the sprite
		boolean bool = false;  //variable to show if the mouse is inside the sprite
		if (mx > x) {
			if (my > y) {
				if (mx < x2) {
					if (my < y2) {
						bool = true;  //The mouse is in the sprite, so change bool to true
					}
				}
			}
		}
		
		if (bool == true) {  //If the mouse is in the sprite...
			if (master.getInPlacement() == master.blankDraggable) {  //...And Good1's inplacement isn't some other sprite...
				mousein = true;
				master.changeInPlacement(this);  //...Change Good1's inplacement to this sprite
				placed = true;
			}
		}
	}

}
