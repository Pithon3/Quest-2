package quest2;

public class Goblin extends Sprite{
	
	Enemy master;
	public boolean appear = false;
	
	int lives = 1;
	int damage = 1;
	
	public Goblin(int x, int y, int xl, int yl, String path, Enemy enemy) {
		super(path, x, y, xl, yl);
		master = enemy;
	}
	
	public Goblin(int x, int y, int xl, int yl, String path, Enemy enemy, boolean appear) {
		super(path, x, y, xl, yl);
		master = enemy;
		this.appear = true;
	}

	@Override
	public void Update() {
		if (y < 0) {
			y = 0;
		} else if (y > (480 - yl)) {
			y = 480 - (2*yl);
		}
		
		x += dx;
		y += dy;
		
		if (appear == true) {
			dx = 1;
		} else {
			dx = 0;
		}
		
		checkLives();
	}

	private void checkLives() {
		if (lives < 1) {
			appear = false;
			x = -300;
			y = -300;
		}
	}

	public void appear() {
		appear = true;
	}

	public void checkLake(Lake lake) {
		if(checkCollision(lake.x, lake.y, lake.xl, lake.yl)) {
			dx = 0;	
		}
	}
	
	public void checkCrater(Crater crater) {
		if(checkCollision(crater.x, crater.y, crater.xl, crater.yl)) {
			dx = 0;
		}
	}
	
	public void checkArcher(Archer archer) {
		if(checkCollision(archer.x, archer.y, archer.xl, archer.yl)) {
			dx = 0;
			lives -= archer.damage;
			archer.defend(damage);
		}
	}
	
	public void checkWarrior(Warrior warrior) {
		if(checkCollision(warrior.x, warrior.y, warrior.xl, warrior.yl)) {
			dx = 0;
			lives -= warrior.damage;
			warrior.defend(damage);
		}
	}
	
	public void checkWall(Wall wall) {
		if(checkCollision(wall.x, wall.y, wall.xl, wall.yl)) {
			dx = 0;
		}
	}

	public void checkArrow(Arrow arrow) {
		if(checkCollision(arrow.x, arrow.y, arrow.xl, arrow.yl)) {
			dx = 0;
			lives -= arrow.damage;
			arrow.defend(damage);
		}
	}

	public void checkHero(Hero hero) {
		if(checkCollision(hero.x, hero.y, hero.xl, hero.yl)) {
			dx = 0;
			lives -= hero.damage;
			hero.defend(damage);
		}
	}

	public void checkSword(HeroSword sword) {
		if(checkCollision(sword.x, sword.y, sword.xl, sword.yl)) {
			dx = 0;
			lives -= sword.damage;
			x -= 50;
		}		
	}
	
}
