package testBall;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import vincent.MainGame;
import vincent.MovingBall;

public class MiniBallTest {
	
	MainGame game = new MainGame();
	MovingBall ball = new  MovingBall(game);

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBallsize() {
		int expect = 30;
		int real = ball.getBallSizer();
		assertEquals(expect, real);
		//fail("Not yet implemented");
	}

}
