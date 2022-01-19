package GameApp;

import GameFramework.Actor;

public class Player extends Actor {
	static final int NEUTRAL = 0;
	static final int LEFT = 1;
	static final int RIGHT = 2;
	static final int UP = 3;
	static final int DOWN = 4;

	/**
	 * constructor
	 * @param x: x position
	 * @param y: y position
	 */
	public Player(int x, int y) {
		super(x, y);
		setImage("imgSrc/player1.png");
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * update players position
	 * @param dir: direction
	 */
	public void move(int dir) 
	{
		if(dir == LEFT)
		{
			x--;
		}
		if(dir == RIGHT)
		{
			x++;
		}
		if(dir == UP)
		{
			y--;
		}
		if(dir == DOWN)
		{
			y++;
		}
		
	}
	
	

}
