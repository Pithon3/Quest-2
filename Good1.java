package quest2;

import java.awt.event.MouseEvent;
import java.util.List;

public class Good1 {
	
	private int lakes = 2;
	private int craters = 3;
	private int archers = 4;
	private int warriors = 7;
	private int walls = 4;
	int gm = 1;
	
	Draggable[] items = new Draggable[20];
	int itemsin = 0;
	
	Draggable inplacement;
	Draggable blankDraggable = new Draggable("src/quest2/lake.gif", -300, -300, 34, 70, "null", this);
	
	public Good1() {
		createLake();
		createCrater();
		createArcher();
		createWarrior();
		createWall();
		
		inplacement = blankDraggable;
	}
	
	public void createLake() {
		if (lakes > 0) {
			items[itemsin] = new Draggable("src/quest2/lake.gif", 20, 20, 34, 70, "lake", this);
			itemsin++;
		}
	}
	
	public void createCrater() {
		if (craters > 0) {
			items[itemsin] = new Draggable("src/quest2/crater.gif", 120, 20, 38, 62, "crater", this);
			itemsin++;
		}
		
	}
	
	public void createArcher() {
		if (archers > 0) {
			items[itemsin] = new Draggable("src/quest2/archer.gif", 220, 20, 39, 66, "archer", this);
			itemsin++;
		}
	}
	
	public void createWarrior() {
		if (warriors > 0) {
			items[itemsin] = new Draggable("src/quest2/warrior.gif", 320, 20, 40, 62, "warrior", this);
			itemsin++;
		}
	}
	
	public void createWall() {
		if (walls > 0) {
			items[itemsin] = new Draggable("src/quest2/wall.png", 420, 20, 65, 86, "wall", this);
			itemsin++;
		}
	}

	public void update() {
		for (int i = 0; i < itemsin; i++) {
			items[i].Update2();
		}
	}


	public void mouseDragged(MouseEvent e) {
		for (int i = 0; i < itemsin; i++) {
			items[i].mouseDragged(e);
		}
	}

	public void mouseClicked(MouseEvent e) {
		for (int i = 0; i < itemsin; i++) {
			items[i].mouseClicked(e);
		}
		checkButton(e);
		
	}
	
	public void changeInPlacement(Draggable d) {
		if (d == null) {
			d = blankDraggable;
		}
		
		//Creating new Draggable
		if (inplacement == blankDraggable) {
			inplacement = d;
		} else {
			checkName(d);
			inplacement = blankDraggable;
		}
		
	}
	
	static void print(Goblin[] goblins) {
		for(int i = 0; i < 22; i++)
			System.out.println(goblins[i]);
	}

	public Draggable getInPlacement() {
		return inplacement;
	}
	
	public void checkName(Draggable d) {
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

	public static void print(int gm2) {
		System.out.println(gm2);
	}
	
	public void minusCount(String name) {
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
	
	public void checkButton(MouseEvent e) {
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

	public static void printl(String name) {
		System.out.print(name);
	}

	public static void print(boolean coll) {
		System.out.println(coll);		
	}

	public static void print(@SuppressWarnings("rawtypes") List order) {
		System.out.println(order);
		
	}

	public static void print(String string) {
		System.out.println(string);
		
	}

	public static void print(long utime) {
		System.out.println(utime);
		
	}

	public static void print(double atan) {
		System.out.println(atan);
		
	}

	public static void printl(int x) {
		System.out.print(x);
		
	}
	
}
