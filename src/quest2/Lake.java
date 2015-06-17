package quest2;

public class Lake extends Sprite{
	
	public boolean appear = true;  //Boolean variable that represent if the sprite is supposed to be shown

	
	int damage = 0;  //Variable for the amount of damage the sprite does
	int lives = 2;  //Variable for the amount of lives the sprite has

	public Lake(double x, double y, int xl, int yl, String filepath) {
		super(filepath, x, y, xl, yl);
	}
	
	@Override
	public void Update() {
		checkLives();  //Check the lives of the lake to see if it should still be living
	}
	
	public void checkLives() {
		if (lives < 1) {  //If the lake has less than one life (0)...
			appear = false;  //...Disappear
			x = -300;
			y = -300;
		}
	}
	
	public void defend() {  //Function that does the damage on the crater
		lives -= 1;  //Deal damage
	}
	
}
