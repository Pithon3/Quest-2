package quest2;

public class Warrior extends Sprite{

	public boolean appear = true;  //Boolean variable that represent if the sprite is supposed to be shown

	
	int damage = 1;  //Variable for the amount of damage the sprite does
	int lives = 2;  //Variable for the amount of lives the sprite has

	public Warrior(double x, double y, int xl, int yl, String filepath) {
		super(filepath, x, y, xl, yl);
	}
	
	@Override
	public void Update() {
		checkLives();  //Check the lives of the crater to see if it should still be living
	}
	
	public void defend(int life) {  //Function that does the damage on the crater
		x += 50;  //Move it back
		lives -= life;  //Deal damage
	}
	
	private void checkLives() {
		if (lives < 1) {  //If the warrior has less than one life (0)...
			appear = false;  //...Disappear
			x = -300;
			y = -300;
		}
	}
	
}
