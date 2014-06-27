package quest2;

public class Good2 {
	
	Good1 good1;  //A copy of the first good side controller
	boolean init = false;  //A variable that signifies if the gamemode is 2 yet
	
	Lake[] lakes = new Lake[2];  //Initiate 2 lakes
	int nlakes = 0;  //set the count of lakes to 0
	
	Crater[] craters = new Crater[3];  //Initiate 3 craters
	int ncraters = 0;  //set the count of craters to 0
	
	Archer[] archers = new Archer[4];  //Initiate 4 archers
	int narchers = 0;  //set the count of archers to 0
	
	Warrior[] warriors = new Warrior[7];  //Initiate 7 warriors
	int nwarriors = 0;  //set the count of warriors to 0
	
	Wall[] walls = new Wall[4];  //Initiate 4 walls
	int nwalls = 0;  //set the count of walls to 0
	
	public Hero hero;  //Create the hero of the game
	
	public Good2(Good1 g1) {
		good1 = g1;  //Assign the argument g1 to the field good1
	}
	
	void convertDraggables(Draggable[] items, Enemy enemy) {  //Function to convert the Draggables to their separate classes
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
		
		hero = new Hero("/quest2/hero.gif", 620, 200, 24, 62);  //Create the hero
		
	}

	public void update() {  //Update the seperate classes
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