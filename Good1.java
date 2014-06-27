package quest2;

//Import stuff
import java.awt.event.MouseEvent;

public class Good1 {
	
	private int lakes = 2;  //Set limit of 2 lakes
	private int craters = 4;  //Set limit of 3 craters
	private int archers = 4;  //Set limit of 4 archers
	private int warriors = 7;  //Set limit of 7 warriors
	private int walls = 4;  //Set limit of 4 walls
	int gm = 1;
	
	Draggable[] items = new Draggable[20];  //List of all 20 different defences
	int itemsin = 0;  //Amount of defences that have been placed
	
	Draggable inplacement;  //The field that represents the defence being placed
	Draggable blankDraggable = new Draggable("/quest2/lake.gif", -300, -300, 34, 70, "null", this);  //A draggable item that can't be placed, it is used when there is nothing else being placed
	
	public Good1() {
		//Create starting defences
		createLake();
		createCrater();
		createArcher();
		createWarrior();
		createWall();
		
		inplacement = blankDraggable;  //Set inplacement to nothing, or the Draggable that represents nothing
	}
	
	public void createLake() {  //Function for creating lakes
		if (lakes > 0) {
			items[itemsin] = new Draggable("/quest2/lake.gif", 20, 20, 34, 70, "lake", this);
			itemsin++;
		}
	}
	
	public void createCrater() {  //Function for creating craters
		if (craters > 0) {
			items[itemsin] = new Draggable("/quest2/crater.gif", 120, 20, 38, 62, "crater", this);
			itemsin++;
		}
		
	}
	
	public void createArcher() {  //Function for creating archers
		if (archers > 0) {
			items[itemsin] = new Draggable("/quest2/archer.gif", 220, 20, 39, 66, "archer", this);
			itemsin++;
		}
	}
	
	public void createWarrior() {  //Function for creating warriors
		if (warriors > 0) {
			items[itemsin] = new Draggable("/quest2/warrior.gif", 320, 20, 40, 62, "warrior", this);
			itemsin++;
		}
	}
	
	public void createWall() {  //Function for creating walls
		if (walls > 0) {
			items[itemsin] = new Draggable("/quest2/wall.png", 420, 20, 65, 86, "wall", this);
			itemsin++;
		}
	}

	public void update() {  //Update things around the fort
		for (int i = 0; i < itemsin; i++) {  //For the ammount of defences that are shown...
			items[i].Update2();  //...Update them
		}
	}


	public void mouseDragged(MouseEvent e) {  //Fuction that is called when the mouse is dragged
		for (int i = 0; i < itemsin; i++) {
			items[i].mouseDragged(e);  //Tell the defences that are shown that the mouse has been dragged
		}
	}

	public void mouseClicked(MouseEvent e) {  //Function that is called when the mouse is clicked
		for (int i = 0; i < itemsin; i++) {
			items[i].mouseClicked(e);  //Tell the defences that are shown that the mouse has been clicked
		}
		checkButton(e);  //Check if the go on button has been clicked
		
	}
	
	public void changeInPlacement(Draggable d) {  //Changes the Draggable that is in place to the Draggable d
		if (d == null) {
			d = blankDraggable;
		}
		
		//Changing the inplacement Draggable to the new Draggable
		if (inplacement == blankDraggable) {
			inplacement = d;
		} else {
			checkName(d);
			inplacement = blankDraggable;
		}
		
	}

	public Draggable getInPlacement() {  //Returns the Draggable that is in placement right now (Real Draggable, or blankDraggable)
		return inplacement;
	}
	
	public void checkName(Draggable d) {  //Checks the name of the Draggable and creates a new one of that type of Draggable
		if (inplacement.name == "lake") {
			createLake();
		} if (inplacement.name == "crater") {
			createCrater();
		} if (inplacement.name == "archer") {
			createArcher();
		} if (inplacement.name == "warrior") {
			createWarrior();
		} if (inplacement.name == "wall") {
			createWall();
		}
	}
	
	public void minusCount(String name) {  //Checks the name of the Draggable and minuses the count of that Draggable
		if (name == "lake") {
			lakes--;
		} if (name == "crater") {
			craters--;
		} if (name == "archer") {
			archers--;
		} if (name == "warrior") {
			warriors--;
		} if (name == "wall") {
			walls--;
		}
	}
	
	public void checkButton(MouseEvent e) {  //Checks if the Go On button has been clicked
		int mx = e.getX();
		int my = e.getY();
		if (mx > 620) {
			if (my < 440) {
				if (mx < 720) {
					if (my < 480) {
						gm = 2;
					}
				}
			}
		}
	}
	
}
