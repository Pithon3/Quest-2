package quest2;

import java.util.Random;

public class EnemyArrow extends Sprite {
	
	GoblinArcher archer;
	Random rand = new Random();

	int lives = 1;
	int damage = 2;
	
	double direction;
	boolean fired = false;
	
	public EnemyArrow(String path, int x, int y, int xl, int yl, GoblinArcher master) {
		super(path, x, y, xl, yl);
		archer = master;
	}

	public void Update() {
		x += dx;
		y += dy;
		
		if (fired == true) {
			dx = 2;
			
			if (checkOutOfRange()) {
				fired = false;
				x = archer.x;
				y = archer.y;
			}
		} else {
			direction = rand.nextInt(3) - 1;
			direction = direction / 2;
			fired = true;
		}
	}

	public void checkLives() {
		fired = false;
		x = archer.x;
		y = archer.y;
	}

	public void checkWarrior(Warrior warrior) {
		if(checkCollision(warrior.x, warrior.y, warrior.xl, warrior.yl)) {
			dx = 0;
			lives -= warrior.damage;
			warrior.defend(damage);
			checkLives();
		}
	}

	public void checkArcher(Archer archer2) {
		if(checkCollision(archer2.x, archer2.y, archer2.xl, archer2.yl)) {
			dx = 0;
			lives -= archer2.damage;
			archer2.defend(damage);
			checkLives();
		}
	}

	public void checkHero(Hero hero) {
		if(checkCollision(hero.x, hero.y, hero.xl, hero.yl)) {
			dx = 0;
			lives -= hero.damage;
			hero.defend(damage);
			checkLives();
		}		
	}

	public void checkSword(HeroSword sword) {
		if(checkCollision(sword.x, sword.y, sword.xl, sword.yl)) {
			dx = 0;
			lives -= sword.damage;
			checkLives();
		}			
	}
	
}

