package quest2;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
//import random stuff
import java.util.Random;

public class Enemy {
	
	Good2 good;  //A copy of an arch enemy
	
	Goblin[] goblins = new Goblin[22];  //Initiate 22 goblins
	int ngoblins = 0;  //Set the number of goblins to 0
	
	GoblinArcher[] garchers = new GoblinArcher[5];  //Initiate 5 goblin archers
	int ngarchers = 0;  //Set the number of archers to 0
	
	BatteringRam[] brams = new BatteringRam[8];  //Initiate 8 battering rams
	int nbrams = 0;  //Set the number of battering rams to 0
	
	KingGoblin kgoblin;  //Initiate the goblin king
	
	//Set the order of the things that appear
	String[] order = new String[]{"goblin", "goblin", "goblin", "goblin", "goblin", "goblin", "goblin", "goblin", "goblin", "goblin", "goblin", "goblin", "goblin", "goblin", "goblin", "battering ram", "battering ram", "battering ram", "battering ram", "battering ram", "archer", "archer", "archer", "archer", "archer", "goblin", "goblin", "goblin", "goblin", "goblin", "goblin", "goblin", "battering ram", "battering ram", "battering ram", "king"};
	
	ArrayList<Sprite> goblinplayers = new ArrayList<Sprite>();
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
		
		//Create actual goblins in the list of goblins
		for (int i = 0; i < 22; i++) {
		    int randnum = rand.nextInt(9) * 48;
		    goblins[i] = new Goblin(-40, randnum, 40, 62, "/quest2/goblin.gif", this);
		}

		//Create actual battering rams in the list of battering rams
		for (int i = 0; i < 8; i++) {
			int randnum = rand.nextInt(9) * 48;
			brams[i] = new BatteringRam(-94, randnum, 94, 63, "/quest2/batteringram.gif", this);
		}

		//Create actual goblin archers in the list of goblin archers
		for (int i = 0; i < 5; i++) {
			int randnum = rand.nextInt(9) * 48;
			garchers[i] = new GoblinArcher(-39, randnum, 39, 66, "/quest2/goblinarcher.gif", this);
		}

