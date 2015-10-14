package quest2;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
//import random stuff
import java.util.Random;

public class Enemy {
	
	Good2 good;  //Arch enemy
	
	ArrayList<Goblin> goblins = new ArrayList<Goblin>();
	ArrayList<GoblinArcher> garchers = new ArrayList<GoblinArcher>();
	ArrayList<BatteringRam> brams = new ArrayList<BatteringRam>();
	ArrayList<GiantGoblin> ggoblins = new ArrayList<GiantGoblin>();
	
	KingGoblin kgoblin;  //Initiate the goblin king
	boolean king = false;
	
	ArrayList<String> order = new ArrayList<String>();
	ArrayList<Integer> intOrder = new ArrayList<Integer>();
	
	ArrayList<GoblinPlayer> goblinplayers = new ArrayList<GoblinPlayer>();
	ArrayList<Sprite> targets = new ArrayList<Sprite>();
	
	//Sprite[] archert = new Sprite[]{Window.blankSprite, Window.blankSprite, Window.blankSprite, Window.blankSprite};
	
	double stime;  //The start time of the attack
	double time;  //The updated current time
	double ntime = 0;  //The time that it has to be before the next foe comes
	boolean lose;
	
	Random rand = new Random();  //A random class
	Window master;  //The master of all, the window

	private boolean Win = false;  //The boolean variable that represents when Good side has won
	
	public Enemy(Good2 good, Window mas) {
		this.good = good;  //Assign good to something
		this.master = mas;  //Let the enemy know who is the master
		
		for (int i = 0; i < 7; i++) {
			sendArmy("goblin", 55 * ((int) (Math.random() * 7)) + 20);
		}
	}
	
	public void start(long starttime) {
		stime = starttime;  //set the start time to what ever the master says
	}
	
	public void update(long utime, boolean lose) {
		time = utime - stime;  //Calculate the time since stime was assigned
		this.lose = lose;
		
		good.updateEnemyList(targets);

		//if (time > ntime) {  //If the time is greater than the time is has to be before the next foe comes...
			try {
				String name = order.get(0);  //...Then calculate what the next foe is based on the order and make it come
			    int num = intOrder.get(0);
				
				if (name == "goblin") {
					Goblin g = new Goblin(-40, num, 40, 62, "/quest2/goblin.gif", this);
					goblins.add(g);
					goblinplayers.add(g);
					targets.add(g);
				} 
				
				if (name == "battering ram") {
					BatteringRam b = new BatteringRam(-94, num, 94, 63, "/quest2/batteringram.gif", this);
					brams.add(b);
					goblinplayers.add(b);
					targets.add(b);
				} 
				
				if (name == "archer") {
					GoblinArcher a = new GoblinArcher(-39, num, 39, 66, "/quest2/goblinarcher.gif", this);
					garchers.add(a);
					goblinplayers.add(a);
					targets.add(a);
				} 
				
				if (name == "giant goblin") {
					GiantGoblin g = new GiantGoblin(-80, num, 80, 122, "/quest2/giantgoblin.png", this);
					ggoblins.add(g);
					goblinplayers.add(g);
					targets.add(g);
				}
				
				if (name == "king") {
					kgoblin = new KingGoblin(-40, num, 40, 67, "/quest2/kinggoblin.gif", this);
					targets.add(kgoblin);
					goblinplayers.add(kgoblin);
				}
				
				order.remove(name);
				intOrder.remove(num);
				
			} catch(Exception e) {
				
			}
		//} 
		
		//Update the goblins and check if they're in the fort
		for (int i = 0; i < goblins.size(); i++) {
			Goblin goblin = goblins.get(i);
			goblin.Update3();
			if (goblin.x > 720) {
				master.lose();
			}
			
		} 
		
		//Update the goblin archers and check if they're in the fort
		for (int i = 0; i < garchers.size(); i++) {
			GoblinArcher archer = garchers.get(i);
			archer.Update3();
			if (garchers.get(i).x > 720) {
				master.lose();
			}
		} 
		
		//Update the battering rams and check if they're in the fort
		for (int i = 0; i < brams.size(); i++) {
			brams.get(i).Update3();
			if (brams.get(i).x > 720) {
				master.lose();
			}
		}
		
		for (int i = 0; i < ggoblins.size(); i++) {
			ggoblins.get(i).Update3();
			if (ggoblins.get(i).x > 720) {
				master.lose();
			}
		}
		
		//Update the king goblin and check if it's in the fort
		try {
			kgoblin.Update3();
			if (kgoblin.x > 720) {
				master.lose();
			}
		} catch (Exception e) {
			//pass
		}
			
		if (!lose) {
			checkCollision();  //Check for collisions among the kings minions
		}
		checkWin();  //Check if the impossible has happened
	}
	

