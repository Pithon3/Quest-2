package quest2;

public class HeroSword extends Sprite{
	
	Hero hero;  //the owner of the sword
	int rotation = 0;  //Variable for the rotation of the sword

	int strikecount = 0;  //Count of how long the sword has been striking
	int damage = 3;  //The amount of damage the sword does
		
	public HeroSword(String path, int x, int y, int xl, int yl, Hero hero) {
		super(path, x, y, xl, yl);
		this.hero = hero;  //Assign something to the sword	
	}
	
	public void Update() {
		if (rotation == 1) {  //If the rotation of the sword is down...
			//...Place the sword according to the hero in such a way
			x = hero.x - 28;
			y = hero.y + 30;
			strikecount++;  //up the count of how long the sword has been striking
		} else {  //If the rotation of the sword is up...
			//...Place the sword according to the hero in such a way
			x = hero.x - 18;
			y = hero.y;
		}
		
		if (strikecount > 100) {  //If the count is above 100...
			//Unstrike
			destrike();
			strikecount = 0;
		}
		
	}
	
	public void strike() {  //Function to strike
		rotation = 1;
	}
	
	public void destrike() {  //Function to destrike
		rotation = 0;
	}
	
}
