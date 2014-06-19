package quest2;

public class BatteringRam extends Sprite{
	
	Enemy master;
	public boolean appear = false;
	
	boolean bram = true;
	Goblin[] goblins = new Goblin[3];
	
	int lives = 3;
	int damage = 2;
	
	public BatteringRam(int x, int y, int xl, int yl, String path, Enemy enemy) {
		super(path, x, y, xl, yl);
		master = enemy;
	}
	
	@Override
	public void Update() {
		if (bram == true) {
			x += dx;
			y += dy;
		} else {
			for (int i = 0; i < 3; i++) {
				goblins[i].Update();
			}
		}
		
		if (appear == true) {
			if (bram == false) {
				appear = false;
			} else {
				dx = 1;
			}
		} else {
			dx = 0;
		}
		
		checkLives();
	}

	public void checkLake(Lake lake) {
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
	
	public void checkCrater(Crater crater) {
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
	
	public void checkArcher(Archer archer) {
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
	
	public void checkWarrior(Warrior warrior) {
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
	
	public void checkWall(Wall wall) {
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

	public void checkArrow(Arrow arrow) {
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
			goblins[i] = new Goblin(x - 50, y + (48 * (i - 1)), 40, 62, "src/quest2/goblin.gif", master, true);
		}
	}
	
	void checkLives() {
		if (bram == false | lives < 1) {
			appear = false;
			x = -300;
			y = -300;
		}
	}

	public void checkHero(Hero hero) {
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

	public void checkSword(HeroSword sword) {
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
