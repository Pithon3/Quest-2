package quest2;

public class HeroSword extends Sprite{
	
	Hero hero;
	int rotation = 0;

	int strikecount = 0;
	int damage = 3;
		
	public HeroSword(String path, int x, int y, int xl, int yl, Hero hero) {
		super(path, x, y, xl, yl);
		this.hero = hero;		
	}
	
	public void Update() {
		if (rotation == 1) {
			x = hero.x - 28;
			y = hero.y + 30;
		} else {
			x = hero.x - 18;
			y = hero.y;
		}
		
		strikecount++;
		
		if (strikecount > 100) {
			destrike();
			strikecount = 0;
		}
		
	}
	
	public void strike() {
		rotation = 1;
	}
	
	public void destrike() {
		rotation = 0;
	}
	
}
