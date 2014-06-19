package quest2;

public class Warrior extends Sprite{

	public boolean appear = true;
	
	int damage = 1;
	int lives = 2;

	public Warrior(int x, int y, int xl, int yl, String filepath) {
		super(filepath, x, y, xl, yl);
	}
	
	@Override
	public void Update() {
		checkLives();
	}
	
	public void defend(int life) {
		x += 50;
		lives -= life;
	}
	
	private void checkLives() {
		if (lives < 1) {
			appear = false;
			x = -300;
			y = -300;
		}
	}
	
}
