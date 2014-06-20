package quest2;

import java.util.Random;

public class Arrow extends Sprite {
	
	Archer archer;  //The archer that shot the arrow
	Random rand = new Random();  //Initiate a random class

	int lives = 1;  //The variable that represents the amount of lives the arrow has
	int damage = 2;  //The variable that represents the amount of damage the arrow does
	
	double direction;  //The direction in wich the arrow if pointing
	boolean fired = false;  //If the arrow is fired
	
	public Arrow(String path, int x, int y, int xl, int yl, Archer master) {
		super(path, x, y, xl, yl);
		archer = master;  //Assign the Archer to archer
	}

	public void Update() {
		//Move the arrow
		x += dx;
		y += dy;
		
		if (fired == true) {
			dx = -2;  //If the arrow is fires, assign the X-speed to -2
			dy = (int)(direction * -2);  //assign the Y-speed to the direction * -2
			
			if (checkOutOfRange()) {  //Check if the arrow is out of the screen
				fired = false;
				x = archer.x;
				y = archer.y;
			}
		} else {  //If the arrow is not fired, fire it
			direction = rand.nextInt(3) - 1;
			direction = direction / 2;
			fired = true;
		}
	}

	public void defend(int life) {  //A function to re-fire
		fired = false;
		x = archer.x;
		y = archer.y;
	}
	
}
