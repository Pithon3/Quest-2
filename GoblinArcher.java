package quest2;

public class GoblinArcher extends Sprite{
	
	int lives = 1;
	int damage = 0;
	
	EnemyArrow arrow = new EnemyArrow("src/quest2/enemyarrow.gif", x, y, 32, 7, this);
	
	Enemy master;
	public boolean appear = false;
	
	public GoblinArcher(int x, int y, int xl, int yl, String path, Enemy enemy) {
		super(path, x, y, xl, yl);
		master = enemy;
	}
	
	@Override
	public void Update() {
		arrow.Update();
		
		x += dx;
		y += dy;
		
		if (appear == true) {
			if (x < 0){
				dx = 1;
			} else {
				dx = 0;
			}
		} else {
			dx = 0;
		}
		
		checkLives();
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
		}
	}
	
	public void checkWarrior(Warrior warrior) {
		if(checkCollision(warrior.x, warrior.y, warrior.xl, warrior.yl)) {
			dx = 0;
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

	public void appear() {
		appear = true;		
	}
	
	public void checkLives() {
		if (lives < 1) {
			appear = false;
			x = -300;
			y = -300;
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
