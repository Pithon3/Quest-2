package quest2;

import java.util.ArrayList;

public class Archer extends Sprite{

	int damage = 0;
	int lives = 1;
	
	ArrayList<Arrow> arrows = new ArrayList<Arrow>();
	int arrowtimer = 201;
	
	Good2 master;
	Sprite target = null;
	int num;
	int facing = 0;
	
	Sprite ptarget = null;
	
	public Archer(Good2 g, double x, double y, int xlong, int ylong, String filepath, int num) {
		super(filepath, x, y, xlong, ylong);
		name = "Archer";
		master = g;
		num = num;
		appear = true;
	}
	
	public void Update(ArrayList<Sprite> targets, Sprite target) {
		checkLives();
		
		arrowtimer++;
		
		if (arrowtimer > 200) {
			arrowtimer = 0;
			
			if (target == Window.blankSprite) {
			
				for (int i = 0; i < 4; i++) {
					try {
					
						target = targets.get(num - i);
						boolean tmoving = targets.get(num - i).moving;
						double dis = Math.sqrt(Math.pow(x-target.x, 2) + Math.pow(y-target.y, 2));
						
						double xaim = 0;
						if (tmoving == true) {
							if (target.name != "GKing") {
								xaim = target.x + (dis/3);
							} else {
								xaim = target.x + dis/2;
							}
						} else {
							xaim = target.x;
						}
						
						
						if (xaim < x) {
							setImage("/quest2/archer.gif");
							facing = 0;
						} else {
							setImage("/quest2/archer2.png");
							facing = 1;
						}
				
						if (facing == 0) {
							arrows.add(new Arrow("/quest2/arrow.gif", x - 15, y + 10, 32, 7, xaim, target.y, this, facing));
						} else {
							arrows.add(new Arrow("/quest2/arrow.gif", x + 15, y + 10, 32, 7, xaim, target.y, this, facing));
						}
				
						break;
					
					} catch (Exception e) { }
				}
				
			} else { 
				double dis = Math.sqrt(Math.pow(x-target.x, 2) + Math.pow(y-target.y, 2));
				
				if ((target.x + (dis / 3)) < x) {
					setImage("/quest2/archer.gif");
					facing = 0;
				} else {
					setImage("/quest2/archer2.png");
					facing = 1;
				}
		
				if (facing == 0) {
					arrows.add(new Arrow("/quest2/arrow.gif", x - 15, y + 10, 32, 7, (target.x + (dis / 3)), target.y, this, facing));
				} else {
					arrows.add(new Arrow("/quest2/arrow.gif", x + 15, y + 10, 32, 7, (target.x + (dis / 3)), target.y, this, facing));
				}
			}
		
		}
		
		for (int i = 0; i < arrows.size(); i++) {
			arrows.get(i).Update();
		}
	}
	
	public void checkLives() {
		if (lives < 1) {  //If the crater has less than one life (0)...
			appear = false;  //...Disappear
			x = -300;
			y = -300;
		}
	}
	
	public void defend(int life) {  //Function that does the damage on the Archer
		x += 50;  //Move it back
		lives -= life;  //Deal damage
	}
	
	public void setTarget(Sprite tar) {
		target = tar;
	}

}