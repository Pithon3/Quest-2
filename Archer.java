package quest2;

public class Archer extends Sprite{

	public boolean appear = true;
	
	int damage = 0;
	int lives = 1;
	
	Arrow arrow = new Arrow("src/quest2/arrow.gif", x, y, 32, 7, this);

	public Archer(int x, int y, int xl, int yl, String filepath) {
		super(filepath, x, y, xl, yl);
	}
	
	@Override
	public void Update() {
		arrow.Update();
		
		checkLives();
	}

	public void defend(int life) {
		x += 50;
		lives -= life;		
	}
	
	public void checkLives() {
		if (lives < 1) {
			appear = false;
			x = -300;
			y = -300;
		}
	}

}
