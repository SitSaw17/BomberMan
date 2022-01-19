package GameApp;

import GameFramework.Actor;

public class Fire extends Actor {
	
	private int duration = 5;

	/**
	 * constructor
	 * @param x: x position
	 * @param y: y position
	 */
	public Fire(int x, int y) {
		super(x, y);
		setImage("imgSrc/fire_1.png");
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * decrease duration
	 */
	public void decDuration()
	{
		duration--;
	}
	/**
	 * get duration
	 * @return duration
	 */
	public int getDuration()
	{
		return duration;
	}

}
