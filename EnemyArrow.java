package quest2;

import java.util.Random;

public class EnemyArrow extends Sprite {
	
	GoblinArcher archer;  //The archer that shot the arrow
	Random rand = new Random();  //Initiate a random class

	int lives = 1;  //The variable that represents the amount of lives the arrow has
	int damage = 2;  //The variable that represents the amount of damage the arrow does
	
	double direction;  //The direction in wich the arrow if pointing
	boolean fired = false;  //If the arrow is fired
	
	public EnemyArrow(String path, double x, double y, int xl, int yl, GoblinArcher master) {
		super(path, x, y, xl, yl);
		archer = master;  //Assign the GoblinArcher to archer
	}

	public void Update() {
		//Move the arrow
		x += dx;
		y += dy;
		
		
		if (fired == true) {
			dx = 2;  //If the arrow is fires, assign the X-speed to 2
			
			if (checkOutOfRange()) {  //Check if the arrow is out of the screen
				fired = false;
				x = archer.x;
				y = archer.y;
			}
			
		} else {  //If the arrow is not fired, fire it
			direction = rand.nextInt(3) - 1;
			direction = direction / 2;
			fired = true;
		}
	}

	public void checkLives() {  //Function to check the lives of the arrow if the arrow hits a defence
		fired = false;  //change fired to false so it can fire again
		x = archer.x;
		y = archer.y;
	}

	public void checkWarrior(Warrior warrior) {  //Check if the arrow is in collision with a warrior
		if(checkCollision(warrior.x, warrior.y, warrior.xl, warrior.yl)) {
			dx = 0;
			lives -= warrior.damage;
			warrior.defend(damage);
			checkLives();
		}
	}

	public void checkArcher(Archer archer2) {  //Check if the arrow is in collision with a archer
		if(checkCollision(archer2.x, archer2.y, archer2.xl, archer2.yl)) {
			dx = 0;
			lives -= archer2.damage;
			archer2.defend(damage);
			checkLives();
		}
	}

	public void checkHero(Hero hero) {  //Check if the arrow is in collision with the hero
		if(checkCollision(hero.x, hero.y, hero.xl, hero.yl)) {
			dx = 0;
			lives -= hero.damage;
			hero.defend(damage);
			checkLives();
		}		
	}

	public void checkSword(HeroSword sword) {  //Check if the arrow is in collision with the hero's sword
		if(checkCollision(sword.x, sword.y, sword.xl, sword.yl)) {
			dx = 0;
			lives -= sword.damage;
			checkLives();
		}			
	}
	
}

