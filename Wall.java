package quest2;

public class Wall extends Sprite{

	public boolean appear = true;
	
	int damage = 0;
	int lives = 3;

	public Wall(int x, int y, int xl, int yl, String filepath) {
		super(filepath, x, y, xl, yl);
	}
	
	@Override
	public void Update() {
		
		checkLives();
	}
	
	public void checkLives() {
		if (lives < 1) {
			appear = false;
			x = -300;
			y = -300;
		}
	}
	
	public void defend() {
		lives -= 1;
	}

}
