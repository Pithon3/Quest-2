package quest2;

public class KingGoblin extends Sprite{
	
	Enemy master;  //The field for the enemy class
	public boolean appear = false;  //Boolean variable that represent if the sprite is supposed to be shown

	
	int lives = 5;  //Variable for the amount of lives the sprite has
	int damage = 3;  //Variable for the amount of damage the sprite does
	
	boolean win = false;  //Variable for if the goblin died
	public boolean targeted = false;
	
	public KingGoblin(int x, int y, int xl, int yl, String path, Enemy enemy) {
		super(path, x, y, xl, yl);
		moving = true;
		master = enemy;  //Assign master to the enemy argument
		name = "GKing";
	}
	
	@Override
	public void Update() {
		//Move the goblin king
		x += dx;
		y += dy;
		
		
		if (appear == true) {
			dx = 2;  //Set the X-speed to 2 if it is alive
		} else {
			dx = 0;  //Otherwise set it to 0
		}
		
		checkLives();  //check the lives of the goblin to see if it should still be living
	}

	private void checkLives() {
		if (lives < 1) {  //If the goblin has less than one life (0)...
			win = true;  //...Then set win to true meaning that it has died and...
			appear = false;  //...Dissappear.
			master.remove(this);
			x = -300;
			y = -300;
		}
	}

	public void checkLake(Lake lake) {  //Function to check if the goblin king is in collision with a 
		if(checkCollision(lake.x, lake.y, lake.xl, lake.yl)) {
			
		}
	}
	
	public void checkCrater(Crater crater) {
		if(checkCollision(crater.x, crater.y, crater.xl, crater.yl)) {  //Function to check if the goblin king is in collision with a lake

		}
	}
	
	public void checkArcher(Archer archer) {
		if(checkCollision(archer.x, archer.y, archer.xl, archer.yl)) {  //Function to check if the goblin king is in collision with a archer
			dx = 0;
			lives -= archer.damage;
			archer.defend(damage);
		}
	}
	
	public void checkWarrior(Warrior warrior) {
		if(checkCollision(warrior.x, warrior.y, warrior.xl, warrior.yl)) {  //Function to check if the goblin king is in collision with a warrior
			dx = 0;
			lives -= warrior.damage;
			warrior.defend(damage);
		}
	}
	
	
	public void checkWall(Wall wall) {
		if(checkCollision(wall.x, wall.y, wall.xl, wall.yl)) {  //Function to check if the goblin king is in collision with a wall

		}
	}

	public void checkArrow(Arrow arrow) {
		if(checkCollision(arrow.x, arrow.y, arrow.xl, arrow.yl)) {  //Function to check if the goblin king is in collision with a arrow
			dx = 0;
			lives -= arrow.damage;
			arrow.defend(damage);
		}
	}
	
	public void appear() {  //Function to appear
		appear = true;  //Appear
	}

	public void checkHero(Hero hero) {
		if(checkCollision(hero.x, hero.y, hero.xl, hero.yl)) {  //Function to check if the goblin king is in collision with the hero
			dx = 0;
			lives -= hero.damage;
			hero.defend(damage);
		}
		
	}

	public void checkSword(HeroSword sword) {
		if(checkCollision(sword.x, sword.y, sword.xl, sword.yl)) {  //Function to check if the goblin king is in collision with the hero's sword
			dx = 0;
			lives -= sword.damage;
			x -= 50;
		}
	}
	
}
