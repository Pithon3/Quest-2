package quest2;

//Import stuff
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Good1 {
	
	private int lakes = 2;  //Set limit of 2 lakes
	private int craters = 3;  //Set limit of 3 craters
	private int archers = 4;  //Set limit of 4 archers
	private int warriors = 7;  //Set limit of 7 warriors
	private int walls = 4;  //Set limit of 4 walls
	int gm = 1;
	
	Draggable draggee = null;
	ArrayList<Draggable> items = new ArrayList<Draggable>();  //List of all 20 different defences
		
	Window master;
	
	public Good1(Window w) {
		this.master = w;
		
		//Create starting defences
		createLake();
		createCrater();
		createArcher();
		createWarrior();
		createWall();
	}
	
	public void createLake() {  //Function for creating lakes
		if (lakes > 0) {
			items.add(new Draggable("/quest2/lake.gif", 20, 450, 34, 70, "Lake", this));
		}
	}
	
	public void createCrater() {  //Function for creating craters
		if (craters > 0) {
			items.add(new Draggable("/quest2/crater.gif", 120, 450, 38, 62, "Crater", this));
		}
		
	}
	
	public void createArcher() {  //Function for creating archers
		if (archers > 0) {
			items.add(new Draggable("/quest2/archer.gif", 220, 450, 39, 66, "Archer", this));
		}
	}
	
	public void createWarrior() {  //Function for creating warriors
		if (warriors > 0) {
			items.add(new Draggable("/quest2/warrior.gif", 320, 450, 40, 62, "Warrior", this));
		}
	}
	
	public void createWall() {  //Function for creating walls
		if (walls > 0) {
			items.add(new Draggable("/quest2/wall.png", 420, 450, 121, 75, "Wall", this));
		}
	}

	public void update() {  //Update things around the fort
		for (int i = 0; i < items.size(); i++) {  //For the ammount of defences that are shown...
			if (items.get(i).placed) {
				items.get(i).Update2();  //...Update them
			}
		}
	}
	
	public void mouseDragged(MouseEvent e) {  //Fuction that is called when the mouse is dragged
		for (int i = 0; i < items.size(); i++) {
			items.get(i).mouseDragged(e);  //Tell the defences that are shown that the mouse has been dragged
		}
		try {
			draggee.mouseUpdate(e.getX(), e.getY());
		} catch (Exception E) {
			//pass
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		try {
			draggee.mouseReleased(e);
		} catch (Exception E) {
			//pass
		}
		checkReleased(e);
	}
	
	public void setDraggee (Draggable d) {
		draggee = d;
	}
	
	public void createNewDraggable(Draggable d) {
		minusCount(d.name);
		checkName(d);
	}
	
	public Draggable getDraggee() {
		return draggee;
	}
	
	public void checkName(Draggable d) {  //Checks the name of the Draggable and creates a new one of that type of Draggable
		if (d.name == "Lake") {
			createLake();
		} if (d.name == "Crater") {
			createCrater();
		} if (d.name == "Archer") {
			createArcher();
		} if (d.name == "Warrior") {
			createWarrior();
		} if (d.name == "Wall") {
			createWall();
		}
	}
	
	public void minusCount(String name) {  //Checks the name of the Draggable and minuses the count of that Draggable
		if (name == "Lake") {
			lakes--;
		} if (name == "Crater") {
			craters--;
		} if (name == "Archer") {
			archers--;
		} if (name == "Warrior") {
			warriors--;
		} if (name == "Wall") {
			walls--;
		}
	}
	
	public void checkButton(MouseEvent e) {  //Checks if the Go On button has been clicked
		if (Sprite.checkCollision(621, 451, 80, 69, e.getX(), e.getY(), 1, 1)) {
			master.clicked();
		}
	}
	
	public void checkReleased(MouseEvent e) {
		if (Sprite.checkCollision(621, 451, 80, 69, e.getX(), e.getY(), 1, 1) && master.click()) {
			gm = 2;
			master.clicked();
		}
	}
	
}
