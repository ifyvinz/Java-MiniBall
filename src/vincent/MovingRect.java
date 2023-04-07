package vincent;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MovingRect {
	int px = 0;
	int dx = 0;
	private MainGame game;

	private static final int Y = 330;
	private static final int WIDTH = 60;
	private static final int HEIGHT = 10; 
	
	public MovingRect(MainGame game) {
		this.game= game;
	}

	public void moveRect() {
		if (px + dx > 0 && px + dx < game.getWidth()- WIDTH)
			px = px + dx;
	}

	public void paint(Graphics2D g) {
		g.fillRect(px, Y, WIDTH, HEIGHT);
	}

	public void keyReleased(KeyEvent e) {
		dx = 0;
	}

	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			dx = -game.speed;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			dx = game.speed;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(px, Y, WIDTH, HEIGHT);
	}
	
	public int getTopY() {
		return Y;
	}
}
