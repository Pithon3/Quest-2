package quest2;

import java.awt.event.MouseEvent;


public class Draggable extends Sprite{

	int mx;
	int my;
	String name;
	boolean mousein = false;
	
	Good1 master;
	
	public Draggable(String filepath, int x, int y, int xlong, int ylong, String n, Good1 good1) {
		super(filepath, x, y, xlong, ylong);
		master = good1;
		name = n;
	}
	
	@Override
	public void Update() {
		//checkOutOfRange();
		checkMouse();
		//System.out.println("updating...");
	}
	
	public void mouseupdate() {
		if (master.getInPlacement() == this) {
			this.x = mx;
			this.y = my;
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		//System.out.println("Mouse Dragged");
		mx = e.getX();
		my = e.getY();
		
		if (mousein) {
			if (master.getInPlacement() == this) {
				mouseupdate();
			}
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		if (mousein == true) {
			master.minusCount(name);		
			master.changeInPlacement(null);
		}
		mousein = false;
	}
	
	public void checkMouse() {
		boolean bool = false;
		if (mx > x) {
			if (my > y) {
				if (mx < x2) {
					if (my < y2) {
						bool = true;
					}
				}
			}
		}
		
		if (bool == true) {
			if (master.getInPlacement() == master.blankDraggable) {
				mousein = true;			
				master.changeInPlacement(this);
			}
		}
	}

}
