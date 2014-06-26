package quest2;

public class Archer extends Sprite{

	public boolean appear = true;  //Boolean variable that represent if the sprite is supposed to be shown
	
	int damage = 0;  //Variable for the amount of damage the sprite does
	int lives = 1;  //Variable for the amount of lives the sprite has
	
	Arrow arrow = new Arrow("/src/quest2/arrow.gif", x, y, 32, 7, this);

	public Archer(int x, int y, int xl, int yl, String filepath) {
		super(filepath, x, y, xl, yl);
	}
	
	@Override
	public void Update() {
		arrow.Update();  //update the archer's arrows
		checkLives();  //Check the lives of the archer to see if it should still be living
	}

	public void defend(int life) {  //Function that does the damage on the arhcer
		x += 50;  //Move it back
		lives -= life;  //Deal damage
	}
	
	public void checkLives() {
		if (lives < 1) {  //If the archer has less than one life (0)...
			appear = false;  //...Disappear
			x = -300;
			y = -300;
		}
	}

}
