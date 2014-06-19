package quest2;

public class KingGoblin extends Sprite{
	
	Enemy master;
	public boolean appear = false;
	
	int lives = 5;
	int damage = 3;
	
	boolean win = false;
	
	public KingGoblin(int x, int y, int xl, int yl, String path, Enemy enemy) {
		super(path, x, y, xl, yl);
		master = enemy;
	}
	
	@Override
	public void Update() {
		x += dx;
		y += dy;
		
		if (appear == true) {
			dx = 2;
		} else {
			dx = 0;
		}
		
		checkLives();
	}

	private void checkLives() {
		if (lives < 1) {
			win = true;
			appear = false;
			x = -300;
			y = -300;
		}
	}

	public void checkLake(Lake lake) {
		if(checkCollision(lake.x, lake.y, lake.xl, lake.yl)) {
			
		}
	}
	
	public void checkCrater(Crater crater) {
		if(checkCollision(crater.x, crater.y, crater.xl, crater.yl)) {

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

		}
	}

	public void checkArrow(Arrow arrow) {
		if(checkCollision(arrow.x, arrow.y, arrow.xl, arrow.yl)) {
			dx = 0;
			lives -= arrow.damage;
			arrow.defend(damage);
		}
	}
	
	public void appear() {
		appear = true;
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
