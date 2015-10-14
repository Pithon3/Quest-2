package quest2;

public class Crater extends Sprite{

	public boolean appear = true;  //Boolean variable that represent if the sprite is supposed to be shown
	
	int damage = 0;  //Variable for the amount of damage the sprite does
	int lives = 1;  //Variable for the amount of lives the sprite has

	public Crater(double x, double y, int xl, int yl, String filepath, String n) {
		super(filepath, n, x, y, xl, yl);
	}
	
	@Override
	public void Update() {
		checkLives();  //Check the lives of the crater to see if it should still be living
	}
	
	public void checkLives() {
		if (lives < 1) {  //If the crater has less than one life (0)...
			appear = false;  //...Disappear
			x = -300;
			y = -300;
		}
	}
	
	public void defend() {  //Function that does the damage on the crater
		lives -= 1;  //Deal damage
	}

}
