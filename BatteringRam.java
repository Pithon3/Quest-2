package quest2;

public class BatteringRam extends Sprite{
	
	Enemy master;  //The field for the enemy class
	public boolean appear = false;  //Boolean variable that represent if the sprite is supposed to be shown

	
	boolean bram = true;  //The boolean variable that represents if the battering ram is still a battering ram
	Goblin[] goblins = new Goblin[3];  //Create 3 goblins that will appear when the battering ram hits a lake, crater, or wall
	
	int lives = 3;  //Variable for the amount of lives the sprite has
	int damage = 2;  //Variable for the amount of damage the sprite does
	
	public BatteringRam(int x, int y, int xl, int yl, String path, Enemy enemy) {
		super(path, x, y, xl, yl);
		master = enemy;  //Assign master to the enemy argument
	}
	
	@Override
	public void Update() {
		if (bram == true) {  //If the battering ram is still a battering ram...
			//...Move it
			x += dx;
			y += dy;
		} else {  //Otherwise...
			for (int i = 0; i < 3; i++) {
				goblins[i].Update();  //...Update the goblins that have appeared
			}
		}
		
		if (appear == true) {
			if (bram == false) {
				appear = false;  //Change appear to false if it is not a battering ram
			} else {
				dx = 1;  //Set the X-speed to 1 if it is alive
			}
		} else {
			dx = 0;  //Otherwise set it to 0
		}
		
		checkLives();
	}

	public void checkLake(Lake lake) {  //Function to check if the battering ram is in collision with a lake
		if(checkCollision(lake.x, lake.y, lake.xl, lake.yl)) {
			dx = 0;
			if (bram == true) {
				goblin();
				lake.defend();
			}
		} if (bram == false) {
			for (int i = 0; i < 3; i++) {
				goblins[i].checkLake(lake);
			}
		}
	}
	
	public void checkCrater(Crater crater) {  //Function to check if the battering ram is in collision with a crater
		if(checkCollision(crater.x, crater.y, crater.xl, crater.yl)) {
			dx = 0;
			if (bram == true) {
				goblin();
				crater.defend();
			}
		} if (bram == false) {
			for (int i = 0; i < 3; i++) {
				goblins[i].checkCrater(crater);
			}
		}
	}
	
	public void checkArcher(Archer archer) {  //Function to check if the battering ram is in collision with a archer
		if(checkCollision(archer.x, archer.y, archer.xl, archer.yl)) {
			dx = 0;
			lives -= archer.damage;
			archer.defend(damage);
		} if (bram == false) {
			for (int i = 0; i < 3; i++) {
				goblins[i].checkArcher(archer);
			}
		}
	}
	
	public void checkWarrior(Warrior warrior) {  //Function to check if the battering ram is in collision with a warrior
		if(checkCollision(warrior.x, warrior.y, warrior.xl, warrior.yl)) {
			dx = 0;
			lives -= warrior.damage;
			warrior.defend(damage);
		} if (bram == false) {
			for (int i = 0; i < 3; i++) {
				goblins[i].checkWarrior(warrior);
			}
		}
	}
	
	public void checkWall(Wall wall) {  //Function to check if the battering ram is in collision with a wall
		if(checkCollision(wall.x, wall.y, wall.xl, wall.yl)) {
			dx = 0;
			if (bram == true) {
				goblin();
				wall.defend();
			}
		} if (bram == false) {
			for (int i = 0; i < 3; i++) {
				goblins[i].checkWall(wall);
			}
		}
	}

	public void checkArrow(Arrow arrow) {  //Function to check if the battering ram is in collision with a arrow
		if(checkCollision(arrow.x, arrow.y, arrow.xl, arrow.yl)) {
			dx = 0;
			if (bram == true) {
				lives -= arrow.damage;
				arrow.defend(damage);
			}
		}
		
		if (bram == false) {
			for (int i = 0; i < 3; i++) {
				goblins[i].checkArrow(arrow);
			}
		}
	}

	public void appear() {
		appear = true;
	}
	
	void goblin() {
		bram = false;
		for (int i = 0; i < 3; i++) {
			goblins[i] = new Goblin(x - 50, y + (48 * (i - 1)), 40, 62, "/quest2/goblin.gif", master, true);
		}
	}
	
	void checkLives() {
		if (bram == false | lives < 1) {
			appear = false;
			x = -300;
			y = -300;
		}
	}

	public void checkHero(Hero hero) {  //Function to check if the battering ram is in collision with the hero
		if(checkCollision(hero.x, hero.y, hero.xl, hero.yl)) {
			dx = 0;
			if (bram == true) {
				lives -= hero.damage;
				hero.defend(damage);
			}
		}
		
		if (bram == false) {
			for (int i = 0; i < 3; i++) {
				goblins[i].checkHero(hero);
			}
		}
		
	}

	public void checkSword(HeroSword sword) {  //Function to check if the battering ram is in collision with the hero's sword
		if(checkCollision(sword.x, sword.y, sword.xl, sword.yl)) {
			dx = 0;
			if (bram == true) {
				lives -= sword.damage;
			}
		}
		
		if (bram == false) {
			for (int i = 0; i < 3; i++) {
				goblins[i].checkSword(sword);
			}
		}		
	}
	
}
