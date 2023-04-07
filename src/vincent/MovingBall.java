package vincent;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class MovingBall{
	int px = 0;
	int py = 0;
	int dx = 1;
	int dy = 1;
	private MainGame game;
	
	
    private int ballsize = 30; //Diameter
    
	public  MovingBall(MainGame game) {
		this.game= game;
	}

	void move() {
		
		boolean changeDirection = true;
		
		if (px + dx < 0)
			dx =game.speed;
		else if (px + dx > game.getWidth() - ballsize )
			dx = -game.speed;
		else if (py + dy < 0)
			dy = game.speed;
		else if (py + dy > game.getHeight() - ballsize )
			game.gameOver();
		else if(collision()) {
			
			dy= -game.speed;
			py = game.rect.getTopY() - ballsize;
			game.speed++;
			
	    }else {
			changeDirection = false;
		}
		
		if (changeDirection) {
			AudioSound.BALL.play();
		}
		
       px = px + dx;
		py = py + dy;
	}
	
	public int getBallSizer() {
		return ballsize;
	}

	public void paint(Graphics2D g) {
		g.fillOval(px, py, ballsize ,ballsize );
	}
	
	public Rectangle getBounds() {
		return new Rectangle(px, py, ballsize, ballsize);
	}
	
	public boolean collision() {
		return game.rect.getBounds().intersects(getBounds());
	}

}
