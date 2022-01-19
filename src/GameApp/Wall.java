package GameApp;

import GameFramework.Actor;

public class Wall extends Actor
{
	/**
	 * constructor
	 * @param x: x position
	 * @param y: y position
	 */
	public Wall(int x, int y) {
		super(x, y);
		setImage("imgSrc/wall.png");
		// TODO Auto-generated constructor stub
	}
}
