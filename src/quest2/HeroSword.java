package quest2;

public class HeroSword extends Sprite{
	
	int displayX, displayY;
	
	Hero hero;  //the owner of the sword
	int rotation = 0;  //Variable for the rotation of the sword
	int lives = 50;
	
	int strikecount = 0;  //Count of how long the sword has been striking
	int damage = 3;  //The amount of damage the sword does
		
	public HeroSword(String path, double x, double y, int xl, int yl, Hero hero) {
		super(path, "Hero Sword", x, y, xl, yl);
		this.hero = hero;
		appear = true;
	}
	
	public void Update() {		
		System.out.println(lives);
		if (rotation == 1) {  //If the rotation of the sword is down...
			//...Place the sword according to the hero in such a way
			x = hero.x - 28;
			y = hero.y + 30;
			
			damage = lives / 10;
			
			strikecount++;  //up the count of how long the sword has been striking
		} else {  //If the rotation of the sword is up...
			//...Place the sword according to the hero in such a way
			x = hero.x - 18;
			y = hero.y;
			
			damage = lives / 15;
		}
		
		if (strikecount > 100) {  //If the count is above 100...
			//Unstrike
			destrike();
			strikecount = 0;
		}
		
		displayX = (int) x;
		displayY = (int) y;
		
		if (lives <= 25) {
			displayX -= 3;
			displayY += 5;
		}
	}
		
	public boolean checkLives() {
		if (lives < 1) {
			appear = false;
			x = -300;
			y = -300;
			return true;
		} else {
			return false;
		}
	}
	
	public void strike() {  //Function to strike
		rotation = 1;
	}
	
	public void destrike() {  //Function to destrike
		rotation = 0;
	}
	
	public boolean defend(int dam) {
		lives -= dam;
		
		if (lives <= 10) {
			setImage("/quest2/herosword3.png");
		} else if (lives <= 25) {
			setImage("/quest2/herosword2.png");
			xl = 25;
			yl = 22;
		}
		
		return checkLives();
	}
	
}
