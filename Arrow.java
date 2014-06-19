package quest2;

import java.util.Random;

public class Arrow extends Sprite {
	
	Archer archer;
	Random rand = new Random();

	int lives = 1;
	int damage = 2;
	
	double direction;
	boolean fired = false;
	
	public Arrow(String path, int x, int y, int xl, int yl, Archer master) {
		super(path, x, y, xl, yl);
		archer = master;
	}

	public void Update() {
		x += dx;
		y += dy;
		
		if (fired == true) {
			dx = -2;
			dy = (int)(direction * -2);
			
			if (checkOutOfRange()) {
				fired = false;
				x = archer.x;
				y = archer.y;
			}
		} else {
			direction = rand.nextInt(3) - 1;
			direction = direction / 2;
			fired = true;
		}
	}

	public void defend(int life) {
		fired = false;
		x = archer.x;
		y = archer.y;
	}
	
}
