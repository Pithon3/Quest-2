package quest2;

public class Hero extends Sprite{
	
	int lives = 3;
	int damage = 1;
	boolean appear = true;
	
	HeroSword sword = new HeroSword("src/quest2/herosword.gif", x, y, 22, 27, this);
	
	public Hero(String path, int x, int y, int xl, int yl) {
		super(path, x, y, xl, yl);
	}
	
	public void Update() {
		sword.Update();
		checkLives();
	}
	
	private void checkLives() {
		if (lives < 1) {
			appear = false;
			x = -300;
			y = -300;
		}
	}
	
	public void defend(int life) {
		x += 50;
		lives -= life;
	}
	
	public void up() {
		if (appear == true) {
			y -= 5;
		}
	}
	
	public void down() {
		if (appear == true) {
			y += 5;
		}	
	}
	
	public void right() {
		if (appear == true) {
			x += 5;
		}	
	}
	
	public void left() {
		if (appear == true) {
			x -= 5;
		}	
	}
	
}
