package quest2;

public class Hero extends Sprite{
	
	int lives = 3;  //The variable that represents the amount of lives the arrow has
	int damage = 1;  //The variable that represents the amount of damage the arrow does
	boolean appear = true;  //Boolean variable that represent if the sprite is supposed to be shown

	
	HeroSword sword = new HeroSword("/src/quest2/herosword.gif", x, y, 22, 27, this);  //create the hero's sword
	
	public Hero(String path, int x, int y, int xl, int yl) {
		super(path, x, y, xl, yl);
	}
	
	public void Update() {
		sword.Update();  //Update the sword
		checkLives();  //Check the lives of the hero
	}
	
	private void checkLives() {
		if (lives < 1) {  //If the hero has 0 or less lives...
			appear = false;  //Dissapear
			x = -300;
			y = -300;
		}
	}
	
	public void defend(int life) {  //A function for taking damage
		x += 50;  //Move back
		lives -= life;  //take damage
	}
	
	public void up() {  //Function for moving up when the up key is pressed
		if (appear == true) {
			y -= 5;
		}
	}
	
	public void down() {  //Function for moving down when the down key is pressed
		if (appear == true) {
			y += 5;
		}	
	}
	
	public void right() {  //Function for moving right when the right key is pressed
		if (appear == true) {
			x += 5;
		}	
	}
	
	public void left() {  //Function for moving left when the left key is pressed
		if (appear == true) {
			x -= 5;
		}	
	}
	
}
