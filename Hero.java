package quest2;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Hero extends Sprite{
	
	int lives = 3;  //The variable that represents the amount of lives the arrow has
	int damage = 1;  //The variable that represents the amount of damage the arrow does
	boolean appear = true;  //Boolean variable that represent if the sprite is supposed to be shown
	
	HeroSword sword = new HeroSword("/quest2/herosword.gif", x, y, 22, 27, this);  //create the hero's sword
	
	public Hero(String path, int x, int y, int xl, int yl) {
		super(path, x, y, xl, yl);
	}
	
	public void Update() {
		sword.Update();  //Update the sword
		checkLives();  //Check the lives of the hero
		
		if (appear) {
			x += dx;
			y += dy;
		}
		
		if (x < 20) {
			x = 20;
		}
	}
	
	private void checkLives() {
		if (lives < 1) {  //If the hero has 0 or less lives...
			appear = false;  //Dissapear
			x = -300;
			y = -300;
		}
	}
	
	public void defend(int life) {  //A function for taking damage
		x += 50;  //Move back
		lives -= life;  //take damage
	}

	public void keyPressed(KeyEvent e) {
		int keycode = 0;
		try {
			keycode = e.getKeyCode();
		} catch (Exception E) { 
			//pass
		}
		
		if (keycode == KeyEvent.VK_UP) {
			dy -= 2;
		} if (keycode == KeyEvent.VK_DOWN) {
			dy += 2;
		} if (keycode == KeyEvent.VK_RIGHT) {
			dx += 2;
		} if (keycode == KeyEvent.VK_LEFT) {
			dx -= 2;
		} if (keycode == KeyEvent.VK_SPACE) {
			sword.strike();
		}
		
		if (dy > 2) {
			dy = 2;
		} if (dy < -2) {
			dy = -2;
		} if (dx > 2) {
			dx = 2;
		} if (dx < -2) {
			dx = -2;
		}
	}

	public void keyReleased(KeyEvent e) {
		int keycode = 0;
		try {
			keycode = e.getKeyCode();
		} catch (Exception E) { 
			//pass
		}
		
		if (keycode == KeyEvent.VK_UP) {
			dy += 2;
		} if (keycode == KeyEvent.VK_DOWN) {
			dy -= 2;
		} if (keycode == KeyEvent.VK_RIGHT) {
			dx -= 2;
		} if (keycode == KeyEvent.VK_LEFT) {
			dx += 2;
		}
	}
	
}
