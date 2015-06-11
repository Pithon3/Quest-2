package quest2;

//Import stuff
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Window extends JPanel implements ActionListener{
	
	private Timer timer;  //A timer for the program to update
	private int gm;  //The gamemode of the game
	
	private WindowFrame frame;  //The frame of the window
	private Good1 good1;  //Gamemode 1 good side (Dragging and placing items
	private Good2 good2;  //Gamemode 2 good side (Fighting and defending from the goblins)
	private Enemy enemy;  //The controller of the goblins
	
	private Graphics g;  //Storing the graphics engine
	private int rate = 0;  //The rate at wich the game progresses
	
	private int counter;  //A count of the ticks between different game intructions
	
	private static final long serialVersionUID = 1L;  //Code so I don't get a warning
	
	static Sprite blankSprite = new Sprite(500, 500);

	public Window(WindowFrame frame) {
		this.frame = frame;  //Assing the argument frame to the field
		gm = 1;  //Set the gamemode to 1
		
		//Add Listeners for external actions
		addMouseMotionListener(new MListener());
		addMouseListener(new MAdapter());
		addKeyListener(new TAdapter());
		
		//Setting things about the content of the window
		setBackground(Color.BLUE);
		setFocusable(true);
		setDoubleBuffered(true);
		
		//Initializing the good side
		good1 = new Good1();
		good2 = new Good2(good1);
		
		//Start the update sequence and timer
		timer = new Timer(10, this);
		timer.start();
	}
	
	public void paint(Graphics g) {
		this.g = g;  //Store the Graphics engine in a field
		super.paint(g);
		
		Graphics2D G = (Graphics2D)g;  //Initialize Graphics2D        
		
		//What to do during the first gamemode
		if (gm == 1) {
			
			//Say the instructions
			good1.update();
			G.setColor(new Color(255, 0, 0));
			if(counter < 300) {
				G.drawString("You are the commander of an army.", 280, 400);
			} else {
				if (counter < 600) {
					G.drawString("You have gotten word that the goblin king is attacking.", 250, 400);
				} else {
					if (counter < 900) {
						G.drawString("Place the defences to defend your territory.", 270, 400);
					} else {
						G.drawString("You have 2 lakes, 3 trenches, 4 archers,", 280, 400);
						G.drawString("7 warriors, and 4 walls", 300, 415);
					}
				}
			}
			
			//Make the text for the go on button
			G.drawString("Go on --->", 620, 435);
			
			//Update the placeable items
			for (int i = 0; i < good1.itemsin; i++) {
				Draggable sprite = good1.items[i];
				G.drawImage(sprite.image, (int) sprite.x, (int) sprite.y, this);
			}
			
			//Update the counter
			counter++;
		}
		
		//Updating in the 2nd Gamemode
		if (gm == 2) {
			//Update the opposing sides
			good2.update();
			enemy.update(time());
			
			//Draw the...
			
			//...lakes
			for (int i = 0; i < good2.nlakes; i++) {
				Lake lake = good2.lakes[i];
				if (lake.appear == true) {
					G.drawImage(lake.image, (int) lake.x, (int) lake.y, this);
				}
			}
			
			//...craters
			for (int j = 0; j < good2.ncraters; j++) {
				Crater crater = good2.craters[j];
				if (crater.appear == true) {
					G.drawImage(crater.image, (int) crater.x, (int) crater.y, this);
				}	
			}
			
			//...arhcers
			for (int k = 0; k < good2.narchers; k++) {
				Archer archer = good2.archers[k];
				if (archer.appear == true) {
					G.drawImage(archer.image, (int) archer.x, (int) archer.y, this);
					
					if (archer.directed == true) {
						G.setColor(new Color(0, 200, 0));
						G.drawRect((int) archer.x, (int) archer.y, (int) archer.xl, (int) archer.yl);
					}
				}
				
				//...Arrows
				for (int i = 0; i < archer.arrows.size(); i++) {
					Arrow arrow = archer.arrows.get(i);
					G.rotate(arrow.direction, arrow.x + 16, arrow.y + 6);
					G.drawImage(arrow.image, (int) arrow.x, (int) arrow.y, this);
					G.rotate(-arrow.direction, arrow.x + 16, arrow.y + 6);
				}
			} 
			
			//...warriors
			for (int l = 0; l < good2.nwarriors; l++) {
				Warrior warrior = good2.warriors[l];
				if (warrior.appear == true) {
					G.drawImage(warrior.image, (int) warrior.x, (int) warrior.y, this);
				}
			}
			
			//...walls
			for (int m = 0; m < good2.nwalls; m++) {
				Wall wall = good2.walls[m];
				if (wall.appear == true) {
					G.drawImage(wall.image, (int) wall.x, (int)  wall.y, this);
				}
			}
			
			//...hero
			Hero hero = good2.hero;
			if (hero.appear == true) {
				HeroSword sword = hero.sword;
				G.drawImage(hero.image, (int) hero.x, (int) hero.y, this);
				
				G.rotate(-sword.rotation, sword.x, sword.y);
				G.drawImage(sword.image, (int) sword.x, (int) sword.y, this);
				G.rotate(sword.rotation, sword.x, sword.y);

			}
			
			//...goblins
			for (int n = 0; n < 22; n++) {
				Goblin goblin = enemy.goblins[n];
				if (goblin.appear == true) {
					G.drawImage(goblin.image, (int) goblin.x, (int) goblin.y, this);
				}
			} 
			
			//...goblin archers
			for (int i = 0; i < 5; i++) {
				GoblinArcher garcher = enemy.garchers[i];
				if (garcher.appear == true) {				
					G.drawImage(garcher.image, (int) garcher.x, (int) garcher.y, this);
				}
				
				//...goblin archer's arrows
				EnemyArrow arrow = garcher.arrow;
				G.drawImage(arrow.image, (int) arrow.x, (int) arrow.y, this);
				
			} 
			
			//...battering rams
			 for (int i = 0; i < 8; i++) {
				BatteringRam bram = enemy.brams[i];
				if (bram.appear == true) {  //If the battering ram is still a battering ram
					if (bram.bram == true) {
						G.drawImage(bram.image, (int) bram.x, (int) bram.y, this);
					}
				} else {  //Otherwise draw the goblins
					if (bram.bram == false) {
						for (int j = 0; j < 3; j++) {
							Goblin goblin = bram.goblins[j];
							if (goblin.appear == true) {
								G.drawImage(goblin.image, (int) goblin.x, (int) goblin.y, this);
							}
						}
					}
				}
			} 
				
			//...goblin king
			if (enemy.kgoblin.appear == true) {
				G.drawImage(enemy.kgoblin.image, (int) enemy.kgoblin.x, (int) enemy.kgoblin.y, this);
			}
			
		}
		
		//Update for the 5th gamemode (losing)
		if (gm == 5) {
			//Create frame for losing
			JFrame frame = new JFrame("You Lose");
			JLabel emptyLabel = new JLabel("");
			emptyLabel.setPreferredSize(new Dimension(300, 100));
			frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);

			//Set stuff about the frame
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.pack();
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setResizable(false);
			
			//Add content to the frame
			frame.add(new MiniFrame("YOU LOSE.", Color.RED));
			
			//Make the screen mostly blank
			gm = 7;
		}
		
		if (gm == 6) {
			//Create frame for winning
			JFrame frame = new JFrame("You Win!");
			JLabel emptyLabel = new JLabel("");
			emptyLabel.setPreferredSize(new Dimension(300, 100));
			frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
			
			//Set stuff about the frame
			frame.pack();
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setResizable(false);
			
			//Add content to the frame
			frame.add(new MiniFrame("YOU WIN!!", Color.BLUE));
			
			//Continue showing the finishing doom of the goblins
			gm = 2;
		}
		
		G.setColor(new Color(255, 0, 0));
		G.drawString("Enemy Grounds", 15, 235);
		G.drawString("The Fort", 650, 235);
				
		Toolkit.getDefaultToolkit().sync();
		G.dispose();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (good1.gm == 2) {  //If Good1 is done placing
			if (gm == 1) {  //And Window doesn't know that yet
				//Make it known
				good2.convertDraggables(good1.items, enemy);
				enemy = new Enemy(good2, this);
				enemy.start(time());
				gm = 2;
			}
		}
		repaint();
		
	}
	
	public void plusgm() {  //Up the gamemode one
		gm++;
	}
	
	public void lose() {  //Change the gamemode to the losing one
		gm = 5;
	}
	
	public void win() {  //Change the gamemode to the winning one
		gm = 6;
	}
	
	public long time() {  //Calculate the time in seconds for the enemy
		long time1 = System.nanoTime();
		long time2 = time1 / 1000000000;
		long time3 = time2 + (rate / 10);
		return time3;
	}
	
	private class TAdapter extends KeyAdapter {  //The keystroke listener
		
		public void keyPressed(KeyEvent e) {
			int keycode = e.getKeyCode();
			
			//Controlling the hero
			if (keycode == KeyEvent.VK_UP) {
				good2.hero.up();				
			} if (keycode == KeyEvent.VK_DOWN) {
				good2.hero.down();
			} if (keycode == KeyEvent.VK_RIGHT) {
				good2.hero.right();
			} if (keycode == KeyEvent.VK_LEFT) {
				good2.hero.left();
			} if (keycode == KeyEvent.VK_SPACE) {
				good2.hero.sword.strike();
			}
			
			//Key commands
			if (keycode == KeyEvent.VK_Q) {
				WindowFrame.exit();
			} if (keycode == KeyEvent.VK_R) {
				WindowFrame.replay(frame.doExit);
			} if (keycode == KeyEvent.VK_O) {
				WindowFrame.play();
			
			//fast forward
			} if (keycode == KeyEvent.VK_F) {
				rate ++;
				for (int i = 0; i < 20; i++) {  //Update things a lot faster if the F key is pressed
					WindowFrame.window.paint(g);
				}
			} else {
				rate = 0;  //Set the rate back to 0 if it is not pressed
			}

		}
		
	}
	
	private class MListener extends MouseMotionAdapter  {  //Listens for the mouse moving
	    
	    public void mouseDragged(MouseEvent e) { 
	    	good1.mouseDragged(e);  //Tell good1 the mouse moved
	    	if (enemy != null) {
				enemy.mouseClicked(e);
			}
	    }
	}
	
	private class MAdapter extends MouseAdapter {  //Listens for the mouse Clicking
		
		public void mouseClicked(MouseEvent e) {
			good1.mouseClicked(e);  //Tell good1 the mouse clicked
			good2.mouseClicked(e);
			if (enemy != null) {
				enemy.mouseClicked(e);
			}
		}
	}
	
	private class MiniFrame extends JPanel {  //A miniframe for multiple purposes
		private static final long serialVersionUID = 1L;
		
		String Message;
		private MiniFrame(String message, Color color) {  //Get the message to display, and the Color to show
			//Set stuff			
			addMouseMotionListener(new MListener());
			addMouseListener(new MAdapter());
			addKeyListener(new TAdapter());
			setBackground(color);
			setFocusable(true);
			setDoubleBuffered(true);
			
			//Assign the argument message to the field Message
			Message = message;
		}
		
		public void paint(Graphics g) {
			super.paint(g);
			
			//Display the message
			Graphics2D G = (Graphics2D)g;
			G.setColor(new Color(0, 75, 75));
			G.drawString(Message, 135, 45);
		}
		
	}
	
}
