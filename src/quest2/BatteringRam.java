package quest2;

import java.util.ArrayList;

public class BatteringRam extends GoblinPlayer{
	
	Enemy master;  //The field for the enemy class
	
	boolean bram = true;  //The boolean variable that represents if the battering ram is still a battering ram
	ArrayList<Goblin> goblins = new ArrayList<Goblin>();  //Create 3 goblins that will appear when the battering ram hits a lake, crater, or wall
	
	int lives = 3;  //Variable for the amount of lives the sprite has
	int damage = 2;  //Variable for the amount of damage the sprite does
	public boolean targeted = false;
	
	public BatteringRam(int x, int y, int xl, int yl, String path, Enemy enemy) {
		super(path, "battering ram", x, y, xl, yl);
		moving = true;
		appear = true;
		master = enemy;  //Assign master to the enemy argument
	}
	
	@Override
	public void Update() {
		if (bram != true) {
			for (int i = 0; i < 3; i++) {
				goblins.get(i).Update3();  //...Update the goblins that have appeared
			}
		}
		
		if (appear == true) {
			if (bram == false) {
				appear = false;  //Change appear to false if it is not a battering ram
			} else {
				dx = 1;  //Set the X-speed to 1 if it is alive
				moving = true;
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
				goblins.get(i).checkLake(lake);
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
				goblins.get(i).checkCrater(crater);
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
				goblins.get(i).checkArcher(archer);
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
				goblins.get(i).checkWarrior(warrior);
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
				goblins.get(i).checkWall(wall);
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
				goblins.get(i).checkArrow(arrow);
			}
		}
	}

	public void appear() {
		appear = true;
	}
	
	void goblin() {
		bram = false;
		for (int i = 0; i < 3; i++) {
			goblins.add(new Goblin(x - 50, y + (48 * (i - 1)), 40, 62, "/quest2/goblin.gif", master, this));
			master.add(goblins.get(i));
		}
	}
	
	void checkLives() {
		if (bram == false | lives < 1) {
			appear = false;
			master.remove(this);
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
				goblins.get(i).checkHero(hero);
			}
		}
		
	}

	public void checkSword(HeroSword sword) {  //Function to check if the battering ram is in collision with the hero's sword
		if(checkCollision(sword.x, sword.y, sword.xl, sword.yl)) {
			dx = 0;
			if (bram == true) {
				lives -= sword.damage;
				sword.defend(3);
			}
		}
		
		if (bram == false) {
			for (int i = 0; i < 3; i++) {
				goblins.get(i).checkSword(sword);
			}
		}		
	}
	
	public void remove (Goblin g) {
		goblins.remove(g);
	}
	
}
