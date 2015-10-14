package quest2;

public class GoblinArcher extends GoblinPlayer{
	
	int lives = 1;  //Variable for the amount of lives the sprite has
	int damage = 0;  //Variable for the amount of damage the sprite does
	
	EnemyArrow arrow = new EnemyArrow("/quest2/enemyarrow.gif", x, y, 32, 7, this);  //Initialize the arrow of the archer
	
	Enemy master;  //The field for the enemy class
	public boolean targeted = false;
	
	public GoblinArcher(int x, int y, int xl, int yl, String path, Enemy enemy) {
		super(path, "archer", x, y, xl, yl);
		moving = true;
		appear = true;
		master = enemy;  //Assign master to the enemy argument
		
	}
	
	@Override
	public void Update() {
		arrow.Update();  //Update the arrow
		
		if (appear == true) {
			if (x < 0){
				dx = 1;  //Set the X-speed to 1 if it is alive and not past it's shooting point
			} else {
				dx = 0;  //Otherwise set it to 0
				moving = false;
			}
		} else {
			dx = 0;  //Otherwise set it to 0
		}
		
		checkLives();  //check the lives of the goblin archer to see if it should still be living
	}

	public void checkLake(Lake lake) {  //Function to check if the goblin archer is in collision with a lake
		if(checkCollision(lake.x, lake.y, lake.xl, lake.yl)) {
			dx = 0;
			moving = false;
		}		
	}
	
	public void checkCrater(Crater crater) {  //Function to check if the goblin archer is in collision with a crater
		if(checkCollision(crater.x, crater.y, crater.xl, crater.yl)) {
			dx = 0;
			moving = false;
		}
	}
	
	public void checkArcher(Archer archer) {  //Function to check if the goblin archer is in collision with a archer
		if(checkCollision(archer.x, archer.y, archer.xl, archer.yl)) {
			dx = 0;
		}
	}
	
	public void checkWarrior(Warrior warrior) {  //Function to check if the goblin archer is in collision with a warrior
		if(checkCollision(warrior.x, warrior.y, warrior.xl, warrior.yl)) {
			dx = 0;
		}
	}
	
	
	public void checkWall(Wall wall) {  //Function to check if the goblin archer is in collision with a wall
		if(checkCollision(wall.x, wall.y, wall.xl, wall.yl)) {
			dx = 0;
			moving = false;
		}
	}

	public void checkArrow(Arrow arrow) {  //Function to check if the goblin archer is in collision with a arrow
		if(checkCollision(arrow.x, arrow.y, arrow.xl, arrow.yl)) {
			dx = 0;
			lives -= arrow.damage;
			arrow.defend(damage);
		}
	}

	public void appear() {  //Function to appear
		appear = true;  //Appear
	}
	
	public void checkLives() {  
		if (lives < 1) {  //If the goblin archer has less than one life (0)...
			appear = false;  //...Dissappear
			master.remove(this);
			x = -300;
			y = -300;
		}
	}

	public void checkHero(Hero hero) {
		if(checkCollision(hero.x, hero.y, hero.xl, hero.yl)) {  //Function to check if the goblin archer is in collision with the hero
			dx = 0;
			lives -= hero.damage;
			hero.defend(damage);
		}
	}

	public void checkSword(HeroSword sword) {
		if(checkCollision(sword.x, sword.y, sword.xl, sword.yl)) {  //Function to check if the goblin archer is in collision with the hero's sword
			dx = 0;
			lives -= sword.damage;
			x -= 50;
			sword.defend(1);
		}		
	}
	
}
