package quest2;

import java.util.ArrayList;

public class Warrior extends Sprite{

	public boolean appear = true;  //Boolean variable that represent if the sprite is supposed to be shown

	
	int damage = 1;  //Variable for the amount of damage the sprite does
	int lives = 2;  //Variable for the amount of lives the sprite has

	public Warrior(double x, double y, int xl, int yl, String filepath, String name) {
		super(filepath, name, x, y, xl, yl);
	}
	
	public void Update(ArrayList<Sprite> targets) {
		checkOutOfRange();
	}
	
	public boolean defend(int life) {  //Function that does the damage on the crater
		lives -= life;  //Deal damage
		boolean ret = checkLives();
		x += 50;  //Move it back
		return ret;
	}
	
	private boolean checkLives() {
		if (lives < 1) {  //If the warrior has less than one life (0)...
			appear = false;  //...Disappear
			x = -300;
			y = -300;
			return true;
		} else {
			return false;
		}
	}
	
}
