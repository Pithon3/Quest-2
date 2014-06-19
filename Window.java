package quest2;

import java.awt.Color;
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
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.swing.*;


public class Window extends JPanel implements ActionListener{
	
	private Timer timer;
	private int gm;
	
	private WindowFrame frame;
	private Good1 good1;
	private Good2 good2;
	private Enemy enemy;
	
	private Graphics g;
	private int rate = 0;
	
	private int counter1;
	
	private static final long serialVersionUID = 1L;

	public Window(WindowFrame frame) {
		this.frame = frame;
		gm = 1;
		
		addMouseMotionListener(new MListener());
		addMouseListener(new MAdapter());
		addKeyListener(new TAdapter());
		setBackground(Color.BLUE);
		setFocusable(true);
		setDoubleBuffered(true);
		
		good1 = new Good1();
		good2 = new Good2(good1);
		
		timer = new Timer(10, this);
		timer.start();
	}
	
	public void paint(Graphics g) {
		this.g = g;
		super.paint(g);
		
		Graphics2D G = (Graphics2D)g;
		
	    //G.translate(170, 0); // Translate the center of our coordinates.
        
	    
		if (gm == 1) {
			good1.update();
			G.setColor(new Color(255, 0, 0));
			if(counter1 < 300) {
				G.drawString("You are the commander of an army.", 280, 400);
			} else {
				if (counter1 < 600) {
					G.drawString("You have gotten word that the goblin king is attacking.", 250, 400);
				} else {
					if (counter1 < 900) {
						G.drawString("Place the defences to defend your territory.", 270, 400);
					} else {
						G.drawString("You have 2 lakes, 3 trenches, 4 archers,", 280, 400);
						G.drawString("7 warriors, and 4 walls", 300, 415);
					}
				}
			}
			
			G.drawString("Go on --->", 620, 435);
			
			for (int i = 0; i < good1.itemsin; i++) {
				Draggable sprite = good1.items[i];
				G.drawImage(sprite.image, sprite.x, sprite.y, this);
			}
			
			counter1++;
		}
		
		if (gm == 2) {
			good2.update();
			enemy.update(time());
			
			//The Good Guys
			for (int i = 0; i < good2.nlakes; i++) {
				Lake lake = good2.lakes[i];
				if (lake.appear == true) {
					G.drawImage(lake.image, lake.x, lake.y, this);
				}
			}
			for (int j = 0; j < good2.ncraters; j++) {
				Crater crater = good2.craters[j];
				if (crater.appear == true) {
					G.drawImage(crater.image, crater.x, crater.y, this);
				}	
			} 
			for (int k = 0; k < good2.narchers; k++) {
				Archer archer = good2.archers[k];
				if (archer.appear == true) {
					G.drawImage(archer.image, archer.x, archer.y, this);
				}
				
				Arrow arrow = archer.arrow;
				G.rotate(arrow.direction, arrow.x, arrow.y);
				G.drawImage(arrow.image, arrow.x, arrow.y, this);
				G.rotate(-arrow.direction, arrow.x, arrow.y);
			} 
			for (int l = 0; l < good2.nwarriors; l++) {
				Warrior warrior = good2.warriors[l];
				if (warrior.appear == true) {
					G.drawImage(warrior.image, warrior.x, warrior.y, this);
				}
			}
			for (int m = 0; m < good2.nwalls; m++) {
				Wall wall = good2.walls[m];
				if (wall.appear == true) {
					G.drawImage(wall.image, wall.x, wall.y, this);
				}
			}
			Hero hero = good2.hero;
			if (hero.appear == true) {
				HeroSword sword = hero.sword;
				G.drawImage(hero.image, hero.x, hero.y, this);
				
				G.rotate(-sword.rotation, sword.x, sword.y);
				G.drawImage(sword.image, sword.x, sword.y, this);
				G.rotate(sword.rotation, sword.x, sword.y);

			}
			
			//The Enemy
			for (int n = 0; n < 22; n++) {
				Goblin goblin = enemy.goblins[n];
				if (goblin.appear == true) {
					
					G.drawImage(goblin.image, goblin.x, goblin.y, this);
				}
			} for (int i = 0; i < 5; i++) {
				GoblinArcher garcher = enemy.garchers[i];
				if (garcher.appear == true) {
					G.drawImage(garcher.image, garcher.x, garcher.y, this);
				}
				
				EnemyArrow arrow = garcher.arrow;
				G.drawImage(arrow.image, arrow.x, arrow.y, this);
				
			} for (int i = 0; i < 8; i++) {
				BatteringRam bram = enemy.brams[i];
				if (bram.appear == true) {
					if (bram.bram == true) {
						G.drawImage(bram.image, bram.x, bram.y, this);
					}
				} else {
					if (bram.bram == false) {
						for (int j = 0; j < 3; j++) {
							Goblin goblin = bram.goblins[j];
							if (goblin.appear == true) {
								G.drawImage(goblin.image, goblin.x, goblin.y, this);
							}
						}
					}
				}
			} 
			if (enemy.kgoblin.appear == true) {
				G.drawImage(enemy.kgoblin.image, enemy.kgoblin.x, enemy.kgoblin.y, this);
			}
			
		}
		
		if (gm == 5) {
			JFrame frame = new JFrame("You Lose");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			JLabel emptyLabel = new JLabel("");
			emptyLabel.setPreferredSize(new Dimension(300, 100));
			frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);

			frame.pack();
			frame.setVisible(true);
			
			BufferedImage BufImage = null;
			
			try {
				BufImage = ImageIO.read(new File("src/quest2/Logo.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			ImageIcon ii = new ImageIcon(BufImage);
			Image image = ii.getImage();
			
			frame.setIconImage(image);
			
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setResizable(false);
			frame.add(new MiniFrame("YOU LOSE.", Color.RED));
			
			gm = 7;
		}
		
		if (gm == 6) {
			JFrame frame = new JFrame("You Win!");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			JLabel emptyLabel = new JLabel("");
			emptyLabel.setPreferredSize(new Dimension(300, 100));
			frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);

			frame.pack();
			frame.setVisible(true);
			
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setResizable(false);
			frame.setIconImage(this.frame.image);
			frame.add(new MiniFrame("YOU WIN!!", Color.BLUE));
			
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
		if (good1.gm == 2) {
			if (gm == 1) {
				good2.convertDraggables(good1.items, enemy);
				enemy = new Enemy(good2, this);
				enemy.start(time());
				gm = 2;
			}
		}
		repaint();
		
	}
	
	public void plusgm() {
		gm++;
	}
	
	public void lose() {
		gm = 5;
	}
	
	public void win() {
		gm = 6;
	}
	
	public long time() {
		long time1 = System.nanoTime();
		long time2 = time1 / 1000000000;
		long time3 = time2 + (rate / 10);
		return time3;
	}
	
	private class TAdapter extends KeyAdapter {
		
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
				WindowFrame.window.paint(g);
				WindowFrame.window.paint(g);
				WindowFrame.window.paint(g);
				WindowFrame.window.paint(g);
				WindowFrame.window.paint(g);
				WindowFrame.window.paint(g);
				WindowFrame.window.paint(g);
				WindowFrame.window.paint(g);
				WindowFrame.window.paint(g);
				WindowFrame.window.paint(g);
				WindowFrame.window.paint(g);
				WindowFrame.window.paint(g);
				WindowFrame.window.paint(g);
				WindowFrame.window.paint(g);
				WindowFrame.window.paint(g);
				WindowFrame.window.paint(g);
				WindowFrame.window.paint(g);
				WindowFrame.window.paint(g);
				WindowFrame.window.paint(g);
			} else {
				rate = 0;
			}

		}
		
	}
	
	private class MListener extends MouseMotionAdapter  {
	    
	    public void mouseDragged(MouseEvent e) { 
	    	good1.mouseDragged(e);
	    }
	}
	
	private class MAdapter extends MouseAdapter {
		
		public void mouseClicked(MouseEvent e) {
			good1.mouseClicked(e) ;
		}
	}
	
	private class MiniFrame extends JPanel {
		private static final long serialVersionUID = 1L;
		
		String Message;
		private MiniFrame(String message, Color color) {
			addMouseMotionListener(new MListener());
			addMouseListener(new MAdapter());
			addKeyListener(new TAdapter());
			setBackground(color);
			setFocusable(true);
			setDoubleBuffered(true);
			
			Message = message;
		}
		
		public void paint(Graphics g) {
			super.paint(g);
			
			Graphics2D G = (Graphics2D)g;
			G.setColor(new Color(0, 75, 75));
			G.drawString(Message, 135, 45);
		}
		
	}
	
}
