package quest2;

public class Goblin extends GoblinPlayer{
	
	Enemy master;  //The field for the enemy class

	int damage = 1;  //Variable for the amount of damage the sprite does
	int lives = 1;  //Variable for the amount of lives the sprite has
	public boolean targeted;
	public boolean partOfBram;
	public BatteringRam bramMaster;
	
	public Goblin(double x, double y, int xl, int yl, String path, Enemy enemy) {
		super(path, "goblin", x, y, xl, yl);
		moving = true;
		appear = true;
		targeted = true;
		master = enemy;  //Assign master to the enemy argument
	}
	
	public Goblin(double x, double y, int xl, int yl, String path, Enemy enemy, BatteringRam bram) {  //The function for the initiation through battering ram
		super(path, "goblin", x, y, xl, yl);
		master = enemy;
		targeted = true;
		moving = true;
		name = "Goblin";
		partOfBram = true;
		bramMaster = bram;
		appear = true;
	}

	@Override
	public void Update() {
		if (appear == true && moving) {
			dx = 1;//Set the X-speed to 1 if it is alive
		} else {
			dx = 0;  //Otherwise set it to 0
		}
		
		checkLives();  //check the lives of the goblin to see if it shoul still be living
	}

	private void checkLives() {
		if (lives < 1) {  //If the goblin has less than one life (0)...
			appear = false;  //...Dissappear
			master.remove(this);
			if (partOfBram) {
				bramMaster.remove(this);
			}
			x = -300;
			y = -300;
		}
	}

	public void appear() {  //Function to appear
		appear = true;  //Appear
	}

	@Override
	public void checkLake(Lake lake) {  //Function to check if the goblin is in collision with a lake
		if(checkCollision(lake.x, lake.y, lake.xl, lake.yl)) {
			dx = 0;
			if (moving) {
				master.fieldReport(lake);
			}
			moving = false;
		}
	}

	@Override
	public void checkCrater(Crater crater) {  //Function to check if the goblin is in collision with a crater
		if(checkCollision(crater.x, crater.y, crater.xl, crater.yl)) {
			dx = 0;
			if (moving) {
				master.fieldReport(crater);
			}
			moving = false;
		}
	}

	@Override
	public void checkArcher(Archer archer) {  //Function to check if the goblin is in collision with a archer
		if(checkCollision(archer.x, archer.y, archer.xl, archer.yl)) {
			dx = 0;
			lives -= archer.damage;
			archer.defend(damage);
			if (!archer.defend(damage)) {
				master.fieldReport(archer);
			}
		}
	}

	@Override
	public void checkWarrior(Warrior warrior) {  //Function to check if the goblin is in collision with a warrior
		if(checkCollision(warrior.x, warrior.y, warrior.xl, warrior.yl)) {
			dx = 0;
			lives -= warrior.damage;
			if (!warrior.defend(damage)) {
				master.fieldReport(warrior);
			}
		}
	}

	@Override
	public void checkWall(Wall wall) {  //Function to check if the goblin is in collision with a wall
		if(checkCollision(wall.x, wall.y, wall.xl, wall.yl)) {
			dx = 0;
			if (moving) {
				master.fieldReport(wall);
			}
			moving = false;
		}
	}

	@Override
	public void checkArrow(Arrow arrow) {  //Function to check if the goblin is in collision with a arrow
		if(checkCollision(arrow.x, arrow.y, arrow.xl, arrow.yl)) {
			dx = 0;
			lives -= arrow.damage;
			arrow.defend(damage);
			master.fieldReport(arrow);
		}
	}

	@Override
	public void checkHero(Hero hero) {  //Function to check if the goblin is in collision with the hero
		if(checkCollision(hero.x, hero.y, hero.xl, hero.yl)) {
			dx = 0;
			lives -= hero.damage;
			hero.defend(damage);
			if (!hero.defend(damage)) {
				master.fieldReport(hero);
			}
		}
	}

	@Override
	public void checkSword(HeroSword sword) {  //Function to check if the goblin is in collision with the hero's sword
		if(checkCollision(sword.x, sword.y, sword.xl, sword.yl)) {
			dx = 0;
			lives -= sword.damage;
			System.out.println("Hi");
			sword.defend(1);
			if (!sword.defend(damage)) {
				master.fieldReport(sword);
			}
		}		
	}
	
}
