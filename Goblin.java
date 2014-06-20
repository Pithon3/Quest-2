package quest2;

public class Goblin extends Sprite{
	
	Enemy master;  //The field for the enemy class
	public boolean appear = false;  //Boolean variable that represent if the sprite is supposed to be shown

	int damage = 1;  //Variable for the amount of damage the sprite does
	int lives = 1;  //Variable for the amount of lives the sprite has
	
	public Goblin(int x, int y, int xl, int yl, String path, Enemy enemy) {
		super(path, x, y, xl, yl);
		master = enemy;  //Assign master to the enemy argument
	}
	
	public Goblin(int x, int y, int xl, int yl, String path, Enemy enemy, boolean appear) {  //The function for the initiation through battering ram
		super(path, x, y, xl, yl);
		master = enemy;
		this.appear = true;  //The goblin can now appear right away
	}

	@Override
	public void Update() {
		//Check the Y bounds and fix them if necessary
		if (y < 0) {
			y = 0;
		} else if (y > (480 - yl)) {
			y = 480 - (2*yl);
		}
		
		//Move the goblin
		x += dx;
		y += dy;
		
		if (appear == true) {
			dx = 1;  //Set the X-speed to 1 if it is alive
		} else {
			dx = 0;  //Otherwise set it to 0
		}
		
		checkLives();  //check the lives of the goblin to see if it shoul still be living
	}

	private void checkLives() {
		if (lives < 1) {  //If the goblin has less than one life (0)...
			appear = false;  //...Dissappear
			x = -300;
			y = -300;
		}
	}

	public void appear() {  //Function to appear
		appear = true;  //Appear
	}

	public void checkLake(Lake lake) {  //Function to check if the goblin is in collision with a lake
		if(checkCollision(lake.x, lake.y, lake.xl, lake.yl)) {
			dx = 0;	
		}
	}
	
	public void checkCrater(Crater crater) {  //Function to check if the goblin is in collision with a crater
		if(checkCollision(crater.x, crater.y, crater.xl, crater.yl)) {
			dx = 0;
		}
	}
	
	public void checkArcher(Archer archer) {  //Function to check if the goblin is in collision with a archer
		if(checkCollision(archer.x, archer.y, archer.xl, archer.yl)) {
			dx = 0;
			lives -= archer.damage;
			archer.defend(damage);
		}
	}
	
	public void checkWarrior(Warrior warrior) {  //Function to check if the goblin is in collision with a warrior
		if(checkCollision(warrior.x, warrior.y, warrior.xl, warrior.yl)) {
			dx = 0;
			lives -= warrior.damage;
			warrior.defend(damage);
		}
	}
	
	public void checkWall(Wall wall) {  //Function to check if the goblin is in collision with a wall
		if(checkCollision(wall.x, wall.y, wall.xl, wall.yl)) {
			dx = 0;
		}
	}

	public void checkArrow(Arrow arrow) {  //Function to check if the goblin is in collision with a arrow
		if(checkCollision(arrow.x, arrow.y, arrow.xl, arrow.yl)) {
			dx = 0;
			lives -= arrow.damage;
			arrow.defend(damage);
		}
	}

	public void checkHero(Hero hero) {  //Function to check if the goblin is in collision with the hero
		if(checkCollision(hero.x, hero.y, hero.xl, hero.yl)) {
			dx = 0;
			lives -= hero.damage;
			hero.defend(damage);
		}
	}

	public void checkSword(HeroSword sword) {  //Function to check if the goblin is in collision with the hero's sword
		if(checkCollision(sword.x, sword.y, sword.xl, sword.yl)) {
			dx = 0;
			lives -= sword.damage;
			x -= 50;
		}		
	}
	
}
