package vincent;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class MainGame extends JPanel implements Serializable{
	
	 int speed = 1;
	 MovingBall ball = new  MovingBall(this);
	  MovingRect rect = new  MovingRect(this);
	  
	  private ArrayList<Integer> scores;
	  private File file = new File("scores.txt");
	 
	 public MainGame() {
		 
		 scores = new ArrayList<Integer>();
		 addKeyListener (new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				rect.keyPressed(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				rect.keyReleased(e);
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				
				
			}
			 
		 });
		 
		 setFocusable(true);
		AudioSound.BACK.loop();
		
	 }
	 
	 public void gameOver() {
		 AudioSound.BACK.stop();
		 AudioSound.GAMEOVER.play();
		 scores.add(getScore());
		 putToFile();
		 JOptionPane.showMessageDialog(this, "your score is: " + getScore(), "Game Over", JOptionPane.YES_NO_OPTION);
		 System.exit(ABORT);
	 }
	
	public void moveObject() {
		ball.move();
		rect.moveRect();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		ball.paint(g2d);
		rect.paint(g2d);
		
		g2d.setColor(Color.GRAY);
		g2d.setFont(new Font("Verdana", Font.BOLD, 30));
		g2d.drawString(String.valueOf(getScore()), 10, 30);
	}
	
	private int getScore() {
		return speed - 1;
	}
	
	public int getSpeed() {
		return speed;
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Mini Tennis");
		MainGame game = new MainGame();
		frame.add(game);
		frame.setSize(400, 400);
		frame.setBounds(500, 100, 300, 400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while (true) {
			game.moveObject();
			
			game.repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}

	}
	
	///Write to file
	public void putToFile() {
		try {
			FileOutputStream f = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(f);
			
			 for(Integer b: scores) {
				 out.writeObject(b);
				 
				 //Player sp = p;
				 out.writeObject("Score: " + b);
				 
				/* out.writeObject("ID: " + b.getId());
				 out.writeObject("ID: " + b.getAuthor());*/
				 
				 
				 
				}
				 
			 out.close();
			}
			catch(IOException ex) { 
				System.out.println("Bad files");
			}
		   System.out.println("Added to File");
		}
	// Write from File
		public void  putFromFile()  {
			try {
			   FileInputStream f = new FileInputStream(file);
			   ObjectInputStream in = new ObjectInputStream(f);
			   
			   while(true) {
					
					 int sp = (Integer)in.readObject();
					 scores.add(sp);
					
					in.close();
				 }
				// in.close();
			}catch (EOFException ex) {
				System.out.println("EOF files");
			}catch(IOException ex) {
				JOptionPane.showMessageDialog(null,"File could not be loaded");
				System.out.println("Bad files");
			}catch(ClassNotFoundException e) {
				System.out.println("ClassNotFound files");
			}
			
			
		}


}
