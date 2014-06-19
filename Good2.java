package quest2;

public class Good2 {
	
	Good1 good1;
	boolean init = false;
	int itemsin;
	
	Lake[] lakes = new Lake[2];
	int nlakes = 0;
	
	Crater[] craters = new Crater[3];
	int ncraters = 0;
	
	Archer[] archers = new Archer[4];
	int narchers = 0;
	
	Warrior[] warriors = new Warrior[7];
	int nwarriors = 0;
	
	Wall[] walls = new Wall[4];
	int nwalls = 0;
	public Hero hero;
	
	public Good2(Good1 g1) {
		good1 = g1;
	}
	
	void convertDraggables(Draggable[] items, Enemy enemy) {
		for (int i = 0; i < good1.itemsin; i++) {
			Draggable convert = items[i];
			
			if (convert.name == "lake") {
				lakes[nlakes] = new Lake(convert.x,  convert.y, convert.xl, convert.yl, convert.filepath);
				nlakes += 1;
			} 
			if (convert.name == "crater") {
				craters[ncraters] = new Crater(convert.x,  convert.y, convert.xl, convert.yl, convert.filepath);
				ncraters += 1;
			} 
			if (convert.name == "archer") {
				archers[narchers] = new Archer(convert.x, convert.y, convert.xl, convert.yl, convert.filepath);
				narchers += 1;
			} 
			if (convert.name == "warrior") {
				warriors[nwarriors] = new Warrior(convert.x,  convert.y, convert.xl, convert.yl, convert.filepath);
				nwarriors += 1;
			} 
			if (convert.name == "wall") {
				walls[nwalls] = new Wall(convert.x, convert.y, convert.xl, convert.yl, convert.filepath);
				nwalls += 1;
			}
		}
		
		hero = new Hero("src/quest2/hero.gif", 620, 200, 24, 62);
		
	}

	public void update() { 
		for (int i = 0; i < nlakes; i++) {
			lakes[i].Update();
		} for (int j = 0; j < ncraters; j++) {
			craters[j].Update();
		} for (int k = 0; k < narchers; k++) {
			archers[k].Update();
		} for (int l = 0; l < nwarriors; l++) {
			warriors[l].Update();
		} for (int m = 0; m < nwalls; m++) {
			walls[m].Update();
		}
		hero.Update2();
	}
	
}