	public void checkCollision() {  //Check each defense against each foe
		
		for (int i = 0; i < goblinplayers.size(); i++) {
			
			GoblinPlayer goblin = goblinplayers.get(i);
			for (int j = 0; j < good.defences.size(); j++) {
				Sprite s = good.defences.get(j);
				String name = s.name;
				if (name == "Lake") {
					goblin.checkLake((Lake) s);
				} else if (name == "Crater") {
					goblin.checkCrater((Crater) s);					
				} else if (name == "Archer") {
					goblin.checkArcher((Archer) s);					
				} else if (name == "Arrow") {
					goblin.checkArrow((Arrow) s);					
				} else if (name == "Warrior") {
					goblin.checkWarrior((Warrior) s);					
				} else if (name == "Wall") {
					goblin.checkWall((Wall) s);					
				} else if (name == "Hero") {
					goblin.checkHero((Hero) s);					
				} else if (name == "Hero Sword") {
					goblin.checkSword((HeroSword) s);					
				}
				
			}
				
		}
					
	}
	
	private void checkWin() {  //Check if the impossible has happened
		int win = 0;
		for (int i = 0; i < goblins.size(); i++) {
			if (goblins.get(i).dx == 0) {
				win++;
			}
		} for (int i = 0; i < garchers.size(); i++) {
			if (garchers.get(i).dx == 0) {
				win++;
			}
		} for (int i = 0; i < brams.size(); i++) {
			BatteringRam bram = brams.get(i);
			if (bram.bram == false) {
				for (int j = 0; j < 3; j++) {
					if (bram.goblins.get(j).dx == 0) {
						win++;
					}
				}
			}
		} for (int i = 0; i < ggoblins.size(); i++) {
			if (ggoblins.get(i).dx == 0) {
				win++;
			}
		}
		if (king && kgoblin.win == true) {
			win++;
		}
		
		if (win > 51 & Win == false) {
			Win  = true;
			master.win();
		}
	}
	
	public void remove(Sprite goblin) {
		goblinplayers.remove(goblin);
		try {
			targets.remove(goblin);
		} catch (Exception e) {
			
		}
		
		/*for (int i = 0; i < 4; i++) {
			if (archert[i] == goblin) {
				archert[i] = Window.blankSprite;
			}
		}*/
	}
	
	public void add (Sprite goblin) {
		targets.add(goblin);
	}

	public void mouseClicked(MouseEvent e) {
		//Futute feature
		
		/*if (good.directoree != Window.blankSprite) {
			for (int i = 0; i < goblinplayers.size(); i++) {
				Sprite s = goblinplayers.get(i);
				if (s.checkCollision(e.getX(), e.getY(), 1, 1)) {
					String name = good.directoree.name;
					if (name == "Archer") {
						archert[good.directoree.num] = s;
					}
				}
			}
			
		}*/
	}
	
	public void sendArmy(String name, int y) {
		order.add(name);
		intOrder.add(y);		
	}
	
	public void fieldReport(Sprite s) {
		if (s.name == "Hero Sword") {
			sendArmy("giant goblin", (int) s.y - 15);
		}
		if (s.name == "Hero") {
			sendArmy("goblin", (int) s.y);
		}
		if (s.name == "Wall") {
			sendArmy("battering ram", (int) s.y);
		}
		if (s.name == "Lake") {
			sendArmy("battering ram", (int) s.y);
		}
		if (s.name == "Crater") {
			sendArmy("battering ram", (int) s.y);
		}
		if (s.name == "Warrior") {
			sendArmy("goblin", (int) s.y);
		}
		if (s.name == "Archer") {
			sendArmy("goblin", (int) s.y);
		}
		if (s.name == "Arrow") {
			sendArmy("archer", (int) s.y);
		}
	}
	
}
