package quest2;

import java.util.ArrayList;
import java.util.Random;

public class Arrow extends Sprite {
	
	Archer archer;  //The archer that shot the arrow
	Random rand = new Random();  //Initiate a random class

	int lives = 1;  //The variable that represents the amount of lives the arrow has
	int damage = 2;  //The variable that represents the amount of damage the arrow does
	
	double direction = 0;  //The direction in wich the arrow if pointing
	
	public Arrow(String path, double x, double y, int xl, int yl, double tx, double ty, Archer master, int facing) {
		super(path, "Arrow", x, y, xl, yl);
		archer = master;  //Assign the arrow to archer
		name = "Arrow";
		
		double dis = Math.sqrt(Math.pow(x-tx, 2) + Math.pow(y-ty, 2));
		dx = -3 * (x-tx) / dis;
		dy = -3 *(y-ty) / dis;
		
		direction = Math.atan((ty-y)/(tx-x)) + facing*Math.PI;
	}

	public void Update() {
		//Move the arrow
		x += dx;
		y += dy;
	}

	public void defend(int life) {  //A function to destroy arrow
		x = archer.x;
		y = archer.y;
		
		archer.arrows.remove(this);
	}
	
}
