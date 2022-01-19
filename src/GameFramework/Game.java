package GameFramework;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Timer;

public abstract class Game{
	
	protected GameBoard board;
	protected GameObserver obs;
	
	/**
	 * Konstructor of game, which create a gameboard, the size is dependent on the parameter
	 * @param x: width of the gameBoard
	 * @param y: height of the gameBoard
	 */
	public Game(int x, int y)
	{
		board = new GameBoard(x,y);
	}
	
	/**
	 * assign the parameter to the variable obs
	 * @param o: an observer
	 */
	public void setObserver(GameObserver o) {
		obs = o;
	}
	
	/**
	 * start up a Timer that equals to 60 fps
	 * the timer calls on refresh()
	 */
	public void startGame() {
		Timer t = new Timer(150, new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				refresh();
				obs.updateSignal();
			}
		});
		t.start();
	}

	public abstract void left1Pressed();
	public abstract void right1Pressed();
	public abstract void up1Pressed();
	public abstract void down1Pressed();
	public abstract void action1Pressed();
	public abstract void mousePressed(int x, int y);
	public abstract void refresh();
	
	
	public abstract GameBoard getBoard();
	public abstract void left2Pressed();
	public abstract void right2Pressed();
	public abstract void up2Pressed();
	public abstract void down2Pressed();
	public abstract void action2Pressed();

}
