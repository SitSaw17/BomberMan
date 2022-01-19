package GameApp;

import GameFramework.Actor;

public class Dynamite extends Actor{
	
	private int duration = 15;

	/**
	 * contructor
	 * @param x: x position
	 * @param y: y position
	 */
	public Dynamite(int x, int y) {
		super(x, y);
		setImage("imgSrc/dynamite.png");
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * decrease the duration
	 */
	public void decDuration()
	{
		duration--;
	}
	
	/**
	 * get the duration
	 * @return duration
	 */
	public int getDuration()
	{
		return duration;
	}
	
	/**
	 * set the duration
	 * @param d: int duration
	 * @precondition d >= 0
	 */
	public void setDuration(int d)
	{
		duration = d;
	}
	

}