		//Create an actual king goblin
		int randnum = rand.nextInt(9) * 48;
		kgoblin = new KingGoblin(-40, randnum, 40, 67, "/quest2/kinggoblin.gif", this);
	    				
	}
	
	public void start(long starttime) {
		stime = starttime;  //set the start time to what ever the master says
	}
	
	public void update(long utime, boolean lose) {
		time = utime - stime;  //Calculate the time since stime was assigned
		this.lose = lose;
		
		good.updateEnemyList(targets);
		
		if (time > ntime) {  //If the time is greater than the time is has to be before the next foe comes...
			try {
				String name = order[(int) (ntime / 2)];  //...Then calculate what the next foe is based on the order and make it come
				if (name == "goblin") {
					goblins[ngoblins].appear();
					goblinplayers.add(goblins[ngoblins]);
					goblins[ngoblins].targeted = true;
					targets.add(goblins[ngoblins]);
					ngoblins ++;
				} if (name == "battering ram") {
					brams[nbrams].appear();
					goblinplayers.add(brams[nbrams]);
					brams[nbrams].targeted = true;
					targets.add(brams[nbrams]);
					nbrams ++;
				} if (name == "archer") {
					garchers[ngarchers].appear();
					goblinplayers.add(garchers[ngarchers]);
					garchers[ngarchers].targeted = true;
					targets.add(garchers[ngarchers]);
					ngarchers ++;
				} if (name == "king") {
					kgoblin.appear();
					kgoblin.targeted = true;
					targets.add(kgoblin);
					goblinplayers.add(kgoblin);
				}
				
			} catch(Exception e) {
				
			}
			ntime += 2;
		} 
		
		//Update the goblins and check if they're in the fort
		for (int i = 0; i < 22; i++) {
			Goblin goblin = goblins[i];
			
			goblin.Update();
			if (goblins[i].x > 720) {
				master.lose();
			}
			
		} 
		
		//Update the goblin archers and check if they're in the fort
		for (int i = 0; i < 5; i++) {
			garchers[i].Update();
			if (garchers[i].x > 720) {
				master.lose();
			}
		} 
		
		//Update the battering rams and check if they're in the fort
		for (int i = 0; i < 8; i++) {
			brams[i].Update();
			if (brams[i].x > 720) {
				master.lose();
			}
		}
		
		//Update the king goblin and check if it's in the fort
		kgoblin.Update();
		if (kgoblin.x > 720) {
			master.lose();
		}
		
		if (!lose) {
			checkCollision();  //Check for collisions among the kings minions
		}
		checkWin();  //Check if the impossible has happened
	}
	

	public void checkCollision() {  //Check each defense against each foe
		//Check Lake
		for(int i = 0; i < good.nlakes; i++) {
			Lake lake = good.lakes[i];
			
			for (int j = 0; j < 22; j++) {
				goblins[j].checkLake(lake);
			} for (int j = 0; j < 5; j++) {
				garchers[j].checkLake(lake);
			} for (int j = 0; j < 8; j++) {
				brams[j].checkLake(lake);
			}
			kgoblin.checkLake(lake);
		
		//Check Crater
		} for(int j = 0; j < good.ncraters; j++) {
			Crater crater = good.craters[j];
			
			for (int i = 0; i < 22; i++) {
				goblins[i].checkCrater(crater);
			} for (int i = 0; i < 5; i++) {
				garchers[i].checkCrater(crater);
			} for (int i = 0; i < 8; i++) {
				brams[i].checkCrater(crater);
			}
			kgoblin.checkCrater(crater);
		
		//Check Archer
		} for(int k = 0; k < good.narchers; k++) {
			Archer archer = good.archers[k];
			
			for (int j = 0; j < 22; j++) {
				goblins[j].checkArcher(archer);
			} for (int j = 0; j < 5; j++) {
				garchers[j].checkArcher(archer);
				garchers[j].arrow.checkArcher(archer);
			} for (int j = 0; j < 8; j++) {
				brams[j].checkArcher(archer);
			}
			kgoblin.checkArcher(archer);
			
			//Check Arrows
			for (int i = 0; i < archer.arrows.size(); i++) {
				Arrow arrow = archer.arrows.get(i);
				for (int j = 0; j < 22; j++) {
					goblins[j].checkArrow(arrow);
				} for (int j = 0; j < 5; j++) {
					garchers[j].checkArrow(arrow);
				} for (int j = 0; j < 8; j++) {
					brams[j].checkArrow(arrow);
				}
				kgoblin.checkArrow(arrow);
			}
			
		//Check Warrior
		} for(int l = 0; l < good.nwarriors; l++) {
			Warrior warrior = good.warriors[l];
			
			for (int j = 0; j < 22; j++) {
				goblins[j].checkWarrior(warrior);
			} for (int j = 0; j < 5; j++) {
				garchers[j].checkWarrior(warrior);
				garchers[j].arrow.checkWarrior(warrior);
			} for (int j = 0; j < 8; j++) {
				brams[j].checkWarrior(warrior);
			}
			kgoblin.checkWarrior(warrior);
		
		//Check Wall
		} for(int m = 0; m < good.nwalls; m++) {
			Wall wall = good.walls[m];
			
			for (int j = 0; j < 22; j++) {
				goblins[j].checkWall(wall);
			} for (int j = 0; j < 5; j++) {
				garchers[j].checkWall(wall);
			} for (int j = 0; j < 8; j++) {
				brams[j].checkWall(wall);
			}
			kgoblin.checkWall(wall);
			
		}
		
		//Check Hero
		Hero hero = good.hero;
		for (int j = 0; j < 22; j++) {
			goblins[j].checkHero(hero);
		} for (int j = 0; j < 5; j++) {
			garchers[j].checkHero(hero);
			garchers[j].arrow.checkHero(hero);
		} for (int j = 0; j < 8; j++) {
			brams[j].checkHero(hero);
		}
		kgoblin.checkHero(hero);
		
		//Check Hero's Sword
		HeroSword sword = hero.sword;
		for (int j = 0; j < 22; j++) {
			goblins[j].checkSword(sword);
		} for (int j = 0; j < 5; j++) {
			garchers[j].checkSword(sword);
			garchers[j].arrow.checkSword(sword);
		} for (int j = 0; j < 8; j++) {
			brams[j].checkSword(sword);
		}
		kgoblin.checkSword(sword);
					
	}
	
	private void checkWin() {  //Check if the impossible has happened
		int win = 0;
		for (int i = 0; i < 22; i++) {
			if (goblins[i].dx == 0) {
				win++;
			}
		} for (int i = 0; i < 5; i++) {
			if (garchers[i].dx == 0) {
				win++;
			}
		} for (int i = 0; i < 8; i++) {
			BatteringRam bram = brams[i];
			if (bram.bram == false) {
				for (int j = 0; j < 3; j++) {
					if (bram.goblins[j].dx == 0) {
						win++;
					}
				}
			}
		}
		if (kgoblin.win == true) {
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
	
}
