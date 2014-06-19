package quest2;

import java.util.Random;

public class Enemy {
	
	Good2 good;
	
	Goblin[] goblins = new Goblin[22];
	int ngoblins = 0;
	
	GoblinArcher[] garchers = new GoblinArcher[5];
	int ngarchers = 0;
	
	BatteringRam[] brams = new BatteringRam[8];
	int nbrams = 0;
	
	KingGoblin kgoblin;
	int nkgoblin = 0;
	
	String[] order = new String[]{"goblin", "goblin", "goblin", "goblin", "goblin", "goblin", "goblin", "goblin", "goblin", "goblin", "goblin", "goblin", "goblin", "goblin", "goblin", "battering ram", "battering ram", "battering ram", "battering ram", "battering ram", "archer", "archer", "archer", "archer", "archer", "goblin", "goblin", "goblin", "goblin", "goblin", "goblin", "goblin", "battering ram", "battering ram", "battering ram", "king"};
	
	long stime;
	long time;
	int ntime = 0;
	
	Random rand = new Random();
	Window master;

	private boolean Win = false;
	
	
	public Enemy(Good2 good, Window mas) {
		this.good = good;
		this.master = mas;
		
		for (int i = 0; i < 15; i++) {
		    int randnum = rand.nextInt(9) * 48;
		    goblins[i] = new Goblin(-40, randnum, 40, 62, "src/quest2/goblin.gif", this);
		}
		
		for (int i = 0; i < 5; i++) {
			int randnum = rand.nextInt(9) * 48;
			brams[i] = new BatteringRam(-94, randnum, 94, 63, "src/quest2/batteringram.gif", this);
		}
		
		for (int i = 0; i < 5; i++) {
			int randnum = rand.nextInt(9) * 48;
			garchers[i] = new GoblinArcher(-39, randnum, 39, 66, "src/quest2/goblinarcher.gif", this);
		}
		
		for (int i = 0; i < 7; i++) {
			int randnum = rand.nextInt(9) * 48;
			goblins[i + 15] = new Goblin(-40, randnum, 40, 62, "src/quest2/goblin.gif", this);
		}
		
		for (int i = 0; i < 3; i++) {
			int randnum = rand.nextInt(9) * 48;
			brams[i + 5] = new BatteringRam(-94, randnum, 94, 63, "src/quest2/batteringram.gif", this);
		}
		
		int randnum = rand.nextInt(9) * 48;
		kgoblin = new KingGoblin(-40, randnum, 40, 67, "src/quest2/kinggoblin.gif", this);
	    				
	}
	
	public void start(long starttime) {
		stime = starttime;
	}
	
	public void update(long utime) {
		time = utime - stime;

		if (time > ntime) {
			try {
				String name = order[ntime / 2];
				if (name == "goblin") {
					goblins[ngoblins].appear();
					ngoblins ++;
				} if (name == "battering ram") {
					brams[nbrams].appear();
					nbrams ++;
				} if (name == "archer") {
					garchers[ngarchers].appear();
					ngarchers ++;
				} if (name == "king") {
					kgoblin.appear();
				}
				
			} catch(Exception e) {
				
			}
			ntime += 2;
		}
		
		for (int i = 0; i < 22; i++) {
			goblins[i].Update();
			if (goblins[i].x > 720) {
				master.lose();
			}
		} for (int i = 0; i < 5; i++) {
			garchers[i].Update();
			if (garchers[i].x > 720) {
				master.lose();
			}
		} for (int i = 0; i < 8; i++) {
			brams[i].Update();
			if (brams[i].x > 720) {
				master.lose();
			}
		}
		kgoblin.Update();
		if (kgoblin.x > 720) {
			master.lose();
		}
		
		checkCollision();
		checkWin();
	}
	
	public void checkCollision() {
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
			Arrow arrow = archer.arrow;
			for (int j = 0; j < 22; j++) {
				goblins[j].checkArrow(arrow);
			} for (int j = 0; j < 5; j++) {
				garchers[j].checkArrow(arrow);
			} for (int j = 0; j < 8; j++) {
				brams[j].checkArrow(arrow);
			}
			kgoblin.checkArrow(arrow);
			
			
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
	
	private void checkWin() {
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
	
}